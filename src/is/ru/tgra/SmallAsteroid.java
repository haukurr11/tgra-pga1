package is.ru.tgra;

import java.nio.FloatBuffer;

/**
 * This class creates the small type of asteroids when an {@link MediumAsteroid} object is destroyed.
 * It inherits methods from the {@link Asteroid} class but creates a new index in the vertex
 * buffer for itself. It also moves even faster on the screen.
 */
public class SmallAsteroid extends Asteroid
{
    /**
     * A constructor which sets the angle of the small asteroid, the position on the screen
     * and the vertex buffer for the vertices of the object.
     * 
     * @param angle			The angle of the object.
     * @param x				The position on the x-grid.
     * @param y				The position on the y-grid.
     * @param vertexBuffer	The vertex buffer which it will be included.
     */
	public SmallAsteroid(int angle, int x, int y,FloatBuffer vertexBuffer)
    {
        super(angle, x, y,vertexBuffer);
        this.index = 16;
        this.radius /= 4;
        this.width = 13;
        this.height = 13;
    }
	
	/**
	 * {@inheritDoc}
	 */
    @Override
    public void update()
    {
        this.moveForward(3);
    }

}
