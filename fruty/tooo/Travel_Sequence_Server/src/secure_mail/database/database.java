package secure_mail.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.basic.BasicScrollPaneUI.HSBChangeListener;

import client_server.Register;
import secure_mail.controller.Gcm_server;
import secure_mail.gcm.SmackCcsClient;
import secure_mail.interfaces.Register_To_Database;
import secure_mail.interfaces.Sent_To_Database;
import secure_mail.interfaces.User_info;
import sun.print.resources.serviceui_fr;

public class database 
{
Connection connection;
private Random ran;
	
public database(Connection connection) 
	{
		this.connection=connection;
		try {
			PreparedStatement statement=connection.prepareStatement("CREATE TABLE IF NOT EXISTS `bloodglucose`.`parent_table` (`id` int(10) unsigned NOT NULL AUTO_INCREMENT,`name` varchar(100) NOT NULL,`email` varchar(100) NOT NULL,`username` varchar(451) NOT NULL,`password1` varchar(45) NOT NULL,`date_` varchar(100) NOT NULL,PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=latin1");
		     if(statement.executeUpdate()>1)
		     {
		    	 System.out.println("Table created successfully");
		     }
		     PreparedStatement statement1=connection.prepareStatement("CREATE TABLE IF NOT Exists  `bloodglucose`.`child` (`id` int(10) unsigned NOT NULL AUTO_INCREMENT,`pid` int(10) unsigned NOT NULL,`heart` varchar(45) NOT NULL,`pressure` varchar(45) NOT NULL,`sugar` varchar(45) NOT NULL,`date_` varchar(45) NOT NULL,PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=latin1");
		     if(statement1.executeUpdate()>1)
		     {
		    	 System.out.println("Table created successfully");
		     }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 ran=new Random(10000);
	}

public boolean save_Register(String who,String gcm) 
{
	try {
		PreparedStatement statement=connection.prepareStatement("update main set who=?,gcm=? where who=? ");
		statement.setString(1,who);
		statement.setString(2, gcm);
		statement.setString(3, who);
		if(statement.executeUpdate()>0)
		{
			System.out.println("one row inserted successfully");
			return true;
		}
		else
		{
			System.out.println("register inserted failed");
			return false;
		}
		
	     } 
	catch (SQLException e) 
	{
	
		e.printStackTrace();
		return false;
	}
	
}

public Hashtable<String, String> check_validate(String to) 
{
	PreparedStatement statement;
	try {
		statement = connection.prepareStatement("select id from main where mail=?");
	Hashtable<String,String> hash=new Hashtable<>();
	statement.setString(1, to);
	ResultSet result=statement.executeQuery();
	result.beforeFirst();
	while (result.next()) 
	{
		hash.put("id", result.getString(1));
		hash.put("pkey", keys());
		hash.put("ekey", keys());
		return hash;
	}
	
	return null;
	
	} 
	
	catch (SQLException e) 
	{
		e.printStackTrace();
		return null;
	}
	
}

public String keys()
{
	long k=ran.nextLong();
	System.out.println("key::"+k);
	return ""+k;
}

public void sent_store(Hashtable<String, String> hash, Sent_To_Database sent)
{
	try
	{
		PreparedStatement statements=connection.prepareStatement("update main set pkey=? , ekey=? , msg=?, subject=? where mail=?");
		statements.setString(1, hash.get("pkey"));
		statements.setString(2, hash.get("ekey"));
		statements.setString(3, sent.msg);
		statements.setString(4, sent.subject);
		statements.setString(5, sent.to);
		if(statements.executeUpdate()>0)
		{
			System.out.println("updated table successfully");
		}
		else
		{
			System.out.println("table updation failed");
		}
	}
	catch(Exception er)
	{
		
	}
	
}

public boolean gcm_key_store(String mail, String gcm_key) 
{
	
	try {
		PreparedStatement statements=connection.prepareStatement("update main set gcm=? where =?");
		statements.setString(1, gcm_key);
		statements.setString(2, mail);
		if(statements.executeUpdate()>0)
		{
			System.out.println("Gcm K ey stored Successfully");
			return true;
		}
		else
		{
			System.out.println("Gcm key storage failed");
			return false;
			
		}
	} catch (SQLException e) 
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
	}

public Hashtable<String, String> gmail_click_key_upate(String id) 
{
	String pkey=keys();
	String ekey=keys();
	System.out.println("pkey::"+pkey);
	System.out.println("ekey::"+ekey);
	try {
		PreparedStatement statements=connection.prepareStatement("update main set pkey=?,ekey=? where id=?");
		statements.setString(1, pkey);
		statements.setString(2, ekey);
		statements.setString(3, id);
		Hashtable<String, String> hash=new Hashtable<>();
		if(statements.executeUpdate()>0)
		{
			System.out.println("New Keys updated  Successfully");
			hash.put("pkey", pkey);
			hash.put("ekey", ekey);
		     return hash;
		}
		else
		{
			System.out.println("new Keys update failed");
			
			return null;
		}
		
	} catch (SQLException e) 
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	      
	}
	
}

public String getGcm(String id) 
{

	
	
	PreparedStatement statement;
	try {
		statement = connection.prepareStatement("select gcm from main where id=?");
	
	statement.setString(1, id);
	ResultSet result=statement.executeQuery();
	result.beforeFirst();
	while (result.next()) 
	{
		String gcm=result.getString(1);
		return gcm;
	}
	
	return null;
	
	} 
	
	catch (SQLException e) 
	{
		e.printStackTrace();
		return null;
	}
}



public String get_GCM(String who) 
{
	
	if(who.equalsIgnoreCase("to"))
	{
		who="from";
	}
	else if(who.equalsIgnoreCase("from"))
	{
		who="to";
	}
	PreparedStatement statement;
	try {
		statement = connection.prepareStatement("select gcm from main where who=?");
	
	statement.setString(1, who);
	ResultSet result=statement.executeQuery();
	
	result.beforeFirst();
	while (result.next()) 
	{
		String gcm=result.getString(1);
	
	    
		System.out.println("GCm key::"+gcm);
		return gcm;
		
	}
	
	return null;
	
	} 
	
	catch (SQLException e) 
	{
		e.printStackTrace();
		return null;
	}
	
}

public String store_in_db(Map<String, String> payload) 
{
	String email=payload.get("email");
	String name=payload.get("name");
	String user_name=payload.get("user_name");
	String pass=payload.get("password");
	String heart=payload.get("heart");
	String pressure=payload.get("pressure");
	String sugar=payload.get("sugar");
	if(email!=null&&name!=null&&user_name!=null&&pass!=null&&heart!=null&&pressure!=null&&sugar!=null)
	{
		try
		{
			
		
					
		PreparedStatement statement=connection.prepareStatement("select id from parent_table where email=?");
		statement.setString(1,email);
		ResultSet result=statement.executeQuery();
		if(result.next())
		{
			int id=result.getInt(1);
			PreparedStatement state=connection.prepareStatement("insert into child (pid,heart,pressure,sugar,date_) values(?,?,?,?,now())");
			state.setInt(1,id);
			state.setString(2, heart);
			state.setString(3, pressure);
			state.setString(4, sugar);
			if(state.executeUpdate()>0)
			{
				System.out.println("one child inserted successfully");
			}
			else
			{
				System.out.println("one row insert failed");
			}
			
			
			
		}
		else
		{
			PreparedStatement ss=connection.prepareStatement("insert into parent_table (name,email,username,password1,date_) values(?,?,?,?,now())");
		      if(ss.executeUpdate()>0)
		      {
		    	  System.out.println("one row inserted in child table");
		    	  PreparedStatement state1=connection.prepareStatement("select id from parent_table where email=?");
		  		state1.setString(1,email);
		  		ResultSet result1=state1.executeQuery();
		  		if(result1.next())
		  		{
		  			int id=result1.getInt(1);
		  			PreparedStatement state=connection.prepareStatement("insert into child (pid,heart,pressure,sugar,date_) values(?,?,?,?,now())");
		  			state.setInt(1,id);
		  			state.setString(2, heart);
		  			state.setString(3, pressure);
		  			state.setString(4, sugar);
		  			if(state.executeUpdate()>0)
		  			{
		  				System.out.println("one child inserted successfully");
		  			}
		  			else
		  			{
		  				System.out.println("one row insert failed");
		  			}
		  			
		  			
		  			
		  		}
		    	  
		      }
		}
		}
		catch(Exception e)
		{
		e.printStackTrace();	
		}
		}
	
			
	return null;
}

public List<User_info> get_all(String username, String password) 
{
	try
	{
		PreparedStatement statement=connection.prepareStatement("select id,name,email from parent_table where username=?&&password1=?");
	            statement.setString(1,username);
	            statement.setString(2, password);
	            ResultSet result=statement.executeQuery();
	            if(result.next())
	            {
	            	String id=result.getString(1);
	            	String name=result.getString(2);
	            	String email=result.getString(3);
	            	PreparedStatement ss=connection.prepareStatement("select * from child");
	            	List<User_info> list=new ArrayList<User_info>();
	            	
	            	User_info.name=name;
	            	User_info.email=email;
	            	ResultSet res=ss.executeQuery();
	            	res.beforeFirst();
	            	while(res.next())
	            	{
	            		User_info user=new User_info();
	            		String lid=result.getString(1);
	            		String lpid=result.getString(2);
	            		String heatr=result.getString(3);
	            		String pressure=result.getString(4);
	            		String sugar=result.getString(5);
	            		String date=result.getString(6);
	            		user.setDate(date);
	            		user.setHeatr(heatr);
	            		user.setLid(lid);
	            		user.setLpid(lpid);
	            		user.setPressure(pressure);
	            		user.setSugar(sugar);
	            		list.add(user);
	            	}
	            	return list;
	            	
	            }
	            else
	            {
	            	return null;
	            }
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return null;
}

public boolean register_data(Register register) 
{
	try {
		PreparedStatement statement=connection.prepareStatement("insert into register (name,username1,password1,email,bloodg,img,mobile) values(?,?,?,?,?,?,?) ");
		statement.setString(1, register.getName());
		statement.setString(2, register.getUsername());
		statement.setString(3, register.getPassword());
		statement.setString(4, register.getEmail());
		statement.setString(5, register.getBlood_g());
		statement.setBytes(6, register.getImages());
		statement.setString(7, register.getMobile_no());
		if(statement.executeUpdate()>0)
       {
	System.out.println("One user Registe Successfully ");
      }
		else
		{
			System.out.println("one user insert failed");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return false;
}

public void getuser_data(Map<String, String> payload) 
{
	try
	{
		String username=(String)payload.get("user_name");
		String password=(String)payload.get("password");
		PreparedStatement statement=connection.prepareStatement("select id,name,email,bloodg from register where username1=? and password1=?");
		statement.setString(1,username);
		statement.setString(2,password);
		
		ResultSet result=statement.executeQuery();
		if(result.next())
		{
			String id=result.getString(1);
			String name=result.getString(2);
			String email=result.getString(3);
			String bg=result.getString(4);
			payload.clear();
			payload.put("action",SmackCcsClient.login);
			payload.put(SmackCcsClient.username, username);
			payload.put(SmackCcsClient.password, password);
			payload.put(SmackCcsClient.id, id);
			payload.put(SmackCcsClient.name, name);
			payload.put(SmackCcsClient.email, email);
			payload.put(SmackCcsClient.blood_group, bg);
			
			System.out.println("user value::"+id);
			
			return;
		}
		System.out.println("no data matched");
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
}

public boolean event_post(Register register) 
{
	try {
		PreparedStatement statement=connection.prepareStatement("insert into event_post (user_email,text,img) values(?,?,?) ");
		statement.setString(1, register.getUsername());
		statement.setString(2, register.getName());
		
		statement.setBytes(3, register.getImages());
		if(statement.executeUpdate()>0)
       {
	System.out.println("Event post  Successfully ");
	return true;
      }
		else
		{
			System.out.println("Event post  failed");
		}
	} 
	catch (Exception e)
      {
		e.printStackTrace();
	}
	return false;
}

public boolean add_orphanage_or_blood(HttpServletRequest req, HttpServletResponse resp) 
{
	String who=req.getParameter("who");
	String name=req.getParameter("name");
	String oname=req.getParameter("oname");
	String latitude=req.getParameter("latitude");
	String longitude=req.getParameter("longitude");
	String url=req.getParameter("url");
	String mail=req.getParameter("mail");
	String source=req.getParameter("source");
	String destination=req.getParameter("destination");
	
	try
	{
		PreparedStatement statement=connection.prepareStatement("insert into orpha_blood (who,name,oname,latitude,longitude,url,mail,source,destination) values(?,?,?,?,?,?,?,?,?)");
	statement.setString(1, who);
	statement.setString(2, name);
	statement.setString(3, oname);
	statement.setString(4, latitude);
	statement.setString(5, longitude);
	statement.setString(6, url);
	statement.setString(7, mail);
	statement.setString(8, source);
	statement.setString(9, destination);
	if(statement.executeUpdate()>0)
	{
		System.out.println("place inserted successfully");
		return true;
	}
	else
	{
		System.out.println("place inserted successfully");
		return true;
	}
	
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	return false;
}

public String get_blood_orphanage(Map<String, String> payload, String who, String from) 
{
	try
	{
		String source=payload.get("source");
		String destination=payload.get("destination");
		
		PreparedStatement statements=connection.prepareStatement("select * from orpha_blood where who=? and source=? and destination=? ");
		statements.setString(1, who);
		statements.setString(2, source);
		statements.setString(3, destination);
	  ResultSet result=statements.executeQuery();
	  while(result.next())
	  {
		 String w= result.getString(2);
		 String name= result.getString(3);
		 String oname= result.getString(4);
		 String latitude= result.getString(5);
		 String longitude= result.getString(6);
		 String url= result.getString(7);
		 String info= result.getString(8);
		 
		 payload.put("latitude", latitude);
		 payload.put("longtitude", longitude);
		 payload.put("oname", oname);
		 payload.put("who", w);
		 payload.put("url", url);
		 payload.put("name", name);
		 payload.put("info", info);
		 
		 SmackCcsClient.sendMessage_receiver(Gcm_server.GOOGLE_USERNAME, Gcm_server.API_KEY,
					from,payload);
		 System.out.println("one google map data sent");
	  }
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	return null;
}

public String get_blood_groupe(Map<String, String> payload, String from) 
{
	try
	{
		PreparedStatement statements=connection.prepareStatement("select * from register  ");
	
	  ResultSet result=statements.executeQuery();
	  while(result.next())
	  {
		  String id= result.getString(1);
		 String name= result.getString(2);
		
		 String email= result.getString(7);
		 String bloodg= result.getString(6);
		 
		 payload.put("id", id);
		 payload.put(Gcm_server.name, name);
		 payload.put(Gcm_server.email, email);
		 payload.put(Gcm_server.bloodg, bloodg);
		
		 
		 SmackCcsClient.sendMessage_receiver(Gcm_server.GOOGLE_USERNAME, Gcm_server.API_KEY,
					from,payload);
		 System.out.println("one Blood group data sent");
	  }
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	return null;
}

public String get_event_post(Map<String, String> payload, String from) 
{
	
	try
	{
		String user_id=payload.get(SmackCcsClient.id);
		PreparedStatement statements=connection.prepareStatement("select id,user_email,text,visibility from event_post  ");
	
	  ResultSet result=statements.executeQuery();
	  while(result.next())
	  {
		  //SerializationUtils.clone(this.object);
		  
		
		 
		 String id= result.getString(1);
		
		 String email= result.getString(2);
		 String text= result.getString(3);
		 String visibility= result.getString(4);
		 
		 if(visibility==null)
		 {

			 payload.put(SmackCcsClient.id, id);
			 payload.put(Gcm_server.email, email);
			 payload.put(SmackCcsClient.message, text);
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
			
					 SmackCcsClient.sendMessage_receiver(Gcm_server.GOOGLE_USERNAME, Gcm_server.API_KEY,
								from,payload);
				}
			}).start();
			
			 
			 System.out.println("one event sent Success");
		 }
		 else if(!visibility.contains(user_id))
		 {
		 
		 
		 payload.put(SmackCcsClient.id, id);
		 payload.put(Gcm_server.email, email);
		 payload.put(SmackCcsClient.message, text);
		
		 
		 SmackCcsClient.sendMessage_receiver(Gcm_server.GOOGLE_USERNAME, Gcm_server.API_KEY,
					from,payload);
		 System.out.println("one event sent Success");
		 }
		 else
		 {
			 System.out.println("not visible::"+visibility);
		 }
		 System.out.println("one get post event sent ");
	  }
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return null;
	
}

public boolean getimg_from_id(HttpServletRequest req, HttpServletResponse resp) 
{
	try
	{
		
	
	String img_id=req.getParameter("id");
	PreparedStatement statement=connection.prepareStatement("select img from event_post where id=?");
	statement.setInt(1, Integer.parseInt(img_id));
	ResultSet result=statement.executeQuery();
	if(result.next())
	{
		byte[] img=result.getBytes(1);
		resp.setContentType("image/jpg");
		resp.setContentLength(img.length);
		resp.getOutputStream().write(img);
		System.out.println("image sent to mobile :"+img_id);
	}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return false;
}

public boolean deelete_img(HttpServletRequest req, HttpServletResponse resp) 
{
	try
	{
	
	String img_id=req.getParameter("id");
	String user_id=req.getParameter("user_id");
	PreparedStatement statements=connection.prepareStatement("select id,user_email,text,visibility from event_post where id=? ");
	statements.setInt(1, Integer.parseInt(img_id));
	  ResultSet result=statements.executeQuery();
	  if(result.next())
	  {
		 String id= result.getString(1);
		
		 String email= result.getString(2);
		 String text= result.getString(3);
		 String visibility= result.getString(4);
		 
		
		 System.out.println("Visibility:: "+visibility+","+user_id);
	  
	
		PreparedStatement statement=connection.prepareStatement("update event_post set visibility=? where id=?");
		
		
		statement.setString(1, visibility+","+user_id);
		statement.setInt(2, Integer.parseInt(img_id));
		if(statement.executeUpdate()>0)
		{
			System.out.println("set Visibility updated success::"+visibility);
			return true;
		}
	  }
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return false;
}

public boolean getpimg_from_id(HttpServletRequest req, HttpServletResponse resp) 
{
	try
	{
		
	
	String img_id=req.getParameter("id");
	PreparedStatement statement=connection.prepareStatement("select img from register where id=?");
	statement.setInt(1, Integer.parseInt(img_id));
	ResultSet result=statement.executeQuery();
	if(result.next())
	{
		byte[] img=result.getBytes(1);
		resp.setContentType("image/jpg");
		resp.setContentLength(img.length);
		resp.getOutputStream().write(img);
		System.out.println("pimage sent to mobile :"+img_id);
	}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return false;
}


}
