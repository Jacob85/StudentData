package il.ac.shenkar.studentdata;

import java.util.ArrayList;

import sun.util.logging.resources.logging;

/**
 * The File Record class is a class that represent Files
 * According to this class the Hibernate knows to which table to write or read the objects from 
 * 
 * @author Jacob, Cadan & Shimon
 *
 */
public class FileRecord
{
	private int id;
	private String university;
	private String trend;
	private String year;
	private String subject;
	private String path;
	private String description;
	
	public  FileRecord() 
	{
		super();
	}

	public FileRecord(int id, String university, String trend, String year,
			String subject, String path, String description) {
		super();
		this.id = id;
		this.university = university;
		this.trend = trend;
		this.year = year;
		this.subject = subject;
		this.path = path;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getTrend() {
		return trend;
	}

	public void setTrend(String trend) {
		this.trend = trend;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * built the path to the file location
	 * path will look like this uni/trend/year/course
	 * @return String of the file path
	 */
	public String builtPath()
	{
		/*	path look like this uni/trend/year/course*/
		StringBuilder builder = new StringBuilder();
		builder.append(this.university + "/");
		builder.append(this.trend + "/");
		builder.append(this.year + "/");
		builder.append(this.subject + "/");
		
		return builder.toString();
	}
	
	/**
	 * This function return a new ArrayList of the FileRecord Objects that belongs to the same course of the requested
	 * @param ArrayList<FileRecord>  fromList
	 * @param String course
	 * @return ArrayList<FileRecord> Results or null if fails
	 */
	public ArrayList<FileRecord> getFilesWithCourse(ArrayList<FileRecord> fromList,String course)
	{
		// if the course is null or the from list is null we return null
		if (course == null || fromList == null )
		{
			return null;
		}
		// create the new Array List
		ArrayList<FileRecord> listToReturn = new ArrayList<FileRecord>();
		
		// go over the from list and add the matches records to the new list
		for (FileRecord record: fromList)
		{
			if (record.getSubject().equals(course))
				listToReturn.add(record);
		}
		return listToReturn;
	}

	
	
}
