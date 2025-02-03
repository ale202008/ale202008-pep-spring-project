package com.example.repository;
// Adding imports
import org.springframework.stereotype.Repository;
import com.example.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.Optional;



@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{
    @Modifying
    @Transactional
    @Query("UPDATE Message msg SET msg.messageText = :messageText WHERE msg.id = :id")
    void updateMessageById(@Param("id") Integer id, @Param("messageText") String messageText);
}
