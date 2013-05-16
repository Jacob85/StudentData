package il.ac.shenkar.studentdata;


import java.awt.Color;
import java.util.ArrayList;

import org.apache.derby.tools.sysinfo;
import org.apache.log4j.Logger;
import org.apache.log4j.pattern.LogEvent;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.mapping.List;

import antlr.StringUtils;

import com.sun.corba.se.impl.oa.toa.TOA;

import sun.font.EAttribute;

/**
 * The File Record DAO class is a class that Access the DB to get the File Records from there
 * it implements the IDataBaseActions interface in order to access the DB
 * This class Read & Write the CourseRecord Objects to the Right table in the DB  
 * Implemented as a Singeltone 
 * 
 * @author Jacob, Cadan & Shimon
 *
 */
public class FileRecordDAO implements IDataBaseActions
{
	private static FileRecordDAO instance = null;
	private SessionFactory factory;
	private static Logger logger;

	private FileRecordDAO()
	{
		// creating factory for getting session
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
		logger = Logger.getLogger(FileRecordDAO.class.getName());
		logger.info("File Record DAO was created"); 
	}
	
	/**
	 * use lazy initialization,create the Object only when needed and return an instance to the course DAO OB
	 * @return instance to CourseRecordDAO
	 */
		public static FileRecordDAO getInstance ()
		{
			if (FileRecordDAO.instance == null)
				instance = new FileRecordDAO();
			return instance;
		}
		
		// delete file from the DB from id received 
		@Override
		public int removeRecord(String itdoremove)
		{
			logger.info("removeRecord was called");
			int id = Integer.parseInt(itdoremove);				/*get the ID i want to remove*/ 
			Session session = factory.openSession();
			if (!session.isConnected())
			{
				factory = new  AnnotationConfiguration().configure().buildSessionFactory();
				session = factory.openSession(); 
			}
			session.beginTransaction();
			FileRecord found = (FileRecord) session.get(FileRecord.class, id);
			if (found != null)
			{
				logger.info("File Record with id: " +id + " & path: "+found.getPath()+ "was found");
				session.delete(found);
				session.close();
				logger.info("File Rcord Was removed, return 1");
				return 1;
			}
			
			session.close();
			logger.error("File Record with id: " +id +" did not found, return 0");
			return 0;
		}
		
		// adding new file record
		@Override
		 public int addRecord(Object toAdd)
		 {
			Session session = factory.openSession();
			if (!session.isConnected())
			{
				factory = new  AnnotationConfiguration().configure().buildSessionFactory();
				session = factory.openSession(); 
			}
			try {
				logger.info("addRecord was called");
				// creating a new session for adding products
				session.beginTransaction();
				session.save((FileRecord)toAdd);
				logger.info("Saving File Record");
				session.getTransaction().commit();
				session.close(); 
				logger.info("File Record Saved, return 1");
				return 1;
			} catch (HibernateException e) 
			{
				session.close();
				logger.error("Save File Record Failed, return 0");
				e.printStackTrace();
				return 0;
			}
		 }
		
		
		/**
		 * Get the files records by condition and column
		 * this method build an HQL Query that matches the column to a conditional value
		 * 
		 * @param String [] columns
		 * @param String []conditials
		 * @return list of results or null if fails
		 */
		public java.util.List getRecordsWhere(String[] columns,String[] conditials)
		{
			logger.info("getRecordsWhere was called");
			Session session = factory.openSession();
			if (!session.isConnected())
			{
				factory = new  AnnotationConfiguration().configure().buildSessionFactory();
				session = factory.openSession(); 
			}
			StringBuilder builder = new StringBuilder();
			builder.append("From FileRecord where ");
			
			// here i build the HQL Query dynamically according to the parameters i received from the user
			for (int i =0; i< columns.length ; i++)
			{
				builder.append(columns[i] +" ='" + conditials[i] + "'");
				if (i+1 <columns.length)
					builder.append(" AND ");
			}
			// creating the Query
			logger.info("Creating Query: "+builder.toString());
			session.beginTransaction();
			Query query = session.createQuery(builder.toString());
			//Execute the query
			java.util.List list = query.list();
			session.close();
			return  list;
		}

		
		
		@Override
		public Object getRecord(String id)
		{
			logger.info("getRecord was called with id: "+id);
			int idToGet = Integer.parseInt(id);
			Session session = factory.openSession();
			if (!session.isConnected())
			{
				factory = new  AnnotationConfiguration().configure().buildSessionFactory();
				session = factory.openSession(); 
			}
			session.beginTransaction();
			FileRecord found = (FileRecord) session.get(FileRecord.class, idToGet);
			if (found != null)
			{
				logger.info("File Record with path: " +found.getPath()+ "Was found!, return 1");
				session.close();
				return found;
			}
			session.close();
			logger.error("File Record not found, return null");
			return null;	
		}
		
		/**
		 * This function create a list of subjects (courses) from fileRecord list
		 * @param filesList
		 * @return ArrayList<String> subjectList or null if fails
		 */
		public ArrayList<String>  getSubjectList(java.util.List filesList)
		{
			logger.info("getSubjectList was called");
			if (filesList == null)
			{
				//the method received null as an argument
				logger.info("argumrnt recieved is null, cannot parse the string, return null");
				return null;
			}
			ArrayList<String> subjectList = new ArrayList<String>();
			for (int i=0; i<filesList.size(); i++)
			{
				FileRecord fileRecord =  (FileRecord) filesList.get(i);
				if (!subjectList.contains(((FileRecord) filesList.get(i)).getSubject()))
				{
					// only if the current file record subject does not appear in the list i want to return
					// only them i will add it to the return list
					logger.info("Add: "+fileRecord.getSubject()+ "to the list to return");
					subjectList.add(fileRecord.getSubject());
				}
			}
			logger.info("return the subject list");
			return subjectList;
		}

		@Override
		public java.util.List getAllRecords() 
		{
			logger.info("getAllRecords was called");
			Session session = factory.openSession();
			if (!session.isConnected())
			{
				factory = new  AnnotationConfiguration().configure().buildSessionFactory();
				session = factory.openSession(); 
			}
			try {
				session.beginTransaction();
				java.util.List list = session.createQuery("from FileRecord").list();
				session.getTransaction().commit();
				logger.info("Query success, return list of all File Records");
				return list;
				} catch (Exception e){
					if ( session.getTransaction() != null )
						session.getTransaction().rollback();
					logger.error("Query failed return null");
					return null;
				}
				finally
				{
					session.close();
				}
		}

		@Override
		public int updateRecord(Object toUpdate) 
		{
			logger.info("updateRecord was called");
			Session session = factory.openSession();
			if (!session.isConnected())
			{
				factory = new  AnnotationConfiguration().configure().buildSessionFactory();
				session = factory.openSession(); 
			}
			try {
				session.beginTransaction();
				session.update((FileRecord)toUpdate);
				session.getTransaction().commit();
				session.close();
				logger.info("File Record was updated, return 1");
				return 1;
			} catch (HibernateException e) 
			{
				session.close();
				logger.error("File Update Failed, return 0");
				e.printStackTrace();
			}
			return 0;
		}

		/**
		 * This function return a list of objects which contains the subString in them
		 * The function is case sensitive 
		 * The Function searches in the File record Description, Trend, Uni &path
		 * @param Substring to search
		 * @return ArrayList<> of the results
		 */
		public ArrayList<FileRecord> searchFiles(String str)
		{
			if (str == null)
			{
				logger.error("Error!, sub string to search is null");
				return null;
			}
			ArrayList<FileRecord> allResults = (ArrayList<FileRecord>) this.getAllRecords();
			logger.debug("Query the DB, returned a list of "+ allResults.size()+" File records");
			ArrayList<FileRecord> relevantResults = new ArrayList<>();
			
			for (FileRecord record: allResults)
			{
				if (containsIgnoreCase(record.getDescription(), str) ||containsIgnoreCase(record.getTrend(), str) || containsIgnoreCase(record.getSubject(), str) ||containsIgnoreCase(record.getPath(), str))
				{
					relevantResults.add(record);
					logger.info("Adding "+record.getPath() + " to the returned Relevand list");
				}
			}
			logger.info("Returned List with "+relevantResults.size()+" File Records");
			return relevantResults;
			
		}
		
		private boolean containsIgnoreCase(String source, String substring)
		{
			if (source == null || substring == null)
				return false;
			if (source.isEmpty() || substring.isEmpty())
				return false;
			
			// to lower the strings and compare them
			String newSource = source.toLowerCase();
			String newSubstring = substring.toLowerCase();
			
			return newSource.contains(newSubstring);
			
			
		}

}
