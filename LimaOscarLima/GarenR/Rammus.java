package LimaOscarLima.GarenR;

import LimaOscarLima.gameObjects.multipleEntities.*;
import LimaOscarLima.gameObjects.singleEntities.player;
import LimaOscarLima.GameLib.GameLib;
import LimaOscarLima.Util.Utilidades;

import static LimaOscarLima.Main.*;
import static LimaOscarLima.Util.Utilidades.*;

public final class Rammus{

    public static void mainLoop(player player, shot playerShot, enemy1 enemy1, enemy2 enemy2, enemy3 enemy3, enemyShot enemyShot){

        Utilidades.setDelta(System.currentTimeMillis() - Utilidades.getCurrentTime());
        Utilidades.updateCurrentTime();

        statesUpdatesPlayer.statePlayer(player, enemy1, enemy2, enemyShot);
        stateUpdatesPlayerShots.verificaPlayerShot(playerShot);
        statesUpdatesEnemy1.stateEnemy1(enemy1, enemyShot, player, playerShot);
        stateUpdatesEnemy2.stateEnemy2(enemy2, playerShot, enemyShot);
        stateUpdatesEnemyShots.verificaEnemyShot(enemyShot);

        Input.verificaInput(player, playerShot);

    }

}

final class Input{

    static void verificaInput(player player, shot playerShot){

        if(player.getplayer_state() == ACTIVE){

            if(GameLib.iskeyPressed(GameLib.KEY_UP)) player.setplayer_Y(player.getplayer_Y() - Utilidades.getDelta() * player.getplayer_VY());
            if(GameLib.iskeyPressed(GameLib.KEY_DOWN)) player.setplayer_Y(player.getplayer_Y() + Utilidades.getDelta() * player.getplayer_VY());
            if(GameLib.iskeyPressed(GameLib.KEY_LEFT)) player.setplayer_X(player.getplayer_X() - Utilidades.getDelta() * player.getplayer_VX());
            if(GameLib.iskeyPressed(GameLib.KEY_RIGHT)) player.setplayer_X(player.getplayer_X() + Utilidades.getDelta() * player.getplayer_VX());
            if(GameLib.iskeyPressed(GameLib.KEY_CONTROL)) {

                if(Utilidades.getCurrentTime() > player.getplayer_nextShot()){

                    playerShot.addNewElement(ACTIVE, player.getplayer_X(), player.getplayer_Y() - 2 * player.getplayer_radius(), 0, -1.0);
                    player.setplayer_nextShot(Utilidades.getCurrentTime() + 100);

                }
            }
        }

        if(GameLib.iskeyPressed(GameLib.KEY_ESCAPE)) Utilidades.setRunning(false);

        /* Verificando se coordenadas do player ainda estão dentro	*/
        /* da tela de jogo após processar entrada do usuário.       */

        if(player.getplayer_X() < 0.0) player.setplayer_X(0.0);
        if(player.getplayer_X() >= GameLib.WIDTH) player.setplayer_X(GameLib.WIDTH - 1);
        if(player.getplayer_Y() < 25.0) player.setplayer_Y(25.0);
        if(player.getplayer_Y() >= GameLib.HEIGHT) player.setplayer_Y(GameLib.HEIGHT - 1);

    }
}

final class statesUpdatesPlayer{
    
    private static void PlayerProjetil(player player, enemyShot enemyShot){

        if(player.getplayer_state() == ACTIVE){
            for(int i = 0; i < enemyShot.getArray().size(); i++){

                double dx = enemyShot.getX(i) - player.getplayer_X();
                double dy = enemyShot.getY(i) - player.getplayer_Y();
                double dist = Math.sqrt(dx * dx + dy * dy);

                if(dist < (player.getplayer_radius() + enemyShot.getRadius()) * 0.8){

                    player.setplayer_state(EXPLODING);
                    player.setplayer_explosion_start(Utilidades.getCurrentTime());
                    player.setplayer_explosion_end(Utilidades.getCurrentTime() + 2000);
                }
            }
        }
    }

    private static void PlayerInimigo(player player, enemy1 enemy1, enemy2 enemy2, enemy3 enemy3){

        if(player.getplayer_state() == ACTIVE){
            for(int i = 0; i < enemy1.getArray().size(); i++){

                double dx = enemy1.getX(i) - player.getplayer_X();
                double dy = enemy1.getY(i) - player.getplayer_Y();
                double dist = Math.sqrt(dx * dx + dy * dy);

                if(dist < (player.getplayer_radius() + enemy1.getRadius()) * 0.8){

                    player.setplayer_state(EXPLODING);
                    player.setplayer_explosion_start(Utilidades.getCurrentTime());
                    player.setplayer_explosion_end(Utilidades.getCurrentTime() + 2000L);
                }
            }

            for(int i = 0; i < enemy2.getArray().size(); i++){

                double dx = enemy2.getX(i) - player.getplayer_X();
                double dy = enemy2.getY(i) - player.getplayer_Y();
                double dist = Math.sqrt(dx * dx + dy * dy);

                if(dist < (player.getplayer_radius() + enemy2.getRadius()) * 0.8){

                    player.setplayer_state(EXPLODING);
                    player.setplayer_explosion_start(Utilidades.getCurrentTime());
                    player.setplayer_explosion_end(Utilidades.getCurrentTime() + 2000L);
                }
            }
        }
    }

    private static void verificaPlayer(player player){
        if(player.getplayer_state() == EXPLODING){

            if(Utilidades.getCurrentTime() > player.getplayer_explosion_end()){

                player.setplayer_state(ACTIVE);
            }
        }
    }
    
    static void statePlayer(player player, enemy1 enemy1, enemy2 enemy2, enemy3 enemy3, enemyShot enemyShot){
        PlayerProjetil(player, enemyShot);
        PlayerInimigo(player, enemy1, enemy2, enemy3);
        verificaPlayer(player);
    }
}

final class statesUpdatesEnemy1{
    
    private static void inimigoProjetil(enemy1 enemy1, shot playerShot) {
        for (int k = 0; k < playerShot.getArray().size(); k++) {

            for (int i = 0; i < enemy1.getArray().size(); i++) {

                if (enemy1.getStateValue(i) == ACTIVE) {

                    double dx = enemy1.getX(i) - playerShot.getX(k);
                    double dy = enemy1.getY(i) - playerShot.getY(k);
                    double dist = Math.sqrt(dx * dx + dy * dy);

                    if (dist < enemy1.getRadius()) {

                        enemy1.setStateValue(i, EXPLODING);
                        enemy1.addExplosion_start(Utilidades.getCurrentTime());
                        enemy1.addExplosion_end(Utilidades.getCurrentTime() + 500);
                    }
                }
            }
        }
    }

