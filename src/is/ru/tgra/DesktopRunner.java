package is.ru.tgra;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
public class DesktopRunner
{
    public static void main(String[] args)
    {
    	LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
    	config.resizable = false;
    	config.width=800;
    	config.height=600;
    	config.title = "Asteroids by Aron&Haukur";
    	LwjglApplication app = new LwjglApplication(new Game(), config);
    }
}