package com.sgxy.smarthome.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sgxy.smarthome.conn.DataModel;

public class SetSenorCo2 extends HttpServlet {
	 private static final long serialVersionUID = 1L;
     
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public SetSenorCo2() {
	        super();
	        // TODO Auto-generated constructor stub
	    }

	    /**
	     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	     */
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       
	    	int value=0;
	    	
	    	value=Integer.valueOf(request.getParameter("value")).intValue();
	        String user=request.getParameter("user");
	        if(value!=0&&user!=""){
	        	try {
					DataModel.addCo2Value(value, user);
					response.setStatus(200);
				} catch (SQLException e) {
					 response.sendError(400, "Add Fail" );
					e.printStackTrace();
				}
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
