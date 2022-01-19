package IntegrationContinue.src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class User {

		Integer id;
		String username;
		String password;
		String groupName;
		User() {
		
		}
		User(int id,String username,String password, String groupName){
			this.id = id;
			this.username = username;
			this.password = password;
			this.groupName = groupName;
		}
		//SETTERS AND GETTERS
		String getUsername() {
			return this.username;
		}
		String getPassword() {
			return this.password;
		}
		Integer getId() {
			return this.id;
		}
		
		void setUsername(String username) {
			this.username = username;
		}
		void setPassword(String password) {
			this.password = password;
		}
		void setId(int id) {
			this.id = id;
		}
		
		//GROUP RELATED SHIET
		void joinGroup(Group group,GroupList groups) {
			if(this.haveGroup()) 
				leaveGroup(groups);
			//GroupList.checkGroup();
			group.addUser(this.username);
			if(group.nmbrUsers > 1)
				group.status = "ACTIF";
		}
		
		boolean haveGroup() {
			if(this.groupName == "NONE")
				return false;
			else
				return true;
		}
		
		List<String> ListUsersWithNoGroup(HashMap<Integer,User> users) {
			List<String> resultList = new ArrayList<String>();
			for (User user : users.values()) {
			    if(! user.haveGroup()) {
			    	resultList.add(user.getUsername());
			    }
			}
			return resultList;
		}
		
		String createGroup(String groupName,GroupList groups,int MAX_USERS) {
			if(this.haveGroup()) 
				leaveGroup(groups);
			List<String> users = new ArrayList<String>();
			users.add(this.username);
			Group newGroup = new Group(groupName,users,"INACTIF",MAX_USERS);
			groups.Groups.add(newGroup);
			return groupName;
		}
		
		void joinRandomGroup(GroupList groups) {
			if(this.haveGroup()) 
				leaveGroup(groups);
			List<Group> availableGroups = new ArrayList<Group>();
			for(Group g : groups.Groups) {
				if(!g.isFull())
					availableGroups.add(g);
			}
			Random rand = new Random();
	        int randomNum = rand.nextInt(0, availableGroups.size() + 1);
	        this.joinGroup(availableGroups.get(randomNum),groups);
		}
		
		void leaveGroup(GroupList groups) {
			if(this.haveGroup()) {
				groups.findGroup(this.groupName).users.remove(this.username);
				this.groupName = "NONE";
			}
		}
		String GroupInfo(GroupList groups) {
			String s= "";
			if(this.haveGroup()) {
				Group g = groups.findGroup(this.groupName);
				s="Group Name : " + g.name + "\n List of user : ";
				for(String username : g.users)
					s+=username + " ";
				s+="\n";
			}
				
			return s;
		}
		
		
}
