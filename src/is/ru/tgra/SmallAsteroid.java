package is.ru.tgra;

import java.nio.FloatBuffer;

public class SmallAsteroid extends Asteroid{
	public SmallAsteroid(int angle, int x, int y,FloatBuffer vertexBuffer)	 {
		super(angle, x, y,vertexBuffer);
		this.index = 16;
	}
	
	@Override
	public void update() {
	   this.moveForward(3);
	}

}
