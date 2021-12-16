package com.miracle.user.repository;

import com.miracle.user.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @Author sqq
 * @Date 2021-12-10 16:38:54
 */
@Repository
public interface UserRepository extends JpaRepository<User, String>,
    JpaSpecificationExecutor<User> {

  Optional<User> findFirstByName(String name);

}
