package br.unicamp.padroesestruturais.legacy.adapter;

import br.unicamp.padroesestruturais.legacy.externo.ChargeStatus;
import br.unicamp.padroesestruturais.legacy.externo.WalletPaySDK;
import br.unicamp.padroesestruturais.legacy.domain.FormaPagamento;
import br.unicamp.padroesestruturais.legacy.externo.ChargeRequest;
import br.unicamp.padroesestruturais.legacy.externo.ChargeResponse;
import br.unicamp.padroesestruturais.legacy.domain.ResultadoCobranca;

public class WalletPaySDKAdapter implements GatewayPagamento {

    private final WalletPaySDK sdk = new WalletPaySDK();

    @Override
    public ResultadoCobranca cobrar(String pedidoId, String cliente, double valor, FormaPagamento forma) {
        
        long valorEmCentavos = Math.round(valor * 100);
        
        ChargeRequest request = new ChargeRequest(pedidoId, cliente, valorEmCentavos);
        ChargeResponse response = sdk.charge(request);

        String status = (response.getStatus() == ChargeStatus.CONFIRMED) ? "APROVADA" : "RECUSADA";

        return new ResultadoCobranca(
                pedidoId,
                valor,
                status,
                response.getWalletTransactionId(),
                forma
        );
    }
}