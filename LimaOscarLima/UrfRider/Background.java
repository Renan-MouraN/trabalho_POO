package LimaOscarLima.UrfRider;

import LimaOscarLima.GameLib.GameLib;
import LimaOscarLima.Interfaces.Interface;

import java.awt.*;
import java.util.Arrays;

public class Background implements Interface {
    private double [] background_X;
    private double [] background_Y;
    private double background_speed;
    private double background_count;

    public static long currentTime = System.currentTimeMillis();
    static long delta = System.currentTimeMillis() - currentTime;

    public Background(int d, double s, double c){
        this.background_X = new double[d];
        this.background_Y = new double[d];
        this.background_speed = s;
        this.background_count = c;

        Arrays.fill(this.background_X, Math.random() * GameLib.WIDTH);
        Arrays.fill(this.background_Y, Math.random() * GameLib.HEIGHT);

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

    public void setX_value (int i, double d){
        background_X[i] = d;
    }

    public void setY_value (int i, double d){
        background_Y[i] = d;
    }

    public void setBackground_count(double c){
        background_count = c;
    }

    public static void DesenhaBackGround(Background background1, Background background2){

        /* desenhando plano fundo distante */

        GameLib.setColor(Color.DARK_GRAY);
        background2.setBackground_count(background2.getBackground_count() + (background1.getBackground_speed() * delta));

        desenhaAux(background2, 2);

        /* desenhando plano de fundo próximo */

        GameLib.setColor(Color.GRAY);
        background1.setBackground_count(background2.getBackground_count() + (background2.getBackground_speed() * delta));

        desenhaAux(background1, 3);
    }

    static void desenhaAux(Background b, int d){

        for(int i = 0; i < b.getBackground_X().length; i++){

            GameLib.fillRect(b.getBackGroundX_value(i), (b.getBackGroundY_value(i) + b.getBackground_count()) % GameLib.HEIGHT, d, d);
        }
    }

}
