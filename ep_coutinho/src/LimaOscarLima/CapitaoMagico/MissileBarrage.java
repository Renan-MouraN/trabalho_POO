package LimaOscarLima.CapitaoMagico;

import LimaOscarLima.BaraoVermelho.Projectile;

public class MissileBarrage extends Projectile {
    double e_projectile_radius;
    
    public MissileBarrage(int d, double c){
        super(d);
        this.e_projectile_radius = c;
    }
    public double getRadius() {
        return e_projectile_radius;
    }

}
