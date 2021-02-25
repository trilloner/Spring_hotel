package com.example.hotel.mapper;

import com.example.hotel.dto.UserRegistrationDto;
import com.example.hotel.models.User;
import org.mapstruct.Mapper;
/**
 * Mapper to convert dto to model
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserRegistrationDto toDTO(User user);

    User toEntity(UserRegistrationDto userDTO);

}
