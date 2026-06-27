package br.unicamp.padroesestruturais.legacy.domain;

public class TaxaInternacionalDecorator extends CobrancaDecorator {
    
    public TaxaInternacionalDecorator(Cobravel cobravel) {
        super(cobravel);
    }

    @Override
    public double calcularValor() {
        // Aplica a taxa de operação internacional de 5%
        return super.calcularValor() * 1.05;
    }
}