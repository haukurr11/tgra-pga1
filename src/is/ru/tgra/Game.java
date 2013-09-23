package is.ru.tgra;

import java.nio.FloatBuffer;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.utils.BufferUtils;

/**
 * This class handles the game as a whole, initializing the objects which will be displayed
 * in the gameplay. It implements the {@link ApplicationListener} to render the whole display
 * and update when keys are pressed or game state changes.
 */

public class Game implements ApplicationListener
{
    private Ship humanPlayer;
    private List<Asteroid> asteroids;
    
    private FloatBuffer vertexBuffer;
    private Random rand;
    private ScreenText scoretext;
    private ScreenText leveltext;
    private ScreenText gameovertext;

    private int init_shipx;
    private int init_shipy;
    private int level;
    private int score;
    private boolean gameover;
    
    /**
     * Creates a new game state, and initializes all the objects which will appear in 
     * the game.
     */
    @Override
    public void create()
    {
        this.gameover = false;
        this.score = 0;
        this.init_shipx = Gdx.graphics.getWidth()/2-25;
        this.init_shipy = Gdx.graphics.getHeight()/2;
        this.level = 0;
        float[] asteroid_vectors_big = new float[] {0,0, 2,50,  45,0,  30,50};
        float[] asteroid_vectors_medium = new float[]
        {
            asteroid_vectors_big[0]/2,asteroid_vectors_big[1]/2,
            asteroid_vectors_big[2]/2,asteroid_vectors_big[3]/2,
            asteroid_vectors_big[4]/2,asteroid_vectors_big[5]/2,
            asteroid_vectors_big[6]/2,asteroid_vectors_big[7]/2
        };
        float[] asteroid_vectors_small = new float[]
        {
            asteroid_vectors_medium[0]/2,asteroid_vectors_medium[1]/2,
            asteroid_vectors_medium[2]/2,asteroid_vectors_medium[3]/2,
            asteroid_vectors_medium[4]/2,asteroid_vectors_medium[5]/2,
            asteroid_vectors_medium[6]/2,asteroid_vectors_medium[7]/2
        };
        this.vertexBuffer = BufferUtils.newFloatBuffer(40);
        this.vertexBuffer.put(new float[] {0,0, 10,20, 10,2, 20,0}); //SPACESHIP
        this.vertexBuffer.put(new float[] {0,0, 0,10, 2,0,  2 ,10}); //ROCKET
        this.vertexBuffer.put(asteroid_vectors_big); //BIG ASTEROIDS
        this.vertexBuffer.put(asteroid_vectors_medium); //MEDIUM ASTEROIDS
        this.vertexBuffer.put(asteroid_vectors_small); //SMALL ASTEROIDS

        this.asteroids = new LinkedList<Asteroid>();
        this.humanPlayer = new Ship(this.init_shipx,this.init_shipy,this.vertexBuffer);
        this.rand = new Random();

        this.scoretext = new ScreenText(600,100, this.vertexBuffer);
        this.leveltext = new ScreenText(130,100, this.vertexBuffer);
        this.gameovertext = new ScreenText(280,300,"GAME OVER! press enter to try again", this.vertexBuffer);

        this.vertexBuffer.rewind();
        Gdx.gl11.glVertexPointer(2, GL11.GL_FLOAT, 0, this.vertexBuffer);
        Gdx.gl11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
        Gdx.gl11.glClearColor(0, 0, .09f, 1f);
    }
    
    /**
     * Function which handles the display of all objects represented in this game.
     */
    private void display()
    {
        Gdx.gl11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        Gdx.gl11.glMatrixMode(GL11.GL_MODELVIEW);
        Gdx.gl11.glLoadIdentity();
        Gdx.gl11.glColor4f(1f, 1f, 1f, 1f);

        if(this.gameover)
        {
            this.gameovertext.display();
            return;
        }
        
        for(Asteroid ast : this.asteroids)
        {
            ast.display();
        }
        
        this.humanPlayer.display();
        this.scoretext.display();
        this.leveltext.display();
    }
    
