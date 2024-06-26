package com.jwtauth.schoolauthorization.ManagementMapStruct;
import com.jwtauth.schoolauthorization.ManagementDto.MessCreationDto;
import com.jwtauth.schoolauthorization.ManagementDto.MessDto;
import com.jwtauth.schoolauthorization.ManagementDto.MessOwnersDto;
import com.jwtauth.schoolauthorization.ManagementEntity.MessEntity;
import com.jwtauth.schoolauthorization.ManagementEntity.MessOwnerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = MessOwnerMapper.class)
public interface MessMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "contactNumber", source = "contactNumber")
    @Mapping(target = "messType", source = "messType")
    @Mapping(target = "location", source = "location")
    MessEntity toEntity(MessCreationDto messCreationDto);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "contactNumber", source = "contactNumber")
    @Mapping(target = "messType", source = "messType")
    @Mapping(target = "location", source = "location")
    MessDto toDto(MessEntity messEntity);

    List<MessDto> toDtoList(List<MessEntity> messEntities);

    @Mapping(source = "messEntity.id", target = "id")
    @Mapping(source = "messEntity.name", target = "name")
    @Mapping(source = "messOwnerEntities", target = "messOwnerDtos")
    MessOwnersDto toMessOwnersDto(MessEntity messEntity, List<MessOwnerEntity> messOwnerEntities);
}
