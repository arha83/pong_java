// importing packages (aka libraries)
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;
// importing other packages (other libraries)
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
// window class
// develop the project by changing this class
public class SimpleGameWindow {
    // window related objects
    private JFrame frame;
    private JPanel panel;
    private Image image;
    // window variables
    private int width;
    private int height;
    // a que for storing pressed keys
    private Queue<Character> keyQueue;
    // class constructor
    // this method is run when a new instance of this class is created
    // parameters:
    //   width      -> desired window width in pixels
    //   height     -> desired window height in pixels
    //   title      -> window title that appears on top bar
    public SimpleGameWindow(int width, int height, String title){
        // setting size variables for futures uses
        this.height = height;
        this.width = width;
        // initializing the que
        keyQueue = new LinkedList<>();
        // some giberrish about initializing the window and frame and blah blah blah....
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        frame = new JFrame(title);
        panel = new JPanel(true) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, null); //TODO: Attend null observer later
            }
        };
        frame.setSize(width, height);
        panel.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        frame.setFocusable(false);
        panel.setFocusable(true);
        panel.requestFocus();
        // other gibberish about setting key listener and etc...
        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyPressed(KeyEvent arg0) {
            }

            @Override
            public void keyReleased(KeyEvent arg0) {
            }

            @Override
            public void keyTyped(KeyEvent ke) {
                keyQueue.add(ke.getKeyChar());
            }
        };
        panel.addKeyListener(keyListener);
        // showing the window
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.setVisible(true);
            }
        });
    }
    // this method draws a complex circle
    // parameters:
    //   x           -> circle center x
    //   y           -> circle center y
    //   r           -> circle radius
    //   colour      -> circle color object (not colour 😒)
    public void drawSimpleCircle(int x, int y, int r, Color colour)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Graphics2D g2d = (Graphics2D) image.getGraphics();
                g2d.setColor(colour);
                g2d.fillOval(x - r, y - r, r, r);
                panel.repaint();
            }
        });
    }
    // this method draws a filled rectangle
    // parameters:
    //   x           -> rectangle upper left corner x
    //   y           -> rectangle upper left corner y
    //   w           -> rectangle width (x axis)
    //   h           -> rectangle height (y axis)
    //   color       -> circle color object
    public void drawFillRectangle(int x, int y, int w, int h, Color color){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Graphics2D g2d = (Graphics2D) image.getGraphics();
                g2d.setColor(color);
                g2d.fillRect(x, y, w, h);
                panel.repaint();
            }
        });
    }
    // this method draws a hollow rectangle
    // parameters:
    //   x           -> rectangle upper left corner x
    //   y           -> rectangle upper left corner y
    //   w           -> rectangle width (x axis)
    //   h           -> rectangle height (y axis)
    //   color       -> circle color object
    public void drawRectangle(int x, int y, int w, int h, Color color){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Graphics2D g2d = (Graphics2D) image.getGraphics();
                g2d.setColor(color);
                g2d.drawRect(x, y, w, h);
                panel.repaint();
            }
        });
    }
    // this method draws a line
    // parameters:
    //   x1          -> rectangle upper left corner x
    //   y1          -> rectangle upper left corner y
    //   x2          -> rectangle width (x axis)
    //   y2          -> rectangle height (y axis)
    //   color       -> circle color object
    public void drawLine(int x1, int y1, int x2, int y2, Color color){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Graphics2D g2d = (Graphics2D) image.getGraphics();
                g2d.setColor(color);
                g2d.drawLine(x1, y1, x2, y2);
                panel.repaint();
            }
        });
    }
    // this method clears the frame
    public void clear() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Graphics2D g2d = (Graphics2D) image.getGraphics();
                g2d.clearRect(0, 0, width, height);
                panel.repaint();
            }
        });
    }
    // this method gets a key from user (like getch() in c++ but worse)
    public Character getKey() {
        if (!keyQueue.isEmpty())
            return keyQueue.poll();
        return null;
    }
}
