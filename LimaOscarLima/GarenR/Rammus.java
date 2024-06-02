package LimaOscarLima.GarenR;

import LimaOscarLima.BaraoVermelho.Player;
import LimaOscarLima.BaraoVermelho.PlayerShot;
import LimaOscarLima.CapitaoMagico.*;
import LimaOscarLima.GameLib.GameLib;

import static LimaOscarLima.Main.*;

public final class Rammus{

    static void PlayerProjetil(Player player, MissileBarrage enemyShot){

        if(player.getplayer_state() == ACTIVE){
            for(int i = 0; i < enemyShot.getStates().length; i++){

                double dx = enemyShot.getX(i) - player.getplayer_X();
                double dy = enemyShot.getY(i) - player.getplayer_Y();
                double dist = Math.sqrt(dx * dx + dy * dy);

                if(dist < (player.getplayer_radius() + enemyShot.getRadius()) * 0.8){

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

    public static void verificaColisão(Player player, Enemies enemy1, EnemyJR enemy2, PlayerShot playerShot, MissileBarrage enemyShot){

        PlayerProjetil(player, enemyShot);
        PlayerInimigo(player, enemy1, enemy2);
        InimigoProjetil(enemy1, enemy2, playerShot);

    }

    public static void verificaEstados(Player player, Enemies enemy1, EnemyJR enemy2, PlayerShot playerShot, MissileBarrage enemyShot){
        Estados.verificaPlayer(player);
        Estados.verificaPlayerShot(playerShot);
        Estados.verificaEnemy1(enemy1,enemyShot,player);
        Estados.verificaEnemy2(enemy2,enemyShot,player);
        Estados.verificaEnemyShot(enemyShot);

    }

}

final class Estados{

    static void verificaPlayer(Player player){
        if(player.getplayer_state() == EXPLODING){

            if(Zillean.getCurrentTime() > player.getplayer_explosion_end()){

                player.setplayer_state(ACTIVE);
            }
        }
    }

    static void verificaPlayerShot(PlayerShot playerShot){
        for(int i = 0; i < playerShot.getStates().length; i++){

            if(playerShot.getStates_value(i) == ACTIVE){

                /* verificando se projétil saiu da tela */
                if(playerShot.getY(i) < 0) {

                    playerShot.setStates_value(i, INACTIVE);
                }
                else {

                    playerShot.setX(i, playerShot.getX(i) + (playerShot.getplayerShotVX(i) * Zillean.getDelta()));
                    playerShot.setY(i, playerShot.getY(i) + (playerShot.getplayerShotVY(i) * Zillean.getDelta()));
                }
            }
        }
    }



    static void verificaEnemy1(Enemies enemy1, MissileBarrage enemyShot, Player player){
        for(int i = 0; i < enemy1.getStates().length; i++){

            if(enemy1.getStates_value(i) == EXPLODING){

                if(Zillean.getCurrentTime() > enemy1.getExplosion_end(i)){

                    enemy1.setStates_value(i, INACTIVE);
                }
            }

            if(enemy1.getStates_value(i) == ACTIVE){

                /* verificando se inimigo saiu da tela */
                if(enemy1.getY(i) > GameLib.HEIGHT + 10) {

                    enemy1.setStates_value(i, INACTIVE);
                }
                else {

                    enemy1.setX(i, enemy1.getX(i) + (enemy1.getV(i) * Math.cos(enemy1.getAngle(i)) * Zillean.getDelta()));
                    enemy1.setY(i, enemy1.getY(i) + (enemy1.getV(i) * Math.sin(enemy1.getAngle(i)) * Zillean.getDelta() *(-1.0)));
                    enemy1.setAngle(i, enemy1.getAngle(i) + (enemy1.getRV(i) * Zillean.getDelta()));

                    if(Zillean.getCurrentTime() > enemy1.getNextShot(i) && enemy1.getY(i) < player.getplayer_Y()){

                        int free = findFreeIndex(enemyShot.getStates());

                        if(free < enemyShot.getStates().length){

                            enemyShot.setX(free, enemy1.getX(i));
                            enemyShot.setY(free, enemy1.getY(i));
                            enemyShot.setplayerShotVX(free, Math.cos(enemy1.getAngle(i)) * 0.45);
                            enemyShot.setplayerShotVY(free, Math.sin(enemy1.getAngle(i)) * 0.45 * (-1.0));
                            enemyShot.setStates_value(free, 1);

                            enemy1.setNext_Shoot(i, (long) (Zillean.getCurrentTime() + 200 + Math.random() * 500));
                        }
                    }
                }
            }
        }
        if(Zillean.getCurrentTime() > enemy1.getNextEnemies()){

            int free = findFreeIndex(enemy1.getStates());

            if(free < enemy1.getStates().length){

                enemy1.setX(free, Math.random() * (GameLib.WIDTH - 20.0) + 10.0);
                enemy1.setY(free, -10.0);
                enemy1.setV(free, 0.20 + Math.random() * 0.15);
                enemy1.setAngle(free, 3 * Math.PI / 2);
                enemy1.setRV(free, 0.0);
                enemy1.setStates_value(free, ACTIVE);
                enemy1.setNext_Shoot(free, Zillean.getCurrentTime() + 500);
                enemy1.setNextEnemies(Zillean.getCurrentTime() + 500);
            }
        }
    }

    static void verificaEnemy2(EnemyJR enemy2, MissileBarrage enemyShot, Player player){
        for(int i = 0; i < enemy2.getStates().length; i++){

            if(enemy2.getStates_value(i) == EXPLODING){

                if(Zillean.getCurrentTime() > enemy2.getExplosion_end(i)){

                    enemy2.setStates_value(i, INACTIVE);
                }
            }

            if(enemy2.getStates_value(i) == ACTIVE){

                /* verificando se inimigo saiu da tela */
                if(	enemy2.getX(i) < -10 || enemy2.getX(i) > GameLib.WIDTH + 10 ) {

                    enemy2.setStates_value(i, INACTIVE);
                }
                else {

                    boolean shootNow = false;
                    double previousY = enemy2.getY(i);

                    enemy2.setX(i, enemy2.getX(i) + (enemy2.getV(i) * Math.cos(enemy2.getAngle(i)) * Zillean.getDelta()));
                    enemy2.setY(i, enemy2.getY(i) + (enemy2.getV(i) * Math.sin(enemy2.getAngle(i)) * Zillean.getDelta() * (-1.0)));
                    enemy2.setAngle(i, enemy2.getAngle(i) +(enemy2.getRV(i) * Zillean.getDelta()));

                    double threshold = GameLib.HEIGHT * 0.30;

                    if(previousY < threshold && enemy2.getY(i) >= threshold) {

                        if(enemy2.getX(i) < GameLib.WIDTH / 2) enemy2.setRV(i, 0.003);
                        else enemy2.setRV(i, -0.003);
                    }

                    if(enemy2.getRV(i) > 0 && Math.abs(enemy2.getAngle(i) - 3 * Math.PI) < 0.05){

                        enemy2.setRV(i, 0.0);
                        enemy2.setAngle(i, 3 * Math.PI);
                        shootNow = true;
                    }

                    if(enemy2.getRV(i) < 0 && Math.abs(enemy2.getAngle(i)) < 0.05){

                        enemy2.setRV(i, 0.0);
                        enemy2.setAngle(i, 0.0);
                        shootNow = true;
                    }

                    if(shootNow){

                        double [] angles = { Math.PI/2 + Math.PI/8, Math.PI/2, Math.PI/2 - Math.PI/8 };
                        int [] freeArray = findFreeIndex(enemyShot.getStates(), angles.length);

                        for(int k = 0; k < freeArray.length; k++){

                            int free = freeArray[k];

                            if(free < enemyShot.getStates().length){

                                double a = angles[k] + Math.random() * Math.PI/6 - Math.PI/12;
                                double vx = Math.cos(a);
                                double vy = Math.sin(a);

                                enemyShot.setX(free, enemy2.getX(i));
                                enemyShot.setY(free, enemy2.getY(i));
                                enemyShot.setplayerShotVX(free, vx * 0.30);
                                enemyShot.setplayerShotVY(free, vy * 0.30);
                                enemyShot.setStates_value(free, 1);
                            }
                        }
                    }
                }
            }
        }
        if(Zillean.getCurrentTime() > enemy2.getNextEnemies()){

            int free = findFreeIndex(enemy2.getStates());

            if(free < enemy2.getStates().length){

                enemy2.setX(free, enemy2.getEnemyJR_spawnX());
                enemy2.setY(free, -10.0);
                enemy2.setV(free, 0.42);
                enemy2.setAngle(free, (3 * Math.PI) / 2);
                enemy2.setRV(free, 0.0);
                enemy2.setStates_value(free, ACTIVE);

                enemy2.addCount();

                if(enemy2.getEnemyJR_count() < 10){

                    enemy2.setNextEnemies(Zillean.getCurrentTime() + 120);
                }
                else {

                    enemy2.setEnemyJR_count(0);
                    enemy2.setEnemyJR_spawnX(Math.random() > 0.5 ? GameLib.WIDTH * 0.2 : GameLib.WIDTH * 0.8);
                    enemy2.setNextEnemies((long) (Zillean.getCurrentTime() + 3000 + Math.random() * 3000));
                }
            }
        }
    }

    static void verificaEnemyShot(MissileBarrage enemyShot){
        for(int i = 0; i < enemyShot.getStates().length; i++){

            if(enemyShot.getStates_value(i) == ACTIVE){

                /* verificando se projétil saiu da tela */
                if(enemyShot.getY(i) > GameLib.HEIGHT) {

                    enemyShot.setStates_value(i, INACTIVE);
                }
                else {

                    enemyShot.setX(i, enemyShot.getX(i) +(enemyShot.getplayerShotVX(i) * Zillean.getDelta()));
                    enemyShot.setY(i, enemyShot.getY(i) +(enemyShot.getplayerShotVY(i) * Zillean.getDelta()));
                }
            }
        }
    }

}
