package LimaOscarLima.GarenR;

import LimaOscarLima.BaraoVermelho.Projectile;
import LimaOscarLima.CapitaoMagico.*;
import LimaOscarLima.UrfRider.Background;

public class Nautilus {

    public static Projectile inicializaProjetile(int d){
        Projectile projectile = new Projectile(d);
        projectile.inicializaClasse();
        return projectile;
    };

    public static Enemies inicializaEnemy(int d, double enemies_radius, long nextEnemies){
        Enemies enemy = new Enemies(d, enemies_radius, nextEnemies);
        enemy.inicializaClasse();
        return enemy;
    };

    public static EnemyJR inicializaEnemyJR(int d, double enemies_radius, long nextEnemies, double enemyJR_spawnX, int enemyJR_count){
        EnemyJR enemy = new EnemyJR(d, enemies_radius, nextEnemies, enemyJR_spawnX, enemyJR_count);
        enemy.inicializaClasse();
        return enemy;
    };

    public static MissileBarrage inicializaMissileBarrage(int d, double l){
        MissileBarrage RdoCorki = new MissileBarrage(d, l);
        RdoCorki.inicializaClasse();
        return RdoCorki;
    }

    public static Background inicializaBackground(int d, double s, double c){
        Background fundo = new Background(d, s, c);
        fundo.inicializaClasse();
        return fundo;
    };
}
