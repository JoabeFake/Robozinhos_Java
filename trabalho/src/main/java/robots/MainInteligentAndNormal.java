package robots;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import robots.excecoes.MovimentoInvalidoException;
import robots.mapa.Tabuleiro;
import robots.objetos.Alimento;
import robots.robos.Robo;
import robots.robos.RoboInteligente;

public class MainInteligentAndNormal {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        Tabuleiro tabuleiro = new Tabuleiro(4);
        
        Robo robo1 = new Robo("Vermelho");
        RoboInteligente robo2 = new RoboInteligente("Azul");

        System.out.print("Digite a coordenada x do alimento (0 a " + (tabuleiro.getTamanho()-1) +  "): ");
        int xAlimento = scanner.nextInt();
        System.out.print("Digite a coordenada y do alimento (0 a " + (tabuleiro.getTamanho()-1) + "): ");
        int yAlimento = scanner.nextInt();
        
        Alimento alimento = new Alimento(xAlimento, yAlimento);

        tabuleiro.AdicionarAlimento(alimento);
        tabuleiro.AdicionarRobo(robo1);
        tabuleiro.AdicionarRobo(robo2);
        
        boolean robo1Found = false, robo2Found = false;
        boolean allFound = false;

        while(!allFound){
            if(!robo1Found){
                try{
                    robo1.mover(random.nextInt(tabuleiro.getTamanho()) + 1, tabuleiro);
                    robo1Found = robo1.encontrouAlimento(xAlimento, yAlimento);
                } catch(MovimentoInvalidoException e){
                    System.out.println("Movimento Inválido: " + e.getMessage());
                }
            }
            
            //para robo2
            if(!robo2Found){
                try{
                    robo2.mover(random.nextInt(tabuleiro.getTamanho()) + 1, tabuleiro);
                    robo2Found = robo2.encontrouAlimento(xAlimento, yAlimento);
                } catch(MovimentoInvalidoException e){
                    System.out.println("Movimento Inválido: " + e.getMessage());
                }
            }
            
            allFound = robo1Found && robo2Found;

            tabuleiro.imprimirTabuleiro();

            try {
                TimeUnit.MILLISECONDS.sleep(100); // Pausa de 500 milissegundos (0.5 segundo)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("O robo " + robo1.getCor() + " realizou: " + robo1.getValidMov() + " Movimentos");
        System.out.println("O robo " + robo2.getCor() + " realizou: " + robo2.getValidMov() + " Movimentos");

    }
}
