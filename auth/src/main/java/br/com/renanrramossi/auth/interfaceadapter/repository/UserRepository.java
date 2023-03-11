package br.com.renanrramossi.auth.interfaceadapter.repository;

import br.com.renanrramossi.auth.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

  @Query("SELECT u FROM User u WHERE u.username =: username")
  User findByUserName(@Param("username") final String username);
}
