package LimaOscarLima.gameObjects.multipleEntities;

import LimaOscarLima.gameObjects.gameObject;

import java.util.ArrayList;

public class powerUp implements LimaOscarLima.Interfaces.multipleGameObjects{

    private final ArrayList<gameObject> powerUps;

    private final double radius;
    private long spawnTime;
    private long powerUpTime;

    public powerUp(double radius, long spawnTime){

        powerUps = new ArrayList<>();

        this.radius = radius;
        this.spawnTime = spawnTime;
    }


    public ArrayList<gameObject> getArray(){
        return powerUps;
    }
    public int getStateValue(int i){ return powerUps.get(i).getState(); }
    public double getX(int i){
        return powerUps.get(i).getX();
    }
    public double getY(int i){
        return powerUps.get(i).getY();
    }
    public double getVX(int i){
        return powerUps.get(i).getVX();
    }
    public double getVY(int i){
        return powerUps.get(i).getVY();
    }
    public double getRadius(){ return radius; }
    public long getSpawnTime(){ return spawnTime; }
    public long getPowerUpTime(){ return powerUpTime; }


    public void addNewElement(int state, double x, double y, double VX, double VY){
        powerUps.add(new gameObject(state,  x,  y,0,  VX, VY));
    }
    public void setStateValue(int i, int d){
        powerUps.get(i).setState(d);
    }
    public void setX(int i, double d) {
        powerUps.get(i).setX(d);
    }
    public void setY(int i, double d) {
        powerUps.get(i).setY(d);
    }
    public void setVX(int i, double d){
        powerUps.get(i).setVX(d);
    }
    public void setVY(int i, double d){
        powerUps.get(i).setVY(d);
    }
    public void setSpawnTime(long t){ spawnTime = t; }
    public void setPowerUpTime(long t){ powerUpTime = t; }
}
