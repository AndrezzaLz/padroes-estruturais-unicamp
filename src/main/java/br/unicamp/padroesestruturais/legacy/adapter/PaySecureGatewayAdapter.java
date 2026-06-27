package br.unicamp.padroesestruturais.legacy.adapter;

import br.unicamp.padroesestruturais.legacy.domain.FormaPagamento;
import br.unicamp.padroesestruturais.legacy.domain.ResultadoCobranca;
import br.unicamp.padroesestruturais.legacy.externo.GatewayIndisponivelException;
import br.unicamp.padroesestruturais.legacy.externo.PaySecureGateway;
import br.unicamp.padroesestruturais.legacy.externo.TransacaoExterna;

import java.util.HashMap;
import java.util.Map;

public class PaySecureGatewayAdapter implements GatewayPagamento {

    private final PaySecureGateway gateway = new PaySecureGateway();

    @Override
    public ResultadoCobranca cobrar(String pedidoId,
                                    String cliente,
                                    double valor,
                                    FormaPagamento forma) {

        Map<String, Object> dadosTransacao = new HashMap<>();

        dadosTransacao.put("orderId", pedidoId);
        dadosTransacao.put("customerName", cliente);
        dadosTransacao.put("amount", valor);
        dadosTransacao.put("currency", "BRL");

        try {
            TransacaoExterna transacao = gateway.processarTransacao(dadosTransacao);

            String status = transacao.getCodigoStatus() == 200
                    ? "APROVADA"
                    : "RECUSADA";

            return new ResultadoCobranca(
                    pedidoId,
                    valor,
                    status,
                    transacao.getReferenciaExterna(),
                    forma
            );

        } catch (GatewayIndisponivelException e) {

            return new ResultadoCobranca(
                    pedidoId,
                    valor,
                    "RECUSADA",
                    null,
                    forma
            );
        }
    }
}