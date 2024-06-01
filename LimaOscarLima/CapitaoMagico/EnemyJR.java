package LimaOscarLima.CapitaoMagico;

import java.util.Arrays;

public class EnemyJR extends Enemies {

    private double enemyJR_spawnX;
    private int enemyJR_count;

    public EnemyJR (int d, double enemies_radius, long nextEnemies, double enemyJR_spawnX, int enemyJR_count){
        super(d, enemies_radius, nextEnemies);
        this.enemyJR_spawnX = enemyJR_spawnX;
        this.enemyJR_count = enemyJR_count;

        Arrays.fill(getStates(), 0);
    }

    public double getEnemyJR_spawnX(){
        return enemyJR_spawnX;
    }

    public int getEnemyJR_count(){
        return enemyJR_count;
    }

    public void setEnemyJR_spawnX(double d){
        enemyJR_spawnX = d;
    }

    public void setEnemyJR_count(int v){
        enemyJR_count = v;
    }

    public void addCount() {
        enemyJR_count++;
    }
}
