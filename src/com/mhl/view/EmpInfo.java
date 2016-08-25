package com.mhl.view;
//�������¹������
import com.mhl.tools.*;
import com.mhl.model.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.event.*;
public class EmpInfo extends JPanel implements ActionListener{
	//�������
	JPanel p1,p2,p3,p4,p5;
	JLabel p1_l1,p3_l1;
	JTextField p1_jtf;
	JButton p1_jb,p4_jb1,p4_jb2,p4_jb3,p4_jb4;
	JTable jtable;
	JScrollPane jsp;
	EmpModel em=null;
	public EmpInfo()
	{
		//�������
		
		//��
		p1=new JPanel(new FlowLayout(FlowLayout.CENTER));
		p1_l1=new JLabel("������������Ա���Ż�ְλ��:");
		p1_l1.setFont(MyTools.f1);
		p1_jtf=new JTextField(20);
		p1_jb=new JButton("��ѯ");
		p1_jb.addActionListener(this);
		p1_jb.setFont(MyTools.f4);
		p1.add(p1_l1);
		p1.add(p1_jtf);
		p1.add(p1_jb);
		
		
		//�м�
		
		p2=new JPanel(new BorderLayout());
		String []params={"1"};
		String sql="select empid,empname,sex,zhiwei,address,xl from rszl where 1=?";
		em=new EmpModel();
		em.query(sql, params);
		jtable= new JTable(em);
//		jtable.setModel(em);
		jsp=new JScrollPane(jtable);

		p2.add(jsp);
		
		//��
		p5=new JPanel(new BorderLayout());
		p3=new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		sql="select count(*) from rszl";
		em=new EmpModel();
		int sum=em.getNum(sql);
		
//		jtable.setModel(em);
		//System.out.print(sum);
		p3_l1=new JLabel("�ܼ�¼��"+sum+"��");
		
		//jsp=new JScrollPane(jtable);
		p3.add(p3_l1);
		p4=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p4_jb1=new JButton("��ϸ��Ϣ");
		p4_jb1.addActionListener(this);
		p4_jb1.setFont(MyTools.f4);
		p4_jb2=new JButton("���");
		p4_jb2.addActionListener(this);
		p4_jb2.setFont(MyTools.f4);
		p4_jb3=new JButton("�޸�");
		p4_jb3.addActionListener(this);
		p4_jb3.setFont(MyTools.f4);
		p4_jb4=new JButton("ɾ��");
		p4_jb4.addActionListener(this);
		p4_jb4.setFont(MyTools.f4);
		p4.add(p4_jb1);
		p4.add(p4_jb2);
		p4.add(p4_jb3);
		p4.add(p4_jb4);
		p5.add(p3,"West");
		p5.add(p4,"East");
		//this.setBackground(Color.blue);
		this.setLayout(new BorderLayout());
		this.add(p1,"North");
		this.add(p2,"Center");
		this.add(p5,"South");
		this.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO �Զ����ɵķ������
		
		if(arg0.getSource().equals(p1_jb))
		{
			
			if(p1_jtf.getText().trim().equals(""))
			{
				String[]params={"1"};
				String sql="select empid,empname,sex,zhiwei,address,xl from rszl where 1=?";
				em=new EmpModel();
				em.query(sql, params);
				jtable.setModel(em);
			}
			else{
			String params[]={p1_jtf.getText().trim(),p1_jtf.getText().trim()};
			String sql="select empid,empname,sex,zhiwei,address,xl from rszl where empid=? or empname=?";
			em=new EmpModel();
			em.query(sql, params);
			
			jtable.setModel(em);
			}
		}
		else if(arg0.getSource().equals(p4_jb1))
		{
			int rowNum=this.jtable.getSelectedRow();
			//System.out.println(rowNum);
			if(rowNum==-1)
			{
				JOptionPane.showMessageDialog(this, "��ѡ��һ�У�");
			}
			
			String empId=(String)this.jtable.getValueAt(rowNum, 0);
			String sql="select*from rszl where empid=?";
			String []params={empId};
			em=new EmpModel();
			em.query(sql, params);
			jtable.setModel(em);
			
		}
		else if(arg0.getSource().equals(p4_jb2))
		{
			if(true)
			{
				new AddEmpDialog(this,"���",true);
			}
			
			String sql="select empid,empname,sex,zhiwei,address,xl from rszl where 1=?";
			String[]params={"1"};
			em=new EmpModel();
			em.query(sql, params);
			jtable.setModel(em);
			
		}
		else if(arg0.getSource().equals(p4_jb3))
		{
			
			
			int rowNum=this.jtable.getSelectedRow();
			if(rowNum==-1)
			{
				JOptionPane.showMessageDialog(this, "��ѡ��һ�У�");
			}
				
//			String empId=(String)this.jtable.getValueAt(rowNum, 0);
//			String []params={empId};
			else
			{
			new UpdEmpDialog(this,"�޸�",true,em,rowNum);
			}
			
			String[]params={"1"};
			String sql="select empid,empname,sex,zhiwei,address,xl from rszl where 1=?";
			em=new EmpModel();
			em.query(sql, params);
			jtable.setModel(em);
		}
		//18854198236
		else if(arg0.getSource().equals(p4_jb4))
		{
			int rowNum=this.jtable.getSelectedRow();
			
			if(rowNum==-1)
			{
				JOptionPane.showMessageDialog(this, "��ѡ��һ�У�");
			}
			else{	
			String empId=(String)this.jtable.getValueAt(rowNum, 0);
			String sql="delete from rszl where empid=?";
			String []params={empId};
			JOptionPane.showMessageDialog(null, "��ϲ��ɾ���ɹ���");
			em=new EmpModel();
			em.UpdateModel(sql, params);
			}
			
			String sql="select empid,empname,sex,zhiwei,address,xl from rszl where 1=?";
			String[]params={"1"};
			em=new EmpModel();
			em.query(sql, params);
			jtable.setModel(em);
		}
	}
}
