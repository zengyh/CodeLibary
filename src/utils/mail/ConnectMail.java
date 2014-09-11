package utils.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;

/**
 * 文件名称: ConnectMail.java
 * 编写人: yh.zeng
 * 编写时间: 13-12-20
 * 文件描述: 验证邮箱登陆
 */
public class ConnectMail {
	
    private Session session;

    private Transport transport;

    private Store store;

    private Properties properties;

    /** 返回过的实例在验证通过后使用 */

    public Session getSession() {
        return session;
    }

    public Store getStore() {
        return store;
    }

    public Transport getTransport() {
        return transport;
    }

    /**
     * 测试支持SMTP协议的邮箱连接
     * @param uid  邮箱账号，如：23@163.com
     * @param pwd  邮箱密码
     * @return
     */
    public boolean connectSmtp(String uid, String pwd) {
    	
    	boolean success = false;
        try {
        	
        	String smtpHost = "smtp."+uid.substring(uid.indexOf("@")+1,uid.length());
        	
            properties = new Properties();
            properties.put("mail.smtp.host", smtpHost);

            properties.put("mail.smtp.auth", "true");
            Authenticator auth = new Email_Autherticator(uid, pwd);
            session = Session.getInstance(properties, auth);

            transport = session.getTransport("smtp");
            transport.connect();
            
            success = true;
            
        } catch (Exception e) {
            //e.printStackTrace();
        	success = false;
        	
        }finally{
        	try {
				transport.close();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
        }
        return success;
    }
    
    /**
     * 测试支持POP3协议的邮箱连接
     * @param uid  邮箱账号：23@163.com
     * @param pwd  邮箱密码
     * @return
     */
    public boolean connectPop3(String uid, String pwd) {

    	boolean success = false;
        try {
        	
        	String popHost = "pop."+uid.substring(uid.indexOf("@")+1,uid.length());
        	
            properties = new Properties();

            session = Session.getDefaultInstance(properties, null);
            store = session.getStore("pop3");
            store.connect(popHost,uid, pwd);

            success =  true;

        } catch (Exception e) {
           /// e.printStackTrace();
        	success =  false;
        	
        }finally{
        	try {
				store.close();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
        }
        
        return success;
    } 
    
    /**
     * 测试支持IMAP协议的邮箱连接
     * @param uid  邮箱账号：23@163.com
     * @param pwd  邮箱密码
     * @return
     */
    public boolean connectImap(String uid, String pwd) {

    	boolean success = false;
        try {
        	
        	String imapHost = "imap."+uid.substring(uid.indexOf("@")+1,uid.length());
        	
            properties = new Properties();

            session = Session.getDefaultInstance(properties, null);
            store = session.getStore("imap");
            store.connect(imapHost,uid, pwd);

            success =  true;

        } catch (Exception e) {
           /// e.printStackTrace();
        	success =  false;
        	
        }finally{
        	try {
				store.close();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        return success;
    } 
    
    /** 
     * 用来进行服务器对用户的认证
     */
    public class Email_Autherticator extends Authenticator
    {
        private String username;
		private String password;

		public Email_Autherticator()
        {
            super();
        }

        public Email_Autherticator(String user, String pwd)
        {
            super();
            username = user;
            password = pwd;
        }

        public PasswordAuthentication getPasswordAuthentication()
        {
            return new PasswordAuthentication(username, password);
        }
    }
}
