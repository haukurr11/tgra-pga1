package is.ru.tgra;


import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ScreenText{
  private String text;
  private Vector2 position;
  private int width;
  private int height;
  private int x;
  private int y;

  public ScreenText(String text,int x,int y){
      this.text = text;
      this.position = position;
      this.x=x;
      this.y=y;
  }

  public void draw(SpriteBatch sp,BitmapFont fnt){
      width=(int)fnt.getBounds(text).width; //Get the width of the text we draw using the current font
      height=(int)fnt.getBounds(text).height; //Get the height of the text we draw using the current font
      fnt.draw(sp,text,this.x-width/2, // Get center value in x direction
              this.y+height/2 //Get center value in y direction
              );
  }

  public String getText() {
      return text;
  }

  public void setText(String text) {
      this.text = text;
  }

  public Vector2 getPosition() {
      return position;
  }

  public void setPosition(Vector2 position) {
      this.position = position;
  }

  public int getWidth() {
      return width;
  }

  public int getHeight() {
      return height;
  }

}