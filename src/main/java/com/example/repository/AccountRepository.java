package com.example.repository;
// Adding imports
import org.springframework.stereotype.Repository;
import com.example.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>  {
}
