package br.com.ciaaerea.infra.repositories.in_memory;

import br.com.ciaaerea.domain.model.*;
import br.com.ciaaerea.domain.repository.Repository;

public class RepositorySeed {

    /**
     * Alimenta o repositório em memória com dados de teste
     * @param rotaRepo
     * @param aeronaveRepo
     * @param vooRepo
     */
    public static void seed(Repository<Rota> rotaRepo, Repository<Aeronave> aeronaveRepo, Repository<Voo> vooRepo, Repository<Passageiro> passageiroRepo, Repository<Reserva> reservaRepo) {
        rotaRepo.save(new Rota("EUA", "China"));
        rotaRepo.save(new Rota("England", "EUA"));
        aeronaveRepo.save(new Aeronave("Boeing 737", 215, 4));
        aeronaveRepo.save(new Aeronave("Airbus A380", 615, 6));
        vooRepo.save(new Voo(aeronaveRepo.findByIndex(0), rotaRepo.findByIndex(0)));
        vooRepo.save(new Voo(aeronaveRepo.findByIndex(1), rotaRepo.findByIndex(1)));
        passageiroRepo.save(new Passageiro("Carlos Eduardo de Souza Coelho Cavaletto", "50301236502", "454545454"));
        passageiroRepo.save(new Passageiro("Gabriel Borsário", "63215454203", "686868686"));
    }
}
