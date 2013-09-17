package is.ru.tgra;

import java.nio.FloatBuffer;
import java.util.Random;

import com.badlogic.gdx.Gdx;

public class Rocket extends GraphicObject{
	private long created;
	private float red;
	private float green;
	private float blue;
	private double speed;
	public long getCreated() {
		return created;
	}
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
	@Override
	public void update() {
	   this.setMoving_angle(this.getAngle());
	   this.moveForward(this.speed);
	}
	
	@Override 
	public void display(){
		Random rand = new Random();

        Gdx.gl11.glColor4f(this.red,this.green,this.blue,1.0f);
		super.display();
		Gdx.gl11.glColor4f(1f,1f,1f,1.0f);
	}
}
