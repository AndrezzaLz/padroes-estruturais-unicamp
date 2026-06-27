package br.unicamp.padroesestruturais.legacy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import br.unicamp.padroesestruturais.legacy.domain.FormaPagamento;
import br.unicamp.padroesestruturais.legacy.domain.ResultadoCobranca;
import br.unicamp.padroesestruturais.legacy.adapter.PaySecureGatewayAdapter;

class PaySecureGatewayAdapterTest {

    @Test
    void deveAprovarCobranca() {
        PaySecureGatewayAdapter adapter = new PaySecureGatewayAdapter();
        ResultadoCobranca resultado = adapter.cobrar("PED-200", "Andrezza Luize", 500.0, FormaPagamento.CARTAO_CREDITO);

        assertEquals("APROVADA", resultado.getStatus());
        assertNotNull(resultado.getReferencia());
        assertTrue(resultado.getReferencia().startsWith("PSEC-"));
    }

    @Test
    void deveRecusarCobrancaAcimaDoLimite() {
        PaySecureGatewayAdapter adapter = new PaySecureGatewayAdapter();
        // Gateway recusa acima de R$ 10.000,00 (código 402)
        ResultadoCobranca resultado = adapter.cobrar("PED-201", "Andrezza Luize", 15000.0, FormaPagamento.CARTAO_CREDITO);

        assertEquals("RECUSADA", resultado.getStatus());
    }

    @Test
    void deveLidarComExcecaoDeGatewayIndisponivelParaValorInvalido() {
        PaySecureGatewayAdapter adapter = new PaySecureGatewayAdapter();
        ResultadoCobranca resultado = adapter.cobrar("PED-202", "Andrezza Luize", -50.0, FormaPagamento.CARTAO_CREDITO);

        assertEquals("RECUSADA", resultado.getStatus());
        assertNull(resultado.getReferencia());
    }
}