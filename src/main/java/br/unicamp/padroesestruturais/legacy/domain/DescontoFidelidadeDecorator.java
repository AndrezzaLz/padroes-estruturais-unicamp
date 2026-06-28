package br.unicamp.padroesestruturais.legacy.domain;

public class DescontoFidelidadeDecorator extends CobrancaDecorator {
    
    public DescontoFidelidadeDecorator(Cobravel cobravel) {
        super(cobravel);
    }

    @Override
    public double calcularValor() {
        
        return super.calcularValor() * 0.95;
    }
}