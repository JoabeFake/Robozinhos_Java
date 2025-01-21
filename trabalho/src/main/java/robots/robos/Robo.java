package robots.robos;

import robots.excecoes.MovimentoInvalidoException;
import robots.mapa.Tabuleiro;

public class Robo {
    protected int xPos;
    protected int yPos;
    protected int lastxPos;
    protected int lastyPos;
    protected String cor;
    protected int validMov;
    protected int invalidMov;
    protected boolean foundFood;
    protected boolean isDead;

    // Construtor que inicializa o robô na posição (0,0) e define a cor
    public Robo(String cor) {
        this.xPos = 0;
        this.yPos = 0;
        this.cor = cor;
        this.validMov = 0;
        this.invalidMov = 0;
        this.foundFood = false;
        this.isDead = false;
    }

    // Métodos getters e setters
    public int getxPos() {
        return xPos;
    }

    public void setxPos(int x) {
        this.xPos = x;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int y) {
        this.yPos = y;
    }

    public int getLastxPos(){
        return lastxPos;
    }

    public int getLastyPos(){
        return lastyPos;
    }

    public String getCor() {
        return cor;
    }

    public int getValidMov(){
        return validMov;
    }

    public int getInvalidMov(){
        return invalidMov;
    }

    public void setIsDead(boolean isDead){
        this.isDead = isDead;
    }

    public boolean getIsDead(){
        return this.isDead;
    }

    //movimentação
    public void mover(String direcao, Tabuleiro tabuleiro) throws MovimentoInvalidoException {
        int xAntes = xPos;
        int yAntes = yPos;
        
        switch (direcao) {
            case "up":
                if (yPos + 1 >= 0 && yPos + 1 < tabuleiro.getTamanho()) {
                    yPos++;
                    validMov++;
                } else {
                    invalidMov++;
                    throw new MovimentoInvalidoException("up");
                }
                break;
            case "down":
                if (yPos - 1 >= 0) {
                    yPos--;
                    validMov++;
                } else {
                    invalidMov++;
                    throw new MovimentoInvalidoException("down");
                }
                break;
            case "right":
                if (xPos + 1 >= 0 && xPos + 1 < tabuleiro.getTamanho()) {
                    xPos++;
                    validMov++;
                } else {
                    invalidMov++;
                    throw new MovimentoInvalidoException("right");
                }
                break;
            case "left":
                if (xPos - 1 >= 0) {
                    xPos--;
                    validMov++;
                } else {
                    invalidMov++;
                    throw new MovimentoInvalidoException("left");
                }
                break;
            default:
                throw new MovimentoInvalidoException(direcao);
        }

        lastxPos = xAntes;
        lastyPos = yAntes;
        
        System.out.println("Posição Robô " + this.cor + ": (" + xPos + ", " + yPos + ")");
        return;
    }

    public void mover(int direcao, Tabuleiro tabuleiro) throws MovimentoInvalidoException {
        switch (direcao) {
            case 1:
                mover("up", tabuleiro);
                break;
            case 2:
                mover("down", tabuleiro);
                break;
            case 3:
                mover("right", tabuleiro);
                break;
            case 4:
                mover("left", tabuleiro);
                break;
            default:
                throw new MovimentoInvalidoException("Direção inválida");
        }
    }

    public boolean encontrouAlimento(int xAlimento, int yAlimento) {
        return xPos == xAlimento && yPos == yAlimento;
    }

    public boolean bateuObstaculo(int xAlimento, int yAlimento) {
        return xPos == xAlimento && yPos == yAlimento;
    }
}