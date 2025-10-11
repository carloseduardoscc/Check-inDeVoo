package ciaaerea.domain;

import br.com.ciaaerea.domain.*;
import br.com.ciaaerea.domain.exceptions.AssentoIndisponivelException;
import br.com.ciaaerea.domain.exceptions.PassageiroIsAlreadyInVooException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VooTest {
    @Test
    public void deveInstanciarVooCorretamente(){
        Rota rota = new Rota("EUA", "China");
        Aeronave aeronave = new Aeronave("Boeing 737", 215, 6);
        Voo voo = new Voo(aeronave, rota);
        Passageiro passageiro = new Passageiro("Eduardo Costa", "52312368302", "RG");
        Assento assento = aeronave.getAssentos().getFirst().getFirst();
        Reserva reserva = new Reserva(passageiro, assento);
        voo.getReservas().add(reserva);

        assertAll(
                ()->assertEquals(aeronave, voo.getAeronave()),
                ()->assertEquals(rota, voo.getRota())
        );
    }

    @Nested
    class quandoCadastrarReserva {

        private Rota rota1;
        private Rota rota2;
        private Aeronave aeronave1;
        private Aeronave aeronave2;
        private Voo voo1;
        private Voo voo2;
        private Passageiro passageiro1;
        private Passageiro passageiro2;
        Assento assento1;
        Reserva reserva1;
        Assento assento2;
        Reserva reserva2;

        @BeforeEach
        void setup() {
            rota1 = new Rota("EUA", "China");
            rota2 = new Rota("England", "EUA");

            aeronave1 = new Aeronave("Boeing 737", 215, 4);
            aeronave2 = new Aeronave("Airbus A380", 615, 6);

            voo1 = new Voo(aeronave1, rota1);
            voo2 = new Voo(aeronave2, rota2);

            passageiro1 = new Passageiro("Carlos Eduardo de Souza Coelho Cavaletto", "50301236502", "454545454");
            passageiro2 = new Passageiro("Gabriel Borsário", "63215454203", "686868686");

            assento1 = voo1.getAeronave().getAssentos().getFirst().getFirst();
            reserva1 = new Reserva(passageiro1, assento1);

            assento2 = voo2.getAeronave().getAssentos().getFirst().getFirst();
            reserva2 = new Reserva(passageiro2, assento2);
        }

        /**
         * Happy-path<br>
         * não deve lançar nenhum tipo de exceção
         */
        @Test
        public void CorretamenteNaoDeveLancarExcecao(){
            assertAll(
                    ()->assertDoesNotThrow(()->voo1.addReserva(reserva1)),
                    ()->assertDoesNotThrow(()->voo2.addReserva(reserva2))
            );
        }

        /**
         * Business violation<br>
         * ao tentar cadastrar uma reserva com um assento já ocupado lança exceção
         * @throws AssentoIndisponivelException
         */
        @Test
        public void EmAssentoOcupadoDeveLancarExcecao(){
            Reserva reservaNoMesmoAssento = new Reserva(passageiro2, assento1);

            assertThrows(AssentoIndisponivelException.class, ()-> {
                voo1.addReserva(reserva1);
                voo1.addReserva(reservaNoMesmoAssento);
            });
        }

        /**
         * Business violation<br>
         * ao tentar cadastrar uma reserva de um passageiro que já tem outra reserva neste mesmo voo deve lançar exceção
         * @throws PassageiroIsAlreadyInVooException
         */
        @Test
        public void comMaisDeUmaReservaPorPassageiroNumVooDeveLancarExcecao(){
            Assento assentoMesmoVoo = voo1.getAeronave().getAssentos().getFirst().getLast();
            Reserva reservaDoMesmoPassageiro = new Reserva(passageiro1, assentoMesmoVoo);

            assertThrows(PassageiroIsAlreadyInVooException.class, ()->{
                voo1.addReserva(reserva1);
                voo1.addReserva(reservaDoMesmoPassageiro);
            });
        }

        /**
         * Argumento inválido<br>
         * ao tentar cadastrar uma reserva com um assento inválido para o voo deve lançar exceção
         */
        @Test
        public void ComAssentoInexistenteDeveLancarExcecao(){
            Assento assentoInexistente = new Assento("10", "Z");
            Reserva reservaInvalida = new Reserva(passageiro1, assentoInexistente);

            assertThrows(IllegalArgumentException.class, ()->{
                voo1.addReserva(reservaInvalida);
            });
        }
    }

}
