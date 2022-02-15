package com.ezypay.ezypay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezypay.ezypay.model.SubscriptionReqVO;
import com.ezypay.ezypay.model.SubscriptionRespVO;
import com.ezypay.ezypay.service.SubscriptionService;

@RestController
@RequestMapping("/ezypay/subscription")
public class SubscriptionController {

	@Autowired
	private SubscriptionService subscriptionService;
	@PostMapping("/create")
	public SubscriptionRespVO createSubscription(@RequestBody SubscriptionReqVO subscriptionReqVO) {
		return subscriptionService.createSubscription(subscriptionReqVO);
	}
}
