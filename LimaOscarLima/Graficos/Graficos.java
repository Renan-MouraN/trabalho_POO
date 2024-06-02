package LimaOscarLima.Graficos;

import LimaOscarLima.BaraoVermelho.Player;
import LimaOscarLima.BaraoVermelho.PlayerShot;
import LimaOscarLima.CapitaoMagico.Enemies;
import LimaOscarLima.CapitaoMagico.EnemyJR;
import LimaOscarLima.CapitaoMagico.EnemyShot;
import LimaOscarLima.GameLib.*;
import LimaOscarLima.Util.Utilidades;

import java.awt.*;

import static LimaOscarLima.Main.ACTIVE;
import static LimaOscarLima.Main.EXPLODING;


public class Graficos {

    public static void desenharCena(Player player, PlayerShot playerShot, EnemyShot enemyShot, Enemies enemy1, EnemyJR enemy2, Background background1, Background background2) {
        Render.desenharPlayer(player);
        Render.desenharProjeteis(playerShot);
        Render.desenharProjeteisInimigos(enemyShot);
        Render.desenharInimigo1(enemy1);
        Render.desenharInimigo2(enemy2);
        Background.DesenhaBackGround(background1,background2);
    }
}

class Render{
    // Desenha player
    static void desenharPlayer(Player player) {
        if (player.getplayer_state() == EXPLODING) {
            double alpha = (Utilidades.getCurrentTime() - player.getplayer_explosion_start()) / (player.getplayer_explosion_end() - player.getplayer_explosion_start());
            GameLib.drawExplosion(player.getplayer_X(), player.getplayer_Y(), alpha);
        } else {
            GameLib.setColor(Color.BLUE);
            GameLib.drawPlayer(player.getplayer_X(), player.getplayer_Y(), player.getplayer_radius());
        }
    }

    // Desenha os projéteis do jogador
    static void desenharProjeteis(PlayerShot playerShot) {
        for (int i = 0; i < playerShot.getStates().length; i++) {
            if (playerShot.getStates_value(i) == ACTIVE) {

                GameLib.setColor(Color.GREEN);
                GameLib.drawLine(playerShot.getX(i), playerShot.getY(i) - 5, playerShot.getX(i), playerShot.getY(i) + 5);
                GameLib.drawLine(playerShot.getX(i) - 1, playerShot.getY(i) - 3, playerShot.getX(i) - 1, playerShot.getY(i) + 3);
                GameLib.drawLine(playerShot.getX(i) + 1, playerShot.getY(i) - 3, playerShot.getX(i) + 1, playerShot.getY(i) + 3);

            }
        }
    }
    // Desenha os projéteis dos inimigos
    static void desenharProjeteisInimigos(EnemyShot enemyShot) {
        for (int i = 0; i < enemyShot.getStates().length; i++) {
            if (enemyShot.getStates_value(i) == ACTIVE) {
                GameLib.setColor(Color.RED);
                GameLib.drawCircle(enemyShot.getX(i), enemyShot.getY(i), enemyShot.getRadius());

            }
        }
    }

    // Desenhando os inimigos do tipo 1
    static void desenharInimigo1(Enemies enemy1) {
        for (int i = 0; i < enemy1.getStates().length; i++) {
            if (enemy1.getStates_value(i) == EXPLODING) {

                double alpha = (Utilidades.getCurrentTime() - enemy1.getExplosion_start(i)) / (enemy1.getExplosion_end(i) - enemy1.getExplosion_start(i));
                GameLib.drawExplosion(enemy1.getX(i), enemy1.getY(i), alpha);
            }

            if (enemy1.getStates_value(i) == ACTIVE) {
                GameLib.setColor(Color.CYAN);
                GameLib.drawCircle(enemy1.getX(i), enemy1.getY(i), enemy1.getRadius());
            }
        }
    }

    // Desenhando os inimigos do tipo 2
    static void desenharInimigo2(EnemyJR enemy2) {
        for(int i = 0; i < enemy2.getStates().length; i++){

            if(enemy2.getStates_value(i) == EXPLODING){

                double alpha = (Utilidades.getCurrentTime() - enemy2.getExplosion_start(i)) / (enemy2.getExplosion_end(i) - enemy2.getExplosion_start(i));
                GameLib.drawExplosion(enemy2.getX(i), enemy2.getY(i), alpha);
            }

            if(enemy2.getStates_value(i) == ACTIVE){

                GameLib.setColor(Color.MAGENTA);
                GameLib.drawDiamond(enemy2.getX(i), enemy2.getY(i), enemy2.getRadius());
            }
        }
    }
}
