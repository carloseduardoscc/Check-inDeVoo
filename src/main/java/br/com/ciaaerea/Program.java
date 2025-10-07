package br.com.ciaaerea;

import br.com.ciaaerea.UI.*;
import br.com.ciaaerea.domain.enums.StatusReserva;
import br.com.ciaaerea.domain.exceptions.AssentoIndisponivelException;
import br.com.ciaaerea.domain.model.*;
import br.com.ciaaerea.repositories.Repository;
import br.com.ciaaerea.repositories.inMemory.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
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
                    System.out.print("Digite a número de assentos por fileira: ");
                    int colunas = ConsoleInput.waitUserInteger();
                    Aeronave aeronave = new Aeronave(modelo, capacidade, colunas);
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
