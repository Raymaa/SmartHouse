package com.sgxy.smarthome.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sgxy.smarthome.conn.AsynResult;
import com.sgxy.smarthome.conn.DataModel;

public class ReceviceCmd   extends HttpServlet{
	 /**
     * @see HttpServlet#HttpServlet()
     */
    public ReceviceCmd() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//��ȡ����
    	try {

			Gson gson = new Gson();
			String jsonStr = gson.toJson(DataModel.getCmd());
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
    
    // ���� POST ��������ķ���
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    
    public void destroy() {
    	// TODO Auto-generated method stub
    	super.destroy();

    }
}