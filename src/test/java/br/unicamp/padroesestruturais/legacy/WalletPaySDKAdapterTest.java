package br.unicamp.padroesestruturais.legacy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import br.unicamp.padroesestruturais.legacy.domain.FormaPagamento;
import br.unicamp.padroesestruturais.legacy.domain.ResultadoCobranca;
import br.unicamp.padroesestruturais.legacy.adapter.WalletPaySDKAdapter;

class WalletPaySDKAdapterTest {

    @Test
    void deveAprovarCobranca() {
        WalletPaySDKAdapter adapter = new WalletPaySDKAdapter();
        ResultadoCobranca resultado = adapter.cobrar("PED-100", "Andrezza Luize", 500.0, FormaPagamento.CARTEIRA_DIGITAL);

        assertEquals("APROVADA", resultado.getStatus());
        assertNotNull(resultado.getReferencia());
        assertTrue(resultado.getReferencia().startsWith("WPAY-"));
    }

    @Test
    void deveRecusarCobrancaAcimaDoLimite() {
        WalletPaySDKAdapter adapter = new WalletPaySDKAdapter();
        // SDK recusa acima de R$ 10.000,00 (1_000_000 centavos)
        ResultadoCobranca resultado = adapter.cobrar("PED-101", "Andrezza Luize", 15000.0, FormaPagamento.CARTEIRA_DIGITAL);

        assertEquals("RECUSADA", resultado.getStatus());
    }
}