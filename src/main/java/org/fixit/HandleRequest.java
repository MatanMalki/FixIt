package org.fixit;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;


public class HandleRequest  implements Runnable  {

	private Socket someClient;
	private Gson gson;
	
	public HandleRequest (Socket someClient)
	{
		this.someClient=someClient;
	}
	
	@Override
	public void run(){
		try {
			   System.out.println("connceted to server");
			   Scanner reader = new Scanner(new InputStreamReader(someClient.getInputStream()));
			   PrintWriter writer = new PrintWriter(new OutputStreamWriter(someClient.getOutputStream()));
			   
			   
			   this.gson = new Gson();
			   String jsonstring=reader.next();
			   System.out.println("server:"+jsonstring);
			   Request request=gson.fromJson(jsonstring, Request.class);
			   Controller control=new Controller(request.getH().getAction());
			   String bodyjson=gson.toJson(request.getB());
			   Response res=control.process(bodyjson);
			   String answer=gson.toJson(res);
			   System.out.println(answer);
			   writer.write(answer);
			   writer.flush();
			   someClient.close();
			  
			   
				
			}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
