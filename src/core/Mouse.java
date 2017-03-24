package core;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author leonardo
 */
public class Mouse extends MouseAdapter {

    private Display display;
    public static double x;
    public static double y;
    public static boolean pressed;
    public static boolean pressedConsumed;

    public Mouse(Display display) {
        this.display = display;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX() / display.getConfiguration().getScaleWidth();
        y = e.getY() / display.getConfiguration().getScaleHeight();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x = e.getX() / display.getConfiguration().getScaleWidth();
        y = e.getY() / display.getConfiguration().getScaleHeight();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        pressed = true;
        pressedConsumed = false;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        pressed = false;
    }
    
}
