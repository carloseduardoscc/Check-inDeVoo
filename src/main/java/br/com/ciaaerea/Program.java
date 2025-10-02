package br.com.ciaaerea;

import br.com.ciaaerea.domain.model.Aeronave;
import br.com.ciaaerea.domain.model.Passageiro;
import br.com.ciaaerea.domain.model.Rota;
import br.com.ciaaerea.repositories.Repository;
import br.com.ciaaerea.repositories.inMemory.RotaRepository;

import java.util.Scanner;

public class Program {

    private static Repository<Rota> rotaRepo = new RotaRepository();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String logoString = "\n" +
                "████████████████████████████████████████████████████████████████████████████████\n" +
                "█─▄▄▄─█─█─█▄─▄▄─█─▄▄▄─█▄─█─▄█▀▀▀▀▀██▄─▄█▄─▀█▄─▄███▄─▄▄▀█▄─▄▄─███▄─█─▄█─▄▄─█─▄▄─█\n" +
                "█─███▀█─▄─██─▄█▀█─███▀██─▄▀██████████─███─█▄▀─█████─██─██─▄█▀████▄▀▄██─██─█─██─█\n" +
                "▀▄▄▄▄▄▀▄▀▄▀▄▄▄▄▄▀▄▄▄▄▄▀▄▄▀▄▄▀▀▀▀▀▀▀▀▄▄▄▀▄▄▄▀▀▄▄▀▀▀▄▄▄▄▀▀▄▄▄▄▄▀▀▀▀▀▄▀▀▀▄▄▄▄▀▄▄▄▄▀";

        String opcoesMenuString = "\n1 - Cadastrar Rota\n" +
                "-> ";

        System.out.print(logoString);
        System.out.print(opcoesMenuString);

        String userInput = scan.nextLine().trim();

        switch (userInput){
            case "1":
                System.out.print("Digite a origem: ");
                String origem = scan.nextLine();
                System.out.print("Digite o destino: ");
                String destino = scan.nextLine();
                Rota rota = new Rota(origem, destino);

                rotaRepo.add(rota);
                break;
        }
    }
}
