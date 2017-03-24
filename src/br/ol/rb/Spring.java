package br.ol.rb;

import core.Entity;
import core.Vec2;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author leonardo
 */
public class Spring extends Entity<RBScene> {
    
    private final RigidBody b1;
    private final RigidBody b2;
    private final Vec2 direction = new Vec2();
    private final Vec2 r = new Vec2();
    
    public Spring(RBScene scene, RigidBody b1, RigidBody b2) {
        super(scene);
        this.b1 = b1;
        this.b2 = b2;
    }

    @Override
    public void update() {
        Vec2 p1 = b1.getGrabbingPointWorld();
        Vec2 p2 = b2.getGrabbingPointWorld();
        direction.set(p2);
        direction.sub(p1);
        direction.scale(2);
        
        //if (direction.getLength() < 50) {
        //    return;
        //}
        
        b1.addForce(direction);
        direction.scale(-1);
        b2.addForce(direction);
        
        r.set(b1.getGrabbingPointWorld());
        r.sub(b1.getPosition());
        //double torque1pd = r.perpDot(direction);
        double torque1 = r.cross(direction);
        b1.addTorque(-torque1);

        //System.out.println("torque1pd = " + torque1pd);
        //System.out.println("torque1 = " + torque1);
        //System.out.println("");
        
        r.set(b2.getGrabbingPointWorld());
        r.sub(b2.getPosition());
        //double torque2pd = r.perpDot(direction);
        double torque2 = r.cross(direction);
        b2.addTorque(torque2);
    }
    
    @Override
    public void draw(Graphics2D g) {
        int x1 = (int) b1.getGrabbingPointWorld().getX();
        int y1 = (int) b1.getGrabbingPointWorld().getY();
        int x2 = (int) b2.getGrabbingPointWorld().getX();
        int y2 = (int) b2.getGrabbingPointWorld().getY();
        g.setColor(Color.BLACK);
        g.drawLine(x1, y1, x2, y2);
    }
    
}
