package bepicky.userservice.repository;

import bepicky.userservice.entity.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SystemUserRepository extends JpaRepository<SystemUser, Long> {

    List<SystemUser> findByChatId(Long chatId);
}
