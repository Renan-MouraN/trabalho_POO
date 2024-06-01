package LimaOscarLima.CapitaoMagico;

import LimaOscarLima.BaraoVermelho.Projectile;

import java.util.Arrays;

public class MissileBarrage extends Projectile{
    double e_projectile_radius;
    
    public MissileBarrage(int d, double c){
        super(d);
        this.e_projectile_radius = c;

        Arrays.fill(getStates(), 0);
    }
    public double getRadius() {
        return e_projectile_radius;
    }

}
