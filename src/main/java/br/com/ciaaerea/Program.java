package br.com.ciaaerea;

import br.com.ciaaerea.domain.model.Aeronave;
import br.com.ciaaerea.domain.model.Passageiro;
import br.com.ciaaerea.domain.model.Rota;

public class Program {
    public static void main(String[] args) {
        Passageiro p = new Passageiro("Ana", "123", "RG");
        Aeronave a = new Aeronave("Boeing 737", 3);
        Rota r = new Rota("GRU", "SSA");

        System.out.println(p.getNome());
        System.out.println(a.getCapacidade());
        System.out.println(r.origem());
    }
}
