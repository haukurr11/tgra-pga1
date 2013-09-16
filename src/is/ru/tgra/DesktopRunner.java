package is.ru.tgra;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
public class DesktopRunner {
    public static void main(String[] args) {
        new LwjglApplication(new Game(), "Asteroids", 800, 600, false);
    }
}