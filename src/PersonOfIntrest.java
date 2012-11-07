import java.io.Serializable;

public class PersonOfIntrest implements Serializable {
	private String name;
	private String lastname;
	private String age;
	private String information;
	private String fullInformation;
	
	public PersonOfIntrest(String name, String lastname, String age)
	{
		this.name = name;
		this.lastname = lastname;
		this.age = age;
		this.information = getFullName();
		this.fullInformation = getFullData();
	}
	
	private String getFullName()
	{
		return name + " " + lastname;
	}
	
	private String getFullData()
	{
		
		String basicInfo = this.getFullName();
		return basicInfo + ", Wiek: " + age;
		
	}
	
	public String toString()
	{
		return getFullData();
	}
	
	public String getName()
	{
		return name + "-" + lastname;
	}
}
