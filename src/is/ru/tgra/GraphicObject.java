package is.ru.tgra;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.BufferUtils;

public class GraphicObject
{
	private int width;
	private int height;
    private float x, y;
    private int angle;
    private float vertices [];
    private FloatBuffer vertexBuffer = null;
    protected int index = 0;
    protected int points = 4;
    
	public GraphicObject(int value_x, int value_y,FloatBuffer vertexBuffer)
    {
    	this.width = 0;
    	this.height = 0;
    	this.x = value_x;
    	this.y = value_y;
    	this.angle = 0;
    	this.vertices = new float[] {};
    	this.vertexBuffer = vertexBuffer;
    }
    
	public void display()
	{

		Gdx.gl11.glPushMatrix();
        Gdx.gl11.glTranslatef(this.getX(),this.getY(), 0);
        Gdx.gl11.glRotatef(this.angle-90, 0,0, 1);
        Gdx.gl11.glTranslatef(-this.getheight()/2,-this.getheight()/2, 0);
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, this.index, this.points);
        Gdx.gl11.glPopMatrix();

	}
	
    public int getWidth()
    {
    	return this.width;
    }
    
    public void setWidth(int value)
    {
    	this.width = value;
    }
    
    public int getheight()
    {
    	return this.height;
    }
    
    public void setHeight(int value)
    {
    	this.height = value;
    }
    
    public float getX()
    {
    	return this.x;
    }
    
    public void setX(float value)
    {
    	this.x = value;
    }
    
    public float getY()
    {
    	return this.y;
    }
    
    public void setY(float value)
    {
    	this.y = value;
    }
    
    public int getAngle()
    {
    	return this.angle;
    }
    
    public void setAngle(int value)
    {
    	this.angle = value % 360;
    }
    
    public float[] getVertices()
    {
    	return this.vertices;
    }
    
    public void setVertices(float[] vertices)
    {
    	this.vertices = vertices;
    }

    public FloatBuffer getVertexBuffer() {
		return vertexBuffer;
	}

	public void setVertexBuffer(FloatBuffer vertexBuffer) {
		this.vertexBuffer = vertexBuffer;
		
	}
	
	public void update() {
	}
	
	public void moveForward(double length) {

		float x = (float) this.getX() + (float) Math.cos(Math.toRadians(this.getAngle()))*(float)length;
  	    float y = (float) this.getY() + (float) Math.sin(Math.toRadians(this.getAngle()))*(float)length;
    	this.setX(x);
    	this.setY(y);
    	if(this.getX()<=0) {
    		this.setX(Gdx.graphics.getWidth());
    	}
    	else if(this.getY() <= 0) {
    		this.setY(Gdx.graphics.getHeight());
    	}
    	else if(Gdx.graphics.getWidth()< this.getX()) {
    		this.setX(0);
    	}
    	else if(Gdx.graphics.getHeight()< this.getY()) {
    		this.setY(0);
    	}
	}
	public void moveBackwards(int length) {
		float x = (float) this.getX() - (float) Math.cos(Math.toRadians(this.getAngle()))*length;
  	    float y = (float) this.getY() - (float) Math.sin(Math.toRadians(this.getAngle()))*length;
    	this.setX(x);
    	this.setY(y);
	}

}
