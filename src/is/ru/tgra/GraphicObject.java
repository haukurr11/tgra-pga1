package is.ru.tgra;

import java.awt.Rectangle;
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
    private int moving_angle;

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

    public boolean collides(GraphicObject obj)
    {
        Rectangle rect1 = new Rectangle();
        rect1.x = (int)this.x;
        rect1.y = (int)this.y;
        rect1.width = 50;
        rect1.height = 50;
        Rectangle rect2 = new Rectangle();
        rect2.x = (int)obj.getX();
        rect2.y = (int)obj.getY();
        rect2.width = 50;
        rect2.height = 50;
        return rect1.intersects(rect2);
    }

    public void update()
    {
    }

    public int getMoving_angle()
    {
        return moving_angle;
    }

    public void setMoving_angle(int moving_angle)
    {
        this.moving_angle = moving_angle % 360;
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

    public FloatBuffer getVertexBuffer()
    {
        return vertexBuffer;
    }

    public void setVertexBuffer(FloatBuffer vertexBuffer)
    {
        this.vertexBuffer = vertexBuffer;
    }

}
