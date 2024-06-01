package LimaOscarLima.GarenR;

import LimaOscarLima.BaraoVermelho.Projectile;
import LimaOscarLima.CapitaoMagico.*;
import LimaOscarLima.UrfRider.Background;

public class Nautilus {

    public static Projectile inicializaProjetile(int d){
        return new Projectile(d);
    }

    public static Enemies inicializaEnemy(int d, double enemies_radius, long nextEnemies){
        return new Enemies(d, enemies_radius, nextEnemies);
    }

    public static EnemyJR inicializaEnemyJR(int d, double enemies_radius, long nextEnemies, double enemyJR_spawnX, int enemyJR_count){
        return new EnemyJR(d, enemies_radius, nextEnemies, enemyJR_spawnX, enemyJR_count);
    }

    public static MissileBarrage inicializaMissileBarrage(int d, double l){
        return new MissileBarrage(d, l);
    }

    public static Background inicializaBackground(int d, double s, double c){
        return new Background(d, s, c);
    }
}
