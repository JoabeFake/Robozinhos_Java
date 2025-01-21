package robots.robos;

import java.util.Random;

import robots.excecoes.MovimentoInvalidoException;
import robots.mapa.Tabuleiro;

public class RoboInteligente extends Robo {
    private String ultimoMovimentoInvalido;
    private Random random = new Random();

    // Construtor
    public RoboInteligente(String cor) {
        super(cor);
    }

    @Override
    public void mover(String direcao, Tabuleiro tabuleiro) throws MovimentoInvalidoException {
        try {
            super.mover(direcao, tabuleiro);
            ultimoMovimentoInvalido = null; // Reinicia se o movimento for válido
        } catch (MovimentoInvalidoException e) {
            ultimoMovimentoInvalido = e.getMessage();
            // Tenta outros movimentos até encontrar um válido
            while (true) {
                String novaDirecao = gerarNovaDirecao(ultimoMovimentoInvalido);
                try {
                    super.mover(novaDirecao, tabuleiro);
                    ultimoMovimentoInvalido = null;
                    break;
                } catch (MovimentoInvalidoException ex) {
                    // Continua tentando outros movimentos
                }
            }
        }
    }

    private String gerarNovaDirecao(String ultimoMovimentoInvalido) {
        String[] direcoes = {"up", "down", "left", "right"};
        int indice = random.nextInt(direcoes.length);
        while (direcoes[indice].equals(ultimoMovimentoInvalido)) {
            indice = random.nextInt(direcoes.length);
        }
        return direcoes[indice];
    }
}