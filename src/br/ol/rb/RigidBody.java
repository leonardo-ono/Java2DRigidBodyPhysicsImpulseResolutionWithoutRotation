package br.ol.rb;

import br.ol.rb.shape.Shape;
import core.Entity;
import core.Time;
import core.Vec2;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 *
 * @author leonardo
 */
public class RigidBody extends Entity<RBScene> {
    
    private Color color = Color.BLUE;
    
    private double mass;
    private final Vec2 force = new Vec2();
    private final Vec2 acceleration = new Vec2();
    private final Vec2 velocity = new Vec2();
    private final Vec2 ds = new Vec2();

    private double inertia;
    private double torque;
    private double angularAcceleration;
    private double angularVelocity;
    private double angle;

    private Shape shape;

    // grabbing point
    private final Vec2 grabbingPointLocal = new Vec2();
    private final Vec2 grabbingPointWorld = new Vec2();
    private final AffineTransform transform = new AffineTransform();
    private final Point2D auxiliarPoint = new Point2D.Double();
    
    private final Vec2 auxVec = new Vec2();
    
    public RigidBody(RBScene scene, Shape shape) {
        super(scene);
        this.shape = shape;
    }

    @Override
    public void init() {
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }
    
    public void addForce(Vec2 force) {
        this.force.add(force);
    }
    
    public Vec2 getForce() {
        return force;
    }

    public void applyImpulse(Vec2 impulse) {
        auxVec.set(impulse);
        auxVec.scale(1 / mass);
        velocity.add(auxVec);
    }
    
    public Vec2 getAcceleration() {
        return acceleration;
    }

    public Vec2 getVelocity() {
        return velocity;
    }

    public double getInertia() {
        return inertia;
    }

    public void setInertia(double inertia) {
        this.inertia = inertia;
    }

    public void addTorque(double torque) {
        this.torque += torque;
    }

    public double getTorque() {
        return torque;
    }

    public double getAngularAcceleration() {
        return angularAcceleration;
    }

    public double getAngularVelocity() {
        return angularVelocity;
    }

    public double getAngle() {
        return angle;
    }

    public Shape getShape() {
        return shape;
    }

    public Vec2 getGrabbingPointLocal() {
        return grabbingPointLocal;
    }

    public Vec2 getGrabbingPointWorld() {
        transform.setToIdentity();
        transform.translate(getPosition().getX(), getPosition().getY());
        transform.rotate(getAngle());
        auxiliarPoint.setLocation(grabbingPointLocal.getX(), grabbingPointLocal.getY());
        transform.transform(auxiliarPoint, auxiliarPoint);
        grabbingPointWorld.set(auxiliarPoint.getX(), auxiliarPoint.getY());
        return grabbingPointWorld;
    }

    public void updateVelocity() {
        double dt = Time.getDelta() / 1000000000.0;
        force.add(getScene().getGravity());
        // linear motion
        acceleration.set(force);
        acceleration.scale(dt / mass);
        velocity.add(acceleration);
    }
    
    @Override
    public void update() {
        double dt = Time.getDelta() / 1000000000.0;
        ds.set(velocity);
        ds.scale(dt);
        getPosition().add(ds);
        
        // angular motion TODO
        //angularAcceleration = torque / inertia;
        //angularVelocity = angularAcceleration * dt;
        //angle += angularVelocity * dt;
        
        // clear net force and torque
        force.set(0, 0);
        torque = 0;
    }

    @Override
    public void draw(Graphics2D g) {
        if (shape != null) {
            AffineTransform at = g.getTransform();
            g.setColor(color);
            g.translate(getPosition().getX(), getPosition().getY());
            g.rotate(angle);
            shape.draw(g);
            
            // grabbing point
            g.setColor(Color.BLACK);
            g.fillOval((int) (grabbingPointLocal.getX() - 2), (int) (grabbingPointLocal.getY() - 2), 4, 4);
            g.setTransform(at);
        }
    }
    
}
