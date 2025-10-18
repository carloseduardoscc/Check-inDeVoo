package br.com.ciaaerea.infra;

import br.com.ciaaerea.application.service.AeronaveService;
import br.com.ciaaerea.application.service.RotaService;
import br.com.ciaaerea.application.service.VooService;
import br.com.ciaaerea.domain.model.Aeronave;
import br.com.ciaaerea.domain.model.Rota;
import br.com.ciaaerea.infra.cli.MenuFactory;
import br.com.ciaaerea.infra.cli.util.model.Menu;
import br.com.ciaaerea.infra.repositories.in_memory.InMemoryRepository;

public class Main {
    public static void main(String[] args) {
        RotaService rotaS = new RotaService(new InMemoryRepository<Rota>());
        AeronaveService aeronaveS = new AeronaveService(new InMemoryRepository<Aeronave>());
        VooService vooS = new VooService(new InMemoryRepository<Rota>());

        MenuFactory mf = new MenuFactory(rotaS);

        Menu mainMenu = mf.create();

        mainMenu.open();

        System.out.println("Saindo do programa...");
    }
}
