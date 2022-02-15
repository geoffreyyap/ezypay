package com.ezypay.ezypay.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class SubscriptionRespVO {
	private BigDecimal amount;
	private String subscriptionType;
	private List<String> invoiceDates = new ArrayList<>();
}
