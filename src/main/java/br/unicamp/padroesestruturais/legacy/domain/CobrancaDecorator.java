package br.unicamp.padroesestruturais.legacy.domain;

public abstract class CobrancaDecorator implements Cobravel {
    protected Cobravel cobravelEnvelopado;

    public CobrancaDecorator(Cobravel cobravel) {
        this.cobravelEnvelopado = cobravel;
    }

    @Override
    public double calcularValor() {
        return cobravelEnvelopado.calcularValor();
    }
}