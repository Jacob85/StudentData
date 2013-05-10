package il.ac.shenkar.studentdata;


import java.awt.Color;
import java.util.ArrayList;

import org.apache.derby.tools.sysinfo;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.mapping.List;

import com.sun.corba.se.impl.oa.toa.TOA;

import sun.font.EAttribute;

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
	
	// Singleton FilesDAO 
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
		
		
		//get the files records by condition and column
		public java.util.List getRecordsWhere(String[] columns,String[] conditials)
		{
			logger.info("getRecordsWhere was called");
			Session session = factory.openSession();
			StringBuilder builder = new StringBuilder();
			builder.append("From FileRecord where ");
			
			for (int i =0; i< columns.length ; i++)
			{
				builder.append(columns[i] +" ='" + conditials[i] + "'");
				if (i+1 <columns.length)
					builder.append(" AND ");
			}
			logger.info("Creating Query: "+builder.toString());
			session.beginTransaction();
			Query query = session.createQuery(builder.toString());
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
					subjectList.add(((FileRecord) filesList.get(i)).getSubject());
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
			try {
				session.beginTransaction();
				java.util.List list = session.createQuery("from files").list();
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

	/*	public ArrayList<FileRecord> searchFiles(String str)
		{
			
		}
*/
}
