package is.ru.tgra;

import java.nio.FloatBuffer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;


public class Ship extends GraphicObject
{
    public Ship(int index,int size, int x, int y,FloatBuffer vertexBuffer)
    {
    	super(index, size,size,x,y,vertexBuffer);
        this.setVertices(new float[] {0,0, this.getheight()/2,
        		                           this.getheight(),  
        	                               this.getheight()/2,
        	                               this.getheight()/5,
        		                           this.getheight(),0});
        this.setAngle(90);
        this.points = 4;
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
        	float x = (float) this.getX() + (float) Math.cos(Math.toRadians(this.getAngle()))*15;
      	    float y = (float) this.getY() + (float) Math.sin(Math.toRadians(this.getAngle()))*15;
        	this.setX(x);
        	this.setY(y);
        }
        if(Gdx.input.isKeyPressed(Keys.DOWN)){
     	   this.setY( this.getY() + 15);
        }
        if(Gdx.input.isKeyPressed(Keys.SPACE)){
      	   this.shoot();
         }
    }
    public void shoot() {

    }
}
