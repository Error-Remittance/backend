package com.bank.api.transaction.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.api.user.service.FCMService;
import com.bank.component.account.vo.GetReceivedReturnRequestsResponseVo;
import com.bank.component.transaction.repository.TransactionRepository;
import com.bank.component.transaction.vo.GetSentReturnRequestsResponseVo;
import com.bank.component.transaction.vo.MakeReturnRequestRequestVo;
import com.bank.component.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReturnRequestService {
	private final FCMService fcmService;
	private final UserRepository userRepository;
	private final TransactionRepository transactionRepository;
	// private final ReturnRequestRepository returnRequestRepository;

	@Transactional
	public void makeReturnRequest(final MakeReturnRequestRequestVo requestVo) throws IOException {

	}

	@Transactional(readOnly = true)
	public GetReceivedReturnRequestsResponseVo getReceivedReturnRequest(final String userId) {
		return null;
	}

	@Transactional(readOnly = true)
	public GetSentReturnRequestsResponseVo getSentReturnRequest(final String userId) {
		return null;
	}

	@Transactional
	public void acceptReturnRequest(final Long returnRequestId) throws IOException {

	}

	@Transactional
	public void reportReturnRequest(final Long returnRequestId) throws IOException {
	}

	// @Transactional
	// public Void makeReturnRequest(final MakeReturnRequestRequestVo requestVo) throws IOException {
	//
	// 	final Optional<Transaction> transactionOptional = transactionRepository.findTopById(
	// 		requestVo.getTransactionId());
	//
	// 	final Transaction transaction = transactionOptional.orElseThrow(() ->
	// 		new CommonException(ResponseCode.TRANSACTION_NOT_EXISTED));
	//
	// 	if (transaction.isRequested()) {
	// 		throw new CommonException(ResponseCode.RETURN_REQUEST_ALREADY_EXISTED);
	// 	}
	//
	// 	final ReturnRequest returnRequest = new ReturnRequest(requestVo.getMessage(), requestVo.getReason(), false,
	// 		false,
	// 		transaction.getSentAccount().getUser(), transaction.getReceivedAccount().getUser(),
	// 		transaction.getSentAccount(), transaction.getReceivedAccount(), transaction);
	//
	// 	returnRequestRepository.save(returnRequest);
	//
	// 	if (returnRequest.getReceivedUser().hasToken()) {
	// 		try {
	// 			fcmService.sendMessageTo(returnRequest.getReceivedUser().getFcmToken().getToken(), "??????????????? ?????? ?????? ??????",
	// 				transaction.getSenderName() + "?????? ??????????????? ????????? ?????????????????????.");
	// 		} catch (Exception e) {
	// 			//????????? ??????
	// 			e.printStackTrace(); //?????? ??????(????????? ????????????)
	// 			throw e; //????????? ???????????? ???????????? ????????? ????????????
	// 		} finally {
	// 			//????????? ??????
	// 			return null;
	// 		}
	// 	}
	//
	// 	return null;
	// }
	//
	// @Transactional(readOnly = true)
	// public GetReceivedReturnRequestsResponseVo getReceivedReturnRequest(final String userId) {
	// 	final Optional<AppUser> userOptional =
	// 		userRepository.findTopByUserId(userId);
	//
	// 	final AppUser user =
	// 		userOptional.orElseThrow(() -> new CommonException(ResponseCode.USER_NOT_EXISTED));
	//
	// 	final List<ReturnRequest> receivedReturnRequests =
	// 		returnRequestRepository.findAllByReceivedUserOrderByCreatedAt(user);
	//
	// 	final List<ReturnRequestVo> returnRequestVoList = ReturnRequest.toVoList(receivedReturnRequests);
	//
	// 	return GetReceivedReturnRequestsResponseVo.builder().receivedReturnRequestList(returnRequestVoList).build();
	// }
	//
	// @Transactional(readOnly = true)
	// public GetSentReturnRequestsResponseVo getSentReturnRequest(final String userId) {
	// 	final Optional<AppUser> userOptional =
	// 		userRepository.findTopByUserId(userId);
	//
	// 	final AppUser user =
	// 		userOptional.orElseThrow(() -> new CommonException(ResponseCode.USER_NOT_EXISTED));
	//
	// 	final List<ReturnRequest> sentReturnRequests =
	// 		returnRequestRepository.findAllBySentUserOrderByCreatedAt(user);
	//
	// 	final List<ReturnRequestVo> returnRequestVoList = ReturnRequest.toVoList(sentReturnRequests);
	//
	// 	return GetSentReturnRequestsResponseVo.builder().sentReturnRequestList(returnRequestVoList).build();
	// }
	//
	// @Transactional
	// public Void acceptReturnRequest(final Long returnRequestId) throws IOException {
	//
	// 	final Optional<ReturnRequest> returnRequestOptional = returnRequestRepository.findTopById(returnRequestId);
	//
	// 	final ReturnRequest returnRequest = returnRequestOptional.orElseThrow(() ->
	// 		new CommonException(ResponseCode.RETURN_REQUEST_NOT_EXISTED));
	//
	// 	returnRequest.accept();
	//
	// 	final Transaction transaction = new Transaction(LocalDateTime.now(),
	// 		returnRequest.getTransaction().getReceiverName(), returnRequest.getTransaction().getSenderName(),
	// 		"??????????????????", "??????????????????", returnRequest.getTransaction().getAmount(),
	// 		returnRequest.getReceivedAccount(), returnRequest.getSentAccount());
	//
	// 	transactionRepository.save(transaction);
	//
	// 	return null;
	// }
	//
	// /**
	//  * Return request??? ??????????????????? ??????
	//  * @param returnRequestId
	//  * @return
	//  * @throws IOException
	//  */
	// @Transactional
	// public Void reportReturnRequest(final Long returnRequestId) throws IOException {
	//
	// 	final Optional<ReturnRequest> returnRequestOptional = returnRequestRepository.findTopById(returnRequestId);
	//
	// 	final ReturnRequest returnRequest = returnRequestOptional.orElseThrow(() ->
	// 		new CommonException(ResponseCode.RETURN_REQUEST_NOT_EXISTED));
	//
	// 	returnRequest.report();
	//
	// 	return null;
	// }
}
