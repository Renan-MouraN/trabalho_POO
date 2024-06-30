package LimaOscarLima.gameObjects.multipleEntities;

import LimaOscarLima.gameObjects.gameObject;

import java.util.ArrayList;

public class enemies implements LimaOscarLima.Interfaces.multipleGameObjects{

    private final ArrayList<gameObject> enemies ;

    private final ArrayList<Double> enemies_angle;				                // ângulos (indicam direção do movimento)
    private final ArrayList<Double> enemies_RV;					                // velocidades de rotação
    private final ArrayList<Double> enemies_explosion_start;		            // instantes dos inícios das explosões
    private final ArrayList<Double> enemies_explosion_end;		                // instantes dos finais da explosões
    private final double enemies_radius;
    private long enemiesSpawnTime;
    
    public enemies(double enemies_radius, long enemiesSpawnTime){

        this.enemies = new ArrayList<>();
        this.enemies_angle = new ArrayList<>();
        this.enemies_RV = new ArrayList<>();
        this.enemies_explosion_start = new ArrayList<>();
        this.enemies_explosion_end = new ArrayList<>();
        this.enemies_radius = enemies_radius;
        this.enemiesSpawnTime = enemiesSpawnTime;


    }

    public ArrayList<gameObject> getArray(){
        return enemies;
    }
    public int getStateValue(int i){ return enemies.get(i).getState(); }
    public double getX(int i){
        return enemies.get(i).getX();
    }
    public double getY(int i){
        return enemies.get(i).getY();
    }
    public double getVY(int i){
        return enemies.get(i).getVY();
    }
    public double getVX(int i){ return enemies.get(i).getVX(); }
    public double getAngle(int i){
        return enemies_angle.get(i);
    }
    public double getRV(int i){
        return enemies_RV.get(i);
    }
    public double getRadius (){
        return enemies_radius;
    }
    public double getExplosion_start(){
        return enemies_explosion_start.getLast();
    }
    public double getExplosion_end(){ return enemies_explosion_end.getLast(); }
    public long getEnemiesSpawnTime(){
        return enemiesSpawnTime;
    }


    public void addNewElement(int state, double x, double y, double VX, double VY){ enemies.add(new gameObject(state, x, y, enemies_radius, VX, VY)); }
    public void removeElement(int i){
        enemies.remove(i);
    }
    public void setStateValue(int i, int d){
        enemies.get(i).setState(d);
    }
    public void setX(int i, double d) {
        enemies.get(i).setX(d);
    }
    public void setY(int i, double d) {
        enemies.get(i).setY(d);
    }
    public void setAngle(int i, double d){
        enemies_angle.set(i, d);
    }
    public void setRV(int i, double d){
        enemies_RV.set(i, d);
    }
    public void setVY(int i, double d){ enemies.get(i).setVY(d); }
    public void setVX(int i, double d){ enemies.get(i).setVX(d); }
    public void setEnemiesSpawnTime(long l){
        enemiesSpawnTime = l;
    }

    public void addAngle(double d) {enemies_angle.add(d);}
    public void addRV(double d) {enemies_RV.add(d);}
    public void addExplosion_start(double d) {enemies_explosion_start.add(d);}
    public void addExplosion_end(double d){enemies_explosion_end.add(d);}
}

