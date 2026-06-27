package br.unicamp.padroesestruturais.legacy.adapter;

import br.unicamp.padroesestruturais.legacy.domain.FormaPagamento;
import br.unicamp.padroesestruturais.legacy.gateway.GatewayPagamentoInterno;

public final class GatewayFactory {

    private GatewayFactory() {
        // Construtor privado para evitar instanciação
    }

    public static GatewayPagamento criar(FormaPagamento forma) {

            switch (forma) {
                case BOLETO, PIX -> {
                    return new GatewayPagamentoInterno();
            }
                case CARTAO_CREDITO -> {
                    return new PaySecureGatewayAdapter();
            }

                case CARTEIRA_DIGITAL -> {
                    return new WalletPaySDKAdapter();
            }

                default -> throw new IllegalArgumentException(
                        "Forma de pagamento nao suportada: " + forma);
            }
        }
}