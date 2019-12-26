package com.sgxy.smarthome.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sgxy.smarthome.conn.DataModel;

public class ReadLedState extends HttpServlet {
	 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadLedState() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    	int value=0;
        String user=request.getParameter("user");
        
        try {
        	ArrayList i=DataModel.queryLed(user);
			
			Gson gson = new Gson();
			String jsonStr = gson.toJson(i);
			System.out.println(jsonStr);
			response.setStatus(200);
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("txt; charset=utf-8");
	
			PrintWriter out = null;
			try {
			    out = response.getWriter();
			    out.write(jsonStr);
			} catch (IOException e) {
			    e.printStackTrace();
			} finally {
			    if (out != null) {
			        out.close();
			    }
			}
//			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			response.sendError(400, "Add Fail" );
			e.printStackTrace();
		}
    }
    
    // 处理 POST 方法请求的方法
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    
    public void destroy() {
    	// TODO Auto-generated method stub
    	super.destroy();
    	DataModel.CloseConnection();
    }
}
