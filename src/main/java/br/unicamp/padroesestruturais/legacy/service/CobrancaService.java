package br.unicamp.padroesestruturais.legacy.service;

import br.unicamp.padroesestruturais.legacy.adapter.GatewayFactory;
import br.unicamp.padroesestruturais.legacy.adapter.GatewayPagamento;
import br.unicamp.padroesestruturais.legacy.domain.FormaPagamento;
import br.unicamp.padroesestruturais.legacy.domain.Pedido;
import br.unicamp.padroesestruturais.legacy.domain.ResultadoCobranca;
import br.unicamp.padroesestruturais.legacy.domain.Cobravel;

import java.util.ArrayList;
import java.util.List;

public class CobrancaService {

   
    public ResultadoCobranca cobrar(Pedido pedido, FormaPagamento forma, Cobravel cobrancaComDecorators) {

       
        double valorFinal = calcularValorFinal(cobrancaComDecorators);

        GatewayPagamento gateway = GatewayFactory.criar(forma);

        return gateway.cobrar(
                pedido.getId(),
                pedido.getCliente(),
                valorFinal,
                forma
        );
    }

   
    public List<ResultadoCobranca> cobrarEmLote(List<Pedido> pedidos, FormaPagamento forma, List<Cobravel> cobrancasDecoradas) {

        List<ResultadoCobranca> resultados = new ArrayList<>();

        for (int i = 0; i < pedidos.size(); i++) {
            resultados.add(
                    cobrar(
                            pedidos.get(i),
                            forma,
                            cobrancasDecoradas.get(i)
                    )
            );
        }

        return resultados;
    }

    
    public double calcularValorFinal(Cobravel cobranca) {
        return cobranca.calcularValor();
    }
}