package ciaaerea.domain;

import br.com.ciaaerea.domain.Rota;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RotaTest {

    @Test
    public void deveInstanciarRotaCorretamente(){
        Rota rota = new Rota("EUA", "China");

        assertAll(
                ()->assertEquals("EUA", rota.origem()),
                ()->assertEquals("China", rota.destino())
        );
    }
}
