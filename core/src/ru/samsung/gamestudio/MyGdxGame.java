package ru.samsung.gamestudio;

import Screens.GameScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;

import static ru.samsung.gamestudio.GameSettings.*;

public class MyGdxGame extends Game {
	SpriteBatch batch;
	public OrthographicCamera Camera;
	public GameScreen gameScreen;
	public World world;
	float accumulator = 0;

	Texture img;
	
	@Override
	public void create () {
		Box2D.init();
		world = new World(new Vector2(0,0),true);

		batch = new SpriteBatch();
		Camera = new OrthographicCamera();
		Camera.setToOrtho(false,SCREEN_WIDTH,SCREEN_HEIGHT);

		gameScreen = new GameScreen(this);
		setScreen(gameScreen);
	}
	public void stepWorld() {
		float delta = Gdx.graphics.getDeltaTime();
		accumulator += delta;
		if (accumulator >= STEP_TIME) {
			accumulator -= STEP_TIME;
			world.step(STEP_TIME, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
		}
	}

	@Override
	public void render () {
		world.step(STEP_TIME, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

}
