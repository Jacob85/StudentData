package il.ac.shenkar.studentdata;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;

public class CourseRecordDAO implements IDataBaseActions 
{
	private static CourseRecordDAO instance = null;
	private SessionFactory factory;
	private static Logger logger;
	
	private CourseRecordDAO()
	{
		// creating factory to get sessions lates on
		factory =  new AnnotationConfiguration().configure().buildSessionFactory();
		logger = Logger.getLogger(CourseRecordDAO.class.getName());
		logger.info("CourseRecordDAO OB was created");
	}
	
	public static CourseRecordDAO getInstance()
	{
		if (instance == null)
			instance = new CourseRecordDAO();
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
			session.save((CourseRecord)toAdd);
			logger.info("Saving Course Record Record");
			session.getTransaction().commit();
			session.close(); 
			logger.info("Course Record Saved, return 1");
			return 1;
		} catch (HibernateException e) 
		{
			session.close();
			logger.error("Save Course Record Failed, return 0");
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
	public List getAllRecords() 
	{
		logger.info("getAllRecords was called");
		Session session = factory.openSession();
		try {
			session.beginTransaction();
			List list = session.createQuery("from CourseRecord").list();
			session.getTransaction().commit();
			logger.info("Query success, return list of all Course Records");
			return  list;
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
	public int updateRecord(Object toUpdate) {
		// TODO Auto-generated method stub
		return 0;
	}

}
