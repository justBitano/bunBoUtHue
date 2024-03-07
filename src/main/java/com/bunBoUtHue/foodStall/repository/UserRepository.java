package com.bunBoUtHue.foodStall.repository;

import com.bunBoUtHue.foodStall.enums.UserRole;
import com.bunBoUtHue.foodStall.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findFirstByEmail(String email);

    User findByRole(UserRole admin);

    @Query("select t from User t where t.role = 'ADMIN'")
    User findByRoleAdmin();
}