    static void verificaEnemy1(enemy1 enemy1, enemyShot enemyShot, player player){
        for(int i = 0; i < enemy1.getArray().size(); i++){

            if(enemy1.getStateValue(i) == EXPLODING){

                if(Utilidades.getCurrentTime() > enemy1.getExplosion_end()){

                        enemy1.setStateValue(i, INACTIVE);
                    }
                }

                if(enemy1.getStateValue(i) == ACTIVE){

                    /* verificando se inimigo saiu da tela */
                    if(enemy1.getY(i) > GameLib.HEIGHT + 10) {

                        enemy1.setStateValue(i, INACTIVE);
                    }
                    else {

                        enemy1.setX(i, enemy1.getX(i) + (enemy1.getVX(i) * Math.cos(enemy1.getAngle(i)) * Utilidades.getDelta()));
                        enemy1.setY(i, enemy1.getY(i) + (enemy1.getVY(i) * Math.sin(enemy1.getAngle(i)) * Utilidades.getDelta() * (-1.0)));
                        enemy1.setAngle(i, enemy1.getAngle(i) + (enemy1.getRV(i) * Utilidades.getDelta()));

                        if(Utilidades.getCurrentTime() > enemy1.getNextShot(i) && enemy1.getY(i) < player.getplayer_Y()){

                            enemyShot.addNewElement(ACTIVE, enemy1.getX(i), enemy1.getY(i), Math.cos(enemy1.getAngle(i)) * 0.45, Math.sin(enemy1.getAngle(i)) * 0.45 * (-1.0));
                            enemy1.setNext_Shoot(i, (long) (Utilidades.getCurrentTime() + 200 + Math.random() * 500));

                        }
                    }
                }
            }
            if(Utilidades.getCurrentTime() > enemy1.getEnemiesSpawnTime()){

                //velocidade original funcionando
                //cpa q o bug de spawn do enemy2 ta consertado, mas o player as vezes explode no centro da tela
                //problema qnd mata mais de um inimigo do tipo 2
                enemy1.addNewElement(ACTIVE, Math.random() * (GameLib.WIDTH - 20.0) + 10.0, -10, 0, 0.2 + Math.random() * 0.15);
                    int free = enemy1.getArray().size() - 1;

                    enemy1.addAngle(3 * Math.PI / 2);
                    enemy1.addRV(0.0);
                    enemy1.setStateValue(free, ACTIVE);
                    enemy1.addNext_Shoot(Utilidades.getCurrentTime() + 500);
                    enemy1.setEnemiesSpawnTime(Utilidades.getCurrentTime() + 500);

            }
        }

        static void stateEnemy1(enemy1 enemy1, enemyShot enemyShot, player player, shot playerShot){
            verificaEnemy1(enemy1,  enemyShot, player);
            inimigoProjetil(enemy1, playerShot);
        }
}

final class stateUpdatesEnemy2{

    static void InimigoProjetil (enemy2 enemy2, shot playerShot) {

        for (int k = 0; k < playerShot.getArray().size(); k++) {
            for (int i = 0; i < enemy2.getArray().size(); i++) {

                if (enemy2.getStateValue(i) == ACTIVE) {

                    double dx = enemy2.getX(i) - playerShot.getX(k);
                    double dy = enemy2.getY(i) - playerShot.getY(k);
                    double dist = Math.sqrt(dx * dx + dy * dy);

                    if (dist < enemy2.getRadius()) {

                        enemy2.setStateValue(i, EXPLODING);
                        enemy2.addExplosion_start(Utilidades.getCurrentTime());
                        enemy2.addExplosion_end(Utilidades.getCurrentTime() + 500L);
                    }
                }
            }
        }
    }

    static void verificaEnemy2(enemy2 enemy2, enemyShot enemyShot){
        for(int i = 0; i < enemy2.getArray().size(); i++){

            if(enemy2.getStateValue(i) == EXPLODING){

                if(Utilidades.getCurrentTime() > enemy2.getExplosion_end()){

                    enemy2.setStateValue(i, INACTIVE);
                }
            }

            if(enemy2.getStateValue(i) == ACTIVE){

                /* verificando se inimigo saiu da tela */
                if(	enemy2.getX(i) < -10 || enemy2.getX(i) > GameLib.WIDTH + 10 ) {

                    enemy2.setStateValue(i, INACTIVE);
                }
                else {

                    boolean shootNow = false;
                    double previousY = enemy2.getY(i);

                    enemy2.setX(i, enemy2.getX(i) + (enemy2.getVY(i) * Math.cos(enemy2.getAngle(i)) * Utilidades.getDelta()));
                    enemy2.setY(i, enemy2.getY(i) + (enemy2.getVY(i) * Math.sin(enemy2.getAngle(i)) * Utilidades.getDelta() * (-1.0)));
                    enemy2.setAngle(i, enemy2.getAngle(i) +(enemy2.getRV(i) * Utilidades.getDelta()));

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
                        int [] freeArray = findFreeIndexArray(enemyShot.getArray(), angles.length);

                        for(int k = 0; k < freeArray.length; k++){

                            int free = freeArray[k];

                            if(free < enemyShot.getArray().size()){

                                double a = angles[k] + Math.random() * Math.PI/6 - Math.PI/12;
                                double vx = Math.cos(a);
                                double vy = Math.sin(a);

                                enemyShot.setX(free, enemy2.getX(i));
                                enemyShot.setY(free, enemy2.getY(i));
                                enemyShot.setVX(free, vx * 0.30);
                                enemyShot.setVY(free, vy * 0.30);
                                enemyShot.setStateValue(free, 1);
                            }
                        }
                    }
                }
            }
        }

        if(Utilidades.getCurrentTime() > enemy2.getEnemiesSpawnTime()){


            enemy2.addNewElement(ACTIVE, enemy2.getEnemy2_spawnX(), -10.0, 0, 0.42);
            enemy2.addAngle((3 * Math.PI) / 2);
            enemy2.addRV(0.0);
            enemy2.addCount();

                if(enemy2.getEnemy2_count() < 10){

                    enemy2.setEnemiesSpawnTime(Utilidades.getCurrentTime() + 120L);
                }
                else {

                    enemy2.setEnemy2_count(0);
                    enemy2.setEnemy2_spawnX(Math.random() > 0.5 ? 96.0 : 384.0);
                    enemy2.setEnemiesSpawnTime((long)((double)(Utilidades.getCurrentTime() + 3000L) + Math.random() * 3000));
                }

        }
    }

    static void stateEnemy2(enemy2 enemy2, shot playerShot, enemyShot enemyShot){
        InimigoProjetil(enemy2, playerShot);
        verificaEnemy2(enemy2, enemyShot);
    }
}

final class statesUpdatesEnemy3 {

    private static void InimigoProjetil(enemy3 enemy3, shot playerShot) {
        for (int k = 0; k < playerShot.getArray().size(); k++) {
            for (int i = 0; i < enemy3.getArray().size(); i++) {
                if (enemy3.getStateValue(i) == ACTIVE) {
                    double dx = enemy3.getX(i) - playerShot.getX(k);
                    double dy = enemy3.getY(i) - playerShot.getY(k);
                    double dist = Math.sqrt(dx * dx + dy * dy);

                    if (dist < enemy3.getRadius()) {
                        enemy3.setStateValue(i, EXPLODING);
                        enemy3.addExplosion_start(getCurrentTime());
                        enemy3.addExplosion_end(getCurrentTime() + 500L);
                    }
                }
            }
        }
    }

