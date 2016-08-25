package com.mhl.view;
import com.mhl.tools.*;
import com.mhl.view2.Window2;
import com.mhl.model.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.event.*;

public class UserLogin extends JDialog implements ActionListener{

	/**
	 * @param args
	 */
	JLabel jl1,jl2,jl3;
	JTextField jname;
	JPasswordField jpass;
	JButton jconfirm,jcancel;
	//Font f1=new Font("����", Font.PLAIN, 16);
	//Font f2=new Font("����", Font.ITALIC, 12);
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		UserLogin ul=new UserLogin();
	}
	public UserLogin(){
		Container ct=this.getContentPane();
		this.setLayout(null);
		jl1=new JLabel("�������û���:");
		jl1.setFont(MyTools.f1);
		jl1.setBounds(60, 190, 150, 30);
		ct.add(jl1);
		jname=new JTextField(20);
		jname.setFont(MyTools.f1);
		jname.setBounds(180, 190, 120, 30);
		jname.setBorder(BorderFactory.createLoweredBevelBorder());
		ct.add(jname);
		
		jl2=new JLabel("(�û�Id)");
		jl2.setFont(MyTools.f2);
		jl2.setForeground(Color.red);
		jl2.setBounds(100, 210, 100, 30);
		ct.add(jl2);
		
		jl3=new JLabel("�� ������ �� :");
		jl3.setFont(MyTools.f1);
		jl3.setBounds(60, 240, 150, 30);
		ct.add(jl3);
		jpass=new JPasswordField(20);
		jpass.setFont(MyTools.f1);
		jpass.setBounds(180, 240, 120, 30);
		jpass.setBorder(BorderFactory.createLoweredBevelBorder());
		ct.add(jpass);
		
		jconfirm=new JButton("ȷ��");
		jconfirm.addActionListener(this);
		jconfirm.setFont(MyTools.f1);
		jconfirm.setBounds(110, 300, 70, 30);
		ct.add(jconfirm);
		
		jcancel=new JButton("ȡ��");
		jcancel.addActionListener(this);
		jcancel.setFont(MyTools.f1);
		jcancel.setBounds(210, 300, 70, 30);
		ct.add(jcancel);
		BackImage bi=new BackImage();
		bi.setBounds(0, 0, 360, 360);
		//this.add(bi);
		ct.add(bi);
		this.setUndecorated(true);
		this.setSize(360, 360);
		int width=Toolkit.getDefaultToolkit().getScreenSize().width;
		int height=Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width/2-200, height/2-200);
		this.setVisible(true);

	}
//	�ڲ��� ��ͼƬ
	public class BackImage extends JPanel{
		Image img;
		public BackImage()
		{
			try {
				img=ImageIO.read(new File("image//login.gif"));
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		public void paintComponent(Graphics g)
		{
			g.drawImage(img, 0, 0, 360, 360,this);
		}
	}
	//��Ӧ��¼����
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		
		if(e.getSource()==jconfirm)
		{
			String uid=this.jname.getText().trim();
			String p=new String(this.jpass.getPassword());
			UserModel um=new UserModel();
			String zhiwei=new String (um.checkUser(uid, p).trim());
			String empname=um.getNameById(uid);
//			String b=zhiwei;
			
//			System.out.println(b);
//			System.out.println(a);
//			System.out.println((boolean)(a==b));
			if(empname!=null){
			if("����".equals(zhiwei)||zhiwei.equals("�ϰ�")||zhiwei.equals("����"))
			{
				
				new Window1();
				
				//this.hide();
				String welcome="��ӭ��--"+zhiwei;
				JOptionPane.showMessageDialog(this, welcome);
				this.dispose();
			}
			else if("����".equals(zhiwei)||zhiwei.equals("���")||zhiwei.equals("����"))
			{
				Window2 w2=new Window2(empname,zhiwei);
				Thread a=new Thread(w2);
				a.start();
				//this.hide();
				String welcome="��ӭ��--"+zhiwei;
				JOptionPane.showMessageDialog(this, welcome);
				this.dispose();
				
			}
			else 
			{
				JOptionPane.showMessageDialog(this, "�Բ�������Ȩ�޲����޷���½��");
			}
			
			}
		}
		else if(e.getSource()==jcancel)
			
		{
			
			//�����ȡ����ťʱ���رյ�½���˳�ϵͳ
			this.dispose();
		}
		
	}

}
