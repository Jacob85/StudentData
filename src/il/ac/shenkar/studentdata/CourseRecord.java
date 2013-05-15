package il.ac.shenkar.studentdata;

/**
 * The Course Record class is a class that represent courses
 * According to this class the Hibernate knows to which table to write or read the objects from 
 * 
 * @author Jacob, Cadan & Shimon
 *
 */
public class CourseRecord 
{
	private String courseName;
	private int id;
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public CourseRecord() {
		super();
	}
	
	

}
