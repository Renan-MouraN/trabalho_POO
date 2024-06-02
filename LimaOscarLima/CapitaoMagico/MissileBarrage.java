package LimaOscarLima.CapitaoMagico;

import LimaOscarLima.BaraoVermelho.PlayerShot;

import java.util.Arrays;

public class MissileBarrage extends PlayerShot {
    double enemyShot_radius;
    
    public MissileBarrage(int d, double c){
        super(d);
        this.enemyShot_radius = c;

        Arrays.fill(getStates(), 0);
    }
    public double getRadius() {
        return enemyShot_radius;
    }

}
