package il.ac.shenkar.studentdata;

import org.apache.log4j.Logger;

public class User 
{
	private String email;
	private String userName;
	private String password;
	private String university;
	private String trend;
	private String year;
	private String subject;
	private String filesToView;
	private String filesHistory;
	private Logger logger;
	
	
	public User() {
		super();
		this.logger = Logger.getLogger(User.class.getName());
	}
	
	
	public User(String email, String userName, String password,
			String university, String trend, String year, String subject) {
		super();
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.university = university;
		this.trend = trend;
		this.year = year;
		this.subject = subject;
		this.logger = Logger.getLogger(User.class.getName());
	}


	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		// checks if the password pass to me is a valid md5 i just put it in the password member
		if (MD5Converter.isValidMD5(password))
		{
			this.password = password;
		}
		// else i create md5 hash from the input string and then store if in the password member
		else
		{
			this.password = new MD5Converter().getMD5(password);
		}
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
	public String getFilesToView() {
		return filesToView;
	}
	public void setFilesToView(String filesToView) {
		this.filesToView = filesToView;
	}
	public String getFilesHistory() {
		return filesHistory;
	}
	public void setFilesHistory(String filesHistory) {
		this.filesHistory = filesHistory;
	}
	
	public boolean isPasswordOK(String passwordToCheck)
	{
		// create MD5 convertor
		MD5Converter md5 = new MD5Converter();
		
		if (this.getPassword().equals(md5.getMD5(passwordToCheck)))
		{
			// mean the password is identical and the user loged in sucssesful
			return true;
		}
		return false;
		
	}
	
	
	
	@Override
	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("User name: "+ this.getUserName()+ "\n");
		builder.append("User email is: " + this.getEmail()+ "\n"); 
		builder.append("User Uni is: " + this.getUniversity()+ "\n");
		builder.append("User trend is: " + this.getTrend() + "\n");
		builder.append("User year is: " + this.getYear()+ "\n"); 
		return  builder.toString();
	}


	public boolean isExistInCart(String filename)
	{
		if (this.filesToView.contains(filename))
			return true;
		return false;
	}

	public void removeFromCart(String fileName)
	{
		//remove the file from the file list
		this.filesToView = filesToView.replace(fileName, "");
		logger.info("Removind "+ fileName + " From the To DO List");
		//remove the duplicate separetor if exists  
		this.filesToView = filesToView.replace("%%","%");
		
		if (this.filesToView.isEmpty())
			filesToView = "none";
		//add the file i removed to the file history
		if (getFilesHistory().equals("none"))
		{
			//mean the files history is empty and we need to run over the data inside the member
			setFilesHistory(fileName);
			logger.info("Files History is empty, Adding: "+ fileName+" to files history");
			return;
		}
		
		if (this.filesHistory.contains(fileName))
		{
			//mean the history list already have the file in it we need to do nothing
			logger.info("File History already contains the file: " + fileName);
			return;
		}
		
		setFilesHistory(this.getFilesHistory() + "%" + fileName);
		logger.info("Adding: " + fileName + " to File History");
	}
	
	public void addToCart(String fileName) 
	{
		if (fileName == null || fileName.isEmpty())
			return;
		// check if the member files to view is empty
		if (getFilesToView().equals("none"))
		{
			//set the files to view to the new file name 
			//the files to view before was 'none'
			setFilesToView(fileName);
			logger.info("To DO File List is empty, Adding: "+ fileName+" to the list");
			return;
		}
		setFilesToView(getFilesToView() + "%" + fileName);
		logger.info("Adding: " + fileName + " to TODO list");
		return;
		
	}
}
