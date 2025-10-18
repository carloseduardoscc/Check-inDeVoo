package br.com.ciaaerea.application.service;

import br.com.ciaaerea.application.command.CadastararAeronaveCommand;
import br.com.ciaaerea.application.dto.AeronaveResponseDTO;
import br.com.ciaaerea.application.mapper.AeronaveMapper;
import br.com.ciaaerea.domain.model.Aeronave;
import br.com.ciaaerea.domain.repository.Repository;

import java.util.List;

public class AeronaveService {
    Repository<Aeronave> repo;

    public AeronaveService(Repository<Aeronave> repo) {
        this.repo = repo;
    }

    public void cadastrar(CadastararAeronaveCommand cmd){
        Aeronave aeronave = new Aeronave(cmd.modelo(), cmd.capacidade(), cmd.colunas());
        repo.save(aeronave);
    }

    public List<AeronaveResponseDTO> findAll() {
        return AeronaveMapper.INSTANCE.toDTOList(repo.findAll());
    }
}
