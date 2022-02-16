package com.ezypay.ezypay.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezypay.ezypay.model.SubscriptionReqVO;
import com.ezypay.ezypay.model.SubscriptionRespVO;
import com.ezypay.ezypay.service.SubscriptionService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/ezypay/subscription")
public class SubscriptionController {

	private static final SubscriptionService subscriptionService = new SubscriptionService();
	
	@PostMapping("/create")
	public ResponseEntity<SubscriptionRespVO> createSubscription(@RequestBody SubscriptionReqVO subscriptionReqVO) {
		log.info("subscriptionReqVO : {}", subscriptionReqVO);
		SubscriptionRespVO subscriptionRespVO = subscriptionService.createSubscription(subscriptionReqVO);
		log.info("subscriptionRespVO : {}", subscriptionRespVO);
		return new ResponseEntity<SubscriptionRespVO>(subscriptionRespVO, HttpStatus.OK);
	}
}
