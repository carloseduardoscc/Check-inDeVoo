package br.com.ciaaerea.infra.cli;

import br.com.ciaaerea.application.command.CadastararAeronaveCommand;
import br.com.ciaaerea.application.command.CadastararVooCommand;
import br.com.ciaaerea.application.command.CadastrarRotaCommand;
import br.com.ciaaerea.application.dto.AeronaveResponseDTO;
import br.com.ciaaerea.application.dto.RotaDTO;
import br.com.ciaaerea.application.service.AeronaveService;
import br.com.ciaaerea.application.service.RotaService;
import br.com.ciaaerea.application.service.VooService;
import br.com.ciaaerea.domain.model.Assento;
import br.com.ciaaerea.domain.model.Passageiro;
import br.com.ciaaerea.domain.model.Reserva;
import br.com.ciaaerea.domain.model.Voo;
import br.com.ciaaerea.infra.cli.util.ConsoleInput;
import br.com.ciaaerea.infra.cli.util.ConsoleOptions;
import br.com.ciaaerea.infra.cli.util.ConsoleOutput;
import br.com.ciaaerea.infra.cli.util.model.Menu;
import br.com.ciaaerea.infra.cli.util.model.MenuOption;
import br.com.ciaaerea.infra.cli.util.model.MenuOptionType;

public class MenuFactory {

    RotaService rotaService;
    AeronaveService aeronaveService;
    VooService vooService;

    public MenuFactory(RotaService rotaService, AeronaveService aeronaveService, VooService vooService) {
        this.rotaService = rotaService;
        this.aeronaveService = aeronaveService;
        this.vooService = vooService;
    }

    public Menu create(){
        String menuLogo = "\n" +
                "████████████████████████████████████████████████████████████████████████████████\n" +
                "█─▄▄▄─█─█─█▄─▄▄─█─▄▄▄─█▄─█─▄█▀▀▀▀▀██▄─▄█▄─▀█▄─▄███▄─▄▄▀█▄─▄▄─███▄─█─▄█─▄▄─█─▄▄─█\n" +
                "█─███▀█─▄─██─▄█▀█─███▀██─▄▀██████████─███─█▄▀─█████─██─██─▄█▀████▄▀▄██─██─█─██─█\n" +
                "▀▄▄▄▄▄▀▄▀▄▀▄▄▄▄▄▀▄▄▄▄▄▀▄▄▀▄▄▀▀▀▀▀▀▀▀▄▄▄▀▄▄▄▀▀▄▄▀▀▀▄▄▄▄▀▀▄▄▄▄▄▀▀▀▀▀▄▀▀▀▄▄▄▄▀▄▄▄▄▀";

        Menu mainMenu = new Menu(menuLogo);

        mainMenu.addOption(
                new MenuOption("Rota", MenuOptionType.CADASTRO, () -> {
                    System.out.print("Digite a origem: ");
                    String origem = ConsoleInput.waitUserString();
                    System.out.print("Digite o destino: ");
                    String destino = ConsoleInput.waitUserString();

                    rotaService.cadastrar(new CadastrarRotaCommand(origem, destino));

                    System.out.println("\nRota cadastrada com sucesso!");
                    ConsoleInput.waitUserEnter();
                })
        );
        mainMenu.addOption(
                new MenuOption("Aeronave", MenuOptionType.CADASTRO, () -> {
                    System.out.print("Digite o modelo: ");
                    String modelo = ConsoleInput.waitUserString();
                    System.out.print("Digite a capacidade: ");
                    int capacidade = ConsoleInput.waitUserInteger();
                    System.out.print("Digite a número de assentos por fileira: ");
                    int colunas = ConsoleInput.waitUserInteger();

                    aeronaveService.cadastrar(new CadastararAeronaveCommand(modelo, capacidade, colunas));

                    System.out.println("\nAeronave cadastrada com sucesso!");
                    ConsoleInput.waitUserEnter();

                })
        );
        mainMenu.addOption(
                new MenuOption("Voo", MenuOptionType.CADASTRO, () -> {
                    RotaDTO rota = ConsoleInput.waitUserChoiceFromList(rotaService.findAll());
                    AeronaveResponseDTO aeronave = ConsoleInput.waitUserChoiceFromList(aeronaveService.findAll());

                    vooService.cadastrar(new CadastararVooCommand());

                    System.out.println("\nVoo cadastrado com sucesso!");
                    ConsoleInput.waitUserEnter();
                })
        );
        mainMenu.addOption(
                new MenuOption("Passageiro", MenuOptionType.CADASTRO, () -> {
                    System.out.print("Digite o nome: ");
                    String nome = ConsoleInput.waitUserString(true);
                    System.out.print("Digite o CPF: ");
                    String cpf = ConsoleInput.waitUserString(true);
                    System.out.print("Digite um documento válido: ");
                    String documento = ConsoleInput.waitUserString(true);
                    Passageiro passageiro = new Passageiro(nome, cpf, documento);

                    passageiroRepo.save(passageiro);

                    System.out.println("\nPassageiro cadastrado com sucesso!");
                    ConsoleInput.waitUserEnter();
                })
        );
        mainMenu.addOption(
                new MenuOption("Nova reserva", MenuOptionType.RESERVAS, () -> {
                    Voo vooSelecionado = ConsoleInput.waitUserChoiceFromList(vooRepo.findAll(), "Selecione o voo");

                    ConsoleOptions.clearConsole();
                    Passageiro passageiroSelecionado = ConsoleInput.waitUserChoiceFromList(passageiroRepo.findAll(), "Selecione o passageiro");
                    ConsoleOptions.clearConsole();

                    System.out.println(vooSelecionado.assentosToString());
                    System.out.print("Selecione o assento: ");
                    Assento assentoSelecionado = vooSelecionado.getAeronave().findAssentoByCode(ConsoleInput.waitUserString());


                    Reserva novaReserva = new Reserva(passageiroSelecionado, assentoSelecionado);
                    reservaRepo.save(novaReserva);

                    vooSelecionado.addReserva(novaReserva);


                    System.out.println("\nNova reserva cadastrada com sucesso!");
                    ConsoleInput.waitUserEnter();
                })
        );
        mainMenu.addOption(
                new MenuOption("Rotas", MenuOptionType.VISUALIZAR, () -> {
                    ConsoleOutput.printList(rotaRepo.findAll(), "Rotas cadastradas");
                    ConsoleInput.waitUserEnter();
                })
        );
        mainMenu.addOption(
                new MenuOption("Aeronaves", MenuOptionType.VISUALIZAR, () -> {
                    ConsoleOutput.printList(aeronaveRepo.findAll(), "Aeronaves cadastradas");
                    ConsoleInput.waitUserEnter();
                })
        );
        mainMenu.addOption(
                new MenuOption("Voos", MenuOptionType.VISUALIZAR, () -> {
                    ConsoleOutput.printList(vooRepo.findAll(), "Voos cadastrados");
                    ConsoleInput.waitUserEnter();
                })
        );
        mainMenu.addOption(
                new MenuOption("Passageiros", MenuOptionType.VISUALIZAR, () -> {
                    ConsoleOutput.printList(passageiroRepo.findAll(), "Passageiros cadastrados");
                    ConsoleInput.waitUserEnter();
                })
        );
    }
}
