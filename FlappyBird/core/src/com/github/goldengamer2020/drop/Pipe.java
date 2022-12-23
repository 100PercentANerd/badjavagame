package com.github.goldengamer2020.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Pipe {
    public Boolean scoreAdded = false;
    Sprite scoreHitbox;
    Texture scoreHitboxImg;
    SpriteBatch batch;
    Sprite pipeBottom;
    Texture pipeTexture;
    Sprite pipeTop;
    Random rand = new Random();
    float x, y;

    public void pipeReset() {
        Random rand = new Random();
        int height = rand.nextInt(480);
        pipeBottom.setX(1000);
        pipeTop.setX(1000);
        pipeBottom.setY(0-height);

        if (pipeBottom.getY() >= -250) {
            pipeBottom.setY(-350);
        }

        pipeTop.setY(pipeBottom.getY()+pipeBottom.getHeight()+150);

        if (pipeTop.getY() <= 60) {
            pipeTop.setY(135);
        }

        System.out.println("T: " + pipeTop.getY());
        System.out.println("B: " + pipeBottom.getY());
    }

    public void render() {
        batch.begin();

        batch.draw(pipeBottom, pipeBottom.getX(), pipeBottom.getY());
        batch.draw(pipeTop, pipeTop.getX(), pipeTop.getY());

        batch.end();
    }
}
