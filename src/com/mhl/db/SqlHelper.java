//package com.mhl.db;
//import java.sql.*;
//public class SqlHelper {
//	Connection con=null;
//	PreparedStatement ps=null;
//	ResultSet rs=null;
//	public SqlHelper()
//	{
//		try {
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//			con=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=restaurant","sa","");
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
//	public ResultSet query(String sql,String []params)
//	{
//	try {
//			ps=con.prepareStatement(sql);
//		
//			for(int i=0;i<params.length;i++)
//			{
//			ps.setString(i+1, params[i]);
//			}
//			rs=ps.executeQuery();
//		} catch (SQLException e) {
//			// TODO �Զ����ɵ� catch ��
//			e.printStackTrace();
//		}
//	return rs;
//	}
//	public void close()
//	{
//	try{
//	if(rs!=null)
//	{
//	rs.close();
//	}
//	if(ps!=null)
//	{
//		ps.close();
//	}
//	if(con!=null)
//	{
//		con.close();
//	}
//	}
//	catch(Exception e)
//	{
//		e.printStackTrace();
//	}
//	}
//}
/*
 * �����ݿ��������
 * �����ݿ�Ĳ���������crud
 * ���ô洢����
 * 
 *ע�⣺����������ݿ�ʱ���������쳣���ʾδ��������JAR������������һ��ԭ�����SQL������﷨����
 *java.lang.ClassNotFoundException: com.microsoft.jdbc.sqlserver.SQLServerDviver
 * */
package com.mhl.db;
import java.sql.*;
public class SqlHelper {
	//������Ҫ�Ķ���
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url="jdbc:sqlserver://127.0.0.1:1499;databaseName=restaurant";
	String user="sa";
	String passwd="522300";
	int sum=0;
	//���캯������ʼ��ct
	public SqlHelper()
	{
		try {
			//��������
			Class.forName(driver);
			//�õ�����
			con=DriverManager.getConnection(url,user,passwd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//[]params��ͨ��?��ֵ��ʽ���Է�ֹ©��ע�뷽ʽ����֤��ȫ��
	//��¼��֤ ���ر�
	public ResultSet queryExecute(String sql,String []params)
	{
		try {
			ps=con.prepareStatement(sql);
			//��sql�Ĳ�����ֵ
			for(int i=0;i<params.length;i++)
			{
				ps.setString(i+1, params[i]);
			}
			//ִ�в�ѯ
			rs=ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		//���ؽ����
		return rs;
	}
	//�鿴�ж��ټ�¼
	public int queryExecute(String sql)
	{
		try {
			ps=con.prepareStatement(sql);
			
			rs=ps.executeQuery();
			if(rs.next())
			{
				sum=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		//���ؽ����
		return sum;
	}
	//��ɾ��
	public boolean updateExecete(String sql,String []params)
	{
		boolean b=true;
		try {
			ps=con.prepareStatement(sql);
			//��sql�Ĳ�����ֵ
			for(int i=0;i<params.length;i++)
			{
				ps.setString(i+1, params[i]);
			}
			//ִ�в�ѯ
			if(ps.executeUpdate()!=1)
			{
				b=false;
			}
		} catch (Exception e) {
			b=false;
			e.printStackTrace();
			// TODO: handle exception
		}
		finally
		{
			this.close();
		}
		//���ؽ����
		return b;
		
	}
	//�ر���Դ�ķ���
	public void close()
	{
		try {
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(con!=null) con.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}
