package ciaaerea.domain.model;

import br.com.ciaaerea.domain.model.Rota;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RotaTest {

    @Test
    public void deveCadastrarRotaCorretamente(){
        Rota rota = new Rota("EUA", "China");

        assertAll(
                ()->assertEquals("EUA", rota.origem()),
                ()->assertEquals("China", rota.destino())
        );
    }
}
