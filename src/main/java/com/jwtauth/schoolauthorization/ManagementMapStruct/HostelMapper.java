package com.jwtauth.schoolauthorization.ManagementMapStruct;
import com.jwtauth.schoolauthorization.ManagementDto.HostelCreationDto;
import com.jwtauth.schoolauthorization.ManagementDto.HostelDto;
import com.jwtauth.schoolauthorization.ManagementEntity.HostelEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HostelMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "hostelType", source = "hostelType")
    @Mapping(target = "location", source = "location")
    HostelEntity toEntity(HostelCreationDto hostelCreationDto);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "hostelType", source = "hostelType")
    @Mapping(target = "location", source = "location")
    HostelDto toDto(HostelEntity hostelEntity);

    List<HostelDto> toDtoList(List<HostelEntity> hostelEntities);
}
