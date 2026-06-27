package br.unicamp.padroesestruturais.legacy.adapter;

import br.unicamp.padroesestruturais.legacy.domain.FormaPagamento;
import br.unicamp.padroesestruturais.legacy.domain.ResultadoCobranca;

public interface GatewayPagamento {

    ResultadoCobranca cobrar(
            String pedidoId,
            String cliente,
            double valor,
            FormaPagamento forma
    );
}