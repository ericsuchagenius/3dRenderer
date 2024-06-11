import javax.swing.*;
import java.awt.*;

public class Renderer extends JFrame {
    static Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
    static double[] viewFrom = {100,100,100};
    static double[] viewTo = {1,1,1.5};
    static Double[][][][] initializeData = {{
//            {{20.0,30.0,50.0},{100.0,20.0,60.0},{70.0,10.0,90.0}}
            {{0.0,0.0,0.0},{20.0,0.0,0.0},{20.0,20.0,0.0},{0.0,20.0,0.0}},
            {{0.0,0.0,30.0},{20.0,0.0,30.0},{20.0,20.0,30.0},{0.0,20.0,30.0}},
            {{0.0,0.0,0.0},{20.0,0.0,0.0},{20.0,0.0,30.0},{0.0,0.0,30.0}},
            {{0.0,20.0,0.0},{20.0,20.0,0.0},{20.0,20.0,30.0},{0.0,20.0,30.0}},
            {{0.0,0.0,0.0},{0.0,20.0,0.0},{0.0,20.0,30.0},{0.0,0.0,30.0}},
            {{20.0,0.0,0.0},{20.0,20.0,0.0},{20.0,20.0,30.0},{20.0,0.0,30.0}}
    }};
    static Color[] colors = {Color.gray};
    static objManager screenObject = new objManager(initializeData, colors);

    public Renderer() {
        setUndecorated(true);
        setSize(screenDim);
        setVisible(true);
        setLayout(new BorderLayout());
        add(screenObject, BorderLayout.CENTER);
        setFocusable(true);
        addKeyListener(screenObject); // Add key listener to the frame

//        Double[][][] planeData = {{{-40.0,-40.0,0.0},{40.0,-40.0,0.0},{40.0,40.0,0.0},{-40.0,40.0,0.0}}};
//        screenObject.addObjects(planeData,Color.LIGHT_GRAY);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Renderer frame = new Renderer();
            new Timer(1000/90, e -> screenObject.refresh()).start();
        });
    }
}