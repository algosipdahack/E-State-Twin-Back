package com.example.Estate_Twin.user.domain.repository;

import com.example.Estate_Twin.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}
