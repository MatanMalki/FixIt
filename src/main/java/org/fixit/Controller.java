package org.fixit;

import java.awt.print.Book;
import java.sql.SQLException;

import com.google.gson.Gson;

import org.fixit.User;

import org.fixit.Request;
import org.fixit.Request.Header;
import org.fixit.Response;
import org.fixit.Server;

public class Controller {

    private static FixItService service;
    private String action;


    public Controller(String action) throws ClassNotFoundException, SQLException {
        this.service=FixItService.getmInstance();
        this.action=action;
    }


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Response process(String jsonstring) throws ClassNotFoundException, SQLException
    {
        Response<Object> res=new Response(null);
        switch(action)
        {
            case "login":
                Gson gson=new Gson();
                User temp=gson.fromJson(jsonstring, User.class);
                res.setAnswer(service.signin(temp.getmUserName(), temp.getmPassword()));
                break;
        }
        return res;
    }
}
