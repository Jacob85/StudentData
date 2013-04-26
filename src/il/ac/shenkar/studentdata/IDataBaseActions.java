package il.ac.shenkar.studentdata;

import org.hibernate.mapping.List;





public interface IDataBaseActions 
{
	/**
	 * removed a Record from the DB by ID
	 * @param itdoremove
	 * @return 1 if record was removed, 0 if not
	 */
	public int removeRecord(String itdoremove);
	/**
	 * Add new Record to the DB
	 * @param toAdd
	 * @return 1 if record was added successful, 0 if not
	 */
	public int addRecord(Object toAdd);
	
	/**
	 * Get Record from the DB by ID
	 * @param id
	 * @return the object if succeed, null if not
	 */
	public Object getRecord(String id);
	
	/**
	 *Get all the Records in the DB 
	 * @return list of all the records in the DB
	 */
	public java.util.List getAllRecords();
	
	/**
	 * Update the Record in the DB 
	 * @param toUpdate
	 * @return i if succeed o if not
	 */
	public int updateRecord(Object toUpdate);

}
