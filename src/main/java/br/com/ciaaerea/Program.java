package br.com.ciaaerea;

import br.com.ciaaerea.UI.Menu;
import br.com.ciaaerea.UI.MenuOption;
import br.com.ciaaerea.UI.MenuOptionType;
import br.com.ciaaerea.UI.ConsoleInput;
import br.com.ciaaerea.domain.model.Aeronave;
import br.com.ciaaerea.domain.model.Rota;
import br.com.ciaaerea.domain.model.Voo;
import br.com.ciaaerea.repositories.Repository;
import br.com.ciaaerea.repositories.inMemory.AeronaveRepository;
import br.com.ciaaerea.repositories.inMemory.RepositorySeed;
import br.com.ciaaerea.repositories.inMemory.RotaRepository;
import br.com.ciaaerea.repositories.inMemory.VooRepository;

import java.util.concurrent.atomic.AtomicInteger;

public class Program {

    private static Repository<Rota> rotaRepo = new RotaRepository();
    private static Repository<Aeronave> aeronaveRepo = new AeronaveRepository();
    private static Repository<Voo> vooRepo = new VooRepository();

    public static void main(String[] args) {
        RepositorySeed.seed(rotaRepo, aeronaveRepo, vooRepo);

        String menuLogo = "\n" +
                "████████████████████████████████████████████████████████████████████████████████\n" +
                "█─▄▄▄─█─█─█▄─▄▄─█─▄▄▄─█▄─█─▄█▀▀▀▀▀██▄─▄█▄─▀█▄─▄███▄─▄▄▀█▄─▄▄─███▄─█─▄█─▄▄─█─▄▄─█\n" +
                "█─███▀█─▄─██─▄█▀█─███▀██─▄▀██████████─███─█▄▀─█████─██─██─▄█▀████▄▀▄██─██─█─██─█\n" +
                "▀▄▄▄▄▄▀▄▀▄▀▄▄▄▄▄▀▄▄▄▄▄▀▄▄▀▄▄▀▀▀▀▀▀▀▀▄▄▄▀▄▄▄▀▀▄▄▀▀▀▄▄▄▄▀▀▄▄▄▄▄▀▀▀▀▀▄▀▀▀▄▄▄▄▀▄▄▄▄▀";

        Menu mainMenu = new Menu(menuLogo);

        mainMenu.addOption(
                new MenuOption("Rota", MenuOptionType.CADASTRAR, () -> {
                    System.out.print("Digite a origem: ");
                    String origem = ConsoleInput.waitUserString();
                    System.out.print("Digite o destino: ");
                    String destino = ConsoleInput.waitUserString();
                    Rota rota = new Rota(origem, destino);
                    System.out.println("\nRota cadastrada com sucesso!");
                    ConsoleInput.waitUserEnter();

                    rotaRepo.add(rota);
                })
        );
        mainMenu.addOption(
                new MenuOption("Aeronave", MenuOptionType.CADASTRAR, () -> {
                    System.out.print("Digite o modelo: ");
                    String modelo = ConsoleInput.waitUserString();
                    System.out.print("Digite a capacidade: ");
                    int capacidade = ConsoleInput.waitUserInteger();
                    Aeronave aeronave = new Aeronave(modelo, capacidade);
                    System.out.println("\nAeronave cadastrada com sucesso!");
                    ConsoleInput.waitUserEnter();

                    aeronaveRepo.add(aeronave);
                })
        );
        mainMenu.addOption(
                new MenuOption("Voo", MenuOptionType.CADASTRAR, () -> {
                    AtomicInteger idx = new AtomicInteger(1);
                    System.out.println("Selecione a rota:");
                    rotaRepo.findAll().forEach(x -> System.out.printf("%3d - %10s\n", idx.getAndIncrement(), x.toString()));
                    Rota rota = rotaRepo.findByIndex(ConsoleInput.waitUserInteger() - 1);

                    idx.set(1);
                    System.out.println("Selecione a aeronave:");
                    aeronaveRepo.findAll().forEach(x -> System.out.printf("%3d - %10s\n", idx.getAndIncrement(), x.toString()));
                    Aeronave aeronave = aeronaveRepo.findByIndex(ConsoleInput.waitUserInteger() - 1);

                    System.out.println("\nVoo cadastrado com sucesso!");
                    ConsoleInput.waitUserEnter();

                    Voo voo = new Voo(aeronave, rota);

                    vooRepo.add(voo);
                })
        );
        mainMenu.addOption(
                new MenuOption("Voo", MenuOptionType.LISTAR, () -> {
                    AtomicInteger idx = new AtomicInteger(1);
                    vooRepo.findAll().forEach(x -> System.out.printf("%3d - %10s\n\n", idx.getAndIncrement(), x.toString()));
                    ConsoleInput.waitUserEnter();

                })
        );

        mainMenu.open();
        System.out.println("Saindo do programa...");
    }


}
