package com.mhl.view2;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JDialog;
import java.util.Calendar;
import java.util.Vector;

import com.mhl.model.*;
import com.mhl.tools.MyTools;

import java.awt.event.*;
//��ӽ���
public class DelOrdered extends JDialog implements ActionListener{

	JPanel p1,p2,p3;
	JLabel jl1;
	JTextField jtf1;
	JButton jb1,jb2;
	
	public DelOrdered()
	{
		super();
		p1=new JPanel(new GridLayout(1,1));
		
		jl1=new JLabel("deskid");
		p1.add(jl1);
		
		p2=new JPanel(new GridLayout(1,1));
		jtf1=new JTextField();
		
		
		p2.add(jtf1);
	
		
		p3=new JPanel(new FlowLayout(FlowLayout.CENTER));
		jb1=new JButton("ȷ��");
		jb1.setFont(MyTools.f4);
		jb1.addActionListener(this);
		jb2=new JButton("ȡ��");
		jb2.setFont(MyTools.f4);
		jb2.addActionListener(this);
		p3.add(jb1);
		p3.add(jb2);
		this.setLayout(new BorderLayout());
		this.add(p1,"West");
		this.add(p2,"Center");
		this.add(p3,"South");
		

		this.setSize(200,100);

		this.setTitle("�˶���λ");
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO �Զ����ɵķ������
		if(arg0.getSource()==jb1)
		{
			
			OrderModel om=new OrderModel();
			
			String status=om.geStatusById(jtf1.getText());
//			System.out.print(status);
			if((0+"").equals(status))
			{
				JOptionPane.showMessageDialog(null, "�Բ��𣬸ò������ھͲͣ�");
			}
			else if((1+"").equals(status))
			{
				String sql="delete from orderdesk where deskid=? ";
				String []params={jtf1.getText()};
				OrderModel em=new OrderModel();
				em.UpdateModel(sql, params);
				String sql2="update status set deskstatus=2 where deskid=?";
				em.UpdateModel(sql2, params);
				JOptionPane.showMessageDialog(null, "��ϲ���˶��ɹ���");
			}
			else if((2+"").equals(status))
			{
				JOptionPane.showMessageDialog(null , "�Բ��𣬸ò�����δԤ����");
			}
			
			this.dispose();
		}
		else if(arg0.getSource()==jb2)
		{
			this.dispose();
		}
	}
	
	
	
	
}
