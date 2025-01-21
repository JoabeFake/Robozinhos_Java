package robots;

import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import robots.excecoes.MovimentoInvalidoException;
import robots.mapa.Tabuleiro;
import robots.objetos.Alimento;
import robots.objetos.Bomba;
import robots.objetos.Obstaculo;
import robots.objetos.Rocha;
import robots.robos.Robo;
import robots.robos.RoboInteligente;

public class MainInteAndNormWithProps {
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

        System.out.println("Você gostaria de adicionar alguns Obstáculos?");
        System.out.println("1) Bomba x1 e Rocha x1");
        System.out.println("2) Bomba x2");
        System.out.println("3) Rocha x2");
        System.out.println("4) nada");
        int escolha = scanner.nextInt();
        switch (escolha) {
            case 1:
                Obstaculo bomba = new Bomba();
                Obstaculo rocha = new Rocha();

                System.out.println("Digite as coordenadas para a Bomba");
                System.out.println("Coordenadas inválidas: (0, 0) e (" + xAlimento + ", " + yAlimento + ")");
                int xPosProp = scanner.nextInt();
                int yPosProp = scanner.nextInt();

                tabuleiro.AdicionarObstaculo(bomba, xPosProp, yPosProp);

                System.out.println("Digite as coordenadas para a Rocha");
                System.out.println("Coordenadas inválidas: (0, 0) e (" + xAlimento + ", " + yAlimento + ")");
                
                xPosProp = scanner.nextInt();
                yPosProp = scanner.nextInt();

                tabuleiro.AdicionarObstaculo(rocha, xPosProp, yPosProp);
                break;
            case 2:
                Obstaculo bomba1 = new Bomba();
                Obstaculo bomba2 = new Bomba();

                System.out.println("Digite as coordenadas para a Bomba");
                System.out.println("Coordenadas inválidas: (0, 0) e (" + xAlimento + ", " + yAlimento + ")");
                xPosProp = scanner.nextInt();
                yPosProp = scanner.nextInt();

                tabuleiro.AdicionarObstaculo(bomba1, xPosProp, yPosProp);

                System.out.println("Digite as coordenadas para a Rocha");
                System.out.println("Coordenadas inválidas: (0, 0) e (" + xAlimento + ", " + yAlimento + ")");
                
                xPosProp = scanner.nextInt();
                yPosProp = scanner.nextInt();

                tabuleiro.AdicionarObstaculo(bomba2, xPosProp, yPosProp);
                break;
            case 3:
                Obstaculo rocha1 = new Rocha();
                Obstaculo rocha2 = new Rocha();

                System.out.println("Digite as coordenadas para a Bomba");
                System.out.println("Coordenadas inválidas: (0, 0) e (" + xAlimento + ", " + yAlimento + ")");
                xPosProp = scanner.nextInt();
                yPosProp = scanner.nextInt();

                tabuleiro.AdicionarObstaculo(rocha1, xPosProp, yPosProp);

                System.out.println("Digite as coordenadas para a Rocha");
                System.out.println("Coordenadas inválidas: (0, 0) e (" + xAlimento + ", " + yAlimento + ")");
                
                xPosProp = scanner.nextInt();
                yPosProp = scanner.nextInt();

                tabuleiro.AdicionarObstaculo(rocha2, xPosProp, yPosProp);
                break;
            default:
                System.out.println("Opção inválida. Nada será adicionado");
                break;
        }

        tabuleiro.AdicionarAlimento(alimento);
        tabuleiro.AdicionarRobo(robo1);
        tabuleiro.AdicionarRobo(robo2);
        
        boolean robotFound = false;
        boolean allDead = false;

        while(!robotFound && !allDead){
            for(Iterator<Robo> iterator = tabuleiro.getRobos().iterator(); iterator.hasNext();){
                Robo robo = iterator.next();
                if(!robo.getIsDead()){
                    try{
                        robo.mover(random.nextInt(tabuleiro.getTamanho()) + 1, tabuleiro);
                        robotFound = robo.encontrouAlimento(xAlimento, yAlimento);
                        Obstaculo obj = tabuleiro.getObstaculo(robo.getxPos(), robo.getyPos()); 
                        
                        if(obj != null){
                            if(obj instanceof Bomba){
                                robo.setIsDead(obj.bater(robo, tabuleiro));
                                iterator.remove();
                                tabuleiro.RemoverObstaculo(obj);
                            }else{
                                obj.bater(robo, tabuleiro);
                            }
                        }
                    } catch(MovimentoInvalidoException e){
                        System.out.println("Movimento Inválido: " + e.getMessage());
                    }
                }
            }

            tabuleiro.imprimirTabuleiro();

            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            allDead = tabuleiro.getRobos().stream().allMatch(robo -> robo.getIsDead());
        }

        if(allDead){
            System.out.println("Todos os Robôs Explodiram");
        }

        for(Robo robo : tabuleiro.getRobos()){
            System.out.println("O robo " + robo.getCor() + " realizou: " + robo1.getValidMov() + " Movimentos");
        }
    }
}