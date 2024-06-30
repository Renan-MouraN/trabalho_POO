package LimaOscarLima.gameObjects.multipleEntities;


import LimaOscarLima.gameObjects.gameObject;
import java.util.ArrayList;


public class enemy3 extends enemies {

    private ArrayList<Long> nextShootTime;
    private ArrayList<Double> targetX;
    private ArrayList<Double> targetY;
    private ArrayList<Double> secondTargetX;
    private ArrayList<Double> secondTargetY;
    private ArrayList<Boolean> atFirstTarget;

    public enemy3(double enemies_radius, long enemiesSpawnTime) {
        super(enemies_radius, enemiesSpawnTime);
        this.nextShootTime = new ArrayList<>();
        this.targetX = new ArrayList<>();
        this.targetY = new ArrayList<>();
        this.secondTargetX = new ArrayList<>();
        this.secondTargetY = new ArrayList<>();
        this.atFirstTarget = new ArrayList<>();
    }

    @Override
    public void addNewElement(int state, double x, double y, double vx, double vy) {
        super.addNewElement(state, x, y, vx, vy);
        this.nextShootTime.add(0L);
        this.targetX.add(0.0);
        this.targetY.add(0.0);
        this.secondTargetX.add(0.0);
        this.secondTargetY.add(0.0);
        this.atFirstTarget.add(true);
    }

    public Long getNextShootTime(int index) {
        return nextShootTime.get(index);
    }

    public void setNextShootTime(int index, Long nextShootTime) {
        this.nextShootTime.set(index, nextShootTime);
    }

    public Double getTargetX(int index) {
        return targetX.get(index);
    }

    public void setTargetX(int index, Double targetX) {
        this.targetX.set(index, targetX);
    }

    public Double getTargetY(int index) {
        return targetY.get(index);
    }

    public void setTargetY(int index, Double targetY) {
        this.targetY.set(index, targetY);
    }

    public Double getSecondTargetX(int index) {
        return secondTargetX.get(index);
    }

    public void setSecondTargetX(int index, Double secondTargetX) {
        this.secondTargetX.set(index, secondTargetX);
    }

    public Double getSecondTargetY(int index) {
        return secondTargetY.get(index);
    }

    public void setSecondTargetY(int index, Double secondTargetY) {
        this.secondTargetY.set(index, secondTargetY);
    }

    public Boolean getAtFirstTarget(int index) {
        return atFirstTarget.get(index);
    }

    public void setAtFirstTarget(int index, Boolean atFirstTarget) {
        this.atFirstTarget.set(index, atFirstTarget);
    }
}