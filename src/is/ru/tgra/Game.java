package is.ru.tgra;

import java.nio.FloatBuffer;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.BufferUtils;

public class Game implements ApplicationListener {

    private Ship humanPlayer;
    private List<GraphicObject> objects;
    private List<Asteroid> asteroids;
    private FloatBuffer vertexBuffer = null;
    private int level;
    private Random rand;
    private int init_shipx;
    private int init_shipy; 
    private ScreenText screentext;
    @Override
    public void create() {

    	this.init_shipx = Gdx.graphics.getWidth()/2-25;
    	this.init_shipy = Gdx.graphics.getHeight()/2;
    	this.level = 0;
    	float[] asteroid_vectors_big = new float[] {0,0, 2,50,  45,0,  30,50};
    	float[] asteroid_vectors_medium = new float[] {
    			asteroid_vectors_big[0]/2,asteroid_vectors_big[1]/2,
    			asteroid_vectors_big[2]/2,asteroid_vectors_big[3]/2,
    			asteroid_vectors_big[4]/2,asteroid_vectors_big[5]/2,
    			asteroid_vectors_big[6]/2,asteroid_vectors_big[7]/2
    			};
    	float[] asteroid_vectors_small = new float[] {
    			asteroid_vectors_medium[0]/2,asteroid_vectors_medium[1]/2,
    			asteroid_vectors_medium[2]/2,asteroid_vectors_medium[3]/2,
    			asteroid_vectors_medium[4]/2,asteroid_vectors_medium[5]/2,
    			asteroid_vectors_medium[6]/2,asteroid_vectors_medium[7]/2
    			};
    	this.vertexBuffer = BufferUtils.newFloatBuffer(100);
    	this.vertexBuffer.put(new float[] {0,0, 10,20, 10,2, 20,0}); //SPACESHIP
    	this.vertexBuffer.put(new float[] {0,0, 0,10, 2,0,  2 ,10}); //ROCKET
    	this.vertexBuffer.put(asteroid_vectors_big); //BIG ASTEROIDS
    	this.vertexBuffer.put(asteroid_vectors_medium); //MEDIUM ASTEROIDS
    	this.vertexBuffer.put(asteroid_vectors_small); //SMALL ASTEROIDS

    	this.objects = new LinkedList<GraphicObject>();
    	this.asteroids = new LinkedList<Asteroid>();
    	this.humanPlayer = new Ship(this.init_shipx,this.init_shipy,this.vertexBuffer);
    	this.objects.add(humanPlayer);
    	this.rand = new Random();

        this.vertexBuffer.rewind();

    	this.screentext = new ScreenText(100,100,"hello world", this.vertexBuffer);

        Gdx.gl11.glVertexPointer(2, GL11.GL_FLOAT, 0, this.vertexBuffer);
        
        Gdx.gl11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
        Gdx.gl11.glClearColor(0, 0, .09f, 1f);
        
    }

    @Override
    public void dispose() {
    }

    @Override
    public void pause() {
    }
    
    private void display(){
    	
        Gdx.gl11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        Gdx.gl11.glMatrixMode(GL11.GL_MODELVIEW);
        Gdx.gl11.glLoadIdentity();
        Gdx.gl11.glColor4f(1f, 1f, 1f, 1f);

        for(GraphicObject obj : this.objects) {
        	obj.display();
        } 
        this.screentext.display();

      }
    
    private void nextLevel() {
    	this.objects = new LinkedList<GraphicObject>();
    	this.objects.add(this.humanPlayer);
    	this.humanPlayer.setX(this.init_shipx);
    	this.humanPlayer.setY(this.init_shipy);
    	this.humanPlayer.setSpeed(0);
    	this.humanPlayer.setAngle(90);
    	this.level++;
    	for(int i =0;i<this.level*(this.level-2)+10;i++) {
 		   int x,y;
 		   do {
     		   x = this.rand.nextInt(Gdx.graphics.getWidth());
 		   }while( Math.abs(humanPlayer.getX()-x) < 200);
 		   
 		   do {
     		   y = this.rand.nextInt(Gdx.graphics.getHeight());
 		   }while( Math.abs(humanPlayer.getY()-y) < 200);
 		   Asteroid a = new Asteroid(this.rand.nextInt(360), x, y, this.vertexBuffer);
 		   this.objects.add(a); 
 		   this.asteroids.add(a);
 	     }
 	
    }
    
    private void update(){
    	if(this.objects.size() == 1) {
    		this.nextLevel();
    	}

        Iterator<GraphicObject> obj_it = this.objects.iterator();
        LinkedList<GraphicObject> ll = new LinkedList<GraphicObject>();
        while (obj_it.hasNext()) {
        	GraphicObject obj = obj_it.next();
    		if(obj != this.humanPlayer) {
    	        Iterator<Rocket> it = this.humanPlayer.getRockets().iterator();
    	        if(obj.collides(this.humanPlayer)) {
    	        	this.level = 0;
    	        	this.nextLevel();
    	        	return;
    	        }
    	        while (it.hasNext()) {
    	            Rocket rkt = it.next();
    	            if(rkt.collides(obj)) {
    	            	if(obj.getClass()==Asteroid.class) {
     	            	   MediumAsteroid ma1 = new MediumAsteroid(this.rand.nextInt(360),(int)obj.getX(),(int)obj.getY(),this.vertexBuffer);
     	            	   MediumAsteroid ma2 = new MediumAsteroid(this.rand.nextInt(360),(int)obj.getX(),(int)obj.getY(),this.vertexBuffer);
     	            	   ll.add(ma1);
     	            	   ll.add(ma2);
     	            	}
    	            	else if(obj.getClass()==MediumAsteroid.class) {
      	            	   SmallAsteroid ma1 = new SmallAsteroid(this.rand.nextInt(360),(int)obj.getX(),(int)obj.getY(),this.vertexBuffer);
      	            	   SmallAsteroid ma2 = new SmallAsteroid(this.rand.nextInt(360),(int)obj.getX(),(int)obj.getY(),this.vertexBuffer);
      	            	   ll.add(ma1);
      	            	   ll.add(ma2);
      	            	}
    	            	else if(obj.getClass()==MediumAsteroid.class) {
       	            	}
                		it.remove();
                		obj_it.remove();
                	}
    	        }
    		}
            obj.update();
        }
        this.objects.addAll(ll);
    	
    	
    		

    }
    
    
    
    @Override
    public void render() {
        this.display();
        this.update();
    }

    @Override
    public void resize(int width, int height) {
        Gdx.gl11.glMatrixMode(GL11.GL_PROJECTION);
        Gdx.gl11.glLoadIdentity();
        Gdx.glu.gluOrtho2D(Gdx.gl11, 0, width, 0, height);
        Gdx.gl11.glViewport(0, 0, width, height);
        Gdx.gl11.glMatrixMode(GL11.GL_MODELVIEW_MATRIX);    
    }

    @Override
    public void resume() {
    }
}

