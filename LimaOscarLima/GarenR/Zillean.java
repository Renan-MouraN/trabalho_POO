package LimaOscarLima.GarenR;

public class Zillean {

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
}
