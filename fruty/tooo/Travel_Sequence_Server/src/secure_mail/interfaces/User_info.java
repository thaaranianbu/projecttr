package secure_mail.interfaces;

public class User_info 
{
	String lid;
	String lpid;
	String heatr;
	String pressure;
	String sugar;
	String date;
	public static String name;
	public static String email;
	
	public String getLid() {
		return lid;
	}
	public void setLid(String lid) {
		this.lid = lid;
	}
	public String getLpid() {
		return lpid;
	}
	public void setLpid(String lpid) {
		this.lpid = lpid;
	}
	public String getHeatr() {
		return heatr;
	}
	public void setHeatr(String heatr) {
		this.heatr = heatr;
	}
	public String getPressure() {
		return pressure;
	}
	public void setPressure(String pressure) {
		this.pressure = pressure;
	}
	public String getSugar() {
		return sugar;
	}
	public void setSugar(String sugar) {
		this.sugar = sugar;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

}
