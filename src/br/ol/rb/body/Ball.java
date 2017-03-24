package br.ol.rb.body;

import br.ol.rb.RBScene;
import br.ol.rb.RigidBody;
import br.ol.rb.shape.Circle;

/**
 *
 * @author leonardo
 */
public class Ball extends RigidBody {
    
    public Ball(RBScene scene, double x, double y, double radius) {
        super(scene, new Circle(radius));
        getGrabbingPointLocal().set(0, 0);
        double vx = 100 * Math.random() - 50;
        double vy = 100 * Math.random() - 50;
        getVelocity().set(vx, vy);
        getPosition().set(x, y);
        setMass(0.1 * radius);
        setInertia(0.1 * radius);
    }

    @Override
    public void init() {
    }

    @Override
    public void update() {
        super.update();
    }

}
