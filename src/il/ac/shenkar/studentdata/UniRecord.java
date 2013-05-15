package il.ac.shenkar.studentdata;
/**
 * The Uni Record class is a class that represent Unis
 * According to this class the Hibernate knows to which table to write or read the objects from 
 * 
 * @author Jacob, Cadan & Shimon
 *
 */
public class UniRecord
{
	private String Uniname;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UniRecord() {
		super();
	}

	public String getUniname() {
		return Uniname;
	}

	public void setUniname(String uniname) {
		Uniname = uniname;
	}
	
	
}
