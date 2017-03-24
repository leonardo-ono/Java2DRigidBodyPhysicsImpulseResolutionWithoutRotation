package core;

import java.awt.Graphics2D;

/**
 *
 * @author leonardo
 * @param <T>
 */
public class Entity<T extends Scene> {
    
    private final T scene;
    private final Vec2 position = new Vec2();
    
    public Entity(T scene) {
        this.scene = scene;
    }
    
    public void init() {
    }
    
    public T getScene() {
        return scene;
    }

    public Vec2 getPosition() {
        return position;
    }
    
    public void update() {
    }

    public void draw(Graphics2D g) {
    }
    
}
