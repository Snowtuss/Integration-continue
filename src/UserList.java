package IntegrationContinue.src;

import java.util.HashMap;
import java.util.stream.Stream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;

public class UserList {
	HashMap<Integer,User> users;
	UserList(){
		users = new HashMap<Integer,User>();
		File yourFile = new File("./userlist.txt");
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
			reader = new BufferedReader(new FileReader("./userlist.txt"));
			String line = reader.readLine();
			while (line != null) {
				//content+=line;
				String[] explode = line.split(":");
				User user = new User(Integer.parseInt(explode[0]),explode[1],explode[2],explode[3]);
				users.put(Integer.parseInt(explode[0]), user);
				// read next line
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	void addUser(User user) {
		users.put(user.getId(), user);
		Path fileName = Path.of("./userlist.txt");
        String content  = user.getId()+":"+user.getUsername()+":"+user.getPassword();
        try {
			Files.writeString(fileName, content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
