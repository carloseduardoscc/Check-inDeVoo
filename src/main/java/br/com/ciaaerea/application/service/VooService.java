package br.com.ciaaerea.application.service;

import br.com.ciaaerea.application.command.CadastararVooCommand;
import br.com.ciaaerea.domain.model.Voo;
import br.com.ciaaerea.domain.repository.Repository;

public class VooService {
    Repository<Voo> repo;

    public VooService(Repository<Voo> repo) {
        this.repo = repo;
    }
    
    public void cadastrar(CadastararVooCommand cmd){

    }
}
