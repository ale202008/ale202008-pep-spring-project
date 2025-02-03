package com.example.repository;
// Adding imports
import org.springframework.stereotype.Repository;
import com.example.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{
}
