package com.bank.component.transaction.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.component.transaction.domain.ReturnRequest;
import com.bank.component.user.domain.AppUser;

public interface ReturnRequestRepository extends JpaRepository<ReturnRequest, Long> {

	Optional<ReturnRequest> findTopById(Long id);

	List<ReturnRequest> findAllBySentUserOrderByCreatedAt(AppUser sentUser);

	List<ReturnRequest> findAllByReceivedUserOrderByCreatedAt(AppUser receivedUser);
}
