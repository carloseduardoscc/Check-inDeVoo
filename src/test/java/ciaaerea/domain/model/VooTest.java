package ciaaerea.domain.model;

import br.com.ciaaerea.domain.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VooTest {
    @Test
    public void deveInstanciarVooCorretamente(){
        Rota rota = new Rota("EUA", "China");
        Aeronave aeronave = new Aeronave("Boeing 737", 215);
        Voo voo = new Voo(aeronave, rota);
        Passageiro passageiro = new Passageiro("Eduardo Costa", "5231236802", "RG");
        Reserva reserva = new Reserva(passageiro, voo);
        voo.getReservas().add(reserva);

        assertAll(
                ()->assertEquals(aeronave, voo.getAeronave()),
                ()->assertEquals(rota, voo.getRota())
        );
    }
}
