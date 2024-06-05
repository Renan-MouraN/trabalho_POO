package LimaOscarLima.gameObjects.singleEntities;

import LimaOscarLima.gameObjects.gameObject;

public class player {

    private final gameObject state;
    private double player_explosion_start;
    private double player_explosion_end;
    private long player_nextShot;

    public player(int player_state, double player_X, double player_Y, double player_VX, double player_VY,
                  double player_radius, double player_explosion_start, double player_explosion_end, long player_nextShot) {

        this.state = new gameObject(player_state, player_X, player_Y, player_radius, player_VX, player_VY);
        this.player_explosion_start = player_explosion_start;
        this.player_explosion_end = player_explosion_end;
        this.player_nextShot = player_nextShot;

    }

    public int getplayer_state(){
        return state.getState();
    }
    public double getplayer_X(){
        return state.getX();
    }
    public double getplayer_Y(){
        return state.getY();
    }
    public double getplayer_VX(){
        return state.getVX();
    }
    public double getplayer_VY(){
        return state.getVY();
    }
    public double getplayer_radius(){
        return state.getRadius();
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
        state.setX(d);
    }
    public void setplayer_Y(double d){
        state.setY(d);
    }
    public void setplayer_state(int player_state){
        state.setState(player_state);
    }
    public void setplayer_explosion_start(double player_explosion_start){ this.player_explosion_start = player_explosion_start; }
    public void setplayer_explosion_end(double player_explosion_end){ this.player_explosion_end = player_explosion_end; }
    public void setplayer_nextShot(long l){
        player_nextShot = l;
    }

}


