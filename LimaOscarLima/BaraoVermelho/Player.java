package LimaOscarLima.BaraoVermelho;

public class Player {

    private int character_state;
    private double character_X;
    private double character_Y;
    private double character_VX;
    private double character_VY;
    private double character_radius;
    private double character_explosion_start;
    private double character_explosion_end;
    private long character_nextShot;

    public Player(int character_state, double character_X, double character_Y, double character_VX, double character_VY,
                  double character_radius, double character_explosion_start, double character_explosion_end, long character_nextShot) {
        this.character_state = character_state;
        this.character_X = character_X;
        this.character_Y = character_Y;
        this.character_VX = character_VX;
        this.character_VY = character_VY;
        this.character_radius = character_radius;
        this.character_explosion_start = character_explosion_start;
        this.character_explosion_end = character_explosion_end;
        this.character_nextShot = character_nextShot;

    }

    public int getCharacter_state(){
        return character_state;
    }

    public double getCharacter_X(){
        return character_X;
    }

    public double getCharacter_Y(){
        return character_Y;
    }

    public double getCharacter_VX(){
        return character_VX;
    }

    public double getCharacter_VY(){
        return character_VY;
    }

    public double getCharacter_radius(){
        return character_radius;
    }

    public double getCharacter_explosion_start(){
        return character_explosion_start;
    }

    public double getCharacter_explosion_end(){
        return character_explosion_end;
    }

    public long getCharacter_nextShot(){
        return character_nextShot;
    }

    public void setCharacter_X(double d){
        character_X = d;
    }

    public void setCharacter_Y(double d){
        character_Y = d;
    }

    public void setCharacter_state(int character_state){
        this.character_state = character_state;
    }

    public void setCharacter_explosion_start(double character_explosion_start){
        this.character_explosion_start = character_explosion_start;
    }

    public void setCharacter_explosion_end(double character_explosion_end){
        this.character_explosion_end = character_explosion_end;
    }

    public void setCharacter_nextShot(long l){
        character_nextShot = l;
    }

}


