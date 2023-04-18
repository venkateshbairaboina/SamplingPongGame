import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class PongPanel extends JPanel implements Runnable{
    int GAME_WIDTH=1000;
    int GAME_HEIGHT=(int)(GAME_WIDTH*(0.555));

    int PADDLE_WIDTH=25;
    int PADDLE_HEIGHT=100;

    int BALL_WIDTH=20;
    Paddle Paddle1;
    Paddle Paddle2;

    Ball ball;
    Image image;
    Graphics graphics;
    Dimension screen_size=new Dimension(GAME_WIDTH,GAME_HEIGHT);

    Thread gameThread;


    PongPanel()
    {
        newPaddle();
        newBall();
        gameThread=new Thread(this);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        //run();

        gameThread.start();
        this.setPreferredSize(screen_size);
    }

    private void newBall()
    {
        Random random=new Random();
       ball= new Ball(GAME_WIDTH/2-BALL_WIDTH/2,random.nextInt(GAME_HEIGHT-BALL_WIDTH),BALL_WIDTH,BALL_WIDTH);
    }
    public void newPaddle()
    {
       Paddle1=new Paddle(0,GAME_HEIGHT/2-PADDLE_HEIGHT/2,PADDLE_WIDTH,PADDLE_HEIGHT,1 );
       Paddle2=new Paddle(GAME_WIDTH-PADDLE_WIDTH,GAME_HEIGHT/2-PADDLE_HEIGHT/2,PADDLE_WIDTH,PADDLE_HEIGHT,2);

    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        image=createImage(getWidth(),getHeight());
        graphics=image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);

    }

    /*int run() {

        while (true) {
          move();
          repaint();
        }
    }*/
    public void move()
    {
        ball.Move();
        Paddle1.move();
        Paddle2.move();
    }
    public void Collision()
    {
        if(ball.y<0)
        {
            ball.setYDirection(-ball.yVelocity);

        }
        if(ball.y>GAME_HEIGHT-BALL_WIDTH)
        {
            ball.setYDirection(-ball.yVelocity);

        }
        if(ball.intersects(Paddle1))
        {
          ball.setXDirection(-ball.xVelocity);
          ball.setYDirection(-ball.yVelocity);
        }
        if(ball.intersects(Paddle2))
        {
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(-ball.yVelocity);
        }
        if(ball.x<=0)
        {
          newBall();
          newPaddle();
          Score.player2++;

        }
        if(ball.x>=GAME_WIDTH-BALL_WIDTH)
        {
          newBall();
          newPaddle();
          Score.player1++;

        }
        if(Paddle1.y<0)
    {
        Paddle1.y=0;
    }
        if(Paddle1.y>GAME_HEIGHT-PADDLE_HEIGHT)
        {
            Paddle1.y=GAME_HEIGHT-PADDLE_HEIGHT;
        }
        if(Paddle2.y<0)
        {
            Paddle2.y=0;

        }
        if(Paddle2.y>=GAME_HEIGHT-PADDLE_HEIGHT)
        {
            Paddle2.y=GAME_HEIGHT-PADDLE_HEIGHT;
        }
    }
    private void draw(Graphics g){
       Paddle1.draw(g);
       Paddle2.draw(g);
       ball.draw(g);
    }

    @Override
    public void run() {
       long lastTime=System.nanoTime();
       double amountOfTicks=60.0;
       double ns=1000000000/amountOfTicks;
       double delta=0;
       while(true)
       {
           long now=System.nanoTime();
           delta+=(now-lastTime)/ns;
           if(delta>=1)
           {
               move();
               Collision();
               repaint();
               delta--;
           }
       }


    }
    public class AL  extends KeyAdapter
    {
        public void keyPressed(KeyEvent e)
        {
         Paddle1.KeyPressed(e);
         Paddle2.KeyPressed(e);
        }
        public void keyReleased(KeyEvent e)
        {
          Paddle1.KeyRealesed(e);
          Paddle2.KeyRealesed(e);
        }
    }



}
