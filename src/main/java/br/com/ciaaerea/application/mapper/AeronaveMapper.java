package br.com.ciaaerea.application.mapper;

import br.com.ciaaerea.application.dto.AeronaveResponseDTO;
import br.com.ciaaerea.domain.model.Aeronave;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "default")
public interface AeronaveMapper {
    AeronaveMapper INSTANCE = Mappers.getMapper(AeronaveMapper.class);
    AeronaveResponseDTO toResponse(Aeronave aeronave);
    List<AeronaveResponseDTO> toDTOList(List<Aeronave> list);
}
