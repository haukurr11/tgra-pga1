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
	
    public Ship(int x, int y,FloatBuffer vertexBuffer)
    {
    	super(x,y,vertexBuffer);
        this.setAngle(90);
        this.points = 4;
        this.index = 0;
        this.rockets = new LinkedList<Rocket>();
        this.setHeight(50);
        this.setWidth(50);
    }
    
    @Override
    public void update() {
    	if(Gdx.input.isKeyPressed(Keys.RIGHT)){
            this.setAngle(this.getAngle() -10);
        }
        if(Gdx.input.isKeyPressed(Keys.LEFT)){
        	this.setAngle(this.getAngle() + 10);
        }
        if(Gdx.input.isKeyPressed(Keys.UP)){
        	this.moveForward(15);
        }
        if(Gdx.input.isKeyPressed(Keys.DOWN)){
     	   this.moveBackwards(15);
        }
        if(Gdx.input.isKeyPressed(Keys.SPACE)){
      	   this.shoot();
         }
        Iterator<Rocket> it = this.rockets.iterator();
        while (it.hasNext()) {
            Rocket rkt = it.next();
            if(rkt.getCreated() < System.currentTimeMillis()-1000) {
                it.remove();
			}else {
                rkt.update();
			}
        }

    }
    public void shoot() {
    	float x = (float) this.getX() + (float) Math.cos(Math.toRadians(this.getAngle()))*(float)30.0;
  	    float y = (float) this.getY() + (float) Math.sin(Math.toRadians(this.getAngle()))*(float)30.0;
    	this.rockets.add(new Rocket(this.getAngle(),(int)Math.round(x),(int)Math.round(y),this.getVertexBuffer()));
    	try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @Override
	public void display()
	{
		super.display();
		for(Rocket rkt : this.rockets) {
			rkt.display();
		}

	}
}
