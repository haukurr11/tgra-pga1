package is.ru.tgra;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ScreenText{
  private int x;
  private int y;
  private SpriteBatch spriteBatch;
  private BitmapFont font;
  private String scoreDisplay;
  private int score;

  public ScreenText(){
      this.spriteBatch = new SpriteBatch();
      this.font = new BitmapFont();
      this.score = 0;
      this.scoreDisplay = "score: " + score;
  }

  public void drawScore(){
	  spriteBatch.begin(); 
	  font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
	  font.draw(spriteBatch, scoreDisplay, 25, 100); 
	  spriteBatch.end();
  }
  
  public void addScore(int adding){
	  this.score += adding;
	  this.scoreDisplay = "score:" + score;
  }
  
  public void resetScore(){
	  this.score = 0;
	  this.scoreDisplay = "score:" + score;
  }
  
}