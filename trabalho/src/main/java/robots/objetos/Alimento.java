package robots.objetos;

public class Alimento {
    private int xPos;
    private int yPos;

    public Alimento(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }

    //Getters e Setters
    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }
}
