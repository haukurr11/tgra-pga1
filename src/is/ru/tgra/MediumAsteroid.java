package is.ru.tgra;

import java.lang.reflect.Method;
import java.nio.FloatBuffer;

/**
 * This class creates the medium type of asteroids when an {@link Asteroid} object is destroyed.
 * It inherits methods from the {@link Asteroid} class but creates a new index in the vertex
 * buffer for itself. It also moves faster on the screen.
 */

public class MediumAsteroid extends Asteroid
{
    /**
     * A constructor which uses the same method from {@link Asteroid} class to create the
     * graphical representation of a MediumAsteroid.
     * 
     * @param angle			The angle of the object.
     * @param x				The position of the object on the x-grid.
     * @param y				The position of the object on the y-grid.
     * @param vertexBuffer	The vertex buffer to input the vertices of the object.
     */
	public MediumAsteroid(int angle, int x, int y,FloatBuffer vertexBuffer)
    {
        super(angle, x, y,vertexBuffer);
        this.index = 12;
    }
	
	/**
	 * {@inheritDoc}
	 */
    @Override
    public void update()
    {
        this.moveForward(2);
    }

}
