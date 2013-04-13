package il.ac.shenkar.studentdata;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;
import org.hibernate.mapping.List;

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
		try {
			// creating a new session for adding products
			Session session = factory.openSession();
			session.beginTransaction();
			session.save((User)toAdd);
			session.getTransaction().commit();
			session.close(); 
			return 1;
		} catch (HibernateException e) 
		{
			// TODO: maybe write to log file
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
		}
		return null;
	}
	
	public boolean isExist(String id)
	{
		Session session = factory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM User WHERE id = :id").setParameter("id",id);
		java.util.List list = query.list();
		if (list.size() > 0)
			return true;
		return false;
	}

	@Override
	public List getAllRecords() 
	{
		Session session = factory.openSession();
		try {
			session.beginTransaction();
			List list = (List) session.createQuery("from Users").list();
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

}
