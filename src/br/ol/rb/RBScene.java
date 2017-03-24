package br.ol.rb;

import br.ol.rb.body.Ball;
import br.ol.rb.body.StaticStick;
import br.ol.rb.shape.Circle;
import br.ol.rb.shape.StaticLine;
import core.Configuration;
import core.Entity;
import core.Mouse;
import core.Scene;
import core.Vec2;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leonardo
 */
public class RBScene extends Scene {
    
    private final Vec2 gravity = new Vec2(0, 300);
    private final List<Contact> contacts = new ArrayList<Contact>();

    public RBScene(Configuration configuration) {
        super(configuration);
    }

    public Vec2 getGravity() {
        return gravity;
    }
    
    @Override
    public void init() {
        getEntities().add(new StaticStick(this, 50, 50, 50, 450));
        getEntities().add(new StaticStick(this, 50, 450, 300, 300));
        getEntities().add(new StaticStick(this, 300, 300, 600, 450));
        getEntities().add(new StaticStick(this, 600, 350, 600, 50));
    }

    @Override
    public void update() {
        if (Mouse.pressed && !Mouse.pressedConsumed) {
            Mouse.pressedConsumed = true;
            addEntity(new Ball(this, Mouse.x, Mouse.y, 10 + 30 * Math.random()));
        }
        
        contacts.clear();
        for (Entity e1 : getEntities()) {
            for (Entity e2 : getEntities()) {
                if (e1 != e2 && e1 instanceof RigidBody && e2 instanceof RigidBody) {
                    RigidBody rb1 = (RigidBody) e1;
                    RigidBody rb2 = (RigidBody) e2;
                    Contact contact = new Contact(rb1, rb2);
                    if (rb1.getShape() instanceof Circle && rb2.getShape() instanceof Circle && CollisionDetection.checkCollisionCircleCircle(rb1, rb2, contact)) {
                        if (!contacts.contains(contact)) {
                            contacts.add(contact);
                        }
                    }
                    else if (rb1.getShape() instanceof Circle && rb2.getShape() instanceof StaticLine && CollisionDetection.checkCollisionCircleStaticLine(rb1, rb2, contact)) {
                        if (!contacts.contains(contact)) {
                            contacts.add(contact);
                        }
                    }
                    else if (rb2.getShape() instanceof Circle && rb1.getShape() instanceof StaticLine && CollisionDetection.checkCollisionCircleStaticLine(rb2, rb1, contact)) {
                        if (!contacts.contains(contact)) {
                            contacts.add(contact);
                        }
                    }
                }
            }
        }
        
        for (Entity entity : getEntities()) {
            if (entity instanceof RigidBody) {
                ((RigidBody) entity).updateVelocity();
            }
        }
        
        for (int i = 0; i < 4; i++) {
            for (Contact contact : contacts) {
                contact.resolveCollision();
            }
        }
        
        for (Entity entity : getEntities()) {
            entity.update();
        }
        
        for (Contact contact : contacts) {
            contact.correctPosition();
        }
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        
        for (Contact contact : contacts) {
            contact.draw(g);
        }
    }
    
}
