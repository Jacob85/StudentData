package il.ac.shenkar.studentdata;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;
import org.hibernate.mapping.List;

public class UniDAO implements IDataBaseActions 
{
	private static UniDAO instance = null;
	private SessionFactory factory;
	private static Logger logger;
	
	
	private UniDAO()
	{
		// creating factory to get sessions lates on
		factory =  new AnnotationConfiguration().configure().buildSessionFactory();
		logger = Logger.getLogger(UniDAO.class.getName());
		logger.info("UniDAO OB was created");
	}
	
	public static UniDAO getInstance ()
	{
		if (instance == null)
			instance = new UniDAO();
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
		try {
			logger.info("addRecord was called");
			// creating a new session for adding products
			Session session = factory.openSession();
			session.beginTransaction();
			session.save((UniRecord)toAdd);
			logger.info("Saving Uni Record");
			session.getTransaction().commit();
			session.close(); 
			logger.info("Uni Record Saved, return 1");
			return 1;
		} catch (HibernateException e) 
		{
			logger.error("Save Uni Record Failed, return 0");
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Object getRecord(String id)
	{
		return null;
	}

	@Override
	public List getAllRecords() 
	{
		logger.info("getAllRecords was called");
		Session session = factory.openSession();
		try {
			session.beginTransaction();
			List list = (List) session.createQuery("from unis").list();
			session.getTransaction().commit();
			logger.info("Query success, return list of all uni Records");
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
	public int updateRecord(Object toUpdate) {
		// TODO Auto-generated method stub
		return 0;
	}

}
