package org.fixit;


import org.fixit.FixItService;

import java.sql.SQLException;

public class main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		FixItService service=FixItService.getmInstance();
		Project p=new Project("HIT","hadar","052-3456788","hadarb@hit.com","24x7");
		User u=new User("matanm", "matan malki", "malkush11@gmail.com", "A123a123");
		Issue i=new Issue(p, u ,"create new server","hi, i need to create new server with ip address 10.0.0.2 thanks." );
		Comment c=new Comment(u, "the server created");
		i.getmFlow().add(c);
		service.getmProjects().add(p.getmName(), p);
		service.getmIssues().add(i.getmId(), i);
		service.getmUsers().add(u.getmUserName(), u);
		service.pushProjectTable();
		service.pushIssueTable();
		service.pushUserTable();
		service.retrieveissue(i.getmId());
		service.retrieveProject("HIT");
		service.retrieveissue(i.getmId());
		service.getmIssues().getobject(i.getmId()).setmStringStatus("RESOLVED");
		service.closeDB();
		service.shutdownDB();
		service.getClock().stop();

	}

}
