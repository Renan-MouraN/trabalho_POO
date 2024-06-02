package LimaOscarLima.Util;

import static LimaOscarLima.Main.*;

public class Utilidades {

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
