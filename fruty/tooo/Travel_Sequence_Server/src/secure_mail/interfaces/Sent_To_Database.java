package secure_mail.interfaces;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

public class Sent_To_Database
{

	public String from;
	public String subject;
	public String to;
	public String msg;
	private String password;
	

	public Sent_To_Database(HttpServletRequest req) 
	{
		from=req.getParameter("from");
		to=req.getParameter("to");
		subject=req.getParameter("subject");
		msg=req.getParameter("msg");
		password=req.getParameter("password");
		
		
		
	}

	

	public boolean sent(String id,String ip) 
	{
		
		 final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		 	     Properties props = System.getProperties();
		     props.setProperty("mail.smtp.host", "smtp.gmail.com");
		     props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		     props.setProperty("mail.smtp.socketFactory.fallback", "false");
		     props.setProperty("mail.smtp.port", "465");
		     props.setProperty("mail.smtp.socketFactory.port", "465");
		     props.put("mail.smtp.auth", "true");
		     props.put("mail.debug", "true");
		     props.put("mail.store.protocol", "pop3");
		     props.put("mail.transport.protocol", "smtp");
		     final String username = from;//
		     final String password = this.password;
		     try{
		     Session session = Session.getDefaultInstance(props, 
		                          new Authenticator(){
		                             protected PasswordAuthentication getPasswordAuthentication() {
		                                return new PasswordAuthentication(username, password);
		                             }});

		   // -- Create a new message --
		     Message msg = new MimeMessage(session);

		  // -- Set the FROM and TO fields --
		     msg.setFrom(new InternetAddress(from));
		     msg.setRecipients(Message.RecipientType.TO, 
		                      InternetAddress.parse(to,false));
		     msg.setSubject("Hello");
		     
		     msg.setText(" Click Below link for show full content:\n\t http://"+ip+":8080/Secure_mail/gmail_click?id="+id);
		     msg.setSentDate(new Date());
		     Transport.send(msg);
		     System.out.println("Message sent.");
		     return true;
		  }
		     catch (MessagingException e)
		     { System.out.println("Erreur d'envoi, cause: " + e);
		     return false;
		     }
		
		
	}

	
}
