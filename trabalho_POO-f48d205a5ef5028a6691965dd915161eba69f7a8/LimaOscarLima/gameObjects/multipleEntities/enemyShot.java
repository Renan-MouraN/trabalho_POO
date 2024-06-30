package LimaOscarLima.gameObjects.multipleEntities;

import LimaOscarLima.gameObjects.gameObject;

public class enemyShot extends shot {

    private final double enemyShotRadius;
    
    public enemyShot(double c){
        super();
        this.enemyShotRadius = c;

    }
    public double getRadius() {
        return enemyShotRadius;
    }

    public void addEnemyShot(int state, double x, double y, double VX, double VY){
        getArray().add(new gameObject(state, x, y, enemyShotRadius, VX, VY));
    }
}
