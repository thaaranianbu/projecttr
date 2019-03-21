package secure_mail.interfaces;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

public class Register_To_Database 
{

	public String name;
	public String mail;
	

  public Register_To_Database(HttpServletRequest req)
	{
		name=req.getParameter("name");
		mail=req.getParameter("mail");
	}

	
	
}
