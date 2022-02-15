package com.bank.component.user.repository;

import java.util.Optional;

import com.bank.component.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
	// Optional<AppUser> findTopByUserId(String userId);

}
