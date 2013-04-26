package il.ac.shenkar.studentdata;

import java.io.Serializable;
import java.util.ArrayList;

import org.hibernate.mapping.List;

import sun.misc.JavaUtilJarAccess;

public class Register implements Serializable
{
	private java.util.List<String> unis;
	private java.util.List<String> trends;
	
	
	public Register() {
		unis = new ArrayList<String>();
		trends = new ArrayList<String>();
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
	}
	
}
