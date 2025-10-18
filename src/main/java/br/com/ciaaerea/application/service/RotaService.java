package br.com.ciaaerea.application.service;

import br.com.ciaaerea.application.command.CadastrarRotaCommand;
import br.com.ciaaerea.application.dto.RotaDTO;
import br.com.ciaaerea.application.mapper.RotaMapper;
import br.com.ciaaerea.domain.model.Rota;
import br.com.ciaaerea.domain.repository.Repository;

import java.util.List;

public class RotaService {
    Repository<Rota> repo;

    public RotaService(Repository<Rota> rotaRepo) {
        this.repo = rotaRepo;
    }

    public void cadastrar(CadastrarRotaCommand cmd){
        Rota rota = new Rota(cmd.origem(), cmd.destino());
        repo.save(rota);
    }

    public List<RotaDTO> findAll() {
         return RotaMapper.INSTANCE.toDTOList(repo.findAll());
    }
}
