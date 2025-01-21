package robots;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import robots.excecoes.MovimentoInvalidoException;
import robots.mapa.Tabuleiro;
import robots.objetos.Alimento;
import robots.robos.Robo;

public class MainTwoNormalRobots {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        Tabuleiro tabuleiro = new Tabuleiro(4);
        
        Robo robo1 = new Robo("Vermelho");
        Robo robo2 = new Robo("Azul");

        System.out.print("Digite a coordenada x do alimento (0 a " + (tabuleiro.getTamanho()-1) +  "): ");
        int xAlimento = scanner.nextInt();
        System.out.print("Digite a coordenada y do alimento (0 a " + (tabuleiro.getTamanho()-1) + "): ");
        int yAlimento = scanner.nextInt();
        
        Alimento alimento = new Alimento(xAlimento, yAlimento);

        tabuleiro.AdicionarAlimento(alimento);
        tabuleiro.AdicionarRobo(robo1);
        tabuleiro.AdicionarRobo(robo2);
        
        boolean encontrouAlimento = false;

        Robo roboAtual = robo1;

        while(!encontrouAlimento){
            roboAtual = roboAtual == robo1 ? robo2 : robo1;

            int direcao = random.nextInt(tabuleiro.getTamanho()) + 1;

            try{
                roboAtual.mover(direcao, tabuleiro);
                encontrouAlimento = roboAtual.encontrouAlimento(xAlimento, yAlimento);
            } catch(MovimentoInvalidoException e){
                System.out.println("Movimento Inválido: " + e.getMessage());
            }
            
            // System.out.print("\033[H\033[2J");
            // System.out.flush();
            tabuleiro.imprimirTabuleiro();

            try {
                TimeUnit.MILLISECONDS.sleep(200); // Pausa de 500 milissegundos (0.5 segundo)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("O robô " + roboAtual.getCor() + " encontrou o Alimento");
        
        System.out.println("O robô " + roboAtual.getCor() + " levou " + roboAtual.getValidMov() + " Movimentos Válidos");
        System.out.println("O robô " + roboAtual.getCor() + " levou " + roboAtual.getInvalidMov() + " Movimentos Inválidos");
        
        roboAtual = roboAtual == robo1 ? robo2 : robo1;

        System.out.println("O robô " + roboAtual.getCor() + " levou " + roboAtual.getValidMov() + " Movimentos Válidos");
        System.out.println("O robô " + roboAtual.getCor() + " levou " + roboAtual.getInvalidMov() + " Movimentos Inválidos");
    }
}
