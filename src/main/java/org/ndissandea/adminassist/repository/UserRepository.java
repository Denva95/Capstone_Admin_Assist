package org.ndissandea.adminassist.repository;

import org.ndissandea.adminassist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
   // User findUserByUserName(String userName);
    User findByUserName(String userName);

}
