package LimaOscarLima.BaraoVermelho;

import LimaOscarLima.Interfaces;

public class Projectile implements Interfaces {
    private int [] projectile_states;
    private double [] projectile_X;
    private double [] projectile_Y;
    private double [] projectile_VX;
    private double [] projectile_VY;


    public Projectile(int d){
        this.projectile_states = new int[d];
        this.projectile_X = new double[d];
        this.projectile_Y = new double[d];
        this.projectile_VX = new double[d];
        this.projectile_VY = new double[d];

    }

    public void setStates (int i, int d){
        projectile_states[i] = d;
    }

    public int getStates_length(){
        return projectile_states.length;
    }

    public double getX(int i){
        return projectile_X[i];
    }

    public double getY(int i){
        return projectile_Y[i];
    }

    public int getStates(int i){ return projectile_states[i];}

    public double getProjectileVX(int i){
        return this.projectile_VX[i];
    }

    public double getProjectileVY(int i){
        return this.projectile_VY[i];
    }

    public void setX(int i, double salsicha) {
        this.projectile_X[i] = salsicha;
    }

    public void setY(int i, double salsicha) {
        this.projectile_Y[i] = salsicha;
    }

}

