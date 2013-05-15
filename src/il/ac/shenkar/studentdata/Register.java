package il.ac.shenkar.studentdata;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.naming.java.javaURLContextFactory;
import org.hibernate.mapping.List;

import sun.misc.JavaUtilJarAccess;

/**
 * The Register Class is a Java Been Object that hold lists of data it get from the DB
 * The Register Class is used to display the dropdown option in the controller
 * @author Jacob, Cadan & Shimon
 *
 */
public class Register implements Serializable
{
	private java.util.List<String> unis;
	private java.util.List<String> trends;
	private java.util.List<String> courses;
	
	
	public Register() {
		unis = new ArrayList<String>();
		trends = new ArrayList<String>();
		courses = new ArrayList<String>();
	}
		
	public java.util.List<String> getCourses() {
		return courses;
	}

	public java.util.List<String> getUnis() {
		return unis;
	}

	public void setUnis(ArrayList<String> unis) {
		this.unis = unis;
	}

	public java.util.List<String> getTrends() {
		return trends;
	}

	public void setTrends(ArrayList<String> trends) {
		this.trends = trends;
	}

	/**
	 * the get list method fill the list with data it gets from the DB
	 * 
	 * 
	 */
	public void getLists()
	{
		java.util.List<UniRecord> unilist = (java.util.List<UniRecord>) UniDAO.getInstance().getAllRecords();
		for (UniRecord record: unilist)
		{
			unis.add(new String(record.getUniname()));
		}
		
		java.util.List<Trend> trendList = (java.util.List<Trend>) TrendDAO.getInstance().getAllRecords();
		for(Trend trend: trendList)
		{
			trends.add(new String(trend.getTrendName()));
		}
		java.util.List<CourseRecord> courseList = (java.util.List<CourseRecord>)CourseRecordDAO.getInstance().getAllRecords();
		for (CourseRecord record: courseList)
		{
			courses.add(new String(record.getCourseName()));
		}
	}
	
}
