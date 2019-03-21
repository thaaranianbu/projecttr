package secure_mail.controller;

import secure_mail.gcm.SmackCcsClient;



public class Gcm_server 
{
	
	public static String action_blood_orphanage="blood_orphanage";
	public static String who_blood="bloodbank";
	public static String who_orphanage="orphanage";
	public static final String action_blood_group="blood_group";
	
	public static String email="email";
	public static String name="name";
	public static String mobile_no="mobile_no";
	public static String action_register="register";
	public static String action="action";
	public static String img_uri="img_uri";
	public static String bloodg="bloodg";
	
	public static final String API_KEY="AIzaSyDYDwdzhlTowoRU2YnvbdR76hZ_06bBqLc";

	// Put your Google Project number here
public static final String GOOGLE_USERNAME = "342875589059" + "@gcm.googleapis.com";
	
	//private static final String GOOGLE_SERVER_KEY = "AIzaSyCdAx2YAfyOf4nWkXC7Tg3QAfjpcNePMO4";
	// Put your Google Project number here
	//final String GOOGLE_USERNAME = "815811126185" + "@gcm.googleapis.com";
	public boolean start()
	{
		try
		{
		String toDeviceRegId ="APA91bHPy6hqKjaxaKcTtp0nq1H1ZLCwRzO5zk4AdBOpG5Yb22IQq8SDepTxTAVj5kOP_QMvqRzMR-zHtWK2hjXsqhfVuSx_DyJrLZiPb71X4AQaRwHwSF5DYSlr0QBi_KKBotf7pU4XCr4gGHGrUk57yjQuzBQ44A";
		SmackCcsClient.sendMessage(GOOGLE_USERNAME, API_KEY,
				toDeviceRegId,"Server started");
		return true;
		}
		catch(Exception e)
		{
			return false;
		}
		
	}
	
	public boolean send(String toDeviceRegId,String msg)
	{
		
		SmackCcsClient.sendMessage(GOOGLE_USERNAME, API_KEY,
				toDeviceRegId,msg);
		
		
		return true;
	}

}
