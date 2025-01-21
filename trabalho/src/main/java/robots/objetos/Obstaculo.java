package robots.objetos;

import robots.mapa.Tabuleiro;
import robots.robos.Robo;

public abstract class Obstaculo {
    protected int id;
    protected int x;
    protected int y;

    public Obstaculo(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setxPos(int xPos){
        this.x = xPos;
    }

    public void setyPos(int yPos){
        this.y = yPos;
    }

    public int getxPos(){
        return this.x;
    }

    public int getyPos(){
        return this.y;
    }

    public abstract boolean bater(Robo robo, Tabuleiro tabuleiro);
}