package ciaaerea.domain;

import br.com.ciaaerea.domain.model.Aeronave;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AeronaveTest {
    @Test
    public void deveInstanciarAeronaveCorretamente(){
        Aeronave aeronave = new Aeronave("Boeing 737", 215, 6);

        assertAll(
                ()->assertEquals("Boeing 737", aeronave.getModelo()),
                ()->assertEquals(215, aeronave.getCapacidade())
        );
    }
}
