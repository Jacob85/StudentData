package il.ac.shenkar.studentdata;

/**
 * The Trend class is a class that represent Trends
 * According to this class the Hibernate knows to which table to write or read the objects from 
 * 
 * @author Jacob, Cadan & Shimon
 *
 */
public class Trend
{
	private int id;
	private String trendName;
	
	
	
	public Trend() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTrendName() {
		return trendName;
	}
	public void setTrendName(String trendName) {
		this.trendName = trendName;
	}
	
	

}
