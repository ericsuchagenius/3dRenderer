import javax.swing.*;
import java.awt.*;

public class Renderer extends JFrame {
    static double Width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    static double Height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    static double[] viewFrom = {1, 1, 1.5};
    static double[] viewTo = {100, 100, 100};
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
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setVisible(true);
        setLayout(new BorderLayout());
        add(screenObject, BorderLayout.CENTER);
        setFocusable(true);
        addKeyListener(screenObject); // Add key listener to the frame
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Renderer frame = new Renderer();
            new Timer(1000/90, e -> screenObject.refresh()).start();
        });
    }
}