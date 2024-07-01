package game.gameObjects.multipleEntities;

public class enemy2 extends game.gameObjects.multipleEntities.enemies {

    private double enemy2_spawnX;
    private int enemy2_count;

    public enemy2(double enemies_radius, long nextEnemies, double enemy2_spawnX, int enemy2_count){
        super(enemies_radius, nextEnemies);

        this.enemy2_spawnX = enemy2_spawnX;
        this.enemy2_count = enemy2_count;

    }

    public double getEnemy2_spawnX(){
        return enemy2_spawnX;
    }
    public int getEnemy2_count(){
        return enemy2_count;
    }


    public void setEnemy2_spawnX(double d){
        enemy2_spawnX = d;
    }
    public void setEnemy2_count(int v){
        enemy2_count = v;
    }
    public void addCount() {
        enemy2_count++;
    }
}
