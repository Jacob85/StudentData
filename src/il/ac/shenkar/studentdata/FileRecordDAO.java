package il.ac.shenkar.studentdata;

import java.awt.Color;
import java.util.ArrayList;

import org.apache.derby.tools.sysinfo;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
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
			builder.append("From FileRecord where ");
			
			for (int i =0; i< columns.length ; i++)
			{
				builder.append(columns[i] +" ='" + conditials[i] + "'");
				if (i+1 <columns.length)
					builder.append(" AND ");
			}
			session.beginTransaction();
			Query query = session.createQuery(builder.toString());
			java.util.List list = query.list();
			
			/*Query query = session.createQuery("From FileRecord where university='uni' AND trend='trend'");
			java.util.List list1 = query.list();
			
			FileRecord file = (FileRecord) list1.get(0);
			System.out.println(file.getSubject());*/
			
			/*Session session = factory.openSession();
			StringBuilder builder = new StringBuilder();
			builder.append("FROM FileRecord where ");
			
			// create the query string
			for (int i =0; i< columns.length ; i++)
			{
				builder.append(columns[i] +" = :" + conditials[i]);
				if (i+1 <columns.length)
					builder.append(" and ");
			}
			Query query = session.createQuery(builder.toString());
			
			// add the parameters to the query
			for (int i =0; i< columns.length ; i++)
			{
				query.setString(columns[i], conditials[i]);
			}
			java.util.List result = query.list();*/
			
		/*	String sql = builder.toString();
			Criteria criteria = session.createCriteria(FileRecord.class).add(Restrictions.like("university", "uni")).add(Restrictions.like("trend", "trend"));  //"SELECT * from Files WHERE university = 'uni' AND trend = 'trend'"
			ArrayList<FileRecord> list =(ArrayList<FileRecord>) criteria.list();
			*/
			return  list;
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
		
		public ArrayList<String>  getSubjectList(java.util.List filesList)
		{
			if (filesList == null)
				return null;
			ArrayList<String> subjectList = new ArrayList<String>();
			for (int i=0; i<filesList.size(); i++)
			{
				FileRecord fileRecord =  (FileRecord) filesList.get(i);
				if (!subjectList.contains(((FileRecord) filesList.get(i)).getSubject()))
				{
					subjectList.add(((FileRecord) filesList.get(i)).getSubject());
				}
			}
			return subjectList;
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

		@Override
		public int updateRecord(Object toUpdate) 
		{
			Session session = factory.openSession();
			try {
				session.beginTransaction();
				session.update((FileRecord)toUpdate);
				session.getTransaction().commit();
				return 1;
			} catch (HibernateException e) 
			{
				e.printStackTrace();
			}
			return 0;
		}



}
