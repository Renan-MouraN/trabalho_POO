package LimaOscarLima.gameObjects.multipleEntities;

public class enemy3 extends LimaOscarLima.gameObjects.multipleEntities.enemies {

    private long enemy3_shootTime;
    private int enemy3_phase;

    public enemy3(double enemies_radius, long nextEnemies, int enemy3_phase, long enemy3_shootTime){
        super(enemies_radius, nextEnemies);

        this.enemy3_phase = enemy3_phase;
        this.enemy3_shootTime = enemy3_shootTime;

    }

    public int getEnemy3_phase(){
        return enemy3_phase;
    }
    public long getShootTime(){
        return  enemy3_shootTime;
    }

    public void setEnemy3_phase(int i){
        enemy3_phase = i;
    }
}
