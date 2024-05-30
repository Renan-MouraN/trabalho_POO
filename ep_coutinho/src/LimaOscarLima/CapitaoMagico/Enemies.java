package LimaOscarLima.CapitaoMagico;

import LimaOscarLima.Interfaces;
import LimaOscarLima.Interfaces_campeoes;

public class Enemies implements Interfaces_campeoes {
    private int [] enemies_states;						// estados
    private double [] enemies_X;					// coordenadas x
    private double [] enemies_Y;					// coordenadas y
    private double [] enemies_V;					// velocidades
    private double [] enemies_angle;				// ângulos (indicam direção do movimento)
    private double [] enemies_RV;					// velocidades de rotação
    private double [] enemies_explosion_start;		// instantes dos inícios das explosões
    private double [] enemies_explosion_end;		// instantes dos finais da explosões
    private long [] enemies_nextShoot;				// instantes do próximo tiro
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

    }

    public void setStates (int i, int d){
        enemies_states[i] = d;
    }

    public int getStates_length(){
        return enemies_states.length;
    }

    public double getX(int i){
        return enemies_X[i];
    }

    public double getY(int i){
        return enemies_Y[i];
    }

    public double getV(int i){
        return this.enemies_V[i];
    }

    public double getAngle(int i){
        return this.enemies_angle[i];
    }

    public double getRV(int i){
        return this.enemies_RV[i];
    }

    public int getStates(int i){ return enemies_states[i];}

    public double getRadius (){return enemies_radius;}

    public double getExplosion_start(int i){
        return this.enemies_explosion_start[i];
    }

    public double getExplosion_end(int i){
        return this.enemies_explosion_end[i];
    }

    public long getNextShot(int i){
        return this.enemies_nextShoot[i];
    }

    public void setX(int i, double salsicha) {
        this.enemies_X[i] = salsicha;
    }

    public void setY(int i, double salsicha) {
        this.enemies_Y[i] = salsicha;
    }

    public void setAngle(int i, double endrick){
        this.enemies_angle[i] = endrick;
    }

    public void setExplosion_start(int i, long l){
        enemies_explosion_start[i] = l;
    }

    public void setExplosion_end(int i, long l){
        enemies_explosion_end[i] = l;
    }
}

