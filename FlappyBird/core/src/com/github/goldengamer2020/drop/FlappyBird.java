package com.github.goldengamer2020.drop;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.github.goldengamer2020.drop.Pipe;
import java.util.concurrent.TimeUnit;


public class FlappyBird extends ApplicationAdapter {
	Boolean hasStarted = false;
	SpriteBatch batch;
	public int score = 0;
	String scoreString = String.valueOf(score);
	BitmapFont font;
	Rectangle playerCollider;
	Boolean isDead = false;

	//Textures
	Texture playerTexture;

	//Sprites
	Sprite p;

	float pVel = 0;
	float pAcc = -0.1f;

	//Pipe Instances
	Pipe pipe = new Pipe();
	Pipe pipeTwo = new Pipe();
	Pipe pipeThree = new Pipe();
	Pipe pipeFour = new Pipe();


	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		//Player Init

		playerTexture = new Texture("placeholderGreen.png");
		p = new Sprite(playerTexture);
		p.setPosition(Gdx.graphics.getWidth()/2 - p.getWidth()/2,
				Gdx.graphics.getHeight()/2 - p.getHeight()/2);

		//Pipe(s) init

		pipe.pipeTexture = new Texture("pipetwo.png");
		pipe.pipeBottom = new Sprite(pipe.pipeTexture);
		pipe.pipeBottom.setPosition(1000, -304);
		pipe.pipeTop = new Sprite(pipe.pipeTexture);
		pipe.pipeTop.setPosition(1000, 850 - pipe.pipeTop.getHeight());
		pipe.scoreHitboxImg = new Texture("placeholderRed.png");
		pipe.scoreHitbox = new Sprite(pipe.scoreHitboxImg);

		pipeTwo.pipeTexture = new Texture("pipetwo.png");
		pipeTwo.pipeBottom = new Sprite(pipeTwo.pipeTexture);
		pipeTwo.pipeBottom.setPosition(1250, -254);
		pipeTwo.pipeTop = new Sprite(pipeTwo.pipeTexture);
		pipeTwo.pipeTop.setPosition(1250, 900 - pipeTwo.pipeTop.getHeight());
		pipeTwo.scoreHitboxImg = new Texture("placeholderRed.png");
		pipeTwo.scoreHitbox = new Sprite(pipeTwo.scoreHitboxImg);

		pipeThree.pipeTexture = new Texture("pipetwo.png");
		pipeThree.pipeBottom = new Sprite(pipeThree.pipeTexture);
		pipeThree.pipeBottom.setPosition(1500, -204);
		pipeThree.pipeTop = new Sprite(pipeThree.pipeTexture);
		pipeThree.pipeTop.setPosition(1500, 950 - pipeThree.pipeTop.getHeight());
		pipeThree.scoreHitboxImg = new Texture("placeholderRed.png");
		pipeThree.scoreHitbox = new Sprite(pipeThree.scoreHitboxImg);

		pipeFour.pipeTexture = new Texture("pipetwo.png");
		pipeFour.pipeBottom = new Sprite(pipeFour.pipeTexture);
		pipeFour.pipeBottom.setPosition(1750, -454);
		pipeFour.pipeTop = new Sprite(pipeFour.pipeTexture);
		pipeFour.pipeTop.setPosition(1750, 700 - pipeFour.pipeTop.getHeight());
		pipeFour.scoreHitboxImg = new Texture("placeholderRed.png");
		pipeFour.scoreHitbox = new Sprite(pipeFour.scoreHitboxImg);

