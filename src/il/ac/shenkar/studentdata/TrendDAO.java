package il.ac.shenkar.studentdata;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;
import org.hibernate.mapping.List;

/**
 * The Trend DAO class is a class that Access the DB to get the Trends from there
 * it implements the IDataBaseActions interface in order to access the DB
 * This class Read & Write the Trends Objects to the Right table in the DB  
 * Implemented as a Singleton 
 * 
 * @author Jacob, Cadan & Shimon
 *
 */
public class TrendDAO implements IDataBaseActions 
{
	private SessionFactory factory;
	private static TrendDAO instance = null;
	private Logger logger;

	private TrendDAO()
	{
		// creating factory for getting session
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
		logger = Logger.getLogger(TrendDAO.class.getName());
		logger.info("Trend DAO was created"); 
	}
	
	/**
	 * use lazy initialization,create the Object only when needed and return an instance to the course DAO OB
	 * @return instance to TrendDAO
	 */
	public static TrendDAO getInstance()
	{
		if (instance == null)
			instance = new TrendDAO();
		return instance;
	}
	
	@Override
	public int removeRecord(String itdoremove) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addRecord(Object toAdd) 
	{
		Session session = factory.openSession();
		try {
			logger.info("addRecord was called");
			// creating a new session for adding products
			session.beginTransaction();
			session.save((Trend)toAdd);
			logger.info("Saving Trend Record");
			session.getTransaction().commit();
			session.close(); 
			logger.info("Trend Record Saved, return 1");
			return 1;
		} catch (HibernateException e) 
		{
			session.close();
			logger.error("Save Trend Record Failed, return 0");
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Object getRecord(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public java.util.List getAllRecords()
	{
		logger.info("getAllRecords was called");
		Session session = factory.openSession();
		try {
			session.beginTransaction();
			java.util.List list = session.createQuery("from Trend").list();
			session.getTransaction().commit();
			logger.info("Query success, return list of all trend Records");
			return list;
			} catch (Exception e){
				e.printStackTrace();
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
	public int updateRecord(Object toUpdate) {
		// TODO Auto-generated method stub
		return 0;
	}

}