    /**
     * Function which creates the next level when the player has finished the current one.
     */
    private void nextLevel()
    {
        this.asteroids = new LinkedList<Asteroid>();
        this.humanPlayer.setX(this.init_shipx);
        this.humanPlayer.setY(this.init_shipy);
        this.humanPlayer.setSpeed(0);
        this.humanPlayer.setAngle(90);
        this.level++;
        for(int i =0; i<this.level*(this.level-2)+10; i++)
        {
            int x,y;
            do
            {
                x = this.rand.nextInt(Gdx.graphics.getWidth());
            }
            while( Math.abs(humanPlayer.getX()-x) < 200);

            do
            {
                y = this.rand.nextInt(Gdx.graphics.getHeight());
            }
            while( Math.abs(humanPlayer.getY()-y) < 200);
            Asteroid a = new Asteroid(this.rand.nextInt(360), x, y, this.vertexBuffer);
            this.asteroids.add(a);
        }

    }
    /**
     * Function which updates the screen whenever buttons are pushed or the state of the game
     * changes.
     */
    private void update()
    {
        if(this.gameover)
        {
            if(Gdx.input.isKeyPressed(Keys.ENTER))
            {
                this.gameover = false;
            }
            return;
        }
        this.scoretext.setText("Score: " + this.score);
        this.leveltext.setText("Level: " + this.level);
        if(this.asteroids.size() == 0)
        {
            this.nextLevel();
        }

        Iterator<Asteroid> ast_it = this.asteroids.iterator();
        LinkedList<Asteroid> ll = new LinkedList<Asteroid>();
        while (ast_it.hasNext())
        {
            GraphicObject ast = ast_it.next();
            if(ast != this.humanPlayer)
            {
                Iterator<Rocket> it = this.humanPlayer.getRockets().iterator();
                if(ast.collides(this.humanPlayer))
                {
                    this.level = 0;
                    this.score = 0;
                    this.gameover = true;
                    this.nextLevel();
                    return;
                }
                while (it.hasNext())
                {
                    Rocket rkt = it.next();
                    if(rkt.collides(ast))
                    {
                        if(ast.getClass()==Asteroid.class)
                        {
                            MediumAsteroid ma1 = new MediumAsteroid(this.rand.nextInt(360),
                            		                    (int)ast.getX(),(int)ast.getY(),this.vertexBuffer);
                            MediumAsteroid ma2 = new MediumAsteroid(this.rand.nextInt(360),
                            		                    (int)ast.getX(),(int)ast.getY(),this.vertexBuffer);
                            ll.add(ma1);
                            ll.add(ma2);
                            this.score += (10 + this.level);
                        }
                        else if(ast.getClass()==MediumAsteroid.class)
                        {
                            SmallAsteroid ma1 = new SmallAsteroid(this.rand.nextInt(360),
                            		                    (int)ast.getX(),(int)ast.getY(),this.vertexBuffer);
                            SmallAsteroid ma2 = new SmallAsteroid(this.rand.nextInt(360),
                            		                    (int)ast.getX(),(int)ast.getY(),this.vertexBuffer);
                            ll.add(ma1);
                            ll.add(ma2);
                            this.score += (20 + this.level);
                        }
                        else if(ast.getClass()==SmallAsteroid.class)
                        {
                        }
                        it.remove();
                        ast_it.remove();
                        this.score += (30 + this.level);
                    }
                }
            }
            ast.update();
        }
        this.asteroids.addAll(ll);
        this.humanPlayer.update();
    }
    /**
     * Function which runs the display and update functions and renders the objects on the
     * screen.
     */
    @Override
    public void render()
    {
        this.display();
        this.update();
    }

    @Override
    public void resize(int width, int height)
    {
    }
    
    @Override
    public void resume()
    {
    }
    @Override
    public void dispose()
    {
    }
    @Override
    public void pause()
    {
    }
}

