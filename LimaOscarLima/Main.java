package LimaOscarLima;
import java.awt.Color;

import LimaOscarLima.BaraoVermelho.Projectile;
import LimaOscarLima.CapitaoMagico.*;
import LimaOscarLima.BaraoVermelho.Character;
import LimaOscarLima.UrfRider.Background;
import LimaOscarLima.GarenR.*;

public class Main {

    /* Constantes relacionadas aos estados que os elementos   */
    /* do jogo (player, projeteis ou inimigos) podem assumir. */

    public static final int INACTIVE = 0;
    public static final int ACTIVE = 1;
    public static final int EXPLODING = 2;


    /* Espera, sem fazer nada, até que o instante de tempo atual seja */
    /* maior ou igual ao instante especificado no parâmetro "time.    */

    public static void busyWait(long time){

        while(System.currentTimeMillis() < time) Thread.yield();
    }

    /* Encontra e devolve o primeiro índice do  */
    /* array referente a uma posição "inativa". */

    public static int findFreeIndex(int [] stateArray){

        int i;

        for(i = 0; i < stateArray.length; i++){

            if(stateArray[i] == INACTIVE) break;
        }

        return i;
    }

    /* Encontra e devolve o conjunto de índices (a quantidade */
    /* de índices é defnida através do parâmetro "amount") do */
    /* array, referentes a posições "inativas".               */

    public static int [] findFreeIndex(int [] stateArray, int amount){

        int i, k;
        int [] freeArray = { stateArray.length, stateArray.length, stateArray.length };

        for(i = 0, k = 0; i < stateArray.length && k < amount; i++){

            if(stateArray[i] == INACTIVE) {

                freeArray[k] = i;
                k++;
            }
        }

        return freeArray;
    }

    /* Método principal */

