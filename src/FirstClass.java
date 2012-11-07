//Import classes and libs
import java.util.*;
import java.io.*;
import java.util.zip.*;

public class FirstClass {

	/**
	 * Main program
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		
		boolean test = false;
		int counter = 0;
		String myFileName = null;
		
		while (!test && counter < 2) {
			Scanner myScanner = new Scanner(System.in);
			System.out.println("Wpisz nazwę pliku, który chcesz aby został odzipowany");
			myFileName = myScanner.nextLine();
			System.out.println(myFileName);
			counter++;
			File myFile = new File(myFileName);
			if (!myFile.exists()) {
				System.out.println("Niestety, ale Twój plik nie istnieje.");
			}
			else
			{
				if (!myFileName.endsWith(".zip")) {
					System.out.println("Twój plik musi mieć rozszerzenie .zip!");
				}
				else {
					test = true;
				}
			}
			
		}
		
		if (counter >= 2 && !test) {
			System.out.println("Nie znaleziono poprawnego pliku po 2 próbach...");
			System.exit(0);
		}
		
		//TODO: Open zip
		ZipInputStream myZip = new ZipInputStream(new FileInputStream(myFileName));
		BufferedReader myBr = new BufferedReader(new InputStreamReader(myZip));
		
		ZipEntry myZe;
		String currentLine;
		ArrayList<String> entriesNames = new ArrayList<String>();
		
		while ((myZe = myZip.getNextEntry()) != null) {
			entriesNames.add(myZe.getName());
			myZip.closeEntry();
		}
		myZip.close();
		
		ZipFile zipFile = new ZipFile(myFileName);
		ArrayList<PersonOfIntrest> persons = new ArrayList<PersonOfIntrest>();
		
		System.out.println("------------");
		for (String entry : entriesNames) {
			System.out.println(entry);
			System.out.println("-----------");
			ZipEntry zipEntry = zipFile.getEntry(entry);
			
			InputStream inputStream = zipFile.getInputStream(zipEntry);
			BufferedReader myBuffer = new BufferedReader(new InputStreamReader(inputStream));
			ArrayList<String> entryPerson = new ArrayList<String>();
			while ((currentLine = myBuffer.readLine()) != null) {	
				entryPerson.add(currentLine);
			}
			PersonOfIntrest person = new PersonOfIntrest(entryPerson.get(0).toString(), entryPerson.get(1).toString(), entryPerson.get(2).toString());
			persons.add(person);
		}
		

		String myChoice;
		Integer myInt = 0;
		Scanner myScanner = new Scanner(System.in);
		System.out.println("=== WYBIERZ ===");
		System.out.println("1 aby utworzyć ZIP z tymi danymi;");
		System.out.println("2 by utworzyc plik serializacji;");
		System.out.println("3 by wyswietlic dane z tablicy");
		System.out.println("lub wcisnij cokolwiek innego by wyjsc.");
		myChoice = myScanner.nextLine();
		
		try 
		{
			myInt = Integer.parseInt(myChoice);
		} catch(NumberFormatException nFE)
		{
			System.exit(0);
		}
		
		switch (myInt) {
			case 1:
				//Create zip with data
				byte[] buff = new byte[1024];
				String targetFile = "data.zip";
				ZipOutputStream output = new ZipOutputStream(new FileOutputStream(targetFile));
				
				for (PersonOfIntrest persona : persons)
				{
					String personaFile = persona.getName() + ".txt";
					
					File f = new File(personaFile);
					FileOutputStream fout = new FileOutputStream(f);
					byte[] contentString = persona.toString().getBytes();
					
					try {
						fout.write(contentString);
						fout.flush();
						fout.close();
					} catch (IOException e)
					{
						System.out.println("Błąd zapisu do pliku: " + e);
						System.exit(0);
					}
					
					output.putNextEntry(new ZipEntry(personaFile));
					FileInputStream in = new FileInputStream(personaFile);
					int len;
					while ((len = in.read(buff)) > 0) {
						output.write(buff, 0 , len);
					}
					output.closeEntry();
					in.close();
					f.delete();
				}
				output.close();
				
				System.out.println("Plik utworzony - miłego dnia! ;)");
				System.exit(0);
			break;
			case 2:
				//Create serialization file
				try 
				{
					FileOutputStream outFile = new FileOutputStream("persons.ser");
					ObjectOutputStream out = new ObjectOutputStream(outFile);
					
					for (Object persona : persons)
					{
						out.writeObject(persona);
					}
					out.close();
					System.out.println("Plik stworzony. Miłego dnia! ;)");
					System.exit(0);
					
				} catch(IOException i)
				{
					i.printStackTrace();
				}
			break;
			case 3:
				System.out.println();
				System.out.println();
				for (Object persona : persons)
				{
					System.out.println(persona.toString());
					System.out.println("-------------------");
				}
			break;
			default:
			break;
		}
		
	}
}
			
			