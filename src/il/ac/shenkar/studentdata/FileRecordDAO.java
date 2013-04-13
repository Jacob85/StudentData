package il.ac.shenkar.studentdata;

import java.awt.Color;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;
import org.hibernate.mapping.List;

import sun.font.EAttribute;

public class FileRecordDAO implements IDataBaseActions
{
	private static FileRecordDAO instance = null;
	private SessionFactory factory;

	private FileRecordDAO()
	{
		// creating factory for getting session
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
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
			int id = Integer.parseInt(itdoremove);
			Session session = factory.openSession();
			session.beginTransaction();
			FileRecord found = (FileRecord) session.get(FileRecord.class, id);
			if (found != null)
			{
				session.delete(found);
				session.close();
				return 1;
			}
			
			session.close();
			return 0;
		}
		
		// adding new file record
		@Override
		 public int addRecord(Object toAdd)
		 {
			try {
				// creating a new session for adding products
				Session session = factory.openSession();
				session.beginTransaction();
				session.save((FileRecord)toAdd);
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
		
		
		//get the files records by condition and column
		public java.util.List getRecordsWhere(String[] columns,String[] conditials)
		{
			Session session = factory.openSession();
			StringBuilder builder = new StringBuilder();
			builder.append("FROM FileRecord where ");
			
			// create the query string
			for (int i =0; i< columns.length ; i++)
			{
				builder.append(columns[i] +" = :" + columns[i]);
				if (i+1 <columns.length)
					builder.append(" and ");
			}
			Query query = session.createQuery(builder.toString());
			
			// add the parameters to the query
			for (int i =0; i< columns.length ; i++)
			{
				query.setParameter(columns[i], conditials[i]);
			}
			
			java.util.List list = query.list();
			return list;
		}

		@Override
		public Object getRecord(String id)
		{
			int idToGet = Integer.parseInt(id);
			Session session = factory.openSession();
			session.beginTransaction();
			FileRecord found = (FileRecord) session.get(FileRecord.class, idToGet);
			if (found != null)
			{
				session.close();
				return found;
			}
			return null;
			
		}

		@Override
		public List getAllRecords() {
			
			Session session = factory.openSession();
			try {
				session.beginTransaction();
				List list = (List) session.createQuery("from files").list();
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
