package is.ru.tgra;

import java.nio.FloatBuffer;
import java.util.Random;

import com.badlogic.gdx.Gdx;

/**
 * This class creates an Asteroid object for the game, setting its configurations and
 * overriding the display and update function from the {@link GraphicObject} class. It serves as the
 * main enemy in the game, and breaks down into two {@link MediumAstroid} when destroyed.
 */

public class Asteroid extends GraphicObject
{
    private int color;
    
    /**
     * A constructor which sets the configuration of the Asteroid object.
     * @param angle			Determines the angle of the object.
     * @param x				Determines the starting location of the object on the x-grid 
     * @param y				Determines the starting location of the object on the y-grid
     * @param vertexBuffer	Determines the vertex buffer being used.
     */
    public Asteroid(int angle, int x, int y,FloatBuffer vertexBuffer)
    {
        super(x,y,vertexBuffer);
        this.points = 4;
        this.index = 8;
        this.setAngle(angle);
        this.setMoving_angle(this.getAngle()+25);
        Random rand = new Random();
        this.color = rand.nextInt(2);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void display()
    {
        if(this.color == 1)
        {
            Gdx.gl11.glColor4f(1f,.5f,.7f,1.0f);
        }
        else
        {
            Gdx.gl11.glColor4f(0f,.3f,.7f,1.0f);
        }
        super.display();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void update()
    {
        this.moveForward(1);
    }
    
}
