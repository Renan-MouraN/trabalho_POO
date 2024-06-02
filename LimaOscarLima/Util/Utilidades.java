package LimaOscarLima.Util;

import static LimaOscarLima.Main.*;

public class Utilidades {

    private static boolean running;
    private static long delta;
    private static long currentTime;

    public static boolean isRunning(){
        return running;
    }

    public static long getDelta(){ return delta;}

    public static long getCurrentTime(){ return currentTime;}

    public static void setRunning(boolean value){
        running = value;
    }

    public static void setDelta(long l){delta = l;}

    public static void updateCurrentTime(){ currentTime = System.currentTimeMillis();}

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

}
