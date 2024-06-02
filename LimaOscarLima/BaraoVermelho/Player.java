package LimaOscarLima.BaraoVermelho;

public class Player {

    private int player_state;
    private double player_X;
    private double player_Y;
    private double player_VX;
    private double player_VY;
    private double player_radius;
    private double player_explosion_start;
    private double player_explosion_end;
    private long player_nextShot;

    public Player(int player_state, double player_X, double player_Y, double player_VX, double player_VY,
                  double player_radius, double player_explosion_start, double player_explosion_end, long player_nextShot) {
        this.player_state = player_state;
        this.player_X = player_X;
        this.player_Y = player_Y;
        this.player_VX = player_VX;
        this.player_VY = player_VY;
        this.player_radius = player_radius;
        this.player_explosion_start = player_explosion_start;
        this.player_explosion_end = player_explosion_end;
        this.player_nextShot = player_nextShot;

    }

    public int getplayer_state(){
        return player_state;
    }

    public double getplayer_X(){
        return player_X;
    }

    public double getplayer_Y(){
        return player_Y;
    }

    public double getplayer_VX(){
        return player_VX;
    }

    public double getplayer_VY(){
        return player_VY;
    }

    public double getplayer_radius(){
        return player_radius;
    }

    public double getplayer_explosion_start(){
        return player_explosion_start;
    }

    public double getplayer_explosion_end(){
        return player_explosion_end;
    }

    public long getplayer_nextShot(){
        return player_nextShot;
    }

    public void setplayer_X(double d){
        player_X = d;
    }

    public void setplayer_Y(double d){
        player_Y = d;
    }

    public void setplayer_state(int player_state){
        this.player_state = player_state;
    }

    public void setplayer_explosion_start(double player_explosion_start){
        this.player_explosion_start = player_explosion_start;
    }

    public void setplayer_explosion_end(double player_explosion_end){
        this.player_explosion_end = player_explosion_end;
    }

    public void setplayer_nextShot(long l){
        player_nextShot = l;
    }

}


