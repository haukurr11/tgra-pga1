package is.ru.tgra;

public class GraphicObject
{
	private int width;
	private int height;
    private int x, y;
    private int angle;
    private float vertices [];
    
    public GraphicObject(int value_width,int value_height, int value_x, int value_y)
    {
    	this.width = value_width;
    	this.height = value_height;
    	this.x = value_x;
    	this.y = value_y;
    	this.angle = 0;
    	this.vertices = new float[] {};
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
    
    public int getX()
    {
    	return this.x;
    }
    
    public void setX(int value)
    {
    	this.x = value;
    }
    
    public int getY()
    {
    	return this.y;
    }
    
    public void setY(int value)
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
    
    public void setVertices(int size)
    {
    	this.vertices = new float[] {0,0, size/2,size,  size/2,size/5,  size,0};
    }
    
}
