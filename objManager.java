import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.awt.geom.Path2D;

import static java.lang.System.currentTimeMillis;

public class objManager extends JPanel implements KeyListener, ActionListener {
    int numObjects = 0;
    List<threeDObj> objects = new ArrayList<>();

    public objManager(Double[][][][] data, Color[] colors) {
        for (int i = 0; i < data.length; i++) {
            objects.add(new threeDObj(data[i], colors[i]));
        }
        setFocusable(true); // Ensure the panel is focusable to receive key events
        addKeyListener(this); // Add key listener to the panel
        numObjects = objects.size();
    }

    public void addObjects(Double[][][] object, Color color){
        objects.add(new threeDObj(object, color));
        numObjects ++;
    }

    public void refresh() {
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString(currentTimeMillis()+"",20,20);
        Graphics2D g2d = (Graphics2D) g;
        g2d.clearRect(0, 0, 2000, 1200);
        for (threeDObj currObj : objects) {
            currObj.computePoints();
            for (List<List<Double>> polygon : currObj.computedPoints) {

                //implementing double drawing
                Path2D path = new Path2D.Double();
                path.moveTo(polygon.get(0).get(0), polygon.get(0).get(1));
                for(int i = 1; i<polygon.size();i++){
                    path.lineTo(polygon.get(i).get(0), polygon.get(i).get(1));
                }
                path.closePath();
                g2d.setPaint(Color.BLACK);
                g2d.draw(path);
                g2d.setPaint(currObj.color);
                g2d.fill(path);
            }
        }
//    drawAxes(g2d);// random axis drawing function
    }
    private void drawAxes(Graphics2D g2) {
        double[][] axes = {
                {0, 0, 0}, {100, 0, 0},  // X axis from (0,0,0) to (100,0,0)
                {0, 0, 0}, {0, 100, 0},  // Y axis from (0,0,0) to (0,100,0)
                {0, 0, 0}, {0, 0, 100}   // Z axis from (0,0,0) to (0,0,100)
        };

        for (int i = 0; i < axes.length; i += 2) {
            double[] from = axes[i];
            double[] to = axes[i + 1];

            int x1 = (int) calculator.CalculatotposX(Renderer.viewFrom, Renderer.viewTo, from[0], from[1], from[2]);
            int y1 = (int) calculator.CalculatotposY(Renderer.viewFrom, Renderer.viewTo, from[0], from[1], from[2]);
            int x2 = (int) calculator.CalculatotposX(Renderer.viewFrom, Renderer.viewTo, to[0], to[1], to[2]);
            int y2 = (int) calculator.CalculatotposY(Renderer.viewFrom, Renderer.viewTo, to[0], to[1], to[2]);

            x1 = (int) reposition.repositon_2dX((double)x1);
            y1 = (int) reposition.repositon_2dX((double)y1);
            x2 = (int) reposition.repositon_2dX((double)x2);
            y2 = (int) reposition.repositon_2dX((double)y2);
            g2.drawLine(x1, y1, x2, y2);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Implement any required actions here
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Usually not used for movement
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
            Renderer.viewFrom[0]-=0.2;
        }
        if (key == KeyEvent.VK_D) {
            Renderer.viewFrom[0]+=0.2;
        }
        if (key == KeyEvent.VK_W) {
            Renderer.viewFrom[1]+=0.2;
        }
        if (key == KeyEvent.VK_S) {
            Renderer.viewFrom[1]-=0.2;
        }
        refresh(); // Call refresh to repaint the panel
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used in this example
    }
}
