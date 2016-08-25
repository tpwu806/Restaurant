package com.mhl.tools;
import java.awt.*; 
import java.io.*; 
import java.util.*; 
import javax.media.*; 
import javax.media.format.*; 
import javax.swing.*; 

public class VideoPlayer implements ControllerListener { 

   Vector audioCapDevList = null; 
   Vector videoCapDevList = null; 
   CaptureDeviceInfo audioCapDevInfo = null; 
   CaptureDeviceInfo videoCapDevInfo = null; 
   MediaLocator audioCapDevLoc = null; 
   MediaLocator videoCapDevLoc = null; 

   Player audioPlayer; 
   Player videoPlayer; 

   public void initAudioCapDevLoc() { 
       //���������д��������Ƶ�����ʽ�������뿴AudioFormat�� 
       audioCapDevList = CaptureDeviceManager.getDeviceList(new 
AudioFormat( 
               AudioFormat.LINEAR)); 

       if ((audioCapDevList.size() > 0)) { 
           //�����м���CaptureDevice,����ȡ��һ�� 
           audioCapDevInfo = (CaptureDeviceInfo) 
audioCapDevList.elementAt(0); 
           audioCapDevLoc = audioCapDevInfo.getLocator(); 
       } else { 
           System.out.println("�Ҳ�����Ƶ�ɼ��豸"); 
           System.exit(0); 
       } 

   } 

   public void initVideoCapDevLoc() { 
       //���������д�����ı�����Ƶ��ʽ�������뿴VideoFormat�� 
       videoCapDevList = CaptureDeviceManager.getDeviceList(new 
VideoFormat( 
               VideoFormat.YUV)); 

       if ((videoCapDevList.size() > 0)) {              //�����м���CaptureDevice,����ȡ��һ�� 
           videoCapDevInfo = (CaptureDeviceInfo) 
videoCapDevList.elementAt(0); 
           videoCapDevLoc = videoCapDevInfo.getLocator(); 
       } else { 
           System.out.println("�Ҳ�����Ƶ�ɼ��豸"); 
           System.exit(0); 
       } 

   } 

   public void initAudioPlayer() { 

       try { 
           audioPlayer = Manager.createPlayer(audioCapDevLoc); 
       } catch (NoPlayerException ex) { 
       } catch (IOException ex) { 
       }         
       //����һ��������������player״̬�ĸı� 
       audioPlayer.addControllerListener(this); 
       audioPlayer.realize(); 
   } 

   public void initVideoPlayer() { 
       try { 
           videoPlayer = Manager.createPlayer(videoCapDevLoc); 
       } catch (NoPlayerException ex) { 
       } catch (IOException ex) { 
       } 
       //����һ��������������player״̬�ĸı� 
       videoPlayer.addControllerListener(this); 
       videoPlayer.realize(); 
   } 

   public void stopAndClosePlayer(){ 
       audioPlayer.stop(); 
       audioPlayer.close(); 
       videoPlayer.stop(); 
       videoPlayer.close(); 
   } 

   public synchronized void controllerUpdate(ControllerEvent ce) { 
       Player p = (Player) ce.getSourceController(); 
         JFrame jFrame = new JFrame(); 
       Component com; 

       if (p == null) { 
           return; 
       } 

       //���player��״̬��ΪRealized 
       if (ce instanceof RealizeCompleteEvent) { 

           if ((com = p.getControlPanelComponent()) != null) { 
               jFrame.add(com, BorderLayout.SOUTH); 
           } 

           if ((com = p.getVisualComponent()) != null) { 
               jFrame.add(com, BorderLayout.NORTH); 
           } 

           jFrame.setVisible(true); 
           jFrame.pack(); 
           jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
           p.start(); 
       } 
   } 

   public static void main(String args[]) { 
    VideoPlayer cap = new VideoPlayer(); 
       cap.initAudioCapDevLoc(); 
       cap.initVideoCapDevLoc(); 
       cap.initAudioPlayer(); 
       cap.initVideoPlayer(); 

       try { 
           Thread.currentThread().sleep(20000);//20���ֹͣ���رղ����� 
       } catch (InterruptedException ex) { 
       } 
       cap.stopAndClosePlayer(); 
   } 
} 
