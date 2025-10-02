package br.com.ciaaerea;

import br.com.ciaaerea.domain.model.Aeronave;
import br.com.ciaaerea.domain.model.Rota;
import br.com.ciaaerea.domain.model.Voo;
import br.com.ciaaerea.repositories.Repository;
import br.com.ciaaerea.repositories.inMemory.AeronaveRepository;
import br.com.ciaaerea.repositories.inMemory.RotaRepository;
import br.com.ciaaerea.repositories.inMemory.VooRepository;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Program {

    private static Repository<Rota> rotaRepo = new RotaRepository();
    private static Repository<Aeronave> aeronaveRepo = new AeronaveRepository();
    private static Repository<Voo> vooRepo = new VooRepository();

    public static void main(String[] args) {
        while (true) {

            inicializarDadosTeste(rotaRepo, aeronaveRepo, vooRepo);

            Scanner scan = new Scanner(System.in);

            String logoString = "\n" +
                    "████████████████████████████████████████████████████████████████████████████████\n" +
                    "█─▄▄▄─█─█─█▄─▄▄─█─▄▄▄─█▄─█─▄█▀▀▀▀▀██▄─▄█▄─▀█▄─▄███▄─▄▄▀█▄─▄▄─███▄─█─▄█─▄▄─█─▄▄─█\n" +
                    "█─███▀█─▄─██─▄█▀█─███▀██─▄▀██████████─███─█▄▀─█████─██─██─▄█▀████▄▀▄██─██─█─██─█\n" +
                    "▀▄▄▄▄▄▀▄▀▄▀▄▄▄▄▄▀▄▄▄▄▄▀▄▄▀▄▄▀▀▀▀▀▀▀▀▄▄▄▀▄▄▄▀▀▄▄▀▀▀▄▄▄▄▀▀▄▄▄▄▄▀▀▀▀▀▄▀▀▀▄▄▄▄▀▄▄▄▄▀" ;

            String opcoesMenuString = String.format("\nCADASTRAR:\n\t%3d - %s\n\t%3d - %s\n\t%3d - %s\nLISTAR:\n\t%3d - %s\n\n-------> ",1,"Rota",2,"Aeronave",3,"Voo",4,"Voo");

            System.out.print(logoString);
            System.out.print(opcoesMenuString);

            String userInput = scan.nextLine().trim();

            switch (userInput) {
                case "1":
                    System.out.print("Digite a origem: ");
                    String origem = scan.nextLine();
                    System.out.print("Digite o destino: ");
                    String destino = scan.nextLine();
                    Rota rota = new Rota(origem, destino);

                    rotaRepo.add(rota);
                    break;
                case "2":
                    System.out.print("Digite o modelo: ");
                    String modelo = scan.nextLine();
                    System.out.print("Digite a capacidade: ");
                    int capacidade = Integer.parseInt(scan.nextLine());
                    Aeronave aeronave = new Aeronave(modelo, capacidade);

                    aeronaveRepo.add(aeronave);
                    break;
                case "3":
                    AtomicInteger idx = new AtomicInteger(1);
                    System.out.println("Selecione a rota:");
                    rotaRepo.findAll().forEach(x -> System.out.printf("%3d - %10s\n", idx.getAndIncrement(), x.toString()));
                    userInput = scan.nextLine().trim();
                    rota = rotaRepo.findByIndex(Integer.parseInt(userInput)-1);

                    idx.set(1);
                    System.out.println("Selecione a aeronave:");
                    aeronaveRepo.findAll().forEach(x -> System.out.printf("%3d - %10s\n", idx.getAndIncrement(), x.toString()));
                    userInput = scan.nextLine().trim();
                    aeronave = aeronaveRepo.findByIndex(Integer.parseInt(userInput)-1);

                    Voo voo = new Voo(aeronave, rota);

                    vooRepo.add(voo);
                    break;
                case "4":
                    idx = new AtomicInteger(1);
                    vooRepo.findAll().forEach(x -> System.out.printf("%3d - %10s\n", idx.getAndIncrement(), x.toString()));
                    break;
            }
        }
    }

    private static void inicializarDadosTeste(Repository<Rota> rotaRepo, Repository<Aeronave> aeronaveRepo, Repository<Voo> vooRepo) {
        rotaRepo.add(new Rota("EUA", "China"));
        rotaRepo.add(new Rota("England", "EUA"));
        aeronaveRepo.add(new Aeronave("Boeing 737", 215));
        aeronaveRepo.add(new Aeronave("Airbus A380", 615));
        vooRepo.add(new Voo(aeronaveRepo.findByIndex(0), rotaRepo.findByIndex(0)));
        vooRepo.add(new Voo(aeronaveRepo.findByIndex(1), rotaRepo.findByIndex(1)));
    }
}
