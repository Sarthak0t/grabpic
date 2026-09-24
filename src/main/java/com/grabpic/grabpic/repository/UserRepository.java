package com.grabpic.grabpic.repository;

import com.grabpic.grabpic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}
