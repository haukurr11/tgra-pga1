package is.ru.tgra;

import java.nio.FloatBuffer;
import java.util.Random;

import com.badlogic.gdx.Gdx;

/**
 * This class creates the rocket object which the {@link Ship} can shoot to destroy the 
 * asteroids in the game. 
 */

public class Rocket extends GraphicObject
{
    private long created;
    private float red;
    private float green;
    private float blue;
    private double speed;
    
    /**
     * Getter function which determines that the Rocket has been created.
     * @return	
     */
    public long getCreated()
    {
        return created;
    }
    
    /**
     * A constructor which initializes the Rocket object and configures the angle, position on
     * the x-grid and y-grid, the speed of the object and what vertex buffer to insert the 
     * vertices along with how many points the object has and what index number it gets in the
     * vertex buffer. It creates these rockets with a random color.
     * 
     * @param angle			Angle of the rocket.
     * @param x				Position on the x-grid.
     * @param y				Position on the y-grid.
     * @param speed			How fast the rocket moves on the screen.
     * @param vertexBuffer	The vertex buffer to insert the vertices of the object.
     */
    public Rocket(int angle, int x, int y,double speed,FloatBuffer vertexBuffer)
    {
        super(x,y,vertexBuffer);
        this.points = 4;
        this.index = 4;
        this.setAngle(angle);
        this.created = System.currentTimeMillis();
        Random rand = new Random();
        this.red = rand.nextFloat();
        this.green = rand.nextFloat();
        this.blue = rand.nextFloat();
        this.speed = speed;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void update()
    {
        this.setMoving_angle(this.getAngle());
        this.moveForward(this.speed);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void display()
    {
        Random rand = new Random();

        Gdx.gl11.glColor4f(this.red,this.green,this.blue,1.0f);
        super.display();
        Gdx.gl11.glColor4f(1f,1f,1f,1.0f);
    }
    
}
