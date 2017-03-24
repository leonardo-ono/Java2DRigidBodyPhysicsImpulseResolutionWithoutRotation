package br.ol.rb.body;

import br.ol.rb.RBScene;
import br.ol.rb.RigidBody;
import br.ol.rb.shape.StaticLine;

/**
 *
 * @author leonardo
 */
public class StaticStick extends RigidBody {
    
    public StaticStick(RBScene scene, double x1, double y1, double x2, double y2) {
        super(scene, new StaticLine(x1, y1, x2, y2));
        getGrabbingPointLocal().set((x1 + x2) / 2, (y1 + y2) / 2);
        getVelocity().set(0, 0);
        getPosition().set(0, 0);
        setMass(99999999);
        setInertia(99999999);
    }

    @Override
    public void init() {
    }

    @Override
    public void update() {
        super.update();
    }

}
