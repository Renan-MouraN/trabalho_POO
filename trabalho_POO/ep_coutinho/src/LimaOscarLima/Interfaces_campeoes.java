package LimaOscarLima;

public interface Interfaces_campeoes extends Interfaces{
    double getRadius ();
    double getV(int i);
    double getAngle(int i);
    double getRV(int i);
    double getExplosion_start(int i);
    double getExplosion_end(int i);
    long getNextShot(int i);
    void setAngle(int i,double endrick);
    void setExplosion_start(int i, long l);
    void setExplosion_end(int i, long l);
}
