package LimaOscarLima.gameObjects.multipleEntities;

import java.util.ArrayList;

public class enemy3 extends LimaOscarLima.gameObjects.multipleEntities.enemies {

    private final ArrayList<Long> enemies_nextShoot;

    public enemy3(double enemies_radius, long enemiesSpawnTime) {
        super(enemies_radius, enemiesSpawnTime);

        this.enemies_nextShoot = new ArrayList<>();
    }

    public long getNextShot(int i){ return enemies_nextShoot.get(i); }

    public void setNext_Shoot(int i, long l){ enemies_nextShoot.set(i, l); }

    public void addNext_Shoot(long l){ enemies_nextShoot.add(l);}
}
