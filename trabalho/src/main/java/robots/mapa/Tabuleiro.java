package robots.mapa;

import java.util.ArrayList;

import robots.objetos.Alimento;
import robots.objetos.Bomba;
import robots.objetos.Obstaculo;
import robots.robos.Robo;

public class Tabuleiro {

    enum espaco {
        VAZIO,
        ALIMENTO,
        BOMBA,
        ROCHA
    }

    private final int tamanho;
    private espaco[][] mapa;
    private ArrayList<Robo> robos;
    private ArrayList<Obstaculo> obstaculos;

    public Tabuleiro(int tam){
        this.tamanho = tam;
        this.robos = new ArrayList<>();
        this.obstaculos = new ArrayList<>();
        mapa = new espaco[tamanho][tamanho];
        iniciarMapa(mapa);
    }

    private void iniciarMapa(espaco[][] mapa){
        for(int i = 0; i < this.tamanho; i++){
            for(int j = 0; j < this.tamanho; j++){
                mapa[i][j] = espaco.VAZIO;
            }
        }
    }

    public ArrayList<Robo> getRobos(){
        return this.robos;
    }

    public ArrayList<Obstaculo> getObstaculos(){
        return this.obstaculos;
    }

    public int getTamanho(){
        return this.tamanho;
    }

    public void AdicionarRobo(Robo r){
        this.robos.add(r);
    }

    public void RemoverRobo(Robo r){
        this.robos.remove(r);
    }

    public void AdicionarAlimento(Alimento a){
        int x = a.getxPos();
        int y = a.getyPos();

        mapa[x][y] = espaco.ALIMENTO;
    }

    public void AdicionarObstaculo(Obstaculo o, int x, int y){
        if(isPosicaoValida(x, y)){
            o.setxPos(x);
            o.setyPos(y);
            if(o instanceof Bomba){
                mapa[x][y] = espaco.BOMBA;
            }else{
                mapa[x][y] = espaco.ROCHA;
            }
            this.obstaculos.add(o);
        }else{
            System.out.println("Posição Inválida");
        }
    }

    public void RemoverObstaculo(Obstaculo o){
        mapa[o.getxPos()][o.getyPos()] = espaco.VAZIO;
        obstaculos.remove(o);
    }

    public Obstaculo getObstaculo(int x, int y){
        if(isPosicaoValida(x, y) && (mapa[x][y] == espaco.ROCHA || mapa[x][y] == espaco.BOMBA)){
            for(Obstaculo o : obstaculos){
                if(o.getxPos() == x && o.getyPos() == y){
                    return o;
                }
            }
        }
        return null;
    }

    public boolean isPosicaoValida(int x, int y){
        return x >= 0 && x < tamanho && y >= 0 && y < tamanho;
    }

    public void imprimirTabuleiro() {
        for (int y = tamanho-1; y >= 0; y--) {
            for (int x = 0; x < tamanho; x++) {
                char simbolo = '.'; // Padrão: terreno vazio
                if (mapa[x][y] == espaco.ALIMENTO) {
                    simbolo = 'O'; // Alimento
                } else if (mapa[x][y] == espaco.BOMBA) {
                    simbolo = 'B'; // Obstáculo
                } else if (mapa[x][y] == espaco.ROCHA){
                    simbolo = 'R';
                } else {
                    for(Robo robo : robos){
                        if (x == robo.getxPos() && y == robo.getyPos()) {
                            simbolo = robo.getCor().toUpperCase().charAt(0);
                        }
                    }
                }
                System.out.print(simbolo);
            }
            System.out.println();
        }
    }
}
