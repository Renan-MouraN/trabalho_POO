package LimaOscarLima;
import java.awt.Color;


import LimaOscarLima.BaraoVermelho.Projectile;
import LimaOscarLima.CapitaoMagico.*;
import LimaOscarLima.BaraoVermelho.Character;
import LimaOscarLima.UrfRider.Background;

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

        Projectile projectile = new Projectile(10);

        /* variáveis dos inimigos tipo 1 */

        Enemies enemy1 = new Enemies(10, 9.0, currentTime + 2000);

        /* variáveis dos inimigos tipo 2 */

        EnemyJR enemy2 = new EnemyJR(10, 12.0, currentTime + 7000,GameLib.WIDTH * 0.20, 0);

        /* variáveis dos projéteis lançados pelos inimigos (tanto tipo 1, quanto tipo 2) */

        MissileBarrage e_projectile = new MissileBarrage(200, 2.0);

        /* estrelas que formam o fundo de primeiro plano */

        Background background1 = new Background(20,0.070,0.0);

        /* estrelas que formam o fundo de segundo plano */

        Background background2 = new Background(50,0.045,0.0);

        /* inicializações */

        for(int i = 0; i < projectile.getStates_length(); i++) projectile.setStates(i, INACTIVE);
        for(int i = 0; i < e_projectile.getStates_length(); i++) e_projectile.setStates(i, INACTIVE);
        for(int i = 0; i < enemy1.getStates_length(); i++) enemy1.setStates(i, INACTIVE);
        for(int i = 0; i < enemy2.getStates_length(); i++) enemy2.setStates(i, INACTIVE);

        for(int i = 0; i < background1.getX_length(); i++){

            background1.setX_value(i,Math.random() * GameLib.WIDTH);
            background1.setY_value(i,Math.random() * GameLib.HEIGHT);
        }

        for(int i = 0; i < background2.getX_length(); i++){

            background2.setX_value(i,Math.random() * GameLib.WIDTH);
            background2.setY_value(i,Math.random() * GameLib.HEIGHT);
        }

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

                for(int i = 0; i < e_projectile.getStates_length(); i++){

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

                for(int i = 0; i < enemy1.getStates_length(); i++){

                    double dx = enemy1.getX(i) - player.getCharacter_X();
                    double dy = enemy1.getY(i) - player.getCharacter_Y();
                    double dist = Math.sqrt(dx * dx + dy * dy);

                    if(dist < (player.getCharacter_radius() + enemy1.getRadius()) * 0.8){

                        player.setCharacter_state(EXPLODING);
                        player.setCharacter_explosion_start(currentTime);
                        player.setCharacter_explosion_end(currentTime + 2000);
                    }
                }

                for(int i = 0; i < enemy2.getStates_length(); i++){

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

            for(int k = 0; k < projectile.getStates_length(); k++){

                for(int i = 0; i < enemy1.getStates_length(); i++){

                    if(enemy1.getStates(i) == ACTIVE){

                        double dx = enemy1.getX(i) - projectile.getX(k);
                        double dy = enemy1.getY(i) - projectile.getY(k);
                        double dist = Math.sqrt(dx * dx + dy * dy);

                        if(dist < enemy1.getRadius()){

                            enemy1.setStates( i, EXPLODING);
                            enemy1.setExplosion_start(i, currentTime);
                            enemy1.setExplosion_end(i, currentTime + 500);
                        }
                    }
                }

                for(int i = 0; i < enemy2.getStates_length(); i++){

                    if(enemy2.getStates(i) == ACTIVE){

                        double dx = enemy2.getX(i) - projectile.getX(k);
                        double dy = enemy2.getY(i) - projectile.getY(k);
                        double dist = Math.sqrt(dx * dx + dy * dy);

                        if(dist < enemy2.getRadius()){

                            enemy2.setStates(i, EXPLODING);
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

            for(int i = 0; i < projectile.getStates_length(); i++){

                if(projectile.getStates(i) == ACTIVE){

                    /* verificando se projétil saiu da tela */
                    if(projectile.getY(i) < 0) {

                        projectile.setStates(i, INACTIVE);
                    }
                    else {

                        projectile.setX(i,projectile.getX(i) + (projectile.getProjectileVX(i) * delta));
                        projectile.setY(i,projectile.getY(i) + (projectile.getProjectileVY(i) * delta));
                    }
                }
            }

            /* projeteis (inimigos) */

            for(int i = 0; i < e_projectile.getStates_length(); i++){

                if(e_projectile.getStates(i) == ACTIVE){

                    /* verificando se projétil saiu da tela */
                    if(e_projectile.getY(i) > GameLib.HEIGHT) {

                        e_projectile.setStates(i, INACTIVE);
                    }
                    else {

                        e_projectile.setX(i, e_projectile.getX(i) +(e_projectile.getProjectileVX(i) * delta));
                        e_projectile.setY(i, e_projectile.getY(i) +(e_projectile.getProjectileVY(i) * delta));
                    }
                }
            }

            /* inimigos tipo 1 */

            for(int i = 0; i < enemy1.getStates_length(); i++){

                if(enemy1.getStates(i) == EXPLODING){

                    if(currentTime > enemy1.getExplosion_end(i)){

                        enemy1.setStates(i, INACTIVE);
                    }
                }

                if(enemy1.getStates(i) == ACTIVE){

                    /* verificando se inimigo saiu da tela */
                    if(enemy1.getY(i) > GameLib.HEIGHT + 10) {

                        enemy1.setStates(i, INACTIVE);
                    }
                    else {

                        enemy1.setX(i, enemy1.getX(i) + (enemy1.getV(i) * Math.cos(enemy1.getAngle(i)) * delta));
                        enemy1.setX(i, enemy1.getX(i) + (enemy1.getV(i) * Math.cos(enemy1.getAngle(i)) * delta *(-1.0)));
                        enemy1.setAngle(i, enemy1.getAngle(i) + (enemy1.getRV(i) * delta));

                        if(currentTime > enemy1.getNextShot(i) && enemy1.getY(i) < player.getCharacter_Y()){

                            int free = findFreeIndex(e_projectile_states);

                            if(free < e_projectile_states.length){

                                e_projectile_X[free] = enemy1_X[i];
                                e_projectile_Y[free] = enemy1_Y[i];
                                e_projectile_VX[free] = Math.cos(enemy1_angle[i]) * 0.45;
                                e_projectile_VY[free] = Math.sin(enemy1_angle[i]) * 0.45 * (-1.0);
                                e_projectile_states[free] = 1;

                                enemy1_nextShoot[i] = (long) (currentTime + 200 + Math.random() * 500);
                            }
                        }
                    }
                }
            }

            /* inimigos tipo 2 */

            for(int i = 0; i < enemy2_states.length; i++){

                if(enemy2_states[i] == EXPLODING){

                    if(currentTime > enemy2_explosion_end[i]){

                        enemy2_states[i] = INACTIVE;
                    }
                }

                if(enemy2_states[i] == ACTIVE){

                    /* verificando se inimigo saiu da tela */
                    if(	enemy2_X[i] < -10 || enemy2_X[i] > GameLib.WIDTH + 10 ) {

                        enemy2_states[i] = INACTIVE;
                    }
                    else {

                        boolean shootNow = false;
                        double previousY = enemy2_Y[i];

                        enemy2_X[i] += enemy2_V[i] * Math.cos(enemy2_angle[i]) * delta;
                        enemy2_Y[i] += enemy2_V[i] * Math.sin(enemy2_angle[i]) * delta * (-1.0);
                        enemy2_angle[i] += enemy2_RV[i] * delta;

                        double threshold = GameLib.HEIGHT * 0.30;

                        if(previousY < threshold && enemy2_Y[i] >= threshold) {

                            if(enemy2_X[i] < GameLib.WIDTH / 2) enemy2_RV[i] = 0.003;
                            else enemy2_RV[i] = -0.003;
                        }

                        if(enemy2_RV[i] > 0 && Math.abs(enemy2_angle[i] - 3 * Math.PI) < 0.05){

                            enemy2_RV[i] = 0.0;
                            enemy2_angle[i] = 3 * Math.PI;
                            shootNow = true;
                        }

                        if(enemy2_RV[i] < 0 && Math.abs(enemy2_angle[i]) < 0.05){

                            enemy2_RV[i] = 0.0;
                            enemy2_angle[i] = 0.0;
                            shootNow = true;
                        }

                        if(shootNow){

                            double [] angles = { Math.PI/2 + Math.PI/8, Math.PI/2, Math.PI/2 - Math.PI/8 };
                            int [] freeArray = findFreeIndex(e_projectile_states, angles.length);

                            for(int k = 0; k < freeArray.length; k++){

                                int free = freeArray[k];

                                if(free < e_projectile_states.length){

                                    double a = angles[k] + Math.random() * Math.PI/6 - Math.PI/12;
                                    double vx = Math.cos(a);
                                    double vy = Math.sin(a);

                                    e_projectile_X[free] = enemy2_X[i];
                                    e_projectile_Y[free] = enemy2_Y[i];
                                    e_projectile_VX[free] = vx * 0.30;
                                    e_projectile_VY[free] = vy * 0.30;
                                    e_projectile_states[free] = 1;
                                }
                            }
                        }
                    }
                }
            }

            /* verificando se novos inimigos (tipo 1) devem ser "lançados" */

            if(currentTime > nextEnemy1){

                int free = findFreeIndex(enemy1_states);

                if(free < enemy1_states.length){

                    enemy1_X[free] = Math.random() * (GameLib.WIDTH - 20.0) + 10.0;
                    enemy1_Y[free] = -10.0;
                    enemy1_V[free] = 0.20 + Math.random() * 0.15;
                    enemy1_angle[free] = 3 * Math.PI / 2;
                    enemy1_RV[free] = 0.0;
                    enemy1_states[free] = ACTIVE;
                    enemy1_nextShoot[free] = currentTime + 500;
                    nextEnemy1 = currentTime + 500;
                }
            }

            /* verificando se novos inimigos (tipo 2) devem ser "lançados" */

            if(currentTime > nextEnemy2){

                int free = findFreeIndex(enemy2_states);

                if(free < enemy2_states.length){

                    enemy2_X[free] = enemy2_spawnX;
                    enemy2_Y[free] = -10.0;
                    enemy2_V[free] = 0.42;
                    enemy2_angle[free] = (3 * Math.PI) / 2;
                    enemy2_RV[free] = 0.0;
                    enemy2_states[free] = ACTIVE;

                    enemy2_count++;

                    if(enemy2_count < 10){

                        nextEnemy2 = currentTime + 120;
                    }
                    else {

                        enemy2_count = 0;
                        enemy2_spawnX = Math.random() > 0.5 ? GameLib.WIDTH * 0.2 : GameLib.WIDTH * 0.8;
                        nextEnemy2 = (long) (currentTime + 3000 + Math.random() * 3000);
                    }
                }
            }

            /* Verificando se a explosão do player já acabou.         */
            /* Ao final da explosão, o player volta a ser controlável */
            if(player_state == EXPLODING){

                if(currentTime > player_explosion_end){

                    player_state = ACTIVE;
                }
            }

            /********************************************/
            /* Verificando entrada do usuário (teclado) */
            /********************************************/

            if(player_state == ACTIVE){

                if(GameLib.iskeyPressed(GameLib.KEY_UP)) player_Y -= delta * player_VY;
                if(GameLib.iskeyPressed(GameLib.KEY_DOWN)) player_Y += delta * player_VY;
                if(GameLib.iskeyPressed(GameLib.KEY_LEFT)) player_X -= delta * player_VX;
                if(GameLib.iskeyPressed(GameLib.KEY_RIGHT)) player_X += delta * player_VY;
                if(GameLib.iskeyPressed(GameLib.KEY_CONTROL)) {

                    if(currentTime > player_nextShot){

                        int free = findFreeIndex(projectile_states);

                        if(free < projectile_states.length){

                            projectile_X[free] = player_X;
                            projectile_Y[free] = player_Y - 2 * player_radius;
                            projectile_VX[free] = 0.0;
                            projectile_VY[free] = -1.0;
                            projectile_states[free] = 1;
                            player_nextShot = currentTime + 100;
                        }
                    }
                }
            }

            if(GameLib.iskeyPressed(GameLib.KEY_ESCAPE)) running = false;

            /* Verificando se coordenadas do player ainda estão dentro	*/
            /* da tela de jogo após processar entrada do usuário.       */

            if(player_X < 0.0) player_X = 0.0;
            if(player_X >= GameLib.WIDTH) player_X = GameLib.WIDTH - 1;
            if(player_Y < 25.0) player_Y = 25.0;
            if(player_Y >= GameLib.HEIGHT) player_Y = GameLib.HEIGHT - 1;

            /*******************/
            /* Desenho da cena */
            /*******************/

            /* desenhando plano fundo distante */

            GameLib.setColor(Color.DARK_GRAY);
            background2_count += background2_speed * delta;

            for(int i = 0; i < background2_X.length; i++){

                GameLib.fillRect(background2_X[i], (background2_Y[i] + background2_count) % GameLib.HEIGHT, 2, 2);
            }

            /* desenhando plano de fundo próximo */

            GameLib.setColor(Color.GRAY);
            background1_count += background1_speed * delta;

            for(int i = 0; i < background1_X.length; i++){

                GameLib.fillRect(background1_X[i], (background1_Y[i] + background1_count) % GameLib.HEIGHT, 3, 3);
            }

            /* desenhando player */

            if(player_state == EXPLODING){

                double alpha = (currentTime - player_explosion_start) / (player_explosion_end - player_explosion_start);
                GameLib.drawExplosion(player_X, player_Y, alpha);
            }
            else{

                GameLib.setColor(Color.BLUE);
                GameLib.drawPlayer(player_X, player_Y, player_radius);
            }


            /* deenhando projeteis (player) */

            for(int i = 0; i < projectile_states.length; i++){

                if(projectile_states[i] == ACTIVE){

                    GameLib.setColor(Color.GREEN);
                    GameLib.drawLine(projectile_X[i], projectile_Y[i] - 5, projectile_X[i], projectile_Y[i] + 5);
                    GameLib.drawLine(projectile_X[i] - 1, projectile_Y[i] - 3, projectile_X[i] - 1, projectile_Y[i] + 3);
                    GameLib.drawLine(projectile_X[i] + 1, projectile_Y[i] - 3, projectile_X[i] + 1, projectile_Y[i] + 3);
                }
            }

            /* desenhando projeteis (inimigos) */

            for(int i = 0; i < e_projectile_states.length; i++){

                if(e_projectile_states[i] == ACTIVE){

                    GameLib.setColor(Color.RED);
                    GameLib.drawCircle(e_projectile_X[i], e_projectile_Y[i], e_projectile_radius);
                }
            }

            /* desenhando inimigos (tipo 1) */

            for(int i = 0; i < enemy1_states.length; i++){

                if(enemy1_states[i] == EXPLODING){

                    double alpha = (currentTime - enemy1_explosion_start[i]) / (enemy1_explosion_end[i] - enemy1_explosion_start[i]);
                    GameLib.drawExplosion(enemy1_X[i], enemy1_Y[i], alpha);
                }

                if(enemy1_states[i] == ACTIVE){

                    GameLib.setColor(Color.CYAN);
                    GameLib.drawCircle(enemy1_X[i], enemy1_Y[i], enemy1_radius);
                }
            }

            /* desenhando inimigos (tipo 2) */

            for(int i = 0; i < enemy2_states.length; i++){

                if(enemy2_states[i] == EXPLODING){

                    double alpha = (currentTime - enemy2_explosion_start[i]) / (enemy2_explosion_end[i] - enemy2_explosion_start[i]);
                    GameLib.drawExplosion(enemy2_X[i], enemy2_Y[i], alpha);
                }

                if(enemy2_states[i] == ACTIVE){

                    GameLib.setColor(Color.MAGENTA);
                    GameLib.drawDiamond(enemy2_X[i], enemy2_Y[i], enemy2_radius);
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
