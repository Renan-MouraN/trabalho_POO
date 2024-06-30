package LimaOscarLima.gameObjects.multipleEntities;
import LimaOscarLima.gameObjects.gameObject;
import LimaOscarLima.Util.Utilidades;
import java.util.ArrayList;


public class PowerUp{

            private ArrayList<gameObject> powerUp;
            private double radius;
            private long spawnTime;

            public PowerUp(double radius) {
                powerUp = new ArrayList<>();
                this.radius = radius;
                this.spawnTime = Utilidades.getCurrentTime();
            }

            public ArrayList<gameObject> getArray(){
                return powerUp;
            }

            public double getX(int i) {
                return powerUp.get(i).getX();
            }

            public double getY(int i) {
                return powerUp.get(i).getY();
            }

            public double getRadius() {
                return radius;
            }

            public long getSpawnTime() {
                return spawnTime;
            }


            public int getStateValue(int i) {
            return powerUp.get(i).getState(); }
}