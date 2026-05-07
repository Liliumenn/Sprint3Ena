package ru.samsung.gamestudio.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import ru.samsung.gamestudio.GameSettings;


public class Boss extends GameObject{

    public static boolean IsBossKilled;
    private static int livesLeft;

    public Boss (int width, int height, String texturePath, World world) {
        super(
                texturePath,
                GameSettings.SCREEN_WIDTH + width / 2,
                GameSettings.SCREEN_HEIGHT + height / 2,
                width, height,
                GameSettings.TRASH_BIT,
                world
        );

        body.setLinearVelocity(new Vector2(0, -GameSettings.TRASH_VELOCITY));
        livesLeft = 5;
    }

    public boolean isAlive() {
        return livesLeft > 0;
    }

    public static boolean IsBossKilled(){
        if (livesLeft <= 0) {
        return true; }
        return false;
    }

    public boolean isInFrame() {
        return getY() + height / 2 > 0;
    }

    @Override
    public void hit() {
        livesLeft -= 1;
    }
}
