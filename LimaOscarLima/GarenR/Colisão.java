package LimaOscarLima.GarenR;

import LimaOscarLima.BaraoVermelho.Player;
import LimaOscarLima.BaraoVermelho.PlayerShot;
import LimaOscarLima.CapitaoMagico.*;
import LimaOscarLima.GarenR.*;

public class Colisão {

    public static final int INACTIVE = 0;
    public static final int ACTIVE = 1;
    public static final int EXPLODING = 2;

    static void PlayerProjetil(Player player, MissileBarrage e_projectile){

        if(player.getplayer_state() == ACTIVE){
            for(int i = 0; i < e_projectile.getStates().length; i++){

                double dx = e_projectile.getX(i) - player.getplayer_X();
                double dy = e_projectile.getY(i) - player.getplayer_Y();
                double dist = Math.sqrt(dx * dx + dy * dy);

                if(dist < (player.getplayer_radius() + e_projectile.getRadius()) * 0.8){

                    player.setplayer_state(EXPLODING);
                    player.setplayer_explosion_start(Zillean.getCurrentTime());
                    player.setplayer_explosion_end(Zillean.getCurrentTime() + 2000);
                }
            }
        }
    }

    static void PlayerInimigo(Player player, Enemies enemy1, EnemyJR enemy2){

        if(player.getplayer_state() == ACTIVE){
            for(int i = 0; i < enemy1.getStates().length; i++){

                double dx = enemy1.getX(i) - player.getplayer_X();
                double dy = enemy1.getY(i) - player.getplayer_Y();
                double dist = Math.sqrt(dx * dx + dy * dy);

                if(dist < (player.getplayer_radius() + enemy1.getRadius()) * 0.8){

                    player.setplayer_state(EXPLODING);
                    player.setplayer_explosion_start(Zillean.getCurrentTime());
                    player.setplayer_explosion_end(Zillean.getCurrentTime() + 2000);
                }
            }

            for(int i = 0; i < enemy2.getStates().length; i++){

                double dx = enemy2.getX(i) - player.getplayer_X();
                double dy = enemy2.getY(i) - player.getplayer_Y();
                double dist = Math.sqrt(dx * dx + dy * dy);

                if(dist < (player.getplayer_radius() + enemy2.getRadius()) * 0.8){

                    player.setplayer_state(EXPLODING);
                    player.setplayer_explosion_start(Zillean.getCurrentTime());
                    player.setplayer_explosion_end(Zillean.getCurrentTime() + 2000);
                }
            }
        }
    }

    static void InimigoProjetil (Enemies enemy1, EnemyJR enemy2, PlayerShot playerShot) {

        for (int k = 0; k < playerShot.getStates().length; k++) {

            for (int i = 0; i < enemy1.getStates().length; i++) {

                if (enemy1.getStates_value(i) == ACTIVE) {

                    double dx = enemy1.getX(i) - playerShot.getX(k);
                    double dy = enemy1.getY(i) - playerShot.getY(k);
                    double dist = Math.sqrt(dx * dx + dy * dy);

                    if (dist < enemy1.getRadius()) {

                        enemy1.setStates_value(i, EXPLODING);
                        enemy1.setExplosion_start(i, Zillean.getCurrentTime());
                        enemy1.setExplosion_end(i, Zillean.getCurrentTime() + 500);
                    }
                }
            }

            for (int i = 0; i < enemy2.getStates().length; i++) {

                if (enemy2.getStates_value(i) == ACTIVE) {

                    double dx = enemy2.getX(i) - playerShot.getX(k);
                    double dy = enemy2.getY(i) - playerShot.getY(k);
                    double dist = Math.sqrt(dx * dx + dy * dy);

                    if (dist < enemy2.getRadius()) {

                        enemy2.setStates_value(i, EXPLODING);
                        enemy2.setExplosion_start(i, Zillean.getCurrentTime());
                        enemy2.setExplosion_end(i, Zillean.getCurrentTime() + 500);
                    }
                }
            }
        }
    }

    public static void verificaColisão(Player player, Enemies enemy1, EnemyJR enemy2, PlayerShot playerShot, MissileBarrage e_projectile){

        PlayerProjetil(player, e_projectile);
        PlayerInimigo(player, enemy1, enemy2);
        InimigoProjetil(enemy1, enemy2, playerShot);

    }

}
