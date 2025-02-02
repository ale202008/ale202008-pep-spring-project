package com.example.repository;
// Adding imports
import org.springframework.stereotype.Repository;
import com.example.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
    // Created custom query to find Account record via username 
    Optional<Account> findByUsername(String username);
    Optional<Account> findByUsernameAndPassword(String username, String password);
}
