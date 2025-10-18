package ciaaerea.domain;

import br.com.ciaaerea.domain.exceptions.BusinessViolationException;
import br.com.ciaaerea.domain.model.Passageiro;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PassageiroTest {
    @ParameterizedTest
    @CsvSource({
        "Carlos,50301725802,5454545411",
        "João,23126845665,6545252155",
        "Jeferson,02325556898,4445445445"
    })
    public void dadoInformacoesCorretasQuandoInstaciarNaoDeveLancarExcecao(String nome, String cpf, String documento){
        assertDoesNotThrow(()->{
            new Passageiro(nome, cpf, documento);
        });
    }

    @ParameterizedTest
    @CsvSource({
            ",50301725802,54545454",
            "João,2312684566,6545252155",
            "Jeferson,02325556898,"
    })
    public void dadasInformacoesIncorretasQuandoInstanciarDeveLancarExcecao(String nome, String cpf, String documento){
        assertThrows(BusinessViolationException.class,()->{
            new Passageiro(nome, cpf, documento);
        });
    }
}
