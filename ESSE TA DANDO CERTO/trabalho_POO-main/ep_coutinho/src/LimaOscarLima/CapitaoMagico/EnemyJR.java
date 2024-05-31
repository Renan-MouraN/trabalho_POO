package LimaOscarLima.CapitaoMagico;

public class EnemyJR extends Enemies {

    double enemyJR_spawnX;			
    int enemyJR_count;

    public EnemyJR (int d, double enemies_radius, long nextEnemies, double enemyJR_spawnX, int enemyJR_count){
        super(d, enemies_radius, nextEnemies);
        this.enemyJR_spawnX = enemyJR_spawnX;
        this.enemyJR_count = enemyJR_count;

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
