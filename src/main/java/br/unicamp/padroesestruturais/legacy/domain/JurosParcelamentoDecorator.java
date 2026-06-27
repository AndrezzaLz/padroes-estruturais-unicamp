package br.unicamp.padroesestruturais.legacy.domain;

public class JurosParcelamentoDecorator extends CobrancaDecorator {
    
    public JurosParcelamentoDecorator(Cobravel cobravel) {
        super(cobravel);
    }

    @Override
    public double calcularValor() {
        // Aplica os juros de parcelamento de 2,99%
        return super.calcularValor() * 1.0299;
    }
}