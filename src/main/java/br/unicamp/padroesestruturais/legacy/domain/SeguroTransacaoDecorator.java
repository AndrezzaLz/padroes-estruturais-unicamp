package br.unicamp.padroesestruturais.legacy.domain;

public class SeguroTransacaoDecorator extends CobrancaDecorator {
    
    public SeguroTransacaoDecorator(Cobravel cobravel) {
        super(cobravel);
    }

    @Override
    public double calcularValor() {
       
        return super.calcularValor() + 4.90;
    }
}