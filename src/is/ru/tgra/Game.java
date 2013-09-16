package is.ru.tgra;

import java.nio.FloatBuffer;
import java.util.LinkedList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.BufferUtils;

public class Game implements ApplicationListener {

    private Ship humanPlayer;
    private List<GraphicObject> objects;
    private FloatBuffer vertexBuffer = null;

    @Override
    public void create() {
    	this.vertexBuffer = BufferUtils.newFloatBuffer(100);
    	this.vertexBuffer.put(new float[] {0,0, 10,20,10,2,20,0});
    	this.vertexBuffer.put(new float[] {0,0, 0,10, 2,0,  2 ,10});
    	this.objects = new LinkedList<GraphicObject>();
    	this.humanPlayer = new Ship(50,50,this.vertexBuffer);
    	this.objects.add(humanPlayer);
        this.vertexBuffer.rewind();
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
    }
    
    private void update(){
    	for(GraphicObject obj : this.objects) {
            obj.update();
    	}
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

