/*
 * ���ܣ�ʵ����������
 * ���ڣ�2010.10.11
 * */
package com.mhl.view;
import java.awt.*;
import javax.swing.*;
public class Index extends JWindow implements Runnable{
	//�������������ص����
	JProgressBar jpb;//���������
	JLabel jl1;//�����ڴ���ı�����һ��ͼƬ���ϲ��ǽ�����
	int width,height;//���ڻ�ȡ��ʾ���ֱ��ʴ�С
	public static void main(String []args){
		Index index=new Index();
		//����index�߳�
		Thread t=new Thread(index);
		//�����߳�
		t.start();
	}
	//���캯��
	public Index()
	{	
		//������ǩ,���ڱ�ǩ�Ϸ���һ��ͼƬ
		jl1=new JLabel(new ImageIcon("image/index/index.gif"));
		
		//����������
		jpb=new JProgressBar();
		//���ý���������
		jpb.setStringPainted(true);//��ʾ��ǰ����ֵ��Ϣ
		jpb.setIndeterminate(false);//ȷ��������ִ����ɺ����ع���
		jpb.setBorderPainted(false);//���ý������߿���ʾ
		jpb.setBackground(Color.pink);//���ý������ı���ɫ
		
		//������
		this.add(jl1,BorderLayout.NORTH);
		this.add(jpb,BorderLayout.SOUTH);
		
		//���ô�������
		this.setSize(400,263);
		//���ô�����ʾ��λ��
		width=Toolkit.getDefaultToolkit().getScreenSize().width;
		height=Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width/2-200,height/2-150);
		//���ô�����ʾ
		this.setVisible(true);
	}
	//�������̵߳�run���������������̵߳�����
//	class paint extends JPanel implements Runnable{
//		Thread t;
//		int x=10;
//		int i=0,j=40,u=10;
//		String gg[]={"ϵ","ͳ","��","��","��","��","��","��","��"};
//		int k=0,tt=0;
//		String shi[]={"��","��","¥","��","��","��","��","��","��","��","��","��","��","ζ"};
//	}
	public void run() {

		//����һ�����飬��Ž�������ʾʱ��Ҫ������
		int []progressValue={0,1,5,8,14,17,26,35,38,43,49,56,65,71,75,78,86,94,98,99,100};
		for(int i=0;i<progressValue.length;i++)
		{
			try {
				//����1�룬��ִ��
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jpb.setValue(progressValue[i]);//ȡ�������еĽ���ֵ
		}
		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//��������ɺ�ִ����Ӧ�Ĳ��������л��������Ĵ��ڣ�ͬʱ�رս��������ڵ�
		new UserLogin();
		//�رս���������
		this.dispose();
		break;
		}
	}
}