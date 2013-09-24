package is.ru.tgra;

import java.awt.Rectangle;
import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.BufferUtils;

/**
 * An abstract class for all visible objects in the game. It's objective is to supply the other
 * objects with configurations on width and height of the object, the position on the grid, 
 * the angle of it, a float array of the vertices, a vertex buffer to hold these arrays and
 * an index number of where it is positioned in the buffer. It also applies the display,
 * movingForward and update functions for the other classes to inherit and override with
 * further logic if needed.
 */

public abstract class GraphicObject
{
    protected int width;
    protected int height;
    protected float x, y;
    private int angle;
    private float vertices [];
    private FloatBuffer vertexBuffer = null;
    protected int index = 0;
    protected int points = 4;
    private int moving_angle;
    protected int radius;

    /**
     * A constructor which initializes the position of an GraphicObject and creates a new
     * float array for the vertices of the object. This is implemented so other derived
     * classes can use the "super" method to create it's graphical representations.
     * 
     * @param value_x		Determines the position of the object on the x-grid
     * @param value_y		Determines the position of the object on the y-grid
     * @param vertexBuffer	Determines what vertex buffer is being inserted in
     */
    public GraphicObject(int value_x, int value_y,FloatBuffer vertexBuffer)
    {
        this.width = 0;
        this.height = 0;
        this.x = value_x;
        this.y = value_y;
        this.angle = 0;
        this.vertices = new float[] {};
        this.vertexBuffer = vertexBuffer;
        this.moving_angle=this.angle;
        this.radius = 50;
    }
    
    /**
     * A function which displays the graphical object on the screen. 
     */
    public void display()
    {
        Gdx.gl11.glPushMatrix();
        Gdx.gl11.glTranslatef(this.getX(),this.getY(), 0);
        Gdx.gl11.glRotatef(this.angle-90, 0,0, 1);
        Gdx.gl11.glTranslatef(-this.getheight()/2,-this.getheight()/2, 0);
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, this.index, this.points);
        Gdx.gl11.glPopMatrix();
    }
    
    /**
     * A function which moves the graphical object on the screen.
     * @param length	Determines the length which the object is moved.
     */
    public void moveForward(double length)
    {
        float x = (float) this.getX() + (float) Math.cos(Math.toRadians(this.moving_angle))*(float)length;
        float y = (float) this.getY() + (float) Math.sin(Math.toRadians(this.moving_angle))*(float)length;
        this.setX(x);
        this.setY(y);
        if(this.getX()<=0)
        {
            this.setX(Gdx.graphics.getWidth());
        }
        else if(this.getY() <= 0)
        {
            this.setY(Gdx.graphics.getHeight());
        }
        else if(Gdx.graphics.getWidth()< this.getX())
        {
            this.setX(0);
        }
        else if(Gdx.graphics.getHeight()< this.getY())
        {
            this.setY(0);
        }
    }
    
    /**
     * Function which handles the logic for graphical object collision. It creates
     * two {@link Rectangle} objects and initializes the position of these rectangles on
     * the screen, serving as a hit box for the objects.
     * 
     * @param obj	The object which the function creates a hit box for collision.
     * @return		Returns True if the two rectangles collide.
     */
    public boolean collides(GraphicObject obj)
    {
        int centerX1 = (int)this.x + this.width/2;
        int centerY1 = (int)this.y + this.height/2;

        int centerX2 = (int)obj.x + obj.width/2;
        int centerY2 = (int)obj.y + obj.height/2;
        
        double dist = Math.sqrt((centerX1-centerX2)*(centerX1-centerX2) + (centerY1-centerY2)*(centerY1-centerY2)); 
        if(dist <= this.radius+obj.radius)
        	return true;
        return false;
    }
    
    /**
     * Function which is for derived classes to override. It updates the position of the object
     * for example if the graphical object is moving on the screen using the moveForward
     * function.
     */
    public void update()
    {
    }
    
    /**
     * Getter function for the rotation of the angle of the object.
     * @return	Returns the rotation transformation
     */
    public int getMoving_angle()
    {
        return moving_angle;
    }
    
    /**
     * Setter function which determines how much the angle of the object rotates.
     * @param moving_angle	value of how much the angle of the object rotates.
     */
    public void setMoving_angle(int moving_angle)
    {
        this.moving_angle = moving_angle % 360;
    }
    
    /**
     * Getter function for the width of the object.
     * @return	The width of the object.
     */
    public int getWidth()
    {
        return this.width;
    }
    
    /**
     * Setter function which determines the width of an object.
     * @param value	The width of an object.
     */
    public void setWidth(int value)
    {
        this.width = value;
    }
    
    /**
     * Getter function for the height of the object.
     * @return	The width of the object.
     */
    public int getheight()
    {
        return this.height;
    }
    
    /**
     * Setter function which determines the height of an object.
     * @param value	The height of an object.
     */
    public void setHeight(int value)
    {
        this.height = value;
    }
    
    /**
     * Getter function for the position on the x-grid.
     * @return	Position on the x-grid.
     */
    public float getX()
    {
        return this.x;
    }
    
    /**
     * Setter function for the position of an object on the x-grid.
     * @param value	The position on the x-grid.
     */
    public void setX(float value)
    {
        this.x = value;
    }
    
    /**
     * Getter function for the position on the y-grid.
     * @return	Position on the y-grid.
     */
    public float getY()
    {
        return this.y;
    }
    
    /**
     * Setter function for the position of an object on the y-grid.
     * @param value	The position on the y-grid.
     */
    public void setY(float value)
    {
        this.y = value;
    }
    
    /**
     * Getter function for the angle of an object.
     * @return	The angle of an object.
     */
    public int getAngle()
    {
        return this.angle;
    }
    
    /**
     * Setter function for the angle of an object.
     * @param value	Value of the angle which an object has.
     */
    public void setAngle(int value)
    {
        this.angle = value % 360;
    }
    
    /**
     * Getter function for the float array of vertices of the object.
     * @return	Vertices of the object.
     */
    public float[] getVertices()
    {
        return this.vertices;
    }
    
    /**
     * Setter function for the vertices of an object.
     * @param vertices	An array of float values which represents vertices of an object.
     */
    public void setVertices(float[] vertices)
    {
        this.vertices = vertices;
    }
    
    /**
     * Getter function for the vertex buffer in use.
     * @return	The vertex buffer which to input the vertices of an object.
     */
    public FloatBuffer getVertexBuffer()
    {
        return vertexBuffer;
    }
    
    /**
     * Setter function for the vertex buffer in use.
     * @param vertexBuffer	The vertex buffer which holds the vertices of all objects.
     */
    public void setVertexBuffer(FloatBuffer vertexBuffer)
    {
        this.vertexBuffer = vertexBuffer;
    }

}
