package br.com.ciaaerea.application.mapper;

import br.com.ciaaerea.application.dto.RotaDTO;
import br.com.ciaaerea.domain.model.Rota;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "default")
public interface RotaMapper {
    RotaMapper INSTANCE = Mappers.getMapper(RotaMapper.class);
    RotaDTO toDTO(Rota rota);
    List<RotaDTO> toDTOList(List<Rota> list);
}
