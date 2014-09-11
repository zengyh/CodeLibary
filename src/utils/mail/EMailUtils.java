package utils.mail;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.FetchProfile;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;


/**
 * 文件名称: EMailUtils.java
 * 编写人: yh.zeng
 * 编写时间: 13-12-20
 * 文件描述: 发送接收包含附件的邮件
 */
public class EMailUtils {
	
	enum PROTOCAL{
		pop3,    //POP3协议
		imap     //IMAP协议
	}

	private static String messageContentMimeType = "text/html; charset=gb2312";
	
	private static final String DIR = "D:/uploadDir";  //附件下载存放的路径
	
	public static Properties getProperties(String uid) {
		
		String smtpHost = "smtp."+uid.substring(uid.indexOf("@")+1,uid.length());
		
	    Properties props = new Properties();
		props.put("mail.smtp.host", smtpHost);
		props.put("mail.smtp.auth", "true");
		
		return props;
	}
	
	/**
	 * 验证邮箱登陆
	 * @param uid        邮箱账号，如324@163.com
	 * @param password   邮箱密码
	 * @return
	 */
	public static boolean isCorrect(String uid, String password){
		boolean success = false;
		
		ConnectMail connectMail = new ConnectMail();
		
		if(connectMail.connectSmtp(uid, password)){
			success = true;
			
		}else if(connectMail.connectPop3(uid, password)){
			success = true;
			
		}else if(connectMail.connectImap(uid, password)){
			success = true;
			
		}
		
		return success;
	}



	/**
	 * 构建邮件
	 * @param session
	 * @param uid        邮箱账号，如324@163.com
	 * @param receivers  收件人地址，如new String[]{223@163.com}
	 * @param subject    主题
	 * @param content    邮件文本内容
	 * @param vector     附件,如  new Vector(){ add("D:/uploadDir/test.txt");  }
	 * @return
	 * @throws javax.mail.internet.AddressException
	 * @throws javax.mail.MessagingException
	 * @throws java.io.UnsupportedEncodingException
	 */
	public static Message buildMimeMessage(Session session, String uid, String[] receivers, 
			String subject, String content, Vector<String> vector) 
	     throws AddressException,MessagingException, UnsupportedEncodingException {
		
		Message msg = new MimeMessage(session);

		msg.addFrom(InternetAddress.parse(uid));   // 发件人地址
		msg.setReplyTo(InternetAddress.parse(uid));// 回复时用的地址
		// 消息接收者
		Address address[] = new Address[receivers.length];
		for(int i = 0; i < receivers.length; i++){
			address[i] = new InternetAddress(receivers[i]);
		}
		msg.addRecipients(Message.RecipientType.TO, address);
		msg.setSubject(subject);
		msg.setSentDate(new Date());

		// 邮件内容数据（Content）
		msg.setContent(buildMimeMultipart(content, vector));

		return msg;
	}

	/**
	 * 构建邮件的正文和附件
	 * 
	 * @param msgContent
	 * @param attachedFileList
	 * @return
	 * @throws javax.mail.MessagingException
	 * @throws java.io.UnsupportedEncodingException
	 */
	public static Multipart buildMimeMultipart(String msgContent,
			Vector<String> attachedFileList) throws MessagingException, UnsupportedEncodingException {
		Multipart mPart = new MimeMultipart();// 多部分实现

		// 邮件正文
		MimeBodyPart mBodyContent = new MimeBodyPart();// MIME邮件段体
		if (msgContent != null) {
			mBodyContent.setContent(msgContent, messageContentMimeType);
		} else {
			mBodyContent.setContent("", messageContentMimeType);
		}
		mPart.addBodyPart(mBodyContent);

		// 附件
		String file;
		String fileName;
		if (attachedFileList != null) {
			for (Enumeration<String> fileList = attachedFileList.elements(); fileList
					.hasMoreElements();) {
				file = fileList.nextElement();
				fileName = file.substring(file.lastIndexOf("/") + 1);
				MimeBodyPart mBodyPart = new MimeBodyPart();
				// 远程资源
				// URLDataSource uds=new
				// URLDataSource(http://www.iteye.com/logo.gif);
				FileDataSource fds = new FileDataSource(file);
				mBodyPart.setDataHandler(new DataHandler(fds));
				// mBodyPart.setFileName(fileName);
				mBodyPart.setFileName( MimeUtility
						.encodeWord(fileName));// 解决中文附件名问题
				mPart.addBodyPart(mBodyPart);
			}
		}

		return mPart;
	}

	/**
	 * 字串解码
	 * 
	 * @param text
	 * @return
	 * @throws java.io.UnsupportedEncodingException
	 */
	protected static String decodeText(String text)
			throws UnsupportedEncodingException {
		if (text == null)
			return null;
		if (text.startsWith("=?GB") || text.startsWith("=?gb")
				|| text.startsWith("=?UTF"))  {
			text = MimeUtility.decodeText(text);
		} else {
			text = new String(text.getBytes("ISO8859_1"),"GBK");
		}
		return text;
	}

