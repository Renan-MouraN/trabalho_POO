package LimaOscarLima.BaraoVermelho;

import LimaOscarLima.Interfaces.Interfaces_Bonecos;

import java.util.Arrays;

public class Projectile implements Interfaces_Bonecos {
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

    public void inicializaClasse(){
        Arrays.fill(this.projectile_states, 0);
    }

    public int [] getStates(){
        return projectile_states;
    }

    public double getX(int i){
        return projectile_X[i];
    }

    public double getY(int i){
        return projectile_Y[i];
    }

    public int getStates_value(int i){
        return projectile_states[i];
    }

    public double getProjectileVX(int i){
        return projectile_VX[i];
    }

    public double getProjectileVY(int i){
        return projectile_VY[i];
    }

    public void setProjectileVX(int i, double d){
        projectile_VX[i] = d;
    }

    public void setProjectileVY(int i, double d){
        projectile_VY[i] = d;
    }

    public void setStates_value(int i, int d){
        projectile_states[i] = d;
    }

    public void setX(int i, double d) {
        projectile_X[i] = d;
    }

    public void setY(int i, double d) {
        projectile_Y[i] = d;
    }



}

