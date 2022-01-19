package IntegrationContinue.src;

public class Program {
	public static void main(String[] args) {
		int MAX_USERS = 4;
		UserList users = new UserList();
		GroupList Groups = new GroupList(MAX_USERS);
		
		User amine = new User(0,"amine","amine");
		User toto = new User(1,"toto","toto");
		
		Group g = new Group("WonderFull Group",MAX_USERS);
		Groups.addGroup(g);
		
		users.addUser(amine);
		users.addUser(toto);
		
		amine.joinGroup(g, Groups);
		toto.joinGroup(g, Groups);
		
		System.out.println(users.users.get(1).getUsername());
		System.out.println(users.users.get(1).GroupInfo(Groups));
		System.out.println(20%5);
		
	}
}
