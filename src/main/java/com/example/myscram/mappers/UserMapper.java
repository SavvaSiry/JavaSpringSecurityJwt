package com.example.myscram.mappers;

import com.example.myscram.entity.User;
import com.example.myscram.model.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "name", source = "username")
    UserDto toUserDto(User user);

}