    public static void main(String [] args){

        /* Indica que o jogo está em execução */
        boolean running = true;

        /* variáveis usadas no controle de tempo efetuado no main loop */

        long delta;
        long currentTime = System.currentTimeMillis();


        /* variáveis do player */

        Character player = new Character(ACTIVE, GameLib.WIDTH / 2, GameLib.HEIGHT * 0.90, 0.25,
                0.25, 12, 0.0, 0.0, currentTime);

        /* variáveis dos projéteis disparados pelo player */

        Projectile projectile = Nautilus.inicializaProjetile(10);

        /* variáveis dos inimigos tipo 1 */

        Enemies enemy1 = Nautilus.inicializaEnemy(10, 9.0, currentTime + 2000);

        /* variáveis dos inimigos tipo 2 */

        EnemyJR enemy2 = Nautilus.inicializaEnemyJR(10, 12.0, currentTime + 7000,GameLib.WIDTH * 0.20, 0);

        /* variáveis dos projéteis lançados pelos inimigos (tanto tipo 1, quanto tipo 2) */

        MissileBarrage e_projectile = Nautilus.inicializaMissileBarrage(200, 2.0);

        /* estrelas que formam o fundo de primeiro plano */

        Background background1 = Nautilus.inicializaBackground(20,0.070,0.0);

        /* estrelas que formam o fundo de segundo plano */

        Background background2 = Nautilus.inicializaBackground(50,0.045,0.0);

        /* iniciado interface gráfica */

        GameLib.initGraphics();

        /*************************************************************************************************/
        /*                                                                                               */
        /* Main loop do jogo                                                                             */
        /*                                                                                               */
        /* O main loop do jogo possui executa as seguintes operações:                                    */
        /*                                                                                               */
        /* 1) Verifica se há colisões e atualiza estados dos elementos conforme a necessidade.           */
        /*                                                                                               */
        /* 2) Atualiza estados dos elementos baseados no tempo que correu desde a última atualização     */
        /*    e no timestamp atual: posição e orientação, execução de disparos de projéteis, etc.        */
        /*                                                                                               */
        /* 3) Processa entrada do usuário (teclado) e atualiza estados do player conforme a necessidade. */
        /*                                                                                               */
        /* 4) Desenha a cena, a partir dos estados dos elementos.                                        */
        /*                                                                                               */
        /* 5) Espera um período de tempo (de modo que delta seja aproximadamente sempre constante).      */
        /*                                                                                               */
        /*************************************************************************************************/

        while(running){

            /* Usada para atualizar o estado dos elementos do jogo    */
            /* (player, projéteis e inimigos) "delta" indica quantos  */
            /* ms se passaram desde a última atualização.             */

            delta = System.currentTimeMillis() - currentTime;

            /* Já a variável "currentTime" nos dá o timestamp atual.  */

            currentTime = System.currentTimeMillis();

            /***************************/
            /* Verificação de colisões */
            /***************************/

            if(player.getCharacter_state() == ACTIVE){

                /* colisões player - projeteis (inimigo) */

                for(int i = 0; i < e_projectile.getStates().length; i++){

                    double dx = e_projectile.getX(i) - player.getCharacter_X();
                    double dy = e_projectile.getY(i) - player.getCharacter_Y();
                    double dist = Math.sqrt(dx * dx + dy * dy);

                    if(dist < (player.getCharacter_radius() + e_projectile.getRadius()) * 0.8){

                        player.setCharacter_state(EXPLODING);
                        player.setCharacter_explosion_start(currentTime);
                        player.setCharacter_explosion_end(currentTime + 2000);
                    }
                }

                /* colisões player - inimigos */

                for(int i = 0; i < enemy1.getStates().length; i++){

                    double dx = enemy1.getX(i) - player.getCharacter_X();
                    double dy = enemy1.getY(i) - player.getCharacter_Y();
                    double dist = Math.sqrt(dx * dx + dy * dy);

                    if(dist < (player.getCharacter_radius() + enemy1.getRadius()) * 0.8){

                        player.setCharacter_state(EXPLODING);
                        player.setCharacter_explosion_start(currentTime);
                        player.setCharacter_explosion_end(currentTime + 2000);
                    }
                }

                for(int i = 0; i < enemy2.getStates().length; i++){

                    double dx = enemy2.getX(i) - player.getCharacter_X();
                    double dy = enemy2.getY(i) - player.getCharacter_Y();
                    double dist = Math.sqrt(dx * dx + dy * dy);

                    if(dist < (player.getCharacter_radius() + enemy2.getRadius()) * 0.8){

                        player.setCharacter_state(EXPLODING);
                        player.setCharacter_explosion_start(currentTime);
                        player.setCharacter_explosion_end(currentTime + 2000);
                    }
                }
            }

            /* colisões projeteis (player) - inimigos */

            for(int k = 0; k < projectile.getStates().length; k++){

                for(int i = 0; i < enemy1.getStates().length; i++){

                    if(enemy1.getStates_value(i) == ACTIVE){

                        double dx = enemy1.getX(i) - projectile.getX(k);
                        double dy = enemy1.getY(i) - projectile.getY(k);
                        double dist = Math.sqrt(dx * dx + dy * dy);

                        if(dist < enemy1.getRadius()){

                            enemy1.setStates_value( i, EXPLODING);
                            enemy1.setExplosion_start(i, currentTime);
                            enemy1.setExplosion_end(i, currentTime + 500);
                        }
                    }
                }

                for(int i = 0; i < enemy2.getStates().length; i++){

                    if(enemy2.getStates_value(i) == ACTIVE){

                        double dx = enemy2.getX(i) - projectile.getX(k);
                        double dy = enemy2.getY(i) - projectile.getY(k);
                        double dist = Math.sqrt(dx * dx + dy * dy);

                        if(dist < enemy2.getRadius()){

                            enemy2.setStates_value(i, EXPLODING);
                            enemy2.setExplosion_start(i, currentTime);
                            enemy2.setExplosion_end(i, currentTime + 500);
                        }
                    }
                }
            }

            /***************************/
            /* Atualizações de estados */
            /***************************/

            /* projeteis (player) */

            for(int i = 0; i < projectile.getStates().length; i++){

                if(projectile.getStates_value(i) == ACTIVE){

                    /* verificando se projétil saiu da tela */
                    if(projectile.getY(i) < 0) {

                        projectile.setStates_value(i, INACTIVE);
                    }
                    else {

                        projectile.setX(i,projectile.getX(i) + (projectile.getProjectileVX(i) * delta));
                        projectile.setY(i,projectile.getY(i) + (projectile.getProjectileVY(i) * delta));
                    }
                }
            }

            /* projeteis (inimigos) */

            for(int i = 0; i < e_projectile.getStates().length; i++){

                if(e_projectile.getStates_value(i) == ACTIVE){

                    /* verificando se projétil saiu da tela */
                    if(e_projectile.getY(i) > GameLib.HEIGHT) {

                        e_projectile.setStates_value(i, INACTIVE);
                    }
                    else {

                        e_projectile.setX(i, e_projectile.getX(i) +(e_projectile.getProjectileVX(i) * delta));
                        e_projectile.setY(i, e_projectile.getY(i) +(e_projectile.getProjectileVY(i) * delta));
                    }
                }
            }

            /* inimigos tipo 1 */

            for(int i = 0; i < enemy1.getStates().length; i++){

                if(enemy1.getStates_value(i) == EXPLODING){

                    if(currentTime > enemy1.getExplosion_end(i)){

                        enemy1.setStates_value(i, INACTIVE);
                    }
                }

                if(enemy1.getStates_value(i) == ACTIVE){

                    /* verificando se inimigo saiu da tela */
                    if(enemy1.getY(i) > GameLib.HEIGHT + 10) {

                        enemy1.setStates_value(i, INACTIVE);
                    }
                    else {

                        enemy1.setX(i, enemy1.getX(i) + (enemy1.getV(i) * Math.cos(enemy1.getAngle(i)) * delta));
                        enemy1.setY(i, enemy1.getY(i) + (enemy1.getV(i) * Math.sin(enemy1.getAngle(i)) * delta *(-1.0)));
                        enemy1.setAngle(i, enemy1.getAngle(i) + (enemy1.getRV(i) * delta));

                        if(currentTime > enemy1.getNextShot(i) && enemy1.getY(i) < player.getCharacter_Y()){

                            int free = findFreeIndex(e_projectile.getStates());

                            if(free < e_projectile.getStates().length){

                                e_projectile.setX(free, enemy1.getX(i));
                                e_projectile.setY(free, enemy1.getY(i));
                                e_projectile.setProjectileVX(free, Math.cos(enemy1.getAngle(i)) * 0.45);
                                e_projectile.setProjectileVY(free, Math.sin(enemy1.getAngle(i)) * 0.45 * (-1.0));
                                e_projectile.setStates_value(free, 1);

                                enemy1.setNext_Shoot(i, (long) (currentTime + 200 + Math.random() * 500));
                            }
                        }
                    }
                }
            }

            /* inimigos tipo 2 */

            for(int i = 0; i < enemy2.getStates().length; i++){

                if(enemy2.getStates_value(i) == EXPLODING){

                    if(currentTime > enemy2.getExplosion_end(i)){

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

                        enemy2.setX(i, enemy2.getX(i) + (enemy2.getV(i) * Math.cos(enemy2.getAngle(i)) * delta));
                        enemy2.setY(i, enemy2.getY(i) + (enemy2.getV(i) * Math.sin(enemy2.getAngle(i)) * delta * (-1.0)));
                        enemy2.setAngle(i, enemy2.getAngle(i) +(enemy2.getRV(i) * delta));

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
                            int [] freeArray = findFreeIndex(e_projectile.getStates(), angles.length);

                            for(int k = 0; k < freeArray.length; k++){

                                int free = freeArray[k];

                                if(free < e_projectile.getStates().length){

                                    double a = angles[k] + Math.random() * Math.PI/6 - Math.PI/12;
                                    double vx = Math.cos(a);
                                    double vy = Math.sin(a);

                                    e_projectile.setX(free, enemy2.getX(i));
                                    e_projectile.setY(free, enemy2.getY(i));
                                    e_projectile.setProjectileVX(free, vx * 0.30);
                                    e_projectile.setProjectileVY(free, vy * 0.30);
                                    e_projectile.setStates_value(free, 1);
                                }
                            }
                        }
                    }
                }
            }

            /* verificando se novos inimigos (tipo 1) devem ser "lançados" */

            if(currentTime > enemy1.getNextEnemies()){

                int free = findFreeIndex(enemy1.getStates());

                if(free < enemy1.getStates().length){

                    enemy1.setX(free, Math.random() * (GameLib.WIDTH - 20.0) + 10.0);
                    enemy1.setY(free, -10.0);
                    enemy1.setV(free, 0.20 + Math.random() * 0.15);
                    enemy1.setAngle(free, 3 * Math.PI / 2);
                    enemy1.setRV(free, 0.0);
                    enemy1.setStates_value(free, ACTIVE);
                    enemy1.setNext_Shoot(free, currentTime + 500);
                    enemy1.setNextEnemies(currentTime + 500);
                }
            }

            /* verificando se novos inimigos (tipo 2) devem ser "lançados" */

            if(currentTime > enemy2.getNextEnemies()){

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

                        enemy2.setNextEnemies(currentTime + 120);
                    }
                    else {

                        enemy2.setEnemyJR_count(0);
                        enemy2.setEnemyJR_spawnX(Math.random() > 0.5 ? GameLib.WIDTH * 0.2 : GameLib.WIDTH * 0.8);
                        enemy2.setNextEnemies((long) (currentTime + 3000 + Math.random() * 3000));
                    }
                }
            }

            /* Verificando se a explosão do player já acabou.         */
            /* Ao final da explosão, o player volta a ser controlável */
            if(player.getCharacter_state() == EXPLODING){

                if(currentTime > player.getCharacter_explosion_end()){

                    player.setCharacter_state(ACTIVE);
                }
            }

            /********************************************/
            /* Verificando entrada do usuário (teclado) */
            /********************************************/

            if(player.getCharacter_state() == ACTIVE){

                if(GameLib.iskeyPressed(GameLib.KEY_UP)) player.setCharacter_Y(player.getCharacter_Y() - delta * player.getCharacter_VY());
                if(GameLib.iskeyPressed(GameLib.KEY_DOWN)) player.setCharacter_Y(player.getCharacter_Y() + delta * player.getCharacter_VY());
                if(GameLib.iskeyPressed(GameLib.KEY_LEFT)) player.setCharacter_X(player.getCharacter_X() - delta * player.getCharacter_VX());
                if(GameLib.iskeyPressed(GameLib.KEY_RIGHT)) player.setCharacter_X(player.getCharacter_X() + delta * player.getCharacter_VX());
                if(GameLib.iskeyPressed(GameLib.KEY_CONTROL)) {

                    if(currentTime > player.getCharacter_nextShot()){

                        int free = findFreeIndex(projectile.getStates());

                        if(free < projectile.getStates().length){

                            projectile.setX(free, player.getCharacter_X());
                            projectile.setY(free, player.getCharacter_Y() - 2 * player.getCharacter_radius());
                            projectile.setProjectileVX(free, 0.0);
                            projectile.setProjectileVY(free, -1.0);
                            projectile.setStates_value(free, 1);
                            player.setCharacter_nextShot(currentTime + 100);
                        }
                    }
                }
            }

            if(GameLib.iskeyPressed(GameLib.KEY_ESCAPE)) running = false;

            /* Verificando se coordenadas do player ainda estão dentro	*/
            /* da tela de jogo após processar entrada do usuário.       */

            if(player.getCharacter_X() < 0.0) player.setCharacter_X(0.0);
            if(player.getCharacter_X() >= GameLib.WIDTH) player.setCharacter_X(GameLib.WIDTH - 1);
            if(player.getCharacter_Y() < 25.0) player.setCharacter_Y(25.0);
            if(player.getCharacter_Y() >= GameLib.HEIGHT) player.setCharacter_Y(GameLib.HEIGHT - 1);

            /*******************/
            /* Desenho da cena */
            /*******************/

            Background.DesenhaBackGround(background1,background2);

            /* desenhando player */

            if(player.getCharacter_state() == EXPLODING){

                double alpha = (currentTime - player.getCharacter_explosion_start()) / (player.getCharacter_explosion_end() - player.getCharacter_explosion_start());
                GameLib.drawExplosion(player.getCharacter_X(), player.getCharacter_Y(), alpha);
            }
            else{

                GameLib.setColor(Color.BLUE);
                GameLib.drawPlayer(player.getCharacter_X(), player.getCharacter_Y(), player.getCharacter_radius());
            }


            /* deenhando projeteis (player) */

            for(int i = 0; i < projectile.getStates().length; i++){

                if(projectile.getStates_value(i) == ACTIVE){

                    GameLib.setColor(Color.GREEN);
                    GameLib.drawLine(projectile.getX(i), projectile.getY(i) - 5, projectile.getX(i), projectile.getY(i) + 5);
                    GameLib.drawLine(projectile.getX(i) - 1, projectile.getY(i) - 3, projectile.getX(i) - 1, projectile.getY(i) + 3);
                    GameLib.drawLine(projectile.getX(i) + 1, projectile.getY(i) - 3, projectile.getX(i) + 1, projectile.getY(i) + 3);
                }
            }

            /* desenhando projeteis (inimigos) */

            for(int i = 0; i < e_projectile.getStates().length; i++){

                if(e_projectile.getStates_value(i) == ACTIVE){

                    GameLib.setColor(Color.RED);
                    GameLib.drawCircle(e_projectile.getX(i), e_projectile.getY(i), e_projectile.getRadius());
                }
            }

            /* desenhando inimigos (tipo 1) */

            for(int i = 0; i < enemy1.getStates().length; i++){

                if(enemy1.getStates_value(i) == EXPLODING){

                    double alpha = (currentTime - enemy1.getExplosion_start(i)) / (enemy1.getExplosion_end(i) - enemy1.getExplosion_start(i));
                    GameLib.drawExplosion(enemy1.getX(i), enemy1.getY(i), alpha);
                }

                if(enemy1.getStates_value(i) == ACTIVE){

                    GameLib.setColor(Color.CYAN);
                    GameLib.drawCircle(enemy1.getX(i), enemy1.getY(i), enemy1.getRadius());
                }
            }

            /* desenhando inimigos (tipo 2) */

            for(int i = 0; i < enemy2.getStates().length; i++){

                if(enemy2.getStates_value(i) == EXPLODING){

                    double alpha = (currentTime - enemy2.getExplosion_start(i)) / (enemy2.getExplosion_end(i) - enemy2.getExplosion_start(i));
                    GameLib.drawExplosion(enemy2.getX(i), enemy2.getY(i), alpha);
                }

                if(enemy2.getStates_value(i) == ACTIVE){

                    GameLib.setColor(Color.MAGENTA);
                    GameLib.drawDiamond(enemy2.getX(i), enemy2.getY(i), enemy2.getRadius());
                }
            }

            /* chamama a display() da classe GameLib atualiza o desenho exibido pela interface do jogo. */

            GameLib.display();

            /* faz uma pausa de modo que cada execução do laço do main loop demore aproximadamente 5 ms. */

            busyWait(currentTime + 5);
        }

        System.exit(0);
    }
}
