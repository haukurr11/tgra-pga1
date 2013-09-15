package is.ru.tgra;

import java.nio.FloatBuffer;

public class Rocket extends GraphicObject{
	private long created;
	public long getCreated() {
		return created;
	}
	public Rocket(int angle, int x, int y,FloatBuffer vertexBuffer)
    {
    	super(x,y,vertexBuffer);
    	this.points = 4;
    	this.index = 4;
    	this.setAngle(angle);
    	this.created = System.currentTimeMillis();
    }
	@Override
	public void update() {
	   this.moveForward(10);
	}
}
