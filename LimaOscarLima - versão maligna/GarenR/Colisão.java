package LimaOscarLima.GarenR;

import LimaOscarLima.BaraoVermelho.Character;
import LimaOscarLima.BaraoVermelho.Projectile;
import LimaOscarLima.CapitaoMagico.*;
import LimaOscarLima.Interfaces.Locate;

import java.awt.*;


public class Colisão {

    public static final int ACTIVE = 1;
    public static final int EXPLODING = 2;

    static double DistanciaGenerica(Locate genericX, Locate genericY, int i){
        double dx = genericX.getX(i) - genericY.getX(i);
        double dy = genericX.getY(i) - genericY.getY(i);
        double dist = Math.sqrt(dx * dx + dy * dy);
        return dist;
    }

    static double DistanciaPlayer(Locate generic, Character player, int i){
        double dx = generic.getX(i) - player.getCharacter_X();
        double dy = generic.getY(i) - player.getCharacter_Y();
        double dist = Math.sqrt(dx * dx + dy * dy);
        return dist;
    }

    static void PlayerProjetil(Character player, MissileBarrage e_projectile, long currentTime){

        if(player.getCharacter_state() == ACTIVE){
            for(int i = 0; i < e_projectile.getStates().length; i++){

                double dist = DistanciaPlayer(e_projectile, player,i);

                if(dist < (player.getCharacter_radius() + e_projectile.getRadius()) * 0.8){

                    player.setCharacter_state(EXPLODING);
                    player.setCharacter_explosion_start(currentTime);
                    player.setCharacter_explosion_end(currentTime + 2000);
                }
            }
        }
    }

    static void PlayerInimigo(Character player, Enemies enemy1, EnemyJR enemy2, long currentTime){

        if(player.getCharacter_state() == ACTIVE){
            for(int i = 0; i < enemy1.getStates().length; i++){

                double dist = DistanciaPlayer(enemy1, player,i);

                if(dist < (player.getCharacter_radius() + enemy1.getRadius()) * 0.8){

                    player.setCharacter_state(EXPLODING);
                    player.setCharacter_explosion_start(currentTime);
                    player.setCharacter_explosion_end(currentTime + 2000);
                }
            }

            for(int i = 0; i < enemy2.getStates().length; i++){

                double dist = DistanciaPlayer(enemy2, player,i);

                if(dist < (player.getCharacter_radius() + enemy2.getRadius()) * 0.8){

                    player.setCharacter_state(EXPLODING);
                    player.setCharacter_explosion_start(currentTime);
                    player.setCharacter_explosion_end(currentTime + 2000);
                }
            }
        }
    }

    static void InimigoProjetil (Enemies enemy1, EnemyJR enemy2, Projectile projectile, long currentTime) {

        for (int k = 0; k < projectile.getStates().length; k++) {

            for (int i = 0; i < enemy1.getStates().length; i++) {

                if (enemy1.getStates_value(i) == ACTIVE) {

                    double dist = DistanciaGenerica(projectile, enemy1, i);

                    if (dist < enemy1.getRadius()) {

                        enemy1.setStates_value(i, EXPLODING);
                        enemy1.setExplosion_start(i, currentTime);
                        enemy1.setExplosion_end(i, currentTime + 500);
                    }
                }
            }

            for (int i = 0; i < enemy2.getStates().length; i++) {

                if (enemy2.getStates_value(i) == ACTIVE) {

                    double dist = DistanciaGenerica(projectile, enemy2, i);

                    if (dist < enemy2.getRadius()) {

                        enemy2.setStates_value(i, EXPLODING);
                        enemy2.setExplosion_start(i, currentTime);
                        enemy2.setExplosion_end(i, currentTime + 500);
                    }
                }
            }
        }
    }

    public static void verificaColisão(Character player, Enemies enemy1, EnemyJR enemy2, Projectile projectile, MissileBarrage e_projectile, long currentTime){

        PlayerProjetil(player, e_projectile, currentTime);
        PlayerInimigo(player, enemy1, enemy2, currentTime);
        InimigoProjetil(enemy1, enemy2, projectile, currentTime);

    }

}
