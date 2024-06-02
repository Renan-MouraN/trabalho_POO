package LimaOscarLima.CapitaoMagico;

import LimaOscarLima.BaraoVermelho.PlayerShot;

import java.util.Arrays;

public class EnemyShot extends PlayerShot {
    double enemyShot_radius;
    
    public EnemyShot(int d, double c){
        super(d);
        this.enemyShot_radius = c;

        Arrays.fill(getStates(), 0);
    }
    public double getRadius() {
        return enemyShot_radius;
    }

}
