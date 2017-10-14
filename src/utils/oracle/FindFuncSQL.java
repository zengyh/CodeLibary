package  utils.oracle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 文件名称: FindFuncSQL.java
 * 编写人: yh.zeng
 * 编写时间: 17-10-14 下午7:33
 * 文件描述: 抓取Oracle Package包中调用函数查询的Select SQL，将这些SQL输出到一个文件中
 *         例如：
 *         select * from test
 *           where name = v_name and test1_package.isHasPriv(v_name) = 'Y';
 */
public class FindFuncSQL
{

    public static void main(String args[]) throws IOException {
        System.out.println("程序执行中...");

        File resultFile = new File("D:\\sql\\result.txt");
        FileWriter writer = new FileWriter(resultFile, true);

        File dir = new File("D:\\sql");
        if(!dir.exists()){
        	dir.mkdirs();
        }
        File files[] = dir.listFiles(new FileFilter(){

			@Override
			public boolean accept(File file) {
				if(file.getName().endsWith(".sql") || file.getName().endsWith(".SQL")){
					return true;
				}else{
					return false;
				}
			}
        	
        });
        
        for(int i = 0; i < files.length; i++){
        	int selectIndex = -1;   //select index
        	int endIndex = -1;      //select SQL的; index
        	int commentIndex = -1;  //--行注释的index
        	int l_BlockCommentIndex = -1;  //  /* 开始的段注释的index
        	int r_BlockCommentIndex = -1;  //  */ 结束的段注释的index
        	boolean blockCommentClose = true;  //段注释是否关闭
        	StringBuffer sqlBuf = new StringBuffer();  //select SQL
        	StringBuffer blockSQLBuf = new StringBuffer();  //一段完整的SQL
        	boolean hashPackage = false;  //select SQL是否调用package包
        	String packageName = null;   //包名
        	String precedureName = null;  //存储过程名或方法名
        	
        	BufferedReader br = new BufferedReader(new FileReader(files[i]));
        	String line = null;
        	boolean isFirst = true;
        	boolean isFirstSelect = false;  //防止select语句第一行重复
        	while( (line = br.readLine()) != null ){
        		
        		//输出包名
        		if(isFirst){
        			int precedureIdx = line.toLowerCase().indexOf(" body ");
        			int asIdx = line.toLowerCase().lastIndexOf(" as");
        			if(asIdx == -1){
        				asIdx = line.toLowerCase().lastIndexOf(" is");
        			}
        			packageName = line.substring(precedureIdx + 6, asIdx).trim();
        			isFirst = false;
        		}
        		
        		//存储过程名或方法名
        		if(line.indexOf("PROCEDURE") > -1){
        			int precedureIdx = line.indexOf("PROCEDURE");
        			int kuohaoIdx = line.indexOf("(");
        			if(kuohaoIdx > -1){
        				precedureName = line.substring(precedureIdx + 9 , kuohaoIdx).trim();
        			}else{
        				precedureName = line;
        			}
        		}else if(line.indexOf("procedure") > -1){
        			int precedureIdx = line.indexOf("procedure");
        			int kuohaoIdx = line.indexOf("(");
        			if(kuohaoIdx > -1){
        				precedureName = line.substring(precedureIdx + 9 , kuohaoIdx).trim();
        			}else{
        				precedureName = line;
        			}
        		}else if(line.toLowerCase().indexOf("function ") > -1){
        			int funcIdx = line.toLowerCase().indexOf("function ");
        			int returnIdx = line.toLowerCase().indexOf(" return ");
        			if(returnIdx == -1){
        				returnIdx = line.indexOf("(");
        			}
        			if(returnIdx > -1){
        				precedureName = line.substring(funcIdx + 9 , returnIdx).trim();
        			}else{
        				precedureName = line;
        			}
        		}
        		
        		blockSQLBuf.append(line).append("\r\n");
        		
        		if(sqlBuf.length() == 0 && ( selectIndex = line.toLowerCase().indexOf("select") ) > -1){
        			sqlBuf.append(line).append("\r\n");
        			endIndex = line.indexOf(";", selectIndex);
        			commentIndex = line.indexOf("--");
        			l_BlockCommentIndex = line.indexOf("/*");
        			r_BlockCommentIndex = line.indexOf("*/");
        			if( ( commentIndex == -1 && l_BlockCommentIndex > -1 ) || ( commentIndex > -1 && l_BlockCommentIndex > -1 && l_BlockCommentIndex < commentIndex) ){
        				blockCommentClose = false;
        			}
        			if(endIndex > -1 && (blockCommentClose == true || (l_BlockCommentIndex > -1 && r_BlockCommentIndex > -1 && r_BlockCommentIndex < endIndex) )){ //段注释内的; 不能作为SQL的结束
        			    if(endIndex > - 1 && (commentIndex == -1 || commentIndex > endIndex) ){ // 行注释的; 不能作为SQL的结束
        			    	if(line.indexOf("_package.") > -1 || line.indexOf("_PACKAGE.") > -1){
        			    		writer.write(packageName + "." + precedureName + "()\r\n\r\n");
        			    		writer.write(blockSQLBuf.toString() + "\r\n\r\n\r\n\r\n");
        			    		writer.write("--------------------------------------------------------------\r\n");
        			    	}
        			    	sqlBuf.setLength(0);
        			    	selectIndex = -1;
        			    	endIndex = -1;
        			    	commentIndex = -1;
        			    	hashPackage = false;
        			    	l_BlockCommentIndex = -1;
        			    	r_BlockCommentIndex = -1;
        			    	blockCommentClose = true;
        			    }
        			}
        			
        			isFirstSelect = true;
        			
        		}
        		
        		if(sqlBuf.length() > 0){
        			if(!isFirstSelect){
        				sqlBuf.append("line").append(line).append("\r\n");
        			}
        			endIndex = line.indexOf(";", selectIndex);
        			commentIndex = line.indexOf("--");
        			l_BlockCommentIndex = line.indexOf("/*");
        			r_BlockCommentIndex = line.indexOf("*/");
        			if( ( commentIndex == -1 && l_BlockCommentIndex > -1 ) || ( commentIndex > -1 && l_BlockCommentIndex > -1 && l_BlockCommentIndex < commentIndex) ){
        				blockCommentClose = false;
        			}
        			if(line.indexOf("_package.") > -1 || line.indexOf("_PACKAGE.") > -1){
        				hashPackage = true;
        			}
        			if(endIndex > -1 && (blockCommentClose == true || (l_BlockCommentIndex > -1 && r_BlockCommentIndex > -1 && r_BlockCommentIndex < endIndex) )){ //段注释内的; 不能作为SQL的结束
        			    if(endIndex > - 1 && (commentIndex == -1 || commentIndex > endIndex) ){ // 行注释的; 不能作为SQL的结束
        			    	if(hashPackage){
        			    		writer.write(packageName + "." + precedureName + "()\r\n\r\n");
        			    		writer.write(blockSQLBuf.toString() + "\r\n\r\n\r\n\r\n");
        			    		writer.write("--------------------------------------------------------------\r\n");
        			    	}
        			    	sqlBuf.setLength(0);
        			    	selectIndex = -1;
        			    	endIndex = -1;
        			    	commentIndex = -1;
        			    	hashPackage = false;
        			    	l_BlockCommentIndex = -1;
        			    	r_BlockCommentIndex = -1;
        			    	blockCommentClose = true;
        			    }
        			}
        			
        		}
        		
        		isFirstSelect = false;
        		
        		if(precedureName != null && line.toLowerCase().indexOf("end ") > -1 && line.toLowerCase().indexOf(precedureName.toLowerCase()) > -1 && line.indexOf(";") > -1){
        			precedureName = null;
        		}
        		
    			endIndex = line.indexOf(";", selectIndex);
    			commentIndex = line.indexOf("--");
    			l_BlockCommentIndex = line.indexOf("/*");
    			r_BlockCommentIndex = line.indexOf("*/");
    			if( ( commentIndex == -1 && l_BlockCommentIndex > -1 ) || ( commentIndex > -1 && l_BlockCommentIndex > -1 && l_BlockCommentIndex < commentIndex) ){
    				blockCommentClose = false;
    			}
    			if(endIndex > -1 && (blockCommentClose == true || (l_BlockCommentIndex > -1 && r_BlockCommentIndex > -1 && r_BlockCommentIndex < endIndex) )){ //段注释内的; 不能作为SQL的结束
    			    if(endIndex > - 1 && (commentIndex == -1 || commentIndex > endIndex) ){ // 行注释的; 不能作为SQL的结束
    			    	blockSQLBuf.setLength(0);
    			    	l_BlockCommentIndex = -1;
    			    	r_BlockCommentIndex = -1;
    			    	blockCommentClose = true;
    			    }
    			}
    			
    			if(r_BlockCommentIndex > -1){
    		    	l_BlockCommentIndex = -1;
    		    	r_BlockCommentIndex = -1;
    		    	blockCommentClose = true;
    			}

        	}
        	
        	br.close();
        	
        }
        
        writer.flush();
        writer.close();
        
        System.out.println("程序执行完成！");


    }

}
