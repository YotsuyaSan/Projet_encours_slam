package fr.pgah.libgdx;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;

public class Cursor {
    Rectangle zoneDeHit;
    int coordX, coordY;
    public void initCursor(){
        coordX =  Gdx.input.getX();
        coordY = Gdx.graphics.getHeight() - Gdx.input.getY();
        zoneDeHit = new Rectangle(coordX, coordY, 1, 1);
    }
    public void setCoordsCursor(){
        coordX =  Gdx.input.getX();
        coordY = Gdx.graphics.getHeight() - Gdx.input.getY();
        zoneDeHit.setPosition(coordX, coordY);
    }
    public boolean estEnCollisionAvec(Sprite sprite) {
    // pour chaque sprite dans sprites
    // si le sprite touche le joueur
    // alors renvoyer vrai
        if (zoneDeHit.overlaps(sprite.zoneDeHit) && Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
        return true;
      }
        return false;
    }

/*     private boolean estEnCollisionAvec(Sprite sprite) {
    // 'overlaps' est une méthode fournie par libGDX
    // Elle teste si 2 rectangles se touchent
        if (zoneDeHit.overlaps(sprite.zoneDeHit)) {
          return true;
        } else {
          return false;
        }
    } */
}
