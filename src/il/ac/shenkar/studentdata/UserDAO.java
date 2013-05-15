package il.ac.shenkar.studentdata;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;
import org.hibernate.mapping.List;

/**
 * The User DAO class is a class that Access the DB to get the Users from there
 * it implements the IDataBaseActions interface in order to access the DB
 * This class Read & Write the User Objects to the Right table in the DB  
 * Implemented as a Singleton 
 * 
 * @author Jacob, Cadan & Shimon
 *
 */
public class UserDAO implements IDataBaseActions
{
	private static UserDAO instance = null;
	private SessionFactory factory;
	
	private UserDAO()
	{
		// creating factory for getting session
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
	}
	
	//Singleton UserDAO
	public static UserDAO getInstance()
	{
		if (UserDAO.instance == null)
			instance = new UserDAO();
		return instance;
	}

	@Override
	public int removeRecord(String itdoremove)
	{
		Session session = factory.openSession();
		session.beginTransaction();
		User found = (User) session.get(User.class, itdoremove);
		if (found != null)
		{
			session.delete(found);
			session.close();
			return 1;
		}
		session.close();
		return 0;
	}

	@Override
	public int addRecord(Object toAdd) 
	{
		Session session = factory.openSession();
		try {
			// creating a new session for adding products
			session.beginTransaction();
			session.save((User)toAdd);
			session.getTransaction().commit();
			session.close(); 
			return 1;
		} catch (HibernateException e) 
		{
			// TODO: maybe write to log file
			session.close();
			e.printStackTrace();
			return 0;
		}	
	}

	//get the user by email
	@Override
	public Object getRecord(String id) 
	{
		if (isExist(id))
		{
			Session session = factory.openSession();
			session.beginTransaction();
			User found = (User) session.get(User.class, id);
			if (found != null)
			{
				session.close();
				return found;
			}
			session.close();
		}
		return null;
	}
	
	/**
	 * check if the user is exist in the DB 
	 * 
	 * @param id
	 * @return boolean
	 */
	public boolean isExist(String id)
	{
		Session session = factory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM User WHERE id = :id").setParameter("id",id);
		java.util.List list = query.list();
		session.close();
		if (list.size() > 0)
			return true;
		return false;
	}

	@Override
	public java.util.List getAllRecords() 
	{
		Session session = factory.openSession();
		try {
			session.beginTransaction();
			java.util.List list = session.createQuery("from Users").list();
			session.getTransaction().commit();
			return list;
			} catch (Exception e){
				if ( session.getTransaction() != null )
					session.getTransaction().rollback();
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
		Session session = factory.openSession();
		try {
			session.beginTransaction();
			session.update((User)toUpdate);
			session.getTransaction().commit();
			session.close();
			return 1;
		} catch (HibernateException e) 
		{
			session.close();
			e.printStackTrace();
		}
		return 0;
	}

}
