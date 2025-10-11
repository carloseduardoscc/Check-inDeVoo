package br.com.ciaaerea.application;

import br.com.ciaaerea.domain.*;
import br.com.ciaaerea.infra.cli.util.ConsoleInput;
import br.com.ciaaerea.infra.cli.util.ConsoleOptions;
import br.com.ciaaerea.infra.cli.util.ConsoleOutput;
import br.com.ciaaerea.infra.cli.util.model.Menu;
import br.com.ciaaerea.infra.cli.util.model.MenuOption;
import br.com.ciaaerea.infra.cli.util.model.MenuOptionType;
import br.com.ciaaerea.infra.repositories.Repository;
import br.com.ciaaerea.infra.repositories.inMemoryImplementation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Program {

    private static Repository<Rota> rotaRepo = new RotaRepository();
    private static Repository<Aeronave> aeronaveRepo = new AeronaveRepository();
    private static Repository<Voo> vooRepo = new VooRepository();
    private static Repository<Passageiro> passageiroRepo = new PassageiroRepository();
    private static Repository<Reserva> reservaRepo = new ReservaRepository();

    public static void main(String[] args) {
        RepositorySeed.seed(rotaRepo, aeronaveRepo, vooRepo, passageiroRepo, reservaRepo);

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
                    Rota rota = new Rota(origem, destino);

                    rotaRepo.save(rota);

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
                    Aeronave aeronave = new Aeronave(modelo, capacidade, colunas);

                    aeronaveRepo.save(aeronave);

                    System.out.println("\nAeronave cadastrada com sucesso!");
                    ConsoleInput.waitUserEnter();

                })
        );
        mainMenu.addOption(
                new MenuOption("Voo", MenuOptionType.CADASTRO, () -> {
                    Rota rota = ConsoleInput.waitUserChoiceFromList(rotaRepo.findAll());
                    Aeronave aeronave = ConsoleInput.waitUserChoiceFromList(aeronaveRepo.findAll());
                    Voo voo = new Voo(aeronave, rota);

                    vooRepo.save(voo);

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

                    System.out.println(assentosToString(vooSelecionado));
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

        mainMenu.open();
        System.out.println("Saindo do programa...");
    }

    private static String assentosToString(Voo voo) {
        StringBuilder sb = new StringBuilder();
        List<List<Assento>> assentos = voo.getAeronave().getAssentos();
        for(List<Assento> fileira: assentos){
            for(Assento assento : fileira){
                List<Assento> assentosOcupados = voo.getReservas().stream().map(Reserva::getAssento).collect(Collectors.toCollection(ArrayList::new));
                if(assentosOcupados.contains(assento)){
                    sb.append(String.format(" [%4s] ","----"));
                }else{
                    sb.append(String.format(" [%4s] ",assento));
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }


}
