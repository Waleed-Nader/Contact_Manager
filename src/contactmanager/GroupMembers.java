package contactmanager;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GroupMembers {
    ArrayList<Person> groupMembers = new ArrayList<Person>(); //array list for group members 
String nameNumberString;
String FirstName ;
String Lastname ;
String City;
String Phonenumber;
    public GroupMembers(String groupName) throws IOException {

String fileName = groupName+".txt";
File file = new File(fileName);
if (!file.exists()) {
 
    // Create a new file if not exists.
    file.createNewFile();
}
try(
BufferedReader br = new BufferedReader(new FileReader(file))) {
       
    while ((nameNumberString = br.readLine()) != null) {

        // reading line from the file.
        // splitting the string to get values

       String[] lineSplit = nameNumberString.split(" ");
       int Id = Integer.parseInt(lineSplit[0]);
       FirstName = lineSplit[1];
       Lastname = lineSplit[2];
        City = lineSplit[3];
       Phonenumber = lineSplit[4];


groupMembers.add(new Person(Id, FirstName, Lastname, City, Phonenumber));

        
    }
} catch (IOException e1) {
    e1.printStackTrace();
}
    }


    public ArrayList<Person> getMembers() {
        return groupMembers;
    }
    
}
