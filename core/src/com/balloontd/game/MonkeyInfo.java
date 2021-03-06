package com.balloontd.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class MonkeyInfo extends Actor {
    private Texture backgroundTexture;
    private Image background;
    private BitmapFont font;
    private Monkey monkey;

    private String nowInfo;
    private String upgradeInfo;

    public MonkeyInfo(){
        super();
        backgroundTexture = new Texture("ui-monkeyInfo.png");
        background = new Image(new TextureRegion(backgroundTexture));
        background.setPosition(955, 180);

        font = new BitmapFont(Gdx.files.internal("font/ComicSansMS.fnt"));
        font.getData().setScale(0.7F);
        font.getData().setLineHeight(40.0F);
    }

    public void setMonkey(Monkey monkey){
        this.monkey = monkey;
        setNowInfo();
        setUpgradeInfo();

    }

    private void setNowInfo(){
        nowInfo = "";
        nowInfo += (monkey.getName() + "\n");
        nowInfo += ("Lv." + monkey.getCurLevel() + "\n");
        nowInfo += ("Sell: $" + (int)monkey.getSellPrice() + "\n");
    }

    private void setUpgradeInfo(){
        upgradeInfo = "";
        if(monkey.getCurLevel() < monkey.getMaxLevel()) {
            upgradeInfo += ("Upgrade : \n");
            upgradeInfo += (monkey.getLevelUpInfoDisplay(monkey.getCurLevel()) + "\n");
            upgradeInfo += ("Price:  $" + (int)monkey.getLevelUpCost(monkey.getCurLevel()));
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        super.draw(batch, parentAlpha);

        background.draw(batch, parentAlpha);

        font.draw(batch, nowInfo + upgradeInfo, 965, 410, 200, 10, true);
    }

    public void dispose(){
        if(backgroundTexture != null) {
            backgroundTexture.dispose();
        }
    }

}
