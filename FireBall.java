import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class FireBall extends JPanel {
    protected Point point;
    private int radius;
    private int speed;
    private int dx;
    private int dy;
    private ImageIcon image;
    private Ellipse2D.Double ball;

    public FireBall(Point p, int r, int s, int xD, int yD) {
        point = p;
        radius = r;
        speed = s;
        dx = xD;
        dy = yD;
        ball = new Ellipse2D.Double(point.x - radius / 2.0, point.y - radius / 2.0, radius, radius);

        image = new ImageIcon(getClass().getResource("/Graphical-Assets/Fireball.png"));
    }

    public void moveBall() {
        point.translate(speed * dx, speed * dy);
        ball.x = point.x - radius / 2.0;
        ball.y = point.y - radius / 2.0;
        repaint();
    }

    public void bounce() {
        dx = -dx;
        dy = -dy;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image.getImage(), (int) ball.x, (int) ball.y, (int) ball.width, (int) ball.height, this);
    }
}
