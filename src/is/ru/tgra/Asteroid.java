package is.ru.tgra;

import java.nio.FloatBuffer;
import java.util.Random;

import com.badlogic.gdx.Gdx;

public class Asteroid extends GraphicObject{
	int color;
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
	@Override 
	public void display(){
		if(this.color == 1) {
		Gdx.gl11.glColor4f(1f,.5f,.7f,1.0f);
		}
		else {
			Gdx.gl11.glColor4f(0f,.3f,.7f,1.0f);
		}
		super.display();
	}
	@Override
	public void update() {
	   this.moveForward(1);
	}
}
