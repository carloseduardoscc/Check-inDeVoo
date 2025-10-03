package br.com.ciaaerea.repositories.inMemory;

import br.com.ciaaerea.domain.model.Aeronave;
import br.com.ciaaerea.domain.model.Rota;
import br.com.ciaaerea.domain.model.Voo;
import br.com.ciaaerea.repositories.Repository;

public class RepositorySeed {

    /**
     * Alimenta o repositório em memória com dados de teste
     * @param rotaRepo
     * @param aeronaveRepo
     * @param vooRepo
     */
    public static void seed(Repository<Rota> rotaRepo, Repository<Aeronave> aeronaveRepo, Repository<Voo> vooRepo) {
        rotaRepo.add(new Rota("EUA", "China"));
        rotaRepo.add(new Rota("England", "EUA"));
        aeronaveRepo.add(new Aeronave("Boeing 737", 215));
        aeronaveRepo.add(new Aeronave("Airbus A380", 615));
        vooRepo.add(new Voo(aeronaveRepo.findByIndex(0), rotaRepo.findByIndex(0)));
        vooRepo.add(new Voo(aeronaveRepo.findByIndex(1), rotaRepo.findByIndex(1)));
    }
}
