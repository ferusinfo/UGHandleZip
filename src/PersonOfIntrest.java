/**
 * UG Handle Zip
 * Program wynkonany na zaliczenie przedmiotu Programowanie Obiektowe
 * @author Maciej Kołek <kontakt@ferus.info>
 */

/**
 * Libraries importing
 */
import java.io.Serializable;

/**
 * Klasa obiektu - żeby nie było za prosto ;)
 *
 */
public class PersonOfIntrest implements Serializable {
	private String name;
	private String lastname;
	private String age;
	private String information;
	private String fullInformation;
	
	/**
	 * Constructor function
	 * @param name String Imie
	 * @param lastname String Nazwisko
	 * @param age String Wiek
	 */
	public PersonOfIntrest(String name, String lastname, String age)
	{
		this.name = name;
		this.lastname = lastname;
		this.age = age;
		this.information = getFullName();
		this.fullInformation = getFullData();
	}
	
	/**
	 * Gets full name of person
	 * @return string
	 */
	private String getFullName()
	{
		return name + " " + lastname;
	}
	
	/**
	 * Gets full data
	 * @return String
	 */
	private String getFullData()
	{
		String basicInfo = this.getFullName();
		return basicInfo + ", Wiek: " + age;
	}
	
	/**
	 * Overrides toString function (printing object purposes)
	 */
	public String toString()
	{
		return getFullData();
	}
	
	/**
	 * Gets a name for file saving later on
	 * @return string
	 */
	public String getName()
	{
		return name + "-" + lastname;
	}
}
