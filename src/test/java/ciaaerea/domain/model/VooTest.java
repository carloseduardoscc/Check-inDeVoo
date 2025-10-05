package ciaaerea.domain.model;

import br.com.ciaaerea.domain.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VooTest {
    @Test
    public void deveInstanciarVooCorretamente(){
        Rota rota = new Rota("EUA", "China");
        Aeronave aeronave = new Aeronave("Boeing 737", 215, 6);
        Voo voo = new Voo(aeronave, rota);
        Passageiro passageiro = new Passageiro("Eduardo Costa", "52312368302", "RG");
        Assento assento = aeronave.getAssentos().getFirst().getFirst();
        Reserva reserva = new Reserva(passageiro, voo, assento);
        voo.getReservas().add(reserva);

        assertAll(
                ()->assertEquals(aeronave, voo.getAeronave()),
                ()->assertEquals(rota, voo.getRota())
        );
    }
}
