package br.com.ciaaerea.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Assento {
    private final String fileira;
    private final String coluna;

    public static List<List<Assento>> gerarLayoutDeAssentos(int capacidade, int colunas) {
        int fileirasInteiras = capacidade / colunas;
        int assentosRestantes = capacidade % colunas;
        int fileiras = fileirasInteiras + Math.min(1, assentosRestantes);

        List<List<Assento>> assentos = new ArrayList<>(fileiras);

        for (int i = 0; i < fileiras; i++){
            assentos.add(new ArrayList<>(colunas));
        }

        for (int f = 0; f < fileiras; f++){
            for (int c = 0; c < ( f == fileiras-1 && assentosRestantes != 0 ? assentosRestantes : colunas ); c++){
                String fileiraFormatado = Integer.toString(f+1);
                String colunaFormatado = Character.toString((char) (65+c));

                assentos.get(f).add(c, new Assento(fileiraFormatado, colunaFormatado));
            }
        }

        return assentos;
    }

    public Assento(String fileira, String coluna) {
        this.fileira = fileira;
        this.coluna = coluna;
    }

    public String getCode(){
        return String.format("%s%s",fileira,coluna);
    }

    public String getFileira() {
        return fileira;
    }

    public String getColuna() {
        return coluna;
    }

    @Override
    public String toString() {
        return getCode();
    }
}
