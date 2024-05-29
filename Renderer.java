import javax.swing.*;
import java.awt.*;

public class Renderer extends JFrame {
    static double Width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    static double Height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    static double[] viewFrom = {3, 0, 0};
    static double[] viewTo = {10, 10, 10};
    static Double[][][][] initializeData = {{
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
            new Timer(1000/30, e -> screenObject.refresh()).start();
        });
    }
}