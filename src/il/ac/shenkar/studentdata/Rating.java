package il.ac.shenkar.studentdata;

/**
 * Rating ENUM
 * */
public enum Rating
{
	NONE(0), LOW(1), MEDIUM(2), HIGH(3), HIGHEST(4);
	private final int value;
	private Rating (int value)
	{
		this.value = value;
	}
	//get the enum value
	public int getValue()
	{
		return value;
	}

}
