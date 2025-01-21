package robots.objetos;

import robots.mapa.Tabuleiro;
import robots.robos.Robo;

public class Rocha extends Obstaculo {
    public Rocha() {
        super(2);
    }

    @Override
    public boolean bater(Robo robo, Tabuleiro tabuleiro) {
        if(robo.getxPos() == x && robo.getyPos() == y){
            System.out.println("O robô " + robo.getCor() + " bateu em uma rocha e voltou para trás.");
            robo.setxPos(robo.getLastxPos());
            robo.setyPos(robo.getLastyPos());
            return false;
        }
        return false;
    }
}