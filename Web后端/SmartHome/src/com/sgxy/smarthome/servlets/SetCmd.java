package com.sgxy.smarthome.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sgxy.smarthome.conn.DataModel;

public class SetCmd extends HttpServlet {
	 /**
     * @see HttpServlet#HttpServlet()
     */
    public SetCmd() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        String type=request.getParameter("type");
        String state=request.getParameter("state");
        try {
			DataModel.postCmd(type, state);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			 response.sendError(400);
			e.printStackTrace();
		}
        response.setStatus(200);
	    	
    }
    
    // 处理 POST 方法请求的方法
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    
    public void destroy() {
    	// TODO Auto-generated method stub
    	super.destroy();

    }
}
