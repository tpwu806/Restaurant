package com.mhl.view;
import java.awt.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JDialog;

import com.mhl.model.*;
import com.mhl.tools.AePlayWave;
import com.mhl.tools.MyTools;

import java.awt.event.*;
//��ӽ���
public class AddLoginDialog extends JDialog implements ActionListener{

	JPanel p1,p2,p3;
	JLabel jl1,jl2;
	JTextField jtf1,jtf2;
	JButton jb1,jb2;
	String soundpath=null;
	public AddLoginDialog(EmpLogin empInfo,String title,boolean model)
	{
		//super(empInfo,title,model);
		p1=new JPanel(new GridLayout(2,1));
		jl1=new JLabel("empid");
		jl2=new JLabel("passwd");
		
		p1.add(jl1);
		p1.add(jl2);
		p2=new JPanel(new GridLayout(2,1));
		jtf1=new JTextField();
		jtf2=new JTextField();
		
		p2.add(jtf1);
		p2.add(jtf2);
		
		p3=new JPanel(new FlowLayout(FlowLayout.CENTER));
		jb1=new JButton("���");
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
		

		this.setSize(400,150);

		this.setTitle("��ӵ�½��Ա");
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO �Զ����ɵķ������
		if(arg0.getSource()==jb1)
		{
			String sql="insert into login values(?,?)";
			String []params={jtf1.getText(),jtf2.getText()};
			EmpModel em=new EmpModel();
			if(!em.UpdateModel(sql, params))
			{
				JOptionPane.showMessageDialog(null, "���ʧ�ܣ���������ȷ��������!");
			}
			JOptionPane.showMessageDialog(null, "��ϲ����ӳɹ���");
			
			
			soundpath=new OperatChoose().getSoundpath();
			System.out.println(soundpath);
			
			AePlayWave apw= new AePlayWave(soundpath);
			 apw.start();
			this.dispose();
		}
		else if(arg0.getSource()==jb2)
		{
			this.dispose();
		}
	}
	
	
	
	
}
