package com.balloontd.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Bloon extends Actor {
    private boolean is_alive;
    private Float distance, radius, speed;
    
    @Override
    public void act(float delta_t) {
        this.distance += (delta_t*speed);
    }

    @Override
    abstract public void draw(Batch batch, float parentAlpha);

    public float getDistance() {
        return this.distance;
    }

    public void setDistance(float dist) {
        this.distance = dist;
    }

    abstract public Vector2 getCoords();

    public float getTouchRadius() {
        return this.radius;
    }
    
    public float getSpeed() {
        return this.speed;
    }

    abstract public void pop(Dart dart);
    
    public void setAliveState(boolean is_alive) {
        this.is_alive = is_alive;
    }
    
    public boolean getAliveState() {
        return this.is_alive;
    };
}
