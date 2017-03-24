package br.ol.rb.shape;

import java.awt.Graphics2D;

/**
 *
 * @author leonardo
 */
public class Circle extends Shape {
    
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }
    
    @Override
    public void draw(Graphics2D g) {
        g.drawOval((int) -radius, (int) -radius, (int) (2 * radius), (int) (2 * radius));
        g.drawLine(0, 0, (int) radius, 0);
    }
    
}
