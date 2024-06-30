package LimaOscarLima;

import LimaOscarLima.GameLib.*;
import LimaOscarLima.Graficos.*;
import LimaOscarLima.GarenR.*;
import LimaOscarLima.Util.*;
import LimaOscarLima.gameObjects.multipleEntities.*;
import LimaOscarLima.gameObjects.singleEntities.player;

public class Main{

    /* Constantes relacionadas aos estados que os elementos   */
    /* do jogo (player, projeteis ou inimigos) podem assumir. */

    public static final int INACTIVE = 0;
    public static final int ACTIVE = 1;
    public static final int EXPLODING = 2;

    /* Método principal */

    public static void main(String [] args){

        Utilidades.setRunning(true); //Indica que o jogo está em execução
        Utilidades.updateCurrentTime(); //variáveis usadas no controle de tempo efetuado no main loop

        player player = new player(ACTIVE, GameLib.WIDTH / 2, GameLib.HEIGHT * 0.90, 0.25,
                0.25, 12, 0.0, 0.0, Utilidades.getCurrentTime()); //objeto do player

        shot playerShot = new shot(); //objeto dos projéteis disparados pelo player
        enemy1 enemy1 = new enemy1(9.0, Utilidades.getCurrentTime() + 2000L); //objetos dos inimigos tipo 1
        enemy2 enemy2 = new enemy2( 12.0, Utilidades.getCurrentTime() + 7000L,GameLib.WIDTH * 0.20, 0); //objetos dos inimigos tipo 2
        enemy3 enemy3 = new enemy3( 12.0, Utilidades.getCurrentTime() + 7000L,0, 10000L); //objetos dos inimigos tipo 3

        enemyShot enemyShot = new enemyShot( 2.0); //objetos dos projéteis lançados pelos inimigos (tanto tipo 1, quanto tipo 2)

        Background background1 = new Background(20,0.070,0.0); //estrelas que formam o fundo de primeiro plano
        background1.inicializaBackground(background1);

        Background background2 = new Background(50,0.045,0.0); //estrelas que formam o fundo de segundo plano
        background2.inicializaBackground(background2);

        GameLib.initGraphics(); //iniciando interface gráfica

        /*************************************************************************************************/
        /* Main loop do jogo                                                                             */
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
        /*************************************************************************************************/

        while(Utilidades.isRunning()){

            Rammus.mainLoop(player, playerShot, enemy1, enemy2, enemy3, enemyShot); //Verificação de colisões, estados e inputs
            Graficos.desenharCena(player, playerShot, enemyShot, enemy1, enemy2, enemy3, background1, background2); //Desenho da cena
            GameLib.display(); //chamama a display() da classe GameLib atualiza o desenho exibido pela interface do jogo.
            Utilidades.busyWait(Utilidades.getCurrentTime() + 5L); //faz uma pausa de modo que cada execução do laço do main loop demore aproximadamente 5 ms.
        }

        System.exit(0);
    }
}
