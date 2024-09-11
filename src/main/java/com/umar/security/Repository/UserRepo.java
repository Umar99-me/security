package com.umar.security.Repository;

// import org.hibernate.mapping.List;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.umar.security.Entity.User;

public interface UserRepo extends JpaRepository<User,Long>{

    List<User> findByname(String username);
}
