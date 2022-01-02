package org.fixit;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class FixItServiceTest {

    private static FixItService service;
    private Project p;
    private User u;
    private Issue i;
    private Comment c;

    @Before
    public void init() throws ClassNotFoundException, SQLException
    {
        service= FixItService.getmInstance();
        p=new Project("HIT","hadar","052-3456788","hadarb@hit.com","24x7");
        u=new User("matanm", "matan malki", "malkush11@gmail.com", "A123a123");
        i=new Issue(p, u ,"create new server","hi, i need to create new server with ip address 10.0.0.2 thanks." );
        c=new Comment(u, "the server created");

    }

    @Test
    public void checkProjectTable() throws ClassNotFoundException, SQLException
    {
        service.getmProjects().add(p.getmName(), p);
        service.pushProjectTable();
        service.retrieveProject("HIT");
        assertEquals("hadarb@hit.com",service.getmProjects().getobject("HIT").getmEmail());
    }

    @Test
    public void checkIssueTable() throws ClassNotFoundException, SQLException
    {
        service.getmIssues().add(i.getmId(), i);
        service.getmIssues().getobject(i.getmId()).getmFlow().add(c);
        service.pushIssueTable();
        service.retrieveissue(i.getmId());
        assertEquals("create new server",service.getmIssues().getobject(i.getmId()).getmSubject());
    }

    @Test
    public void checkUserTable() throws ClassNotFoundException, SQLException
    {
        service.getmUsers().add(u.getmUserName(), u);
        service.pushUserTable();
        service.retrieveUser(u.getmUserName());
        assertEquals("malkush11@gmail.com",service.getmUsers().getobject(u.getmUserName()).getmEmail());
    }


    @AfterClass
    public static void end() throws SQLException, ClassNotFoundException
    {
        File f=new File("C:\\Derby\\database.db");
        f.delete();
        service.closeDB();
        service.shutdownDB();
        service.getClock().stop();
    }

}

