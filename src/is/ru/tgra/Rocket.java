package is.ru.tgra;

import java.nio.FloatBuffer;

public class Rocket extends GraphicObject{
	public Rocket(int index,int x, int y,FloatBuffer vertexBuffer)
    {
    	super(index,1,10,x,y,vertexBuffer);
    	this.setVertices(new float[] {0,0, 0,this.getheight(), this.getheight()/3,0,  this.getheight()/3,this.getheight()});
    	this.points = 4;
    	this.setAngle(90);
    }
}
