package br.com.ciaaerea;

import br.com.ciaaerea.UI.ConsoleInput;
import br.com.ciaaerea.UI.Menu;
import br.com.ciaaerea.UI.MenuOption;
import br.com.ciaaerea.UI.MenuOptionType;
import br.com.ciaaerea.domain.model.*;
import br.com.ciaaerea.repositories.Repository;
import br.com.ciaaerea.repositories.inMemory.*;

import java.util.concurrent.atomic.AtomicInteger;

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
                    System.out.println("\nRota cadastrada com sucesso!");
                    ConsoleInput.waitUserEnter();

                    rotaRepo.save(rota);
                })
        );
        mainMenu.addOption(
                new MenuOption("Aeronave", MenuOptionType.CADASTRO, () -> {
                    System.out.print("Digite o modelo: ");
                    String modelo = ConsoleInput.waitUserString();
                    System.out.print("Digite a capacidade: ");
                    int capacidade = ConsoleInput.waitUserInteger();
                    Aeronave aeronave = new Aeronave(modelo, capacidade);
                    System.out.println("\nAeronave cadastrada com sucesso!");
                    ConsoleInput.waitUserEnter();

                    aeronaveRepo.save(aeronave);
                })
        );
        mainMenu.addOption(
                new MenuOption("Voo", MenuOptionType.CADASTRO, () -> {
                    Rota rota = ConsoleInput.waitUserChoiceFromList(rotaRepo.findAll());
                    Aeronave aeronave = ConsoleInput.waitUserChoiceFromList(aeronaveRepo.findAll());
                    System.out.println("\nVoo cadastrado com sucesso!");
                    ConsoleInput.waitUserEnter();

                    Voo voo = new Voo(aeronave, rota);

                    vooRepo.save(voo);
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
                    System.out.println("\nPassageiro cadastrado com sucesso!");

                    passageiroRepo.save(passageiro);
                })
        );
        mainMenu.addOption(
                new MenuOption("Nova reserva", MenuOptionType.RESERVAS, () -> {
                    System.out.println("Selecione o voo:");
                    Voo vooSelecionado = ConsoleInput.waitUserChoiceFromList(vooRepo.findAll());
                    Passageiro passageiroSelecionado = ConsoleInput.waitUserChoiceFromList(passageiroRepo.findAll());
                    Reserva novaReserva = new Reserva(passageiroSelecionado, vooSelecionado);
                    System.out.println("\nNova reserva cadastrada com sucesso!");

                    reservaRepo.save(novaReserva);
                })
        );
        mainMenu.addOption(
                new MenuOption("Voos disponíveis", MenuOptionType.VISUALIZAR, () -> {
                    AtomicInteger idx = new AtomicInteger(1);
                    vooRepo.findAll().forEach(x -> System.out.printf("%3d - %10s\n\n", idx.getAndIncrement(), x.toString()));
                    ConsoleInput.waitUserEnter();

                })
        );

        mainMenu.open();
        System.out.println("Saindo do programa...");
    }


}