	/**
	 * 分析邮件
	 * 
	 * @param content
	 */
	public static void parseMailContent(Object content) {
		try {
			if (content instanceof Multipart) {
				Multipart mPart = (MimeMultipart) content;
				for (int i = 0; i < mPart.getCount(); i++) {
					extractPart((MimeBodyPart) mPart.getBodyPart(i));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 抽取内容
	 * 
	 * @param part
	 */
	public static void extractPart(MimeBodyPart part) {
		try {
			String disposition = part.getDisposition();

			if (disposition != null
					&& (disposition.equalsIgnoreCase(Part.ATTACHMENT) || disposition
							.equalsIgnoreCase(Part.INLINE))) {// 附件
				String fileName = decodeText(part.getFileName());
				System.out.println(fileName);
				saveAttachFile(part);// 保存附件
			} else {// 正文
				if (part.getContent() instanceof String) {// 接收到的纯文本
					System.out.println(part.getContent());
				}
				if (part.getContent() instanceof MimeMultipart) {// 接收的邮件有附件时
					BodyPart bodyPart = ((MimeMultipart) part.getContent())
							.getBodyPart(0);
					System.out.println(bodyPart.getContent());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存附件
	 * 
	 * @param part
	 */
	public static void saveAttachFile(Part part) {
		try {
			if (part.getDisposition() == null)
				return;

			String dir =  DIR;
			String filename = decodeText(part.getFileName());
			
			File dirRoot = new File(dir);
			if(!dirRoot.exists()){
				dirRoot.mkdirs();
			}

			InputStream in = part.getInputStream();
			OutputStream out = new FileOutputStream(new File(dir + File.separator + filename));

			byte[] buffer = new byte[8192];
			while (in.read(buffer) != -1) {
				out.write(buffer);
			}

			in.close();
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	/**
	 * 发送邮件
	 * @param uid        邮箱账号，如324@163.com
	 * @param password   邮箱密码
	 * @param receivers  收件人地址，如new String[]{223@163.com}
	 * @param subject    主题
	 * @param content    邮件文本内容
	 * @param vector     附件,如  new Vector(){ {add("D:/uploadDir/test.txt");}  }
	 * @return
	 */
	public static boolean sendMail(String uid, String password, String[] receivers, 
			String subject, String content, Vector<String> vector){
		return _sendMail(uid, password, receivers, subject, content, vector, 3); 
	}
	
	/**
	 * 发送邮件
	 * @param uid        邮箱账号，如324@163.com
	 * @param password   邮箱密码
	 * @param receivers  收件人地址，如new String[]{223@163.com}
	 * @param subject    主题
	 * @param content    邮件文本内容
	 * @param vector     附件,如  new Vector(){ {add("D:/uploadDir/test.txt");}  }
	 * @param count      若发送失败，重试发送的次数 
	 * @return
	 */
	public static boolean _sendMail(String uid, String password, String[] receivers, 
			String subject, String content, Vector<String> vector, int count){
		
		boolean success = false;
		
		if(count != 0){
			Transport transport = null;
			try {
				Session session = Session.getDefaultInstance(getProperties(uid));  
				session.setDebug(true);  // 有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使用（你可以在控制台（console)上看到发送邮件的过程）  
				
				String smtpHost = "smtp."+uid.substring(uid.indexOf("@")+1,uid.length());		
				transport = session.getTransport("smtp");   // 连接服务器的邮箱  
				transport.connect(smtpHost, uid, password);    // 把邮件发送出去  
				Message message = buildMimeMessage(session, uid, receivers, subject, content, vector);
				transport.sendMessage(message, message.getAllRecipients());   
				transport.close(); 

				success = true;
				System.out.println("Message send...");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				count --;
				_sendMail(uid, password, receivers, 
						subject, content, vector, count);
			}finally{
				if(transport != null){
					try {
						transport.close();
					} catch (MessagingException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} 
				}
			}
		}
		
		return success;
	}


	/**
	 * 读取邮件
	 * @param em_protocal  协议类型，如 PROTOCAL.imap
	 * @param uid          邮箱账号，如324@163.com
	 * @param password     邮箱密码
	 * @return
	 */
	public static boolean fetchMail(PROTOCAL em_protocal, String uid,
			 String password) {
	
		boolean success = false;
		
		String host = null;
		String protocal = null;
		
		if(em_protocal.equals(PROTOCAL.imap)){
			host = "imap."+uid.substring(uid.indexOf("@")+1,uid.length());
			protocal = "imap";
		}else if(em_protocal.equals(PROTOCAL.pop3)){
			host = "pop."+uid.substring(uid.indexOf("@")+1,uid.length());
			protocal = "pop3";
		}
		
		Store store = null;
		Folder inbox = null;
		try {
			Properties props = new Properties();
			Session session = Session.getDefaultInstance(props, null);
			session.setDebug(true);  // 有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使用（你可以在控制台（console)上看到发送邮件的过程）  
			store = session.getStore(protocal);
			store.connect(host,uid, password);
			
			inbox = store.getFolder("INBOX");// 收件箱
			inbox.open(Folder.READ_ONLY);
			//FetchProfile profile = new FetchProfile();
			//profile.add(FetchProfile.Item.ENVELOPE);
			Message[] messages = inbox.getMessages();
			//inbox.fetch(messages, profile);
			System.out.println("收件箱的邮件数：" + messages.length);
			System.out.println("未读邮件数：" + inbox.getUnreadMessageCount());
			System.out.println("新邮件数：" + inbox.getNewMessageCount());

			for (int i = 0; i < messages.length; i++) {
				// 邮件发送者
				String from = decodeText(messages[i].getFrom()[0].toString());
				InternetAddress ia = new InternetAddress(from);
				System.out.println("FROM:" + ia.getPersonal() + '('
						+ ia.getAddress() + ')');
				// 邮件标题
				System.out.println("TITLE:" + messages[i].getSubject());
				// 邮件内容
				parseMailContent(messages[i].getContent());

				// 邮件大小
				System.out.println("SIZE:" + messages[i].getSize());
				// 邮件发送时间
				System.out.println("DATE:" + messages[i].getSentDate());
			}
			
			success = true;
		}catch(Exception e){
			e.printStackTrace();
		}finally {
		
			try {
				inbox.close(false);
			} catch (Exception e) {
			}
			try {
				store.close();
			} catch (Exception e) {
			}
		}
		
		return success;
	}

}
