package com.bank.component.user.repository;

import com.bank.component.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	// Optional<AppUser> findTopByUserId(String userId);
}
