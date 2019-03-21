package secure_mail.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import client_server.Register;
import secure_mail.database.Jdbc_obj;
import secure_mail.database.database;
import secure_mail.gcm.SmackCcsClient;
import secure_mail.interfaces.Register_To_Database;
import secure_mail.interfaces.Sent_To_Database;
import secure_mail.interfaces.User_info;


public class controller extends HttpServlet
{
	
	
	database data;
	
	private String ip;
	@Override
	public void init(ServletConfig config) throws ServletException
	{
       try {
		Class.forName("com.mysql.jdbc.Driver");
		 Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/travel_sequence", "root", "root");
		 data=new database(connection);
		 Jdbc_obj.datas=data;
		 
		 try {
				InetAddress address = InetAddress.getLocalHost();
				ip=address.getHostAddress();
				System.out.println("ip address:"+ip);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 System.out.println("database connection success");
	} catch (ClassNotFoundException | SQLException e) {
		
		e.printStackTrace();
	}
       
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
			{
		String uri=req.getRequestURI();
		System.out.println("uri::"+uri);
		if(uri.equals("/Bloodbank_Orphanage_Server/register"))
		{
			System.out.println("local ip::"+req.getLocalAddr());
			
			
			
		}
		
		else if(uri.equals("/Bloodbank_Orphanage_Server/gcm_server"))
		{
			System.out.println("gcm server called");
			Gcm_server server=new Gcm_server();
			if(server.start())
			{
				req.setAttribute("success","Server Started Successfully");
				req.getRequestDispatcher("success.jsp").forward(req, resp);
			}
			else
			{
				req.setAttribute("error","Error During Server start");
				req.getRequestDispatcher("error.jsp").forward(req, resp);
				
			}
		}
		
		if(uri.equals("/Bloodbank_Orphanage_Server/add_bloodbank_orphanage"))
		{
			System.out.println("local ip::"+req.getLocalAddr());
			try
			{
			if(data.add_orphanage_or_blood(req,resp))
			{
				req.setAttribute("success","One plance Inserted Successfully");
				req.getRequestDispatcher("success.jsp").forward(req, resp);
			}
			else
			{
				req.setAttribute("success","One plance Insert Failed");
				req.getRequestDispatcher("success.jsp").forward(req, resp);
			}
			}
			catch(Exception c)
			{
				c.printStackTrace();
			}
			
			
			
		}
		
		if(uri.equals("/Bloodbank_Orphanage_Server/img"))
		{
			System.out.println("img called::"+req.getLocalAddr());
			try
			{
			if(data.getimg_from_id(req,resp))
			{
				
			}
			else
			{
				System.out.println("controller img if false");
			}
			}
			catch(Exception c)
			{
				c.printStackTrace();
			}
			
			
			
		}
		
		if(uri.equals("/Bloodbank_Orphanage_Server/pimg"))
		{
			System.out.println("pimg called::"+req.getLocalAddr());
			try
			{
			if(data.getpimg_from_id(req,resp))
			{
				
			}
			else
			{
				System.out.println("controller img if false");
			}
			}
			catch(Exception c)
			{
				c.printStackTrace();
			}
			
			
			
		}
		
		if(uri.equals("/Bloodbank_Orphanage_Server/delete_img"))
		{
			System.out.println("local ip::"+req.getLocalAddr());
			try
			{
			if(data.deelete_img(req,resp))
			{
				resp.getWriter().write("true");
				System.out.println("delete sent true");
			}
			else
			{
				 resp.getWriter().write("false");
				System.out.println("delete sent false");

			}
			}
			catch(Exception c)
			{
				c.printStackTrace();
			}
			
			
			
		}
		
		
		
			

	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
			{
		String uri=req.getRequestURI();
		System.out.println("uri::"+uri);
		
		if(uri.equals("/Bloodbank_Orphanage_Server/register"))
		{
			try
			{
			System.out.println("Register called");
			InputStream in=req.getInputStream();
			ObjectInputStream obj_in=new ObjectInputStream(in);
			Register register=(Register)obj_in.readObject();
			
			if(data.register_data(register))
			{
				System.out.println("Register insert Failed");
			}
			
			
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else if(uri.equals("/Bloodbank_Orphanage_Server/event_post"))
		{
			try
			{
			System.out.println("Even post called");
			InputStream in=req.getInputStream();
			ObjectInputStream obj_in=new ObjectInputStream(in);
			Register register=(Register)obj_in.readObject();
			
			if(data.event_post(register))
			{
				System.out.println("Event post insert Failed");
			}
			
			
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
		      System.out.println("post method called");
		      String username=req.getParameter("username");
		      String password=req.getParameter("password");
		      if(username!=null&& password!=null)
		      {
		    	  List<User_info> list=data.get_all(username,password);
		    	  if(list!=null)
		    	  {
		    		  req.setAttribute("data", list);
		    		  req.getRequestDispatcher("display.jsp").forward(req, resp);
		    	  }
		    	  else
		    	  {
		    		  req.setAttribute("error","Invalid Username and password");
		    		  req.getRequestDispatcher("error.jsp").forward(req, resp);
		    		  return;
		    	  }
		      }
		      
		}
		
	         }
		

}
