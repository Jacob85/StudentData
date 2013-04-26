package il.ac.shenkar.studentdata;

import java.io.Serializable;
import java.util.ArrayList;

import org.hibernate.mapping.List;

public class Register implements Serializable
{
	private List unis;
	private List trends;
	
	
	public Register() {
		super();
	}
	public List getUnis() {
		return unis;
	}
	public void setUnis(List unis) {
		this.unis = unis;
	}
	public List getTrends() {
		return trends;
	}
	public void setTrends(List trends) {
		this.trends = trends;
	}
	
	public void getLists()
	{
		this.unis = UniDAO.getInstance().getAllRecords();
		this.trends = TrendDAO.getInstance().getAllRecords();
	}
	
}
