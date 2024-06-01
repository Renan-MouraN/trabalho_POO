package LimaOscarLima.CapitaoMagico;
import LimaOscarLima.Interfaces.Interfaces_campeoes;
import LimaOscarLima.Interfaces.Locate;

import java.util.Arrays;

public class Enemies implements Interfaces_campeoes, Locate{
    private int [] enemies_states;						    // estados
    private double [] enemies_X;					        // coordenadas x
    private double [] enemies_Y;					        // coordenadas y
    private double [] enemies_V;					        // velocidades
    private double [] enemies_angle;				        // ângulos (indicam direção do movimento)
    private double [] enemies_RV;					        // velocidades de rotação
    private double [] enemies_explosion_start;		        // instantes dos inícios das explosões
    private double [] enemies_explosion_end;		        // instantes dos finais da explosões
    private long [] enemies_nextShoot;				        // instantes do próximo tiro
    private double enemies_radius;							// raio (tamanho do inimigo 1)
    private long nextEnemies;
    
    public Enemies(int d, double enemies_radius, long nextEnemies){

        this.enemies_states = new int [d];
        this.enemies_X = new double[d];
        this.enemies_Y = new double[d];
        this.enemies_V = new double[d];
        this.enemies_angle = new double[d];
        this.enemies_RV = new double[d];
        this.enemies_explosion_start = new double[d];
        this.enemies_explosion_end = new double[d];
        this.enemies_nextShoot = new long[d];
        this.enemies_radius = enemies_radius;
        this.nextEnemies = nextEnemies;

        Arrays.fill(this.enemies_states, 0);

    }

    public int [] getStates(){
        return enemies_states;
    }

    public int getStates_value(int i){
        return enemies_states[i];
    }

    public double getX(int i){
        return enemies_X[i];
    }

    public double getY(int i){
        return enemies_Y[i];
    }

    public double getV(int i){
        return enemies_V[i];
    }

    public double getAngle(int i){
        return enemies_angle[i];
    }

    public double getRV(int i){
        return enemies_RV[i];
    }

    public double getRadius (){
        return enemies_radius;
    }

    public double getExplosion_start(int i){
        return enemies_explosion_start[i];
    }

    public double getExplosion_end(int i){
        return enemies_explosion_end[i];
    }

    public long getNextShot(int i){
        return enemies_nextShoot[i];
    }

    public long getNextEnemies(){
        return nextEnemies;
    }

    public void setStates_value(int i, int d){
        enemies_states[i] = d;
    }

    public void setX(int i, double d) {
        enemies_X[i] = d;
    }

    public void setY(int i, double d) {
        enemies_Y[i] = d;
    }

    public void setAngle(int i, double d){
        this.enemies_angle[i] = d;
    }

    public void setExplosion_start(int i, long l){
        enemies_explosion_start[i] = l;
    }

    public void setExplosion_end(int i, long l){
        enemies_explosion_end[i] = l;
    }

    public void setNext_Shoot(int i, long l){
        enemies_nextShoot[i] = l;
    }

    public void setRV(int i, double d){
        enemies_RV[i] = d;
    }

    public void setV(int i, double d){
        enemies_V[i] = d;
    }

    public void setNextEnemies(long l){
        nextEnemies = l;
    }

}

