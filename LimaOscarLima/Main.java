package LimaOscarLima;
import java.awt.Color;

import LimaOscarLima.BaraoVermelho.*;
import LimaOscarLima.CapitaoMagico.*;
import LimaOscarLima.GameLib.GameLib;
import LimaOscarLima.Interfaces.Constantes;
import LimaOscarLima.UrfRider.Background;
import LimaOscarLima.GarenR.*;

public class Main implements Constantes {

    /* Constantes relacionadas aos estados que os elementos   */
    /* do jogo (player, projeteis ou inimigos) podem assumir. */

    public static final int INACTIVE = 0;
    public static final int ACTIVE = 1;
    public static final int EXPLODING = 2;
    

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
        Zillean.setRunning(true);

        /* variáveis usadas no controle de tempo efetuado no main loop */

        Zillean.updateCurrentTime();

        /* variáveis do player */

        Player player = new Player(ACTIVE, GameLib.WIDTH / 2, GameLib.HEIGHT * 0.90, 0.25,
                0.25, 12, 0.0, 0.0, Zillean.getCurrentTime());

        /* variáveis dos projéteis disparados pelo player */

        PlayerShot playerShot = new PlayerShot(10);

        /* variáveis dos inimigos tipo 1 */

        Enemies enemy1 = new Enemies(10, 9.0, Zillean.getCurrentTime() + 2000);

        /* variáveis dos inimigos tipo 2 */

        EnemyJR enemy2 = new EnemyJR(10, 12.0, Zillean.getCurrentTime() + 7000,GameLib.WIDTH * 0.20, 0);

        /* variáveis dos projéteis lançados pelos inimigos (tanto tipo 1, quanto tipo 2) */

        MissileBarrage enemyShot = new MissileBarrage(200, 2.0);

        /* estrelas que formam o fundo de primeiro plano */

        Background background1 = new Background(20,0.070,0.0);

        /* estrelas que formam o fundo de segundo plano */

        Background background2 = new Background(50,0.045,0.0);

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

        while(Zillean.isRunning()){

            /* Usada para atualizar o estado dos elementos do jogo    */
            /* (player, projéteis e inimigos) "delta" indica quantos  */
            /* ms se passaram desde a última atualização.             */

            Zillean.setDelta(System.currentTimeMillis() - Zillean.getCurrentTime());

            /* Já a variável "currentTime" nos dá o timestamp atual.  */

            Zillean.updateCurrentTime();

            /***************************/
            /* Verificação de colisões */
            /***************************/

            Rammus.verificaColisão(player, enemy1, enemy2, playerShot, enemyShot);

            /***************************/
            /* Atualizações de estados */
            /***************************/

            Rammus.verificaEstados(player, enemy1, enemy2, playerShot, enemyShot);

            /********************************************/
            /* Verificando entrada do usuário (teclado) */
            /********************************************/

            if(player.getplayer_state() == ACTIVE){

                if(GameLib.iskeyPressed(GameLib.KEY_UP)) player.setplayer_Y(player.getplayer_Y() - Zillean.getDelta() * player.getplayer_VY());
                if(GameLib.iskeyPressed(GameLib.KEY_DOWN)) player.setplayer_Y(player.getplayer_Y() + Zillean.getDelta() * player.getplayer_VY());
                if(GameLib.iskeyPressed(GameLib.KEY_LEFT)) player.setplayer_X(player.getplayer_X() - Zillean.getDelta() * player.getplayer_VX());
                if(GameLib.iskeyPressed(GameLib.KEY_RIGHT)) player.setplayer_X(player.getplayer_X() + Zillean.getDelta() * player.getplayer_VX());
                if(GameLib.iskeyPressed(GameLib.KEY_CONTROL)) {

                    if(Zillean.getCurrentTime() > player.getplayer_nextShot()){

                        int free = findFreeIndex(playerShot.getStates());

                        if(free < playerShot.getStates().length){

                            playerShot.setX(free, player.getplayer_X());
                            playerShot.setY(free, player.getplayer_Y() - 2 * player.getplayer_radius());
                            playerShot.setplayerShotVX(free, 0.0);
                            playerShot.setplayerShotVY(free, -1.0);
                            playerShot.setStates_value(free, 1);
                            player.setplayer_nextShot(Zillean.getCurrentTime() + 100);
                        }
                    }
                }
            }

            if(GameLib.iskeyPressed(GameLib.KEY_ESCAPE)) Zillean.setRunning(false);

            /* Verificando se coordenadas do player ainda estão dentro	*/
            /* da tela de jogo após processar entrada do usuário.       */

            if(player.getplayer_X() < 0.0) player.setplayer_X(0.0);
            if(player.getplayer_X() >= GameLib.WIDTH) player.setplayer_X(GameLib.WIDTH - 1);
            if(player.getplayer_Y() < 25.0) player.setplayer_Y(25.0);
            if(player.getplayer_Y() >= GameLib.HEIGHT) player.setplayer_Y(GameLib.HEIGHT - 1);

            /*******************/
            /* Desenho da cena */
            /*******************/

            Background.DesenhaBackGround(background1,background2);

            /* desenhando player */

            if(player.getplayer_state() == EXPLODING){

                double alpha = (Zillean.getCurrentTime() - player.getplayer_explosion_start()) / (player.getplayer_explosion_end() - player.getplayer_explosion_start());
                GameLib.drawExplosion(player.getplayer_X(), player.getplayer_Y(), alpha);
            }
            else{

                GameLib.setColor(Color.BLUE);
                GameLib.drawPlayer(player.getplayer_X(), player.getplayer_Y(), player.getplayer_radius());
            }


            /* deenhando projeteis (player) */

            for(int i = 0; i < playerShot.getStates().length; i++){

                if(playerShot.getStates_value(i) == ACTIVE){

                    GameLib.setColor(Color.GREEN);
                    GameLib.drawLine(playerShot.getX(i), playerShot.getY(i) - 5, playerShot.getX(i), playerShot.getY(i) + 5);
                    GameLib.drawLine(playerShot.getX(i) - 1, playerShot.getY(i) - 3, playerShot.getX(i) - 1, playerShot.getY(i) + 3);
                    GameLib.drawLine(playerShot.getX(i) + 1, playerShot.getY(i) - 3, playerShot.getX(i) + 1, playerShot.getY(i) + 3);
                }
            }

            /* desenhando projeteis (inimigos) */

            for(int i = 0; i < enemyShot.getStates().length; i++){

                if(enemyShot.getStates_value(i) == ACTIVE){

                    GameLib.setColor(Color.RED);
                    GameLib.drawCircle(enemyShot.getX(i), enemyShot.getY(i), enemyShot.getRadius());
                }
            }

            /* desenhando inimigos (tipo 1) */

            for(int i = 0; i < enemy1.getStates().length; i++){

                if(enemy1.getStates_value(i) == EXPLODING){

                    double alpha = (Zillean.getCurrentTime() - enemy1.getExplosion_start(i)) / (enemy1.getExplosion_end(i) - enemy1.getExplosion_start(i));
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

                    double alpha = (Zillean.getCurrentTime() - enemy2.getExplosion_start(i)) / (enemy2.getExplosion_end(i) - enemy2.getExplosion_start(i));
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

            Zillean.busyWait(Zillean.getCurrentTime() + 5);
        }

        System.exit(0);
    }
}
