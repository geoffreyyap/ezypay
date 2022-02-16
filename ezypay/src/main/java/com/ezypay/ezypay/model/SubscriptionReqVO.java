package com.ezypay.ezypay.model;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class SubscriptionReqVO {
	
	private BigDecimal amount;
	private String subscriptionType;
	private String dayOfWeek;
	private int dateOfMonth;
	private String startDate;
	private String endDate;
}
