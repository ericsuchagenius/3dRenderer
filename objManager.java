import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
//import java.awt.geom.Path2D;

import static java.lang.System.currentTimeMillis;

public class objManager extends JPanel implements KeyListener, ActionListener {
    int numObjects = 0;
    List<threeDObj> objects = new ArrayList<>();
    Boolean[] keyEval = new Boolean[8];

    public objManager(Double[][][][] data, Color[] colors) {
        for (int i = 0; i < data.length; i++) {
            objects.add(new threeDObj(data[i], colors[i]));
        }
        setFocusable(true); // Ensure the panel is focusable to receive key events
        addKeyListener(this); // Add key listener to the panel
        numObjects = objects.size();

        Arrays.fill(keyEval, false);// to make every element false for no bugs
    }

    public void addObjects(Double[][][] object, Color color){
        objects.add(new threeDObj(object, color));
        numObjects ++;
    }

    public void refresh() {
        control();
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
    }

    public void control(){
        double[] ViewTo = Renderer.viewTo;
        double[] ViewFrom = Renderer.viewFrom;

        Vector ViewVector = new Vector(ViewTo[0] - ViewFrom[0], ViewTo[1] - ViewFrom[1], ViewTo[2] - ViewFrom[2]);
        if(keyEval[2]){
            Renderer.viewTo[0] += ViewVector.x;
            Renderer.viewTo[1] += ViewVector.y;
            Renderer.viewTo[2] += ViewVector.z;
            Renderer.viewFrom[0] += ViewVector.x;
            Renderer.viewFrom[1] += ViewVector.y;
            Renderer.viewFrom[2] += ViewVector.z;
        }
        if(keyEval[3]){
            Renderer.viewTo[0] -= ViewVector.x;
            Renderer.viewTo[1] -= ViewVector.y;
            Renderer.viewTo[2] -= ViewVector.z;
            Renderer.viewFrom[0] -= ViewVector.x;
            Renderer.viewFrom[1] -= ViewVector.y;
            Renderer.viewFrom[2] -= ViewVector.z;
        }

        Vector verticalVector = new Vector(0,0,1);
        Vector sideViewVector = ViewVector.CrossProduct(verticalVector);
        if(keyEval[1]){
            Renderer.viewTo[0] += sideViewVector.x;
            Renderer.viewTo[1] += sideViewVector.y;
            Renderer.viewTo[2] += sideViewVector.z;
            Renderer.viewFrom[0] += sideViewVector.x;
            Renderer.viewFrom[1] += sideViewVector.y;
            Renderer.viewFrom[2] += sideViewVector.z;
        }
        if(keyEval[0]){
            Renderer.viewTo[0] -= sideViewVector.x;
            Renderer.viewTo[1] -= sideViewVector.y;
            Renderer.viewTo[2] -= sideViewVector.z;
            Renderer.viewFrom[0] -= sideViewVector.x;
            Renderer.viewFrom[1] -= sideViewVector.y;
            Renderer.viewFrom[2] -= sideViewVector.z;
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
        if (e.getKeyCode() == KeyEvent.VK_A) {
            keyEval[0] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            keyEval[1] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            keyEval[2] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            keyEval[3] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            keyEval[4] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            keyEval[5] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            keyEval[6] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            keyEval[7] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            keyEval[0] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            keyEval[1] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            keyEval[2] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            keyEval[3] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            keyEval[4] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            keyEval[5] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            keyEval[6] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            keyEval[7] = false;
        }
    }
}
