package LimaOscarLima.Util;

import LimaOscarLima.gameObjects.gameObject;
import java.util.ArrayList;

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

    public static int findFreeIndexArray(ArrayList <gameObject> stateArray){

        int i;

        for(i = 0; i < stateArray.size() + 1; i++){

            if(stateArray.get(i).getState() == INACTIVE) break;
        }

        return i;
    }

    /* Encontra e devolve o conjunto de índices (a quantidade */
    /* de índices é defnida através do parâmetro "amount") do */
    /* array, referentes a posições "inativas".               */

    public static int [] findFreeIndexArray(ArrayList<gameObject> Array, int amount){

        int i, k;
        int [] freeArray = { Array.size(),  Array.size(),  Array.size() };

        for(i = 0, k = 0; i <  Array.size() && k < amount; i++){

            if(Array.get(i).getState() == INACTIVE) {

                freeArray[k] = i;
                k++;
            }
        }

        return freeArray;
    }

}
