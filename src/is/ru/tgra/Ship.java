package is.ru.tgra;

import java.awt.Rectangle;
import java.nio.FloatBuffer;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

/**
 * This class handles the Ship {@link GraphicObject} which the player controls in the game.
 * This ship uses {@link Rocket} to destroy the {@link Asteroid} objects.
 *
 */

public class Ship extends GraphicObject
{
    private List<Rocket> rockets;
    private double speed;
    private double acceleration;
    private int max_speed;
    private boolean firing;
    private int rocket_speed;
    private int rocket_timealive;
    
    /**
     * Getter function which returns a list of {@link Rocket} objects.
     * @return	List of {@link Rocket} objects.
     */
    public List<Rocket> getRockets()
    {
        return rockets;
    }
    
    /**
     * Getter function for the movement speed of the ship.
     * @return	The speed of the ship.
     */
    public double getSpeed()
    {
        return speed;
    }

    /**
     * Setter function which determines the movement speed of the ship.
     * @param speed	The speed of the ship.
     */
    public void setSpeed(double speed)
    {
        this.speed = speed;
    }
    
    /**
     * A constructor which configures the position on the screen, and includes it in the 
     * vertex buffer. This function also determines the index number in the vertex buffer,
     * the speed of acceleration when moveForward is pushed, the max speed of the ship and
     * also the speed and live time of the {@link Rocket} objects fired.
     * 
     * @param x				The position on the x-grid.
     * @param y				The position on the y-grid.
     * @param vertexBuffer	
     */
    public Ship(int x, int y,FloatBuffer vertexBuffer)
    {
        super(x,y,vertexBuffer);

        this.setAngle(90);
        this.points = 4;
        this.index = 0;
        this.speed = 0;
        this.rockets = new LinkedList<Rocket>();
        this.setHeight(20);
        this.setWidth(20);
        this.acceleration = 0.1;
        this.max_speed = 10;
        this.rocket_speed = 10;
        this.rocket_timealive = 500;
        this.radius = 20;
        this.width = 20;
        this.height = 20;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void update()
    {
        this.moveForward(this.speed);
        if(Gdx.input.isKeyPressed(Keys.RIGHT))
        {
            this.setAngle(this.getAngle() - 10);
        }
        if(Gdx.input.isKeyPressed(Keys.LEFT))
        {
            this.setAngle(this.getAngle() + 10);
        }
        if(Gdx.input.isKeyPressed(Keys.UP))
        {
            if(!Gdx.input.isKeyPressed(Keys.LEFT) && !Gdx.input.isKeyPressed(Keys.RIGHT) )
            {
                this.speed -= Math.abs(this.getMoving_angle()-this.getAngle())/10;
            }
            this.setMoving_angle(this.getAngle());
            if(this.speed <= 0 )
            {
                this.speed = 0.01;
            }
            if(Math.round(this.speed) < this.max_speed)
            {
                this.speed += this.acceleration;
            }
        }
        if(Gdx.input.isKeyPressed(Keys.SPACE))
        {
            if(!this.firing)
            {
                this.shoot();
                this.firing = true;
            }
        }
        else
        {
            this.firing = false;
        }
        Iterator<Rocket> it = this.rockets.iterator();
        while (it.hasNext())
        {
            Rocket rkt = it.next();
            if(rkt.getCreated() < System.currentTimeMillis()-this.rocket_timealive)
            {
                it.remove();
            }
            else
            {
                rkt.update();
            }
        }
    }
    
    /**
     * Function which adds {@link Rocket} objects to a list and enables the ship to fire
     * them with a touch of a button.
     */
    public void shoot()
    {
        float x = (float) this.getX() + 
        		  (float) Math.cos(Math.toRadians(this.getAngle()))*(float)this.getWidth()/4;
        float y = (float) this.getY() + 
        		  (float) Math.sin(Math.toRadians(this.getAngle()))*(float)this.getheight()/4;
        this.rockets.add(new Rocket(this.getAngle(),(int)Math.round(x),(int)Math.round(y),
        		         this.speed+this.rocket_speed, this.getVertexBuffer()));
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void display()
    {
        Gdx.gl11.glColor4f(0f, 1f, 3f, 1f);
        super.display();
        for(Rocket rkt : this.rockets)
        {
            rkt.display();
        }
    }

}
