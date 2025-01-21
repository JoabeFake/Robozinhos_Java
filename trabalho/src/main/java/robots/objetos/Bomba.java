package robots.objetos;

import robots.mapa.Tabuleiro;
import robots.robos.Robo;

public class Bomba extends Obstaculo {
    public Bomba() {
        super(1);
    }

    @Override
    public boolean bater(Robo robo, Tabuleiro tabuleiro) {
        if(robo.getxPos() == x && robo.getyPos() == y){
            System.out.println("BUM! O robô " + robo.getCor() + " explodiu!");
            // Simula a explosão do robô (removendo-o do jogo)
            robo.setxPos(-1); // Define uma posição inválida para indicar que o robô explodiu
            robo.setyPos(-1);
            
            return true;
        }
        return false;
    }
}