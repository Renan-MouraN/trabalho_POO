package LimaOscarLima.CapitaoMagico;

public class EnemyJR extends Enemies {

    double enemyJR_spawnX;			
    int enemyJR_count;

    public EnemyJR (int d, double enemies_radius, long nextEnemies, double enemyJR_spawnX, int enemyJR_count){
        super(d, enemies_radius, nextEnemies);
        this.enemyJR_spawnX = enemyJR_spawnX;
        this.enemyJR_count = enemyJR_count;

    }

}
