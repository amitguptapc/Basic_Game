package Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

@SuppressWarnings("Duplicates")
public class Demo implements KeyListener, MouseListener {
    private static int yVel = 0;
    private static int yAcc=0;
    private static int yCord=0;
    private static AudioClip jumpAudio;
    private static boolean crashed=false;
    private static boolean paused=false;
    private static int crashCount=0;
    private static int score=0;
    private static JPanel p;
    public static void main(String[] args) {
        JFrame f = new JFrame();
        p = new JPanel();
        p.setPreferredSize(new Dimension(800, 450));
        f.add(p);
        f.pack();
        p.setFocusable(true);
        p.requestFocus();
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setVisible(true);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Demo g1;
        g1 = new Demo();
        p.addKeyListener(g1);
        p.addMouseListener(g1);
        Graphics g = p.getGraphics();
        g.setColor(Color.CYAN);
        Demo.jumpAudio= Applet.newAudioClip(Demo.class.getResource("../sounds/onjump.wav"));
        AudioClip hitAudio= Applet.newAudioClip(Demo.class.getResource("../sounds/hit.wav"));
        Image i1 = null;
        Image i2 = null;
        Image i3 = null;
        Image i4 = null;
        Image i5 = null;
        Image grass = null;
        Image jumpImage = null;
        Image block1Image=null;
        Image block2Image=null;
        Image block3Image=null;
        Image block4Image=null;
        Image block5Image=null;
        Image pauseImage=null;
        Image playImage=null;

        try {
            i1 = ImageIO.read(Demo.class.getResource("../images/run_anim1.png"));
            i2 = ImageIO.read(Demo.class.getResource("../images/run_anim2.png"));
            i3 = ImageIO.read(Demo.class.getResource("../images/run_anim3.png"));
            i4 = ImageIO.read(Demo.class.getResource("../images/run_anim4.png"));
            i5 = ImageIO.read(Demo.class.getResource("../images/run_anim5.png"));
            jumpImage = ImageIO.read(Demo.class.getResource("../images/jump.png"));
            grass = ImageIO.read(Demo.class.getResource("../images/grass.png"));
            block1Image = ImageIO.read(Demo.class.getResource("../images/block.png"));
            block2Image = ImageIO.read(Demo.class.getResource("../images/block.png"));
            block3Image = ImageIO.read(Demo.class.getResource("../images/block.png"));
            block4Image = ImageIO.read(Demo.class.getResource("../images/block.png"));
            block5Image = ImageIO.read(Demo.class.getResource("../images/block.png"));
            playImage = ImageIO.read(Demo.class.getResource("../images/play.png"));
            pauseImage = ImageIO.read(Demo.class.getResource("../images/pause.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image ar[] = {i1, i2, i3, i4, i5,i4,i3,i2};
        Image playerImage;
        int count = 0;
        Demo.yCord = 308;
        int block1XCord=900;
        int block2XCord=1100;
        int block3XCord=1300;
        int block4XCord=1500;
        int block5XCord=1700;
        Rectangle playerRect=new Rectangle(72,97);
        Rectangle block1Rect=new Rectangle(20,50);
        Rectangle block2Rect=new Rectangle(20,50);
        Rectangle block3Rect=new Rectangle(20,50);
        Rectangle block4Rect=new Rectangle(20,50);
        Rectangle block5Rect=new Rectangle(20,50);
        int playerXCord=364;
        block1Rect.y=355;
        block2Rect.y=355;
        block3Rect.y=355;
        block4Rect.y=355;
        block5Rect.y=355;
        boolean block1Visible=true;
        boolean block2Visible=true;
        boolean block3Visible=true;
        boolean block4Visible=true;
        boolean block5Visible=true;
        g.setColor(Color.WHITE);
        g.clearRect(0, 0, 800, 450);
        while (true) {
            try {
                Thread.sleep(70);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(Demo.paused){
                g.setColor(Color.RED);
                g.setFont(new Font("Kristen ITC",Font.BOLD,24));
                g.drawString("Game paused",300,200);
                g.drawImage(playImage,20,20,null);
                continue;
            }

            if (Demo.crashCount == 1 && Demo.crashed) {
                g.setColor(Color.RED);
                g.setFont(new Font("Kristen ITC",Font.BOLD,24));
                g.drawString("Game Over. Press ENTER to restart", 180, 200);
                continue;
            }
            if (Demo.crashCount == 2 && !Demo.crashed) {
                block1XCord=900;
                block2XCord=1100;
                block3XCord=1300;
                block4XCord=1500;
                block5XCord=1700;
                block1Visible=true;
                block2Visible=true;
                block3Visible=true;
                block4Visible=true;
                block5Visible=true;
                Demo.yCord=305;
                playerXCord=364;
                Demo.score=0;
                Demo.crashCount = 1;
            }


            count = (count + 1) % 8;
            Demo.yCord+=Demo.yVel;
            Demo.yVel+=Demo.yAcc;
            playerImage=ar[count];

            if(Demo.yCord>=308)
            {
                Demo.yCord=308;
                Demo.yVel=0;
            }
            g.clearRect(0, 0, 800, 500);
            block1XCord-=15;
            block2XCord-=15;
            block3XCord-=15;
            block4XCord-=15;
            block5XCord-=15;
            if(!Demo.paused)
                g.drawImage(pauseImage,20,20,null);
            if(block1XCord<=-20)
            {
                if(block1Visible)
                    Demo.score+=10;
                block1XCord=980;
                block1Visible=true;
            }
            if(block2XCord<=-20)
            {
                if(block2Visible)
                    Demo.score+=10;
                block2XCord=980;
                block2Visible=true;
            }
            if(block3XCord<=-20)
            {
                if(block3Visible)
                    Demo.score+=10;
                block3XCord=980;
                block3Visible=true;
            }
            if(block4XCord<=-20)
            {
                if(block4Visible)
                    Demo.score+=10;
                block4XCord=980;
                block4Visible=true;
            }
            if(block5XCord<=-20)
            {
                if(block5Visible)
                    Demo.score+=10;
                block5XCord=980;
                block5Visible=true;
            }
            playerRect.x=playerXCord;
            playerRect.y=Demo.yCord;
            block1Rect.x=block1XCord;
            block2Rect.x=block2XCord;
            block3Rect.x=block3XCord;
            block4Rect.x=block4XCord;
            block5Rect.x=block5XCord;

            if(playerRect.intersects(block1Rect)&&block1Visible){
                hitAudio.play();
                block1Visible=false;
                playerXCord-=100;
            }
            if(playerRect.intersects(block2Rect)&&block2Visible){
                hitAudio.play();
                block2Visible=false;
                playerXCord-=100;
            }
            if(playerRect.intersects(block3Rect)&&block3Visible){
                hitAudio.play();
                block3Visible=false;
                playerXCord-=100;
            }
            if(playerRect.intersects(block4Rect)&&block4Visible){
                hitAudio.play();
                block4Visible=false;
                playerXCord-=100;
            }
            if(playerRect.intersects(block5Rect)&&block5Visible){
                hitAudio.play();
                block5Visible=false;
                playerXCord-=100;
            }
            if(playerXCord<=0){
                Demo.crashed=true;
                Demo.crashCount=1;
            }


            if(Demo.yCord<308)
                playerImage=jumpImage;
            g.drawImage(grass, 0, 405, null);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Kristen ITC",Font.BOLD,18));
            g.drawString("Your Score : "+Demo.score,310,50);
            g.drawImage(playerImage, playerXCord, Demo.yCord, null);
            if(block1Visible)
                g.drawImage(block1Image, block1XCord,355 , null);
            if(block2Visible)
                g.drawImage(block2Image, block2XCord,355 , null);
            if(block3Visible)
                g.drawImage(block3Image, block3XCord,355 , null);
            if(block4Visible)
                g.drawImage(block4Image, block4XCord,355 , null);
            if(block5Visible)
                g.drawImage(block5Image, block5XCord,355 , null);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_SPACE&&Demo.yCord==308&&!Demo.paused&&!Demo.crashed){
            Demo.jumpAudio.play();
            Demo.yVel=-22;
            Demo.yAcc=4;
        }
        if(e.getKeyCode()==KeyEvent.VK_ENTER&&Demo.crashed){
            Demo.crashed = false;
            Demo.crashCount = 2;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getX()>=20&&e.getX()<=65&&e.getY()>=20&&e.getY()<=65&&!Demo.crashed)
        {
            Demo.paused=!Demo.paused;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}