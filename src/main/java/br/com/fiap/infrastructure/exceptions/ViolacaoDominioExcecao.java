package br.com.fiap.infrastructure.exceptions;

public class ViolacaoDominioExcecao extends RuntimeException {

    final String mensagem;
    String codigo;
    Throwable causa = null;

    public ViolacaoDominioExcecao(String mensagem, String codigo, Throwable causa) {
        super(mensagem, causa);
        this.mensagem = mensagem;
        this.codigo = codigo;
        this.causa = causa;
    }

    public ViolacaoDominioExcecao(String mensagem) {
        super(mensagem);
        this.mensagem = mensagem;
    }
}
