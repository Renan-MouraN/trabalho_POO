package LimaOscarLima.UrfRider;

public class Background {
    private double [] background_X;
    private double [] background_Y;
    private double background_speed;
    private double background_count;

    public Background(int d, double s, double c){
        this.background_X = new double[d];
        this.background_Y = new double[d];
        this.background_speed = s;
        this.background_count = c;
    }
    
    public double [] getBackground_X(){
        return background_X;
    }

    public double getBackGroundX_value(int i){
        return background_X[i];
    }

    public double getBackGroundY_value(int i){
        return background_Y[i];
    }

    public double getBackground_count() {
        return background_count;
    }

    public double getBackground_speed() {
        return background_speed;
    }

    public void setX_value (int i, double d){
        background_X[i] = d;
    }

    public void setY_value (int i, double d){
        background_Y[i] = d;
    }

    public void setBackground_count(double c){
        background_count = c;
    }
}
