package fi.coin.semanticdict.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fi.coin.semanticdict.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserNameOrEmail(String username, String email);

    boolean existsByUserName(String username);

    boolean existsByEmail(String email);
}