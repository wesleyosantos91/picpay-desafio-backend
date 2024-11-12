package io.github.wesleyosantos91.core.mapper;


import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import io.github.wesleyosantos91.api.v1.request.UserQueryRequest;
import io.github.wesleyosantos91.api.v1.request.UserRequest;
import io.github.wesleyosantos91.api.v1.response.UserRespose;
import io.github.wesleyosantos91.domain.entity.UserEntity;
import java.util.ArrayList;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

@Mapper(nullValuePropertyMappingStrategy = IGNORE,
        nullValueCheckStrategy = ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
@Component
public interface UserMapper {

    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    UserEntity toEntity(UserQueryRequest request);

    UserEntity toEntity(UserRequest request);

    UserEntity toEntity(UserRequest request, @MappingTarget UserEntity entity);

    UserRespose toResponse(UserEntity entity);

    default List<UserRespose> toListResponse(List<UserEntity> entities) {
        final List<UserRespose> list = new ArrayList<>();
        entities.forEach(e -> list.add(toResponse(e)));
        return list;
    }

    default Page<UserRespose> toPageResponse(Page<UserEntity> pages) {
        final List<UserRespose> list = toListResponse(pages.getContent());
        return new PageImpl<>(list, pages.getPageable(), pages.getTotalElements());

    }
}
