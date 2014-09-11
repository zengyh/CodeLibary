package utils.mail;

import java.util.Vector;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * 文件名称: EmailUtilsTest.java
 * 编写人: yh.zeng
 * 编写时间: 13-12-20
 * 文件描述: 收发邮件EMailUtils的测试类
 */
public class EmailUtilsTest  extends TestCase{
	
	@Test
	public void testIsCorrect() {
		EMailUtils.isCorrect("1181159889@qq.com", "密码");
	}

	@SuppressWarnings("serial")
	@Test
	public void testSendMail() {
		EMailUtils.sendMail("1181159889@qq.com",
				            "密码", 
				            new String[]{"miklejhon6688@163.com","2795927698@qq.com"},
				            "测试", "这只是个测试!!",
				            new Vector<String>(){
			                  {
			                	  add("D:/uploadDir/2014广州领豪本册新款产品报价单.ppt");
			                	  add("D:/uploadDir/mail.eml");
			                   }
			                }
	    );
	}

	@Test
	public void testFetchMail() {
		EMailUtils.fetchMail(EMailUtils.PROTOCAL.pop3,
				             "1181159889@qq.com", 
				             "密码");
/*		EMailUtils.fetchMail(EMailUtils.PROTOCAL.pop3,
	             "miklejhon6688@163.com", 
	             "密码");*/
	}

}
