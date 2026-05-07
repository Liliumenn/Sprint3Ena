package ru.samsung.gamestudio.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import ru.samsung.gamestudio.GameSettings;


public class Boss extends GameObject {
    public boolean isKilled;
    private int livesLeft;

    public Boss(int width, int height, String texturePath, World world) {
        super(
                texturePath,
                (GameSettings.SCREEN_WIDTH + width) / 2,
                GameSettings.SCREEN_HEIGHT + height / 2,
                width, height,
                GameSettings.TRASH_BIT,
                world
        );

        body.setLinearVelocity(new Vector2(0, -GameSettings.TRASH_VELOCITY));
        livesLeft = 3;
    }

    public boolean isAlive() {
        return livesLeft > 0;
    }

    public boolean isInFrame() {
        return getY() + height / 2 > 0;
    }

    @Override
    public void hit() {
        livesLeft -= 1;
    }

    public void destroyIfNeed(World world) {
        if(!isKilled){
            isKilled = livesLeft <= 0;
            world.destroyBody(body);
        }
    }
}
