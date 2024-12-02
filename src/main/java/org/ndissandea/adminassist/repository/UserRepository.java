package org.ndissandea.adminassist.repository;

import org.ndissandea.adminassist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String userName);
    User findUserByUserName(String userName);


}
