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
    
	public GraphicObject(int value_width,int value_height, int value_x, int value_y,int points)
    {
    	this.width = value_width;
    	this.height = value_height;
    	this.x = value_x;
    	this.y = value_y;
    	this.angle = 0;
    	this.vertices = new float[] {};
    	this.vertexBuffer = BufferUtils.newFloatBuffer(points);
    }
    
	public void display() 
	{
		Gdx.gl11.glPushMatrix();
        Gdx.gl11.glTranslatef(this.getX(),this.getY(), 0);
        Gdx.gl11.glRotatef(this.angle-90, 0,0, 1);
        Gdx.gl11.glTranslatef(-this.getheight()/2,-this.getheight()/2, 0);
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, 4);
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
    	this.angle = value;
    }
    
    public float[] getVertices()
    {
    	return this.vertices;
    }
    
    public void setVertices(float[] vertices)
    {
    	this.vertices = vertices;
    	this.vertexBuffer.put(vertices);
        this.vertexBuffer.rewind();
        Gdx.gl11.glVertexPointer(2, GL11.GL_FLOAT, 0, this.vertexBuffer);
    }

    public FloatBuffer getVertexBuffer() {
		return vertexBuffer;
	}

	public void setVertexBuffer(FloatBuffer vertexBuffer) {
		this.vertexBuffer = vertexBuffer;
		
	}
	
	public void update() {
	}

}
