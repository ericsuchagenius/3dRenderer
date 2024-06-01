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

        //updating the shapes
        g.drawString(currentTimeMillis()+"",20,20);
        Graphics2D g2d = (Graphics2D) g;
        g2d.clearRect(0, 0, 2000, 1200);

        for (threeDObj currObj : objects) {
            currObj.refreshPoly();
        }

        //drawing the shapes
        for(threeDObj currObj: objects){
            for(Polygon Poly: currObj.drawPoly){
                g.setColor(Color.BLACK);
                g.drawPolygon(Poly);// draw poly is the list that stores the polygons, and by using a index that is uniform with the input data and by sorting the distance withe the hash map and the list, we could achieve
                g.setColor(currObj.color); // to implement this method you need to finish the each shape distance calulation
                g.fillPolygon(Poly);
            }
        }
//    drawAxes(g2d);// random axis drawing function
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
            Renderer.viewFrom[0]-=15;
        }
        if (key == KeyEvent.VK_D) {
            Renderer.viewFrom[0]+=15;
        }
        if (key == KeyEvent.VK_W) {
            Renderer.viewFrom[1]+=15;
        }
        if (key == KeyEvent.VK_S) {
            Renderer.viewFrom[1]-=15;
        }
        if (key == KeyEvent.VK_UP) {
            Renderer.viewFrom[1]+=15;
        }
        if (key == KeyEvent.VK_DOWN) {
            Renderer.viewFrom[1]-=15;
        }
        refresh(); // Call refresh to repaint the panel
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used in this example
    }
}
