package com.balloontd.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CompositeTrail implements Trail {
    private List<Trail> trails;
    private float total_length;
    private ArrayList<Float> trail_lengths;

    public CompositeTrail(List<Trail> trails){
        // don't even check whether trail is connected
        if(trails.size() == 0)
            throw new RuntimeException("CompositeTrails gets a list with size 0.");

        this.trails = trails;

        trail_lengths = new ArrayList<>();
        float len_sum = 0F;
        trail_lengths.add(len_sum); // note that trail_length has 0 in front
        for(Trail tr: trails){
            len_sum += tr.getTotalLength();
            trail_lengths.add(len_sum);
        }
        total_length = len_sum;
    }

    public float getTotalLength() {
        return total_length;
    }

    public Vector2 getCoordinate(float dist) {
        if(dist < 0) return getStartPoint();
        if(dist > getTotalLength()) return getEndPoint();

        int pos = Collections.binarySearch(trail_lengths, dist);
        if(pos < 0) return trails.get(-pos-2)
                                 .getCoordinate(dist - trail_lengths.get(-pos-2));
        else if(pos > 0) return trails.get(pos-1)
                                      .getCoordinate(dist - trail_lengths.get(pos-1));
        else return trails.get(0).getCoordinate(dist);
    }
    public void draw(Batch batch, float parentAlpha) {
        for(Trail t: trails)
            t.draw(batch, parentAlpha);
    }
    public Vector2 getStartPoint() {
        return trails.get(0).getStartPoint();
    }
    public Vector2 getEndPoint() {
        return trails.get(trails.size() - 1).getEndPoint();
    }

    @Override
    public boolean checkIntersectCircle(Vector2 coord, float radius) {
        for(Trail tr: trails)
            if(tr.checkIntersectCircle(coord, radius)) return true;
        return false;
    }

    public static Trail makeTrailByLines(String filename) {
        // makes a composite trail full of lines
        // filename contains lots of lines, each with 2 float
        // representing (x, y)

        String input = Gdx.files.internal(filename).readString();
        Scanner scanner = new Scanner(new StringReader(input));
        List<Trail> trails = new ArrayList<>();
        Vector2 last_pos = null;
        while(scanner.hasNextFloat()){
            // will actually take 2 floats
            if(last_pos == null)
                last_pos = new Vector2(
                        scanner.nextFloat(), scanner.nextFloat()
                );
            else {
                Vector2 cur_pos = new Vector2(
                        scanner.nextFloat(), scanner.nextFloat()
                );
                trails.add(new StraightTrail(last_pos, cur_pos));
                last_pos = cur_pos;
            }
        }
        return new CompositeTrail(trails);
    }
}
