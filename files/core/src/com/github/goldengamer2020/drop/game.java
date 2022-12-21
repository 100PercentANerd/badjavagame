package com.github.goldengamer2020.drop;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class game extends ApplicationAdapter {
	SpriteBatch batch;
	Boolean isDead = false;

	//Textures
	Texture pipeImg;
	Texture playerTexture;

	//Sprites
	Sprite pipe;
	Sprite pipe2;
	Sprite p;

	float pVel = 0;
	float pAcc = -0.1f;
	float pY = 0;

	Enemy Enemy = new Enemy();

	@Override
	public void create () {
		batch = new SpriteBatch();
		Enemy.enemyImg = new Texture("placeholderRed.png");
		Enemy.enemy = new Sprite(Enemy.enemyImg);
		playerTexture = new Texture("placeholderGreen.png");
		p = new Sprite(playerTexture);
		p.setPosition(Gdx.graphics.getWidth()/2 - p.getWidth()/2,
				Gdx.graphics.getHeight()/2 - p.getHeight()/2);
		pipeImg = new Texture("pipe____totally.png");
		pipe = new Sprite(pipeImg);
		pipe.setPosition(800,0);
		pipe2 = new Sprite(pipeImg);
		//pipe.setPosition(800, );
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
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
				pAcc = .1f;
				pVel += 5;
		}

		if(p.getY() >= 0) {
			if(p.getY() <= pipe.getY() + pipe.getHeight()) {
				if(p.getX()+p.getWidth() >= pipe.getX() && p.getX() <= pipe.getX() + pipe.getWidth()) {
					System.exit(0);
					isDead = true;
				}
			}
		}
		System.out.println(p.getX() + " " + p.getWidth());
		//Gravity
		pAcc = -.1f;
		pVel += pAcc;
		pY = p.getY();
		pY += pVel;
		p.setY(pY);
		//Pipe Movement
		pipe.translateX(-1f);

		//Other

		ScreenUtils.clear(0, 0, 100, 1);
		batch.begin();
		if(isDead != true) {
			batch.draw(p, p.getX(), p.getY());
		}
		batch.draw(Enemy.enemy, 0, 0);
		batch.draw(Enemy.enemy, 0, 480);
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