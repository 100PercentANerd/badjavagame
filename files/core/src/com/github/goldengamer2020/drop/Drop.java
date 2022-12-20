package com.github.goldengamer2020.drop;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Drop extends ApplicationAdapter {
	SpriteBatch batch;
	Texture playerTexture;
	Sprite p;
	Boolean isDead = false;
	Texture pipeImg;
	Sprite pipe;

	@Override
	public void create () {
		batch = new SpriteBatch();
		playerTexture = new Texture("placeholderGreen.png");
		p = new Sprite(playerTexture);
		p.setPosition(Gdx.graphics.getWidth()/2 - p.getWidth()/2,
				Gdx.graphics.getHeight()/2 - p.getHeight()/2);
		pipeImg = new Texture("pipe____totally.png");
		pipe = new Sprite(pipeImg);
		pipe.setPosition(800,0);
	}

	@Override
	public void render () {
		//Input & Player movement

		if(p.getY()+64 > 480) {
			isDead = true;
			System.out.println("You Died");
			System.exit(0);
		} else if(p.getY() <= 0) {
			isDead = true;
			System.out.println("You Died");
			System.exit(0);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			p.translateY(1f);
			System.out.println("( " + p.getX() + ", " + p.getY() + " )");

		} else if(!Gdx.input.isKeyPressed((Input.Keys.SPACE))) {
			p.translateY(-2f);
			System.out.println("( " + p.getX() + ", " + p.getY() + " )");

		}

		//Pipe Movement
		pipe.translateX(-1f);

		//Other

		ScreenUtils.clear(0, 0, 100, 1);
		batch.begin();
		batch.draw(p, p.getX(), p.getY());
		batch.draw(pipe, pipe.getX(), pipe.getY());

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		playerTexture.dispose();
		pipeImg.dispose();
	}
}