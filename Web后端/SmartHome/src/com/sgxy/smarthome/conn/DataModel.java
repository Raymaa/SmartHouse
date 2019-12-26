package com.sgxy.smarthome.conn;




import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


/***
 *���ݿ����
 * @author asus
 *
 */
public class DataModel {
	public static Connection con;
	public static Object lo=new Object();
	

	public static  final Connection getConnection(){
	
		synchronized(lo){
			try {
				if(con==null){
					Class.forName("com.mysql.jdbc.Driver");
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","13415629843");
					System.out.println(con.hashCode());
				}
				return con;
			}catch (ClassNotFoundException e) {
				System.out.println("������"+e);
			} catch (SQLException e) {
				System.out.println("������"+e);
			}
			
		}
		return null;
	}
	
	
	public static  final void CloseConnection() {
		
		synchronized(lo){
			if(con==null){
				return;
			}
			try {
				con.close();
			} catch (SQLException e) {
					// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	/***
	 * ���������������
	 * @param value ����
	 * @param username	�û���
	 * @throws SQLException 
	 */
	public static void addSmogValue(int value,String username) throws SQLException{
		
		Statement sql=getConnection().createStatement();
		sql.execute("insert into smarthome.senor_smog(Value,User) values("+value+",'"+username+"')");
		
		
		System.out.println("�������");
	}
	
	
	/***
	 * Co2�������������
	 * @param value ����
	 * @param username	�û���
	 * @throws SQLException 
	 */
	public static void addCo2Value(int value,String username) throws SQLException{
		Statement sql=getConnection().createStatement();
		sql.execute("insert into smarthome.senor_co2(Value,User) values("+value+",'"+username+"')");
		
		
		System.out.println("���Co2");
	}
	/***
	 * �¶ȴ������������
	 * @param value ����
	 * @param username	�û���
	 * @throws SQLException 
	 */
	public static void addTemperatureValue(int value,String username) throws SQLException{
		
		Statement sql=getConnection().createStatement();
		sql.execute("insert into smarthome.senor_temperature(Value,User) values("+value+",'"+username+"')");
		
		
		System.out.println("����¶�");
	}
	
	/***
	 * ��ӵ�����
	 * @param value ����
	 * @param username	�û���
	 * @throws SQLException 
	 */
	public static void SetLedValue(int value,String username) throws SQLException{
		
		Statement sql=getConnection().createStatement();
		if(value==1||value==2){
			sql.execute("delete from  smarthome.led");
			sql.execute("insert into smarthome.led(state,user) values("+value+",'"+username+"')");
			System.out.println("��ӵ�״̬");
		}else{
			System.out.println("��״ֻ̬��Ϊ1��2");
		}
		

	}
	
	
	
	/**
	 * ��ѯTemperature
	 * @param value
	 * @throws SQLException
	 */
	public static ArrayList queryTemperatureValue(String username) throws SQLException{
		ArrayList a=new  ArrayList();
		Statement sql=getConnection().createStatement();
		ResultSet rs=null;
		rs=sql.executeQuery("SELECT * FROM smarthome.senor_temperature where user='"+username+"' order by createtime desc");
		
		int row=0;
		while(rs.next()){
			DataBean d=new DataBean();
			d.value=Integer.valueOf(rs.getInt("value")).intValue();
			d.time=Timestamp.valueOf(rs.getString("createtime"));
			a.add(row++,d);
		} 
		System.out.println("��ѯ�¶�"+a.size());
		return a;
	}
	
	
	
	/**
	 * ��ѯCo2
	 * @param value
	 * @throws SQLException
	 */
	public static ArrayList queryCo2Value(String username) throws SQLException{
		ArrayList a=new  ArrayList();
		Statement sql=getConnection().createStatement();
		ResultSet rs=null;
		rs=sql.executeQuery("SELECT * FROM smarthome.senor_co2 where user='"+username+"' order by createtime desc");
		int row=0;
		while(rs.next()){
			DataBean d=new DataBean();
			d.value=Integer.valueOf(rs.getInt("value")).intValue();
			d.time=Timestamp.valueOf(rs.getString("createtime"));
			a.add(row++,d);
		} 
		System.out.println("��ѯCo2"+a.size());
		return a;
	}
	
	
	
	/**
	 * ��ѯ����
	 * @param value
	 * @throws SQLException
	 */
	public static ArrayList querysmogValue(String username) throws SQLException{
		ArrayList a=new  ArrayList();
		Statement sql=getConnection().createStatement();
		ResultSet rs=null;
		rs=sql.executeQuery("SELECT * FROM smarthome.senor_smog where user='"+username+"' order by createtime desc");
		
		int row=0;
		while(rs.next()){
			DataBean d=new DataBean();
			d.value=Integer.valueOf(rs.getInt("value")).intValue();
			d.time=Timestamp.valueOf(rs.getString("createtime"));
			a.add(row++,d);
		} 
		System.out.println("��ѯ����"+a.size());
		return a;
	}
	
	
	
	/**
	 * ��ѯ��״̬
	 * @param value
	 * @throws SQLException
	 */
	public static ArrayList queryLed(String username) throws SQLException{
		ArrayList a=new  ArrayList();
		Statement sql=getConnection().createStatement();
		ResultSet rs=null;
		rs=sql.executeQuery("SELECT * FROM smarthome.led where user='"+username+"'");
		
		int row=0;
		while(rs.next()){
			DataBean d=new DataBean();
			d.value=Integer.valueOf(rs.getInt("state")).intValue();
			a.add(row++,d);
		} 
		System.out.println("��ѯ��"+a.size());
		return a;
	}


	/***
	 * �ϴ�ָ��
	 * @param type
	 * @param state
	 * @throws SQLException
	 */
	
	public static void postCmd(String type,String state) throws SQLException{

		Statement sql=getConnection().createStatement();
		ResultSet rs=null;
		sql.execute("delete from  smarthome.cmd");
		sql.execute("insert into smarthome.cmd(type,state) values('"+type+"','"+state+"')");
		
		System.out.println("�������"+type+"  "+state);
	
	}
	/***
	 * ��ȡ
	 * @return
	 * @throws SQLException
	 */
	public static Cmd getCmd() throws SQLException{

		Statement sql=getConnection().createStatement();
		ResultSet rs=null;
		Cmd c=new Cmd();
		rs=sql.executeQuery("SELECT * FROM smarthome.cmd");
		while(rs.next()){
			c.setType(rs.getString("type"));
			c.setState(rs.getString("state"));
			c.setOver(rs.getInt("over"));
		}
		return c;
	
	}





	
}

