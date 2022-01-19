package IntegrationContinue.src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class GroupList {

	
	//UserList users;
	int nmbrGroups;
	String lastGroup;
	List<Group> Groups;
	int MAX_USERS;
	/*
	GroupList(int nmbrUsers,int nmbrGroups,String lastGroup,UserList users){
		//this.nmbrUsers=nmbrUsers;
		this.nmbrGroups=nmbrGroups;
		this.lastGroup=lastGroup;
		//this.users=users;
	}
	*/
	void setLast(){
		int nmbrUsers = this.MAX_USERS;
		int mod = nmbrUsers%nmbrGroups;
		if(mod == 0) 
			lastGroup = "LAST_NONE";
		else if((nmbrUsers/nmbrGroups)>mod) 
			lastGroup = "LAST_MIN";
		else
			lastGroup = "LAST_MAX";
	}
	
	GroupList(int MAX_USERS){
		Groups = new ArrayList<Group>();
		File yourFile = new File("./grouplist.txt");
		try {
			yourFile.createNewFile();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} // if file already exists will do nothing 
		try {
			FileOutputStream oFile = new FileOutputStream(yourFile, false);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
        //String content  = "hello world !!";
		//String content = "";
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("./grouplist.txt"));
			String line = reader.readLine();
			while (line != null) {
				//content+=line;
				String[] explode = line.split(":");
				//User user = new User(Integer.parseInt(explode[0]),explode[1],explode[2],explode[3],explode[4]);
				//users.put(Integer.parseInt(explode[0]), user);
				List<String> users = ArrayToListConversion(explode);
				users.remove(0);
				String status;
				if(users.size()>1)
					status="ACTIF";
				else
					status="INACTIF";
				Group g = new Group(explode[0],users,status,MAX_USERS);
				// read next line
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.MAX_USERS=MAX_USERS;
		this.nmbrGroups = this.Groups.size();
		//setLast();
	}
	void addGroup(Group group) {
		Groups.add(group);
		Path fileName = Path.of("./grouplist.txt");
        String content  = group.name;
        for(String username:group.users)
        	content+=":"+username;
        try {
			Files.writeString(fileName, content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	void checkGroups() {
		for(Group g : Groups) {
			if(g.nmbrUsers <2)
				Groups.remove(g);
		}
	}
	
	Group findGroup(String groupName) {
		Group g = null;
		for(Group group : Groups) {
			if(group.name == groupName)
				g = group;
		}
		return g;
	}
	
	void updateGroups(String username,String groupname,String option) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("./grouplist.txt"));
			String line = reader.readLine();
			while (line != null) {
				//content+=line;
				String[] explode = line.split(":");
				//User user = new User(Integer.parseInt(explode[0]),explode[1],explode[2],explode[3],explode[4]);
				//users.put(Integer.parseInt(explode[0]), user);
				if(explode[0].equals(groupname)) {
					if(option.equals("ADD"))
						replaceLine(line,line+":"+username);
					else
						replaceLine(line,line.replaceAll(":"+username, ""));
				}
					
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//TOOLS
	public static <T> List<T> ArrayToListConversion(T array[])   
	{   
		//creating the constructor of the List class  
		List<T> list = new ArrayList<>();   
		//using for-each loop to iterate over the array  
		for (T t : array)   
		{   
		//adding each element to the List  
		list.add(t);   
		}  
		//returns the list converted into Array  
		return list;   
	} 
	void replaceLine(String oldLine,String newLine) throws IOException {
		//Instantiating the File class
	      String filePath = "./grouplist.txt";
	      //Instantiating the Scanner class to read the file
	      Scanner sc = new Scanner(new File(filePath));
	      //instantiating the StringBuffer class
	      StringBuffer buffer = new StringBuffer();
	      //Reading lines of the file and appending them to StringBuffer
	      while (sc.hasNextLine()) {
	         buffer.append(sc.nextLine()+System.lineSeparator());
	      }
	      String fileContents = buffer.toString();
	      //System.out.println("Contents of the file: "+fileContents);
	      //closing the Scanner object
	      sc.close();
	      //Replacing the old line with new line
	      fileContents = fileContents.replaceAll(oldLine, newLine);
	      //instantiating the FileWriter class
	      FileWriter writer = new FileWriter(filePath);
	     // System.out.println("");
	      //System.out.println("new data: "+fileContents);
	      writer.append(fileContents);
	      writer.flush();
	}
	
}
