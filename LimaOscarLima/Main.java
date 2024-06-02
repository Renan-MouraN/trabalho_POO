package LimaOscarLima;

import LimaOscarLima.BaraoVermelho.*;
import LimaOscarLima.CapitaoMagico.*;
import LimaOscarLima.GameLib.*;
import LimaOscarLima.Graficos.*;
import LimaOscarLima.GarenR.*;
import LimaOscarLima.Util.*;

public class Main{

    /* Constantes relacionadas aos estados que os elementos   */
    /* do jogo (player, projeteis ou inimigos) podem assumir. */

    public static final int INACTIVE = 0;
    public static final int ACTIVE = 1;
    public static final int EXPLODING = 2;

    /* Método principal */

    public static void main(String [] args){

        /* Indica que o jogo está em execução */

        Utilidades.setRunning(true);

        /* variáveis usadas no controle de tempo efetuado no main loop */

        Utilidades.updateCurrentTime();

        /* variáveis do player */

        Player player = new Player(ACTIVE, GameLib.WIDTH / 2, GameLib.HEIGHT * 0.90, 0.25,
                0.25, 12, 0.0, 0.0, Utilidades.getCurrentTime());

        /* variáveis dos projéteis disparados pelo player */

        PlayerShot playerShot = new PlayerShot(10);

        /* variáveis dos inimigos tipo 1 */

        Enemies enemy1 = new Enemies(10, 9.0, Utilidades.getCurrentTime() + 2000);

        /* variáveis dos inimigos tipo 2 */

        EnemyJR enemy2 = new EnemyJR(10, 12.0, Utilidades.getCurrentTime() + 7000,GameLib.WIDTH * 0.20, 0);

        /* variáveis dos projéteis lançados pelos inimigos (tanto tipo 1, quanto tipo 2) */

        EnemyShot enemyShot = new EnemyShot(200, 2.0);

        /* estrelas que formam o fundo de primeiro plano */

        Background background1 = new Background(20,0.070,0.0);
        background1.inicializaBackground(background1);

        /* estrelas que formam o fundo de segundo plano */

        Background background2 = new Background(50,0.045,0.0);
        background2.inicializaBackground(background2);

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

        while(Utilidades.isRunning()){

            /* Verificação de colisões, estados e inputs*/

            Rammus.mainLoop(player, playerShot, enemy1, enemy2, enemyShot);

            /* Desenho da cena */

            Graficos.desenharCena(player, playerShot, enemyShot, enemy1, enemy2, background1, background2);

            /* chamama a display() da classe GameLib atualiza o desenho exibido pela interface do jogo. */

            GameLib.display();

            /* faz uma pausa de modo que cada execução do laço do main loop demore aproximadamente 5 ms. */

            Utilidades.busyWait(Utilidades.getCurrentTime() + 5);

            //apaguei os /******/ msm acho ruim chama o batman
        }

        System.exit(0);
    }
}
