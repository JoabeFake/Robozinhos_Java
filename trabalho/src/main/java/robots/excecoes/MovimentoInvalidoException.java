package robots.excecoes;

public class MovimentoInvalidoException extends Exception {
    public MovimentoInvalidoException(String movimento) {
        super("Movimento inválido: " + movimento);
    }
}