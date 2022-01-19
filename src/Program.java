package IntegrationContinue.src;

public class Program {
	public static void main(String[] args) {
		int MAX_USERS = 4;

		UserList users = new UserList();

		
		GroupList Groups = new GroupList(MAX_USERS);
		
		User amine = new User(0,"amine","amine");
		User toto = new User(1,"toto","toto");
		User tata = new User(2,"tata","toto");
		
		Group g = new Group("WonderFull Group",MAX_USERS);
		Groups.addGroup(g);
		Group g2 = new Group("Awefull Group",MAX_USERS);
		Groups.addGroup(g2);
		
		users.addUser(amine);
		users.addUser(toto);
		users.addUser(tata);
		
		amine.joinGroup(g, Groups);
		toto.joinGroup(g, Groups);
		tata.joinRandomGroup(Groups);
		
		System.out.println(users.users.get(1).getUsername());
		System.out.println(users.users.get(1).GroupInfo(Groups));
		System.out.println(users.users.get(2).GroupInfo(Groups));
		
		amine.leaveGroup(Groups);
		
		System.out.println("Amine left T_T\n"+users.users.get(1).GroupInfo(Groups));
	}
}
