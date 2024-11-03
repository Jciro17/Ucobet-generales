package co.edu.uco.UcoBet.generales.application.primaryports.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import co.edu.uco.UcoBet.generales.application.primaryports.dto.EmailDataDto;
import co.edu.uco.UcoBet.generales.domain.EmailData;

@Mapper(componentModel = "spring")
public interface EmailDataMapper {
	
    EmailDataMapper INSTANCE = Mappers.getMapper(EmailDataMapper.class);
    EmailData toDomain(EmailDataDto dto);
}
