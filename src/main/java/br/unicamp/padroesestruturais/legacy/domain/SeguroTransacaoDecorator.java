package br.unicamp.padroesestruturais.legacy.domain;

public class SeguroTransacaoDecorator extends CobrancaDecorator {
    
    public SeguroTransacaoDecorator(Cobravel cobravel) {
        super(cobravel);
    }

    @Override
    public double calcularValor() {
        // Soma o valor fixo de R$ 4,90 do seguro de transação
        return super.calcularValor() + 4.90;
    }
}