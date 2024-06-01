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
    
    public int getX_length(){
        return background_X.length;
    }

    public void setX_value (int i, double d){
        background_X[i] = d;
    }

    public void setY_value (int i, double d){
        background_Y[i] = d;
    }
}
