package com.balloontd.start;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.balloontd.*;

public class EnterMenuButton extends Button {

    public EnterMenuButton(final BalloonTD balloonTD, ButtonStyle style){
        super(style);

        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                balloonTD.enterMenuScreen();
            }
        });
    }
}
