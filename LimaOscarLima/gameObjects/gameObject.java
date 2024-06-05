package LimaOscarLima.gameObjects;

public class gameObject {

    private int state;
    private double x;
    private double y;
    private double radius;
    private double VX;
    private double VY;

    public gameObject(int state, double x, double y, double radius, double VX, double VY) {

        this.state = state;
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.VX = VX;
        this.VY = VY;
    }

    public int getState() {
        return state;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getRadius() {
        return radius;
    }
    public double getVX() {
        return VX;
    }
    public double getVY() {
        return VY;
    }


    public void setState(int state) {
        this.state = state;
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public void setRadius(double radius) {
        this.radius = radius;
    }
    public void setVX(double VX) {
        this.VX = VX;
    }
    public void setVY(double VY) {
        this.VY = VY;
    }
}
