package com.balloontd.game.monkeys;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.balloontd.game.Bloon;
import com.balloontd.game.GameScreen;
import com.balloontd.game.Monkey;

import java.util.List;

public class BoomerangShooter extends Monkey {
    public static final Texture texture = new Texture(Gdx.files.internal("boomerang_shooter.png"));
    public static final TextureRegion region = new TextureRegion(texture);

    private static final Float[3] levelup_cost = new Float[3] {600, 200, 300};

    public BoomerangShooter(GameScreen game_screen, Vector2 coord) {
        super(game_screen, region, coord,
              region.getRegionWidth() * 0.5F + 10F, 70F);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        // may need to add animations...?
        batch.draw(
                region,
                getX(), getY(),
                getOriginX(), getOriginY(),
                getWidth(), getHeight(),
                getScaleX(), getScaleY(),
                getRotation()
        );
    }

    public String getName() {
        return "BoomerangShooter";
    }
    public String getIntro() {
        return "Shoot a boomerang";
    }
    public int getCurLevel() {
        return 0;
    }
    public int getMaxLevel() {
        return 0;
    }
    public float getLevelUpCost(int cur_level) {
        return 0;
    }
    public void levelUp() {
    }
    public String getLevelUpInfoDisplay(int cur_level) {
        return "";
    }
    public float getBuyPrice() {
        return 0;
    }
    public float getSellPrice() {
        return 0;
    }
    public void shoot(List<Bloon> in_range_bloons) {
        // choose nearest one
        Bloon bloon = in_range_bloons.stream().min(
                (b1, b2) -> {
                    Float f1 = b1.getCoords().dst2(getCoords());
                    Float f2 = b2.getCoords().dst2(getCoords());
                    return f1.compareTo(f2);
                }
        ).get();

        // shoot
        game_screen.getDartManager().addDartInBuffer(
                new NormalDart(
                        game_screen, 3, getCoords(),
                        bloon.getCoords().sub(getCoords()).setLength(2000F)
                )
        );
    }

    public float getCooldownTime() {
        return 1.5F;
    }
}