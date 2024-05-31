package LimaOscarLima.Interfaces;

public interface Interfaces_campeoes extends Interfaces_Bonecos {
    double getRadius ();
    double getV(int i);
    double getAngle(int i);
    double getRV(int i);
    double getExplosion_start(int i);
    double getExplosion_end(int i);
    long getNextShot(int i);
    void setAngle(int i,double d);
    void setExplosion_start(int i, long l);
    void setExplosion_end(int i, long l);
    void setNext_Shoot(int i, long l);
    void setRV(int i, double d);
    void setV(int i, double d);
    void setNextEnemies(long l);
}
