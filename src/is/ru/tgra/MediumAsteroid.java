package is.ru.tgra;

import java.nio.FloatBuffer;

public class MediumAsteroid extends Asteroid{
	public MediumAsteroid(int angle, int x, int y,FloatBuffer vertexBuffer)	 {
		super(angle, x, y,vertexBuffer);
		this.index = 12;
	}

}
