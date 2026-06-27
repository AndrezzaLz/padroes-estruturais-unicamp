package br.unicamp.padroesestruturais.legacy.domain;

public class DescontoFidelidadeDecorator extends CobrancaDecorator {
    
    public DescontoFidelidadeDecorator(Cobravel cobravel) {
        super(cobravel);
    }

    @Override
    public double calcularValor() {
        // Aplica o desconto de fidelidade de 5% sobre o valor acumulado
        return super.calcularValor() * 0.95;
    }
}