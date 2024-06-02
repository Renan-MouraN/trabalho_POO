package LimaOscarLima.BaraoVermelho;

import LimaOscarLima.Interfaces.Interfaces_Bonecos;

import java.util.Arrays;

public class PlayerShot implements Interfaces_Bonecos{
    private int [] playerShot_states;
    private double [] playerShot_X;
    private double [] playerShot_Y;
    private double [] playerShot_VX;
    private double [] playerShot_VY;


    public PlayerShot(int d){
        this.playerShot_states = new int[d];
        this.playerShot_X = new double[d];
        this.playerShot_Y = new double[d];
        this.playerShot_VX = new double[d];
        this.playerShot_VY = new double[d];

        Arrays.fill(this.playerShot_states, 0);

    }


    public int [] getStates(){
        return playerShot_states;
    }

    public double getX(int i){
        return playerShot_X[i];
    }

    public double getY(int i){
        return playerShot_Y[i];
    }

    public int getStates_value(int i){
        return playerShot_states[i];
    }

    public double getplayerShotVX(int i){
        return playerShot_VX[i];
    }

    public double getplayerShotVY(int i){
        return playerShot_VY[i];
    }

    public void setplayerShotVX(int i, double d){
        playerShot_VX[i] = d;
    }

    public void setplayerShotVY(int i, double d){
        playerShot_VY[i] = d;
    }

    public void setStates_value(int i, int d){
        playerShot_states[i] = d;
    }

    public void setX(int i, double d) {
        playerShot_X[i] = d;
    }

    public void setY(int i, double d) {
        playerShot_Y[i] = d;
    }



}

