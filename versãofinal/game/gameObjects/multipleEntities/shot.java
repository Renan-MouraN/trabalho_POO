package game.gameObjects.multipleEntities;

import game.gameObjects.gameObject;

import java.util.ArrayList;

public class shot implements game.Interfaces.multipleGameObjects{

    private final ArrayList<gameObject> shots;

    public shot(){

        shots = new ArrayList<>();

    }


    public ArrayList<gameObject> getArray(){
        return shots;
    }
    public int getStateValue(int i){ return shots.get(i).getState(); }
    public double getX(int i){
        return shots.get(i).getX();
    }
    public double getY(int i){
        return shots.get(i).getY();
    }
    public double getVX(int i){
        return shots.get(i).getVX();
    }
    public double getVY(int i){
        return shots.get(i).getVY();
    }


    public void addNewElement(int state, double x, double y, double VX, double VY){
        shots.add(new gameObject(state,  x,  y,0,  VX, VY));
    }
    public void setStateValue(int i, int d){
        shots.get(i).setState(d);
    }
    public void setX(int i, double d) {
        shots.get(i).setX(d);
    }
    public void setY(int i, double d) {
        shots.get(i).setY(d);
    }
    public void setVX(int i, double d){
        shots.get(i).setVX(d);
    }
    public void setVY(int i, double d){
        shots.get(i).setVY(d);
    }

}

