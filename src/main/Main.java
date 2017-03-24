package main;

import br.ol.rb.RBScene;
import core.Configuration;
import core.Display;
import core.Scene;
import core.Window;
import javax.swing.SwingUtilities;

/**
 *
 * @author leonardo
 */
public class Main {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Configuration configuration = new Configuration("2D Physics - Impulse resolution test (no rotation)", 640, 480, 1, 1, 60);
                Display display = new Display(configuration);
                Scene scene = new RBScene(configuration);
                display.setScene(scene);
                Window window = new Window(display);
                window.start();
            }
        });
    }
    
}
