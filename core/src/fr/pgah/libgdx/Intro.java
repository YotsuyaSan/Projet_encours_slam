package fr.pgah.libgdx;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Intro extends ApplicationAdapter {

  final int NB_SPRITES = 2;
  SpriteBatch batch;
  int longueurFenetre;
  int hauteurFenetre;
  ArrayList<Sprite> sprites;
  Joueur joueur;
  boolean gameOver;
  Texture gameOverTexture;
  Cursor cursor;

  @Override
  public void create() {
    batch = new SpriteBatch();
    longueurFenetre = Gdx.graphics.getWidth();
    hauteurFenetre = Gdx.graphics.getHeight();

    gameOver = false;
    gameOverTexture = new Texture("game_over.png");
    cursor = new Cursor();
    initialisationSprites();
  //  initialiserJoueur();
    cursor.initCursor();
  }

  private void initialisationSprites() {
    sprites = new ArrayList<>();
    for (int i = 0; i < NB_SPRITES; i++) {
      sprites.add(new Sprite("flush.png"));
    }
  }

  private void initialiserJoueur() {
    joueur = new Joueur();
    Gdx.input.setCursorCatched(true);

  }

  @Override
  public void render() {
    // gameOver est mis à TRUE dès qu'un "hit" est repéré
    
    if (!gameOver) {
      reinitialiserArrierePlan();
      cursor.setCoordsCursor();
      majEtatProtagonistes();
   //   majEtatJeu();
      dessiner();
      
    }
  }

  private void reinitialiserArrierePlan() {
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
  }

  private void majEtatProtagonistes() {
    // Sprites
    for (Sprite sprite : sprites) {
      sprite.majEtat();
      Iterator<Sprite> i = sprites.iterator();
      while (i.hasNext()) {
        Sprite s = i.next(); // must be called before you can call i.remove()
      if(cursor.estEnCollisionAvec(s)){
        i.remove();
      }
    }
  }
    // Joueur
/*     if(cursor.estEnCollisionAvec(sprites)){
      gameOver=true;
    } */
 //   joueur.majEtat();
  }

  private void majEtatJeu() {
    // On vérifie si le jeu continue ou pas
    if (joueur.estEnCollisionAvec(sprites)) {
      gameOver = true;
    }
  }

  private void dessiner() {
    batch.begin();
    if (gameOver) {
      // cet affichage GAME OVER ne se fera qu'une fois
      // à la fin de la dernière frame au moment du "hit"
      // puisqu'ensuite le render ne fera plus rien
      batch.draw(gameOverTexture, 100, 100);
    } else {
      // Affichage "normal", jeu en cours
      for (Sprite sprite : sprites) {
        sprite.dessiner(batch);
      }
 //     joueur.dessiner(batch);
    }
    batch.end();
  }
}
