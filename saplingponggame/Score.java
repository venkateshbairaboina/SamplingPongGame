import javax.xml.namespace.QName;
import java.awt.*;

public class Score extends Rectangle {
    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    static int player1;
    static int player2;
    Score(int GAME_WIDTH,int GAME_HEIGHT)
    {
        Score.GAME_HEIGHT=GAME_HEIGHT;
        Score.GAME_WIDTH=GAME_WIDTH;
    }
    public void draw(Graphics g)
    {
        g.setColor(Color.white);
        g.setFont(new Font( "Consulas",Font.PLAIN, 60 ));
        g.drawString(String.valueOf(player1/10)+String.valueOf(player1%10),GAME_WIDTH/2-80,100);
        g.drawString(String.valueOf(player2/10)+String.valueOf(player2%10),GAME_WIDTH/2-80,100);
    }




}
