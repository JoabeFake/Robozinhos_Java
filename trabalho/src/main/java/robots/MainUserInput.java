package robots;

import java.util.Scanner;

import robots.excecoes.MovimentoInvalidoException;
import robots.mapa.Tabuleiro;
import robots.objetos.Alimento;
import robots.robos.Robo;

public class MainUserInput {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tabuleiro tabuleiro = new Tabuleiro(4);
        
        Robo robo = new Robo("Vermelho");

        System.out.print("Digite a coordenada x do alimento (0 a " + (tabuleiro.getTamanho()-1) +  "): ");
        int xAlimento = scanner.nextInt();
        System.out.print("Digite a coordenada y do alimento (0 a " + (tabuleiro.getTamanho()-1) + "): ");
        int yAlimento = scanner.nextInt();
        
        Alimento alimento = new Alimento(xAlimento, yAlimento);

        tabuleiro.AdicionarAlimento(alimento);
        tabuleiro.AdicionarRobo(robo);
        
        boolean encontrouAlimento = false;

        tabuleiro.imprimirTabuleiro();

        while(!encontrouAlimento){
            System.out.print("Digite a direção (1-up, 2-down, 3-right, 4-left): ");
            int direcao = scanner.nextInt();

            try{
                robo.mover(direcao, tabuleiro);
                encontrouAlimento = robo.encontrouAlimento(xAlimento, yAlimento);
            } catch(MovimentoInvalidoException e){
                System.out.println("Movimento Inválido: " + e.getMessage());
            }

            tabuleiro.imprimirTabuleiro();
        }
        System.out.println("O robô encontrou o Alimento");
    }
}