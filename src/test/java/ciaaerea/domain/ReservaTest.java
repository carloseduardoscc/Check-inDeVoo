package ciaaerea.domain;

import br.com.ciaaerea.domain.*;
import br.com.ciaaerea.domain.enums.StatusReserva;
import br.com.ciaaerea.domain.exceptions.TransicaoInvalidaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static br.com.ciaaerea.domain.enums.StatusReserva.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReservaTest {

    Aeronave aeronave;
    Rota rota;
    Voo voo;
    Passageiro passageiro;
    Assento assento;

    @BeforeEach
    public void setup() {
        aeronave = aeronave = new Aeronave("Boeing 737", 215,6);
        rota = new Rota("England", "EUA");
        voo = new Voo(aeronave, rota);
        passageiro = new Passageiro("Carlos Eduardo de Souza Coelho Cavaletto", "50301236502", "454545454");
        assento = aeronave.getAssentos().getFirst().getFirst();

    }

    @Test
    public void dadoInformacoesCorretasQuandoInstanciarNaoDeveLancarExcecao() {
        assertDoesNotThrow(() -> {
            new Reserva(passageiro, assento);
        });
    }

    @Nested
    class QuandoFazerTransicoesDeStatus {

        /**
         * Cobre várias combinações que violam as regras de negócio
         *
         * @throws TransicaoInvalidaException em todas combinações
         */
        static Stream<List<StatusReserva>> sequenciasDeTransicaoNaoPermitidas() {
            return Stream.of(
                    List.of(EMBARCADA),
                    List.of(CONFIRMADA, ABERTA),
                    List.of(CONFIRMADA, EMBARCADA, CANCELADA),
                    List.of(CONFIRMADA, EMBARCADA, ABERTA),
                    List.of(CONFIRMADA, EMBARCADA, CONFIRMADA),
                    List.of(CANCELADA, CANCELADA),
                    List.of(CONFIRMADA, CANCELADA, EMBARCADA)
            );
        }

        @ParameterizedTest
        @MethodSource("sequenciasDeTransicaoNaoPermitidas")
        public void dadaSequenciaNaoPermitidaDeveLancarExcecao(List<StatusReserva> sequenciaStatus) {
            Reserva reserva = new Reserva(passageiro, assento);
            assertThrows(TransicaoInvalidaException.class, () -> {
                sequenciaStatus.forEach(reserva::setStatus);
            });
        }

        /**
         * Cobre várias combinações permitidas pelas regras de negócio
         * Não deve lançar nenhuma exceção
         */
        static Stream<List<StatusReserva>> sequenciasDeTransicaoPermitidas() {
            return Stream.of(
                    List.of(CONFIRMADA, EMBARCADA),
                    List.of(CONFIRMADA, CANCELADA),
                    List.of(CANCELADA)
            );
        }

        @ParameterizedTest
        @MethodSource("sequenciasDeTransicaoPermitidas")
        public void dadaSequenciaPermitidaNaoDeveLancarExcecao(List<StatusReserva> sequenciaStatus) {
            Reserva reserva = new Reserva(passageiro, assento);
            assertDoesNotThrow(() -> {
                sequenciaStatus.forEach(reserva::setStatus);
            });
        }
    }

}
