package IntegrationContinue.src;

import java.util.ArrayList;
import java.util.List;

public class Group{
	List<String> users;
	String status;
	String name;
	int nmbrUsers;
	int MAX_USERS;
	//CONSTRUCTORS
	Group(String name, List<String> users,String status,int MAX_USERS){
		this.name=name;
		this.users=users;
		this.status = status;
		this.nmbrUsers = users.size();
		this.MAX_USERS = MAX_USERS;
	}
	Group(String name,int MAX_USERS){
		this.name=name;
		this.users= new ArrayList<String>();
		this.status = "INACTIF";
		this.nmbrUsers = 0;
		this.MAX_USERS = MAX_USERS;
	}
	
	boolean isFull() {
		if(nmbrUsers < MAX_USERS)
			return false;
		else
			return true;
	}
	void addUser(String username) {
		if(!this.isFull()) {
			users.add(username);
			this.nmbrUsers++;
		}
	}

}
