package co.com.memoodm.tenpo.service.user.mapper;

import co.com.memoodm.tenpo.service.user.web.client.User;
import co.com.memoodm.tenpo.service.user.entity.UserEntity;

import java.util.Optional;

public class UserMapper
{

    public static User entityToDto(UserEntity entity)
    {
        return User.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .password("********")
                .created( Optional.ofNullable(entity.getCreated())
                        .map( e -> e.toString())
                        .orElse(null) )
                .build();
    }

    public static UserEntity dtoToEntity(User dto)
    {
        return UserEntity.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .build();
    }

}
