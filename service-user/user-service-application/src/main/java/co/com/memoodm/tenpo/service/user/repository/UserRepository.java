package co.com.memoodm.tenpo.service.user.repository;

import co.com.memoodm.tenpo.service.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity,Long>
{
    public Optional<UserEntity> findByEmail(String email);
}