		start();
	}

	@Override
	public void render () {
		scoreString = String.valueOf(score);
		float deltaTime = Gdx.graphics.getDeltaTime();

		//Pipe Reset

		if (pipe.pipeBottom.getX()+pipe.pipeBottom.getWidth() <= 0) {
			pipe.pipeReset();
		} else if (pipeTwo.pipeBottom.getX()+pipeTwo.pipeBottom.getWidth() <= 0) {
			pipeTwo.pipeReset();
		} else if (pipeThree.pipeBottom.getX()+pipeThree.pipeBottom.getWidth() <= 0) {
			pipeThree.pipeReset();
		} else if (pipeFour.pipeBottom.getX()+pipeFour.pipeBottom.getWidth() <= 0) {
			pipeFour.pipeReset();
		}

		//Score++

		if(pipe.pipeTop.getX()+pipe.pipeTop.getWidth()/2 == 500) {
			score++;
			System.out.println("Score: " + score);
		} else if(pipeTwo.pipeTop.getX()+pipe.pipeTop.getWidth()/2 == 500) {
			score++;
			System.out.println("Score: " + score);
		} else if(pipeThree.pipeTop.getX()+pipe.pipeTop.getWidth()/2 == 500) {
			score++;
			System.out.println("Score: " + score);
		} else if(pipeFour.pipeTop.getX()+pipe.pipeTop.getWidth()/2 == 500) {
			score++;
			System.out.println("Score: " + score);
		}

		//Top & Bottom collision

		if(p.getY()+p.getHeight() > 480) {
			dead();
		} else if(p.getY() <= 0) {
			dead();
		}

		//Jump

		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			if(hasStarted == false) {
				pVel += 2;
				hasStarted = true;
				Sound jump = Gdx.audio.newSound(Gdx.files.internal("jump.wav"));
				//jump.play(5f);
			} else {
				pVel += 5;
				hasStarted = true;
				Sound jump = Gdx.audio.newSound(Gdx.files.internal("jump.wav"));
				//jump.play(5f);
			}
		}

		//Gravity

		if(hasStarted == true) {
			pAcc = -.1f;
			pVel += pAcc;
			p.setY(p.getY() + pVel);
		}

		//Pipe Movement
		if(hasStarted == true) {
			pipe.pipeBottom.translateX(-100f * deltaTime);
			pipe.pipeTop.translateX(-100f * deltaTime);
			pipeTwo.pipeBottom.translateX(-100f * deltaTime);
			pipeTwo.pipeTop.translateX(-100f * deltaTime);
			pipeThree.pipeBottom.translateX(-100f * deltaTime);
			pipeThree.pipeTop.translateX(-100f * deltaTime);
			pipeFour.pipeBottom.translateX(-100f * deltaTime);
			pipeFour.pipeTop.translateX(-100f * deltaTime);
		}

		//Other

		ScreenUtils.clear(0, 0, 100, 1);
		batch.begin();
		if(isDead != true) {
			batch.draw(p, p.getX(), p.getY());
		}

		//Sprite Drawing
		batch.draw(pipe.scoreHitbox, pipe.pipeBottom.getX(), pipe.pipeBottom.getY()+pipe.pipeBottom.getHeight());
		batch.draw(pipeTwo.scoreHitbox, pipeTwo.pipeBottom.getX(), pipeTwo.pipeBottom.getY()+pipeTwo.pipeBottom.getHeight());
		batch.draw(pipeThree.scoreHitbox, pipeThree.pipeBottom.getX(), pipeThree.pipeBottom.getY()+pipeThree.pipeBottom.getHeight());
		batch.draw(pipeFour.scoreHitbox, pipeFour.pipeBottom.getX(), pipeFour.pipeBottom.getY()+pipeFour.pipeBottom.getHeight());

		batch.draw(pipe.pipeBottom, pipe.pipeBottom.getX(), pipe.pipeBottom.getY());
		batch.draw(pipe.pipeTop, pipe.pipeTop.getX(), pipe.pipeTop.getY());
		batch.draw(pipeTwo.pipeTop, pipeTwo.pipeTop.getX(), pipeTwo.pipeTop.getY());
		batch.draw(pipeTwo.pipeBottom, pipeTwo.pipeBottom.getX(), pipeTwo.pipeBottom.getY());
		batch.draw(pipeThree.pipeTop, pipeThree.pipeTop.getX(), pipeThree.pipeTop.getY());
		batch.draw(pipeThree.pipeBottom, pipeThree.pipeBottom.getX(), pipeThree.pipeBottom.getY());
		batch.draw(pipeFour.pipeTop, pipeFour.pipeTop.getX(), pipeFour.pipeTop.getY());
		batch.draw(pipeFour.pipeBottom, pipeFour.pipeBottom.getX(), pipeFour.pipeBottom.getY());

		font.draw(batch, scoreString, scoreString.length()+500, 470); //Drawing the score to the screen (centered)

		batch.end();

		//Pipe Collisions

		if(p.getBoundingRectangle().overlaps(pipe.pipeTop.getBoundingRectangle())) {
			dead();
		} else if(p.getBoundingRectangle().overlaps(pipe.pipeBottom.getBoundingRectangle())) {
			dead();
		} else if(p.getBoundingRectangle().overlaps(pipeTwo.pipeTop.getBoundingRectangle())) {
			dead();
		} else if(p.getBoundingRectangle().overlaps(pipeTwo.pipeBottom.getBoundingRectangle())) {
			dead();
		} else if(p.getBoundingRectangle().overlaps(pipeThree.pipeTop.getBoundingRectangle())) {
			dead();
		} else if(p.getBoundingRectangle().overlaps(pipeThree.pipeBottom.getBoundingRectangle())) {
			dead();
		} else if(p.getBoundingRectangle().overlaps(pipeFour.pipeTop.getBoundingRectangle())) {
			dead();
		} else if(p.getBoundingRectangle().overlaps(pipeFour.pipeBottom.getBoundingRectangle())) {
			dead();
		}

		//Score Collisions

		if(p.getBoundingRectangle().overlaps(pipe.scoreHitbox.getBoundingRectangle())) {
			scoreMethod();
		}
		if(hasStarted == false) {
			pAcc = 0;
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
		playerTexture.dispose();
		pipe.pipeTexture.dispose();
		pipeTwo.pipeTexture.dispose();
		pipeThree.pipeTexture.dispose();
		pipeFour.pipeTexture.dispose();
	}

	public void start() {
		hasStarted = false;
		pVel = 0;
		p.setPosition(Gdx.graphics.getWidth()/2-p.getWidth()/2,
				Gdx.graphics.getHeight()/2-p.getHeight()/2);
		pipeFour.pipeReset();
		pipeThree.pipeReset();
		pipeTwo.pipeReset();
		pipe.pipeReset();
		pipeTwo.pipeTop.setX(1250);
		pipeThree.pipeTop.setX(1500);
		pipeFour.pipeTop.setX(1750);
		pipeTwo.pipeBottom.setX(1250);
		pipeThree.pipeBottom.setX(1500);
		pipeFour.pipeBottom.setX(1750);
	}

	public void dead() {
		start();
		System.out.println("Score: " + score);
		isDead = false;
		Sound die = Gdx.audio.newSound(Gdx.files.internal("hit.wav"));
		die.play(100000f);
	}

	public void scoreMethod() {
		score++;
		pipe.scoreAdded = true;
		System.out.println("Score: " + scoreString);
	}

}
