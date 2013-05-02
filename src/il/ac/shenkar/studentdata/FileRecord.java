package il.ac.shenkar.studentdata;

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

	
	
}
