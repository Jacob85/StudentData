package il.ac.shenkar.studentdata;

public class FileRecord
{
	private int id;
	private String university;
	private String trend;
	private String year;
	private String subject;
	private String path;
	private int rating;
	
	public  FileRecord() 
	{
		super();
	}

	public FileRecord(int id, String university, String trend, String year,
			String subject, String path, int rating) {
		super();
		this.id = id;
		this.university = university;
		this.trend = trend;
		this.year = year;
		this.subject = subject;
		this.path = path;
		this.rating = rating;
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

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	
	
}
