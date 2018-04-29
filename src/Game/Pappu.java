package Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class Pappu implements KeyListener {
  private static int yCord=300;
  private static int yVel=0;
  private static int yAcc=0;
  public static void main(String[] args) {
    JFrame frame=new JFrame();
    JPanel panel=new JPanel();
    panel.setPreferredSize(new Dimension(1000,500));
    frame.add(panel);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    Graphics g=panel.getGraphics();
    panel.setFocusable(true);
    panel.requestFocus();
    Pappu p=new Pappu();
    panel.addKeyListener(p);
    Image back=null;
    Image p1=null;
    Image p2=null;
    Image p3=null;
    Image p4=null;
    Image p5=null;
    Image p6=null;
    Image p7=null;
    Image p8=null;
    Image fork=null;
    try {
      back=ImageIO.read(Pappu.class.getResource("../images/back.png"));
      p1=ImageIO.read(Pappu.class.getResource("../images/pappu_1.png"));
      p2=ImageIO.read(Pappu.class.getResource("../images/pappu_2.png"));
      p3=ImageIO.read(Pappu.class.getResource("../images/pappu_3.png"));
      p4=ImageIO.read(Pappu.class.getResource("../images/pappu_4.png"));
      p5=ImageIO.read(Pappu.class.getResource("../images/pappu_5.png"));
      p6=ImageIO.read(Pappu.class.getResource("../images/pappu_6.png"));
      p7=ImageIO.read(Pappu.class.getResource("../images/pappu_7.png"));
      p8=ImageIO.read(Pappu.class.getResource("../images/pappu_8.png"));
      fork=ImageIO.read(Pappu.class.getResource("../images/fork_handle.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    int count=0;
    int xVal=1100;
    Image imageArray[]={p1,p2,p3,p4,p5,p6,p7,p8};
    while(true) {
      Pappu.yCord+=Pappu.yVel;
      Pappu.yVel+=Pappu.yAcc;
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      if(Pappu.yCord>450)
      { Pappu.yVel=0;
      Pappu.yCord=450;}
      if(Pappu.yCord<0){
        Pappu.yCord=0;
        Pappu.yVel=Pappu.yVel*-1;
      }
      xVal=xVal-15;
      if(xVal<0)
        xVal=1100;
      count=(count+1)%8;
      g.drawImage(back, 0, 0, null);
      g.drawImage(fork, xVal, 0, null);
      g.drawImage(imageArray[count],100,Pappu.yCord,null);
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {
    if(e.getKeyCode()==KeyEvent.VK_SPACE){
      Pappu.yVel=-30;
      Pappu.yAcc=8;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {

  }
}