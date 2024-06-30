package LimaOscarLima.Graficos;

import LimaOscarLima.GameLib.GameLib;
import LimaOscarLima.Util.*;

import java.awt.*;

public class Background{
    private final double [] background_X;
    private final double [] background_Y;
    private final double background_speed;
    private double background_count;

    public Background(int d, double s, double c){

        this.background_X = new double[d];
        this.background_Y = new double[d];
        this.background_speed = s;
        this.background_count = c;
    }

    public void inicializaBackground(Background b) {

        for (int i = 0; i < b.getBackground_X().length; i++) {

            b.setBackground_X(i,Math.random() * GameLib.WIDTH);
            b.setBackground_Y(i, Math.random() * GameLib.HEIGHT);
        }
    }

    public double [] getBackground_X(){
        return background_X;
    }

    public double getBackGroundX_value(int i){
        return background_X[i];
    }

    public double getBackGroundY_value(int i){
        return background_Y[i];
    }

    public double getBackground_count() {
        return background_count;
    }

    public double getBackground_speed() {
        return background_speed;
    }

    public void setBackground_count(double c){
        background_count = c;
    }

    void setBackground_X(int i, double d){
        background_X[i] = d;
    }

    void setBackground_Y(int i, double d){
        background_Y[i] = d;
    }

    public static void DesenhaBackGround(Background background1, Background background2){

        /* desenhando plano fundo distante */

        desenhaAux(background2, 2, Color.DARK_GRAY);

        /* desenhando plano de fundo próximo */

        desenhaAux(background1, 3, Color.GRAY);
    }

    static void desenhaAux(Background b, int d, Color cor){

        GameLib.setColor(cor);
        b.setBackground_count(b.getBackground_count() + (b.getBackground_speed() * Utilidades.getDelta()));

        for(int i = 0; i < b.getBackground_X().length; i++){

            GameLib.fillRect(b.getBackGroundX_value(i), (b.getBackGroundY_value(i) + b.getBackground_count()) % GameLib.HEIGHT, d, d);
        }
    }

}
