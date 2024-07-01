package game.Graficos;

import game.gameObjects.singleEntities.player;
import game.gameObjects.multipleEntities.*;
import game.GameLib.*;
import game.Util.Utilidades;

import java.awt.*;

import static game.Game.*;


public class Graficos {

    public static void desenharCena(player player, shot playerShot, enemyShot enemyShot, enemies enemy1, enemy2 enemy2, enemy3 enemy3, Background background1, Background background2, powerUp powerup) {
        Render.desenharPlayer(player);
        Render.desenharProjeteis(playerShot);
        Render.desenharProjeteisInimigos(enemyShot);
        Render.desenharInimigo1(enemy1);
        Render.desenharInimigo2(enemy2);
        Render.desenharInimigo3(enemy3);
        Render.desenharPowerUp(powerup);
        Render.desenharVida(player);


        Background.DesenhaBackGround(background1,background2);
    }
}

class Render{
    // Desenha player
    static void desenharPlayer(player player) {
        if (player.getplayer_state() == EXPLODING) {
            double alpha = (Utilidades.getCurrentTime() - player.getplayer_explosion_start()) / (player.getplayer_explosion_end() - player.getplayer_explosion_start());
            GameLib.drawExplosion(player.getplayer_X(), player.getplayer_Y(), alpha);
        } else if(player.getplayer_state() == ACTIVE){
            GameLib.setColor(Color.BLUE);
            GameLib.drawPlayer(player.getplayer_X(), player.getplayer_Y(), player.getplayer_radius());
        }else if(player.getplayer_state() == INVINCIBLE){
            GameLib.setColor(Color.WHITE);
            GameLib.drawPlayer(player.getplayer_X(), player.getplayer_Y(), player.getplayer_radius());
        }else if(player.getplayer_state() == DAMAGED){
            // Alterne entre preto e azul
            long time = Utilidades.getCurrentTime() % 1000; // Divida o tempo atual em intervalos de 1 segundo
            if (time < 250 ||(500 < time && time < 750)) {
                GameLib.setColor(Color.BLACK);
            } else {
                GameLib.setColor(Color.BLUE);
            }
            GameLib.drawPlayer(player.getplayer_X(), player.getplayer_Y(), player.getplayer_radius());
        }
    }


    // Desenha os projéteis do jogador
    static void desenharProjeteis(shot playerShot) {
        for (int i = 0; i < playerShot.getArray().size(); i++) {
            if (playerShot.getStateValue(i) == ACTIVE) {

                GameLib.setColor(Color.GREEN);
                GameLib.drawLine(playerShot.getX(i), playerShot.getY(i) - 5, playerShot.getX(i), playerShot.getY(i) + 5);
                GameLib.drawLine(playerShot.getX(i) - 1, playerShot.getY(i) - 3, playerShot.getX(i) - 1, playerShot.getY(i) + 3);
                GameLib.drawLine(playerShot.getX(i) + 1, playerShot.getY(i) - 3, playerShot.getX(i) + 1, playerShot.getY(i) + 3);

            }
        }
    }
    // Desenha os projéteis dos inimigos
    static void desenharProjeteisInimigos(enemyShot enemyShot) {
        for (int i = 0; i < enemyShot.getArray().size(); i++) {
            if (enemyShot.getStateValue(i) == ACTIVE) {
                GameLib.setColor(Color.RED);
                GameLib.drawCircle(enemyShot.getX(i), enemyShot.getY(i), enemyShot.getRadius());

            }
        }
    }

    // Desenhando os inimigos do tipo 1
    static void desenharInimigo1(enemies enemy1) {
        for (int i = 0; i < enemy1.getArray().size(); i++) {
            if (enemy1.getStateValue(i) == EXPLODING) {

                double alpha = (Utilidades.getCurrentTime() - enemy1.getExplosion_start()) / (enemy1.getExplosion_end() - enemy1.getExplosion_start());
                GameLib.drawExplosion(enemy1.getX(i), enemy1.getY(i), alpha);
            }

            if (enemy1.getStateValue(i) == ACTIVE) {
                GameLib.setColor(Color.CYAN);



                GameLib.drawCircle(enemy1.getX(i), enemy1.getY(i), enemy1.getRadius());
            }
        }
    }




    // Desenhando os inimigos do tipo 2
    static void desenharInimigo2(enemy2 enemy2) {
        for(int i = 0; i < enemy2.getArray().size(); i++){

            if(enemy2.getStateValue(i) == EXPLODING){

                double alpha = (Utilidades.getCurrentTime() - enemy2.getExplosion_start()) / (enemy2.getExplosion_end() - enemy2.getExplosion_start());
                GameLib.drawExplosion(enemy2.getX(i), enemy2.getY(i), alpha);
            }

            if(enemy2.getStateValue(i) == ACTIVE){

                GameLib.setColor(Color.MAGENTA);
                GameLib.drawDiamond(enemy2.getX(i), enemy2.getY(i), enemy2.getRadius());
            }
        }
    }

    // Desenhando os inimigos do tipo 3
    static void desenharInimigo3(enemies enemy3) {
        for (int i = 0; i < enemy3.getArray().size(); i++) {
            if (enemy3.getStateValue(i) == EXPLODING) {

                double alpha = (Utilidades.getCurrentTime() - enemy3.getExplosion_start()) / (enemy3.getExplosion_end() - enemy3.getExplosion_start());
                GameLib.drawExplosion(enemy3.getX(i), enemy3.getY(i), alpha);
            }

            if (enemy3.getStateValue(i) == ACTIVE) {
                GameLib.setColor(Color.ORANGE);
                GameLib.drawCircle(enemy3.getX(i), enemy3.getY(i), enemy3.getRadius());
            }
        }
    }

    static void desenharPowerUp(powerUp powerUp) {
        for (int i = 0; i < powerUp.getArray().size(); i++) {

            if (powerUp.getStateValue(i) == ACTIVE) {
                for(int k = 0; k<powerUp.getRadius(); k++) {
                    GameLib.setColor(Color.WHITE);
                    GameLib.drawCircle(powerUp.getX(i), powerUp.getY(i), k);
                }
            }
        }
    }

    static void desenharVida(player player){

        if(player.getplayer_state() != EXPLODING){
        double [] locais = {30, 70, 110};

        for(int k = 0; k < player.getHP(); k++){
          for(int i = 0; i<15; i++) {
              GameLib.setColor(Color.RED);
              GameLib.drawCircle(locais[k], 60, i);
          }
        }
        }
    }
}
