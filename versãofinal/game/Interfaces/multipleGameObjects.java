package game.Interfaces;

import game.gameObjects.gameObject;
import java.util.ArrayList;

public interface multipleGameObjects {

    ArrayList<gameObject> getArray();
    int getStateValue(int i);
    double getX(int i);
    double getY(int i);
    double getVX(int i);
    double getVY(int i);

    void addNewElement(int state, double x, double y, double VX, double VY);
    void setStateValue(int i, int x);
    void setX(int i, double x);
    void setY(int i, double y);
    void setVX(int i, double vx);
    void setVY(int i, double vy);
}
