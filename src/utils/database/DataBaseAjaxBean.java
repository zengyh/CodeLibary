package utils.database;

import utils.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

/**
 * 文件名称: DataBaseAjaxBean.java
 * 编写人: yh.zeng
 * 编写时间: 13-11-7
 * 文件描述: 数据库备份工具类 ，目前只支持Mysql
 */
public class DataBaseAjaxBean {

	private static final String ADDRESS = "127.0.0.1";        //服务器地址
	private static final String DATABASENAME = "sq_wleshop";  //数据库名称
	private static final String USERNAME = "root";            //用户名
	private static final String PASSWORD = "root";            // 密码
	private static final String FILEPATH = "E:\\数据库备份\\";   //数据库备份存放的路径
	private static String BIN_PATH = null;    // 数据库备份的EXE执行文件的存放路径
	
	private static final IDatabaseBackupService service = new DatabaseBackupServiceImpl();

	static {
		BIN_PATH = DataBaseAjaxBean.class.getClassLoader()
				.getResource("").getPath();
		BIN_PATH = BIN_PATH.substring(1, BIN_PATH.indexOf("classes"));
		BIN_PATH = BIN_PATH.replace("/", "\\") + "exe\\";
	}

	public DataBaseAjaxBean() {
		File dir = new File(FILEPATH);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		//拷贝项目中的数据库的EXE文件到数据库备份存放的目录下
		if(!new File(FILEPATH+"mysql.exe").exists()){
			copyFile(BIN_PATH+"mysql.exe",FILEPATH+"mysql.exe");
		}
		if(!new File(FILEPATH+"mysqldump.exe").exists()){
			copyFile(BIN_PATH+"mysqldump.exe",FILEPATH+"mysqldump.exe");
		}

	}
	
	/**
     * 将file1文件拷贝作为file2文件
     * @param file1
     * @param file2
     */
	private void copyFile(String file1, String file2) {
		// TODO Auto-generated method stub
		try {
			FileInputStream fis = new FileInputStream(URLDecoder.decode(file1,"UTF-8"));
			FileOutputStream fos = new FileOutputStream(file2);
			byte bytes[] = new byte[1024*3];
			while(fis.read(bytes) != -1){
				fos.write(bytes);
			}
			fos.flush();
			fos.close();
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	/**
	 * 备份数据库
	 * @return
	 */
	public boolean backupDataBase() {
		
		Date date = new Date();
		String time = StringUtils.getString( date, "yyyy-MM-dd hh:mm:ss" );
		String fileName = FILEPATH + DATABASENAME + "-" + date.getTime() + ".sql";
		
		String command = getBackupCommandExcludeTables(new String[]{"tab_database_backup"},fileName);
		
		Process process = executeCMDCommand(command);
		
		//保存备份的路径等信息到数据库
		boolean success = false;
		try {
			service.saveDatabaseBackup(fileName, time);
			success = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return (process != null && process.exitValue() == 0 && success) ? true : false;

	}

    /**
     * 获取排除某些表数据库备份导出命令
     * @param tableNames  要排除的表名
     * @param fileName  备份保存的文件名(含路径)
     * @return
     */
	private String getBackupCommandExcludeTables(String[] tableNames, String fileName) {
		// TODO Auto-generated method stub
		StringBuffer buf = new StringBuffer();

		if(tableNames != null){
			for(String tableName : tableNames){
				buf.append(" --ignore-table="+DATABASENAME+"."+tableName+" "); 
			}
		}
		
		String command = FILEPATH+"mysqldump -h " + ADDRESS + " -u" + USERNAME
		+ " -p" + PASSWORD + buf.toString() + " -R " + DATABASENAME + "> " + fileName;
		
		
		return command;
	}

	/**
	 * 执行CMD命令
	 * @param command  CMD命令
	 * @return
	 */
	private Process executeCMDCommand(String command) {
		// TODO Auto-generated method stub
		Process process = null;
		command = "cmd /c \""+ command +"\"";
		
		//执行备份数据库的CMD命令
		try {
			process = Runtime.getRuntime().exec(command);
			try {
				process.waitFor(); // 等待程序直到子程序结束
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return process;
	}


	/**
	 * 还原数据库
	 * @param fileName  数据库备份文件名(含路径)
	 * @return
	 */
	public boolean restoreDataBase(String fileName){
		
		String command = FILEPATH+"mysql -h "+ADDRESS+" -u"+USERNAME
		                 +" -p"+PASSWORD+"  "+DATABASENAME+"< "+fileName;

		Process process = executeCMDCommand(command);
		
		return (process != null && process.exitValue() == 0) ? true : false;
	}
	
	/**
	 * 删除数据库备份
	 * @param databaseBackups
	 * @return
	 */
	public boolean deleteDatabaseBackups(List<TabDatabaseBackup> databaseBackups){
		boolean success = false;
		
		try {
			service.deleteDatabaseBackups(databaseBackups);
			success = true;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return success;
	}
	
	public static void main(String[] args) {
		new DataBaseAjaxBean().backupDataBase();
		//new DataBaseAjaxBean().restoreDataBase(FILEPATH+"sq_wleshop-1383788762671.sql");
	}
}
