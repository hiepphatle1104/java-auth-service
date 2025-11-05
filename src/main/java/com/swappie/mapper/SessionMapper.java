package com.swappie.mapper;

import com.swappie.domain.entities.Session;
import com.swappie.dto.SessionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SessionMapper {
    SessionDTO toDto(Session session);
}
