package il.ac.shenkar.studentdata;

import org.apache.log4j.Logger;

/**
 * The User class is a class that represent Users
 * According to this class the Hibernate knows to which table to write or read the objects from 
 * 
 * @author Jacob, Cadan & Shimon
 *
 */
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
		this.university = university;
		this.trend = trend;
		this.year = year;
		this.subject = subject;
		setPassword(password);
		this.logger = Logger.getLogger(User.class.getName());
	}


	public String getEmail() {
		return email;
	}
	public void setEmail(String email) 
	{
		email = email.trim();
		email = email.toLowerCase();
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


	/**
	 * check if a specific file in exists in the user file to view member
	 * @param filename
	 * @return boolean
	 */
	public boolean isExistInCart(String filename)
	{
		if (filename == null)
			return false;
		if (filename.isEmpty())
			return false;
		if (this.filesToView.contains(filename))
			return true;
		return false;
	}

	/**
	 * Remove a file name from the user cart and add it to the files history
	 * @param fileName
	 */
	public void removeFromCart(String fileName)
	{
		//remove the file from the file list
		this.filesToView = filesToView.replace(fileName, "");
		logger.info("Removind "+ fileName + " From the To DO List");
		//remove the duplicate separetor if exists  
		this.filesToView = filesToView.replace("%%","%");
		
		if (filesToView.startsWith("%"))
			filesToView = filesToView.replaceFirst("%", "");					/*remove the first char of % from the string*/
		if (filesToView.endsWith("%"))
			filesToView = filesToView.substring(0,filesToView.length()-1);		/*remove the last char of % from the string*/
		
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
	
	/**add the file name to the cart
	 * if the file name is null or empty -> do nothing
	 * @param fileName
	 */
	public void addToCart(String fileName) 
	{
		if (fileName == null )
			return;
		if (fileName.isEmpty())
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
	
	/**
	 * remove file name from the file history
	 * 
	 * @param fileName
	 */
	public void removeFromHistory(String fileName)
	{
		//remove the file from the list
		this.filesHistory = this.filesHistory.replace(fileName, "");
		logger.info("Removind "+ fileName + " files History list");
		
		//remove the duplicate separetor if exists  
		this.filesHistory = filesHistory.replace("%%","%");
		
		if (filesHistory.startsWith("%"))
			filesHistory = filesHistory.replaceFirst("%", "");
		if (filesHistory.startsWith(" %"))
			filesHistory = filesHistory.replaceFirst(" %", "");
		if (filesHistory.endsWith("%"))
			filesHistory = filesHistory.substring(0, filesHistory.length()-1);
		
		
		if (this.filesHistory.isEmpty())
			filesHistory = "none";
		
		return;
	}
	
	/**
	 * clear the file history
	 */
	public void clearHistory()
	{
		this.filesHistory = "none";
	}
	
	/**
	 * clear the cart move all of the records to the file history
	 */
	public void clearCart()
	{
		//check if the file history id empty
		if (filesHistory.equals("none"))
			this.setFilesHistory(this.getFilesToView());
		else
			this.setFilesHistory(this.getFilesHistory() + "%" + this.getFilesToView());
		
		// put the cart content in the history list & clear the cart
		this.setFilesToView("none");
		logger.info("Copy all items from cart to history and clear the cart");
		return;

	}
}
