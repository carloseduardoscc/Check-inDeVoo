package br.com.ciaaerea.application;

import br.com.ciaaerea.application.service.RotaService;
import br.com.ciaaerea.infra.cli.MenuFactory;
import br.com.ciaaerea.infra.cli.util.model.Menu;

public class Program {

    private static final RotaRepository rotaRepo = new RotaRepository();
    private static final AeronaveRepository aeronaveRepo = new AeronaveRepository();
    private static final VooRepository vooRepo = new VooRepository();
    private static final PassageiroRepository passageiroRepo = new PassageiroRepository();
    private static final ReservaRepository reservaRepo = new ReservaRepository();

    private static final RotaService rotaService = new RotaService(rotaRepo);

    public static void main(String[] args) {
//        vai pra configuração de perfil
//        RepositorySeed.seed(rotaRepo, aeronaveRepo, vooRepo, passageiroRepo, reservaRepo);

        MenuFactory mf = new MenuFactory(rotaService);

        Menu mainMenu = mf.create();

        mainMenu.open();

        System.out.println("Saindo do programa...");
    }




}
