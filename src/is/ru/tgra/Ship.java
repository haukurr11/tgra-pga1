package is.ru.tgra;

import java.nio.FloatBuffer;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;


public class Ship extends GraphicObject
{
	private List<Rocket> rockets;
	private double speed;
	private double acceleration;
	private double deceleration;
    private int max_speed;
	private boolean firing;
	private int rocket_speed;
	private int rocket_timealive;
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
        this.acceleration = 0.5;
        this.deceleration = 0.1;
        this.max_speed = 10;
        this.rocket_speed = 10;
        this.rocket_timealive = 500;

    }
    
    @Override
    public void update() {
    	this.moveForward(this.speed);
    	if(Gdx.input.isKeyPressed(Keys.RIGHT)){
            this.setAngle(this.getAngle() -10);
        }
        if(Gdx.input.isKeyPressed(Keys.LEFT)){
        	this.setAngle(this.getAngle() + 10);
        }
        if(Gdx.input.isKeyPressed(Keys.UP)){
        	if(this.speed <= 0 ) {
        		this.speed = 0.01;
        	}
        	if(Math.round(this.speed) <= this.max_speed) {
        	this.speed += this.acceleration;

        	}
        }
        else {
        	if(Math.round(this.speed) >0) {
        	    this.speed -= this.deceleration;
        	}
        	else {
        		this.speed = 0;
        	}
        }
        if(Gdx.input.isKeyPressed(Keys.SPACE)){
           if(!this.firing) {
      	      this.shoot();
      	      this.firing = true;
           }
         }
        else {
        	this.firing = false;
        }
        Iterator<Rocket> it = this.rockets.iterator();
        while (it.hasNext()) {
            Rocket rkt = it.next();
            if(rkt.getCreated() < System.currentTimeMillis()-this.rocket_timealive) {
                it.remove();
			}else {
                rkt.update();
			}
        }
    }
    public void shoot() {
    	float x = (float) this.getX() + (float) Math.cos(Math.toRadians(this.getAngle()))*(float)this.getWidth()/4;
  	    float y = (float) this.getY() + (float) Math.sin(Math.toRadians(this.getAngle()))*(float)this.getheight()/4;
    	this.rockets.add(new Rocket(this.getAngle(),(int)Math.round(x),(int)Math.round(y),this.speed+this.rocket_speed,  this.getVertexBuffer()));
    }
    @Override
	public void display()
	{
        Gdx.gl11.glColor4f(0f, 1f, 3f, 1f);
		super.display();
		for(Rocket rkt : this.rockets) {
			rkt.display();
		}

	}
    @Override 
    public void moveForward(double speed) {
    	super.moveForward(speed);

    }
}