    private static void verificaEnemy3(enemy3 enemy3, enemyShot enemyShot) {
        for (int i = 0; i < enemy3.getArray().size(); i++) {
            if (enemy3.getStateValue(i) == EXPLODING) {
                if (getCurrentTime() > enemy3.getExplosion_end()) {
                    enemy3.setStateValue(i, INACTIVE);
                }
            }

            if (enemy3.getStateValue(i) == ACTIVE) {
                // verificando se inimigo saiu da tela
                if (enemy3.getX(i) < -10 || enemy3.getX(i) > GameLib.WIDTH + 10) {
                    enemy3.setStateValue(i, INACTIVE);
                } else {
                    boolean shootNow = false;
                    long lastTime = 0;
                    if(enemy3.getEnemy3_phase() == 0){
                        if (enemy3.getX(i) == 120 && enemy3.getY(i) == 540) {
                            enemy3.setEnemy3_phase(1);
                            lastTime = getCurrentTime();
                        }
                        else if(enemy3.getX(i) < 120)
                            enemy3.setX(i, enemy3.getX(i) + (enemy3.getVX(i) * getDelta()));

                         else if(enemy3.getY(i) < 540)
                             enemy3.setY(i, enemy3.getY(i) + (enemy3.getVY(i) * getDelta() * (-1.0)));

                    }
                    if(enemy3.getEnemy3_phase() == 2){
                        if (enemy3.getX(i) == 360 && enemy3.getY(i) == 180) {
                            enemy3.setEnemy3_phase(3);
                            lastTime = getCurrentTime();
                        }
                        else if(enemy3.getX(i) < 360)
                            enemy3.setX(i, enemy3.getX(i) + (enemy3.getVX(i) * getDelta()));

                        else if(enemy3.getY(i) < 180)
                            enemy3.setY(i, enemy3.getY(i) - (enemy3.getVY(i) * getDelta() * (-1.0)));
                    }

                    if(enemy3.getEnemy3_phase() == 2){
                        if (enemy3.getX(i) == 500) {
                            enemy3.setStateValue(i, INACTIVE);
                        }
                        else if(enemy3.getX(i) < 500)
                            enemy3.setX(i, enemy3.getX(i) + (enemy3.getVX(i) * getDelta()));
                    }

                    if(enemy3.getEnemy3_phase() == 1 || enemy3.getEnemy3_phase() == 3){
                        shootNow = true;
                        if(lastTime + enemy3.getShootTime() <= getCurrentTime()){
                            if(enemy3.getEnemy3_phase() < 2) enemy3.setEnemy3_phase(2);
                            else enemy3.setEnemy3_phase(4);
                        }

                    }

                    if (shootNow) {

                        double [] angles = { 0, Math.PI/2, Math.PI, (Math.PI * 3)/2 };

                        for(int m = 0; m < 3; m++){

                            double a = angles[m];
                            double vx = Math.cos(a);
                            double vy = Math.sin(a);
                            enemyShot.addEnemyShot(ACTIVE, enemy3.getX(i), enemy3.getY(i), vx * 0.30, vy * 0.30);
                        }


                    }

                }
            }
            if (enemy3.getStateValue(i) == INACTIVE) {
                enemy3.removeElement(i);
            }
        }

        if (getCurrentTime() > enemy3.getEnemiesSpawnTime() && enemy3.getArray().isEmpty()) {
            // Ajustando as posições de spawn dos inimigos
            enemy3.addNewElement(ACTIVE, -10.0, -10.0, 0.42, 0.42);

            enemy3.setEnemiesSpawnTime((long)((double)(getCurrentTime() + 3000L) + Math.random() * 3000));

        }
    }

    static void stateEnemy3(enemy3 enemy3, shot playerShot, enemyShot enemyShot) {
        InimigoProjetil(enemy3, playerShot);
        verificaEnemy3(enemy3, enemyShot);
    }
}





final class stateUpdatesPlayerShots{

    static void verificaPlayerShot(shot playerShot){
        for(int i = 0; i < playerShot.getArray().size(); i++){

            if(playerShot.getStateValue(i) == ACTIVE){

                /* verificando se projétil saiu da tela */
                if(playerShot.getY(i) < 0) {

                    playerShot.setStateValue(i, INACTIVE);
                }
                else {

                    playerShot.setX(i, playerShot.getX(i) + (playerShot.getVX(i) * Utilidades.getDelta()));
                    playerShot.setY(i, playerShot.getY(i) + (playerShot.getVY(i) * Utilidades.getDelta()));
                }
            }
        }
    }
}

final class stateUpdatesEnemyShots{
    static void verificaEnemyShot(enemyShot enemyShot){
        for(int i = 0; i < enemyShot.getArray().size(); i++){

            if(enemyShot.getStateValue(i) == ACTIVE){

                /* verificando se projétil saiu da tela */
                if(enemyShot.getY(i) > GameLib.HEIGHT) {

                    enemyShot.setStateValue(i, INACTIVE);
                }
                else {

                    enemyShot.setX(i, enemyShot.getX(i) +(enemyShot.getVX(i) * Utilidades.getDelta()));
                    enemyShot.setY(i, enemyShot.getY(i) +(enemyShot.getVY(i) * Utilidades.getDelta()));
                }
            }
        }
    }
}

