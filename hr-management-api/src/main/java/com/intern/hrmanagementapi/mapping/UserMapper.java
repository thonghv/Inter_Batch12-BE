package com.intern.hrmanagementapi.mapping;

import com.intern.hrmanagementapi.entity.UserEntity;
import com.intern.hrmanagementapi.model.UserAddingDto;
import com.intern.hrmanagementapi.model.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    UserDto userEntityToUserDto(UserEntity userEntity);
    UserEntity userDtoToUserEntity(UserDto userDto);
    List<UserDto> userEntityToUserDto(List<UserEntity> userEntity);
    List<UserEntity> userDtoToUserEntity(List<UserDto> userDto);
    UserEntity addingUserToUserEntity(UserAddingDto userAddingDto);
}
