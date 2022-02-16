package com.ezypay.ezypay.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ezypay.ezypay.model.SubscriptionReqVO;
import com.ezypay.ezypay.model.SubscriptionRespVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SubscriptionService {
	
	private DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public SubscriptionRespVO createSubscription(SubscriptionReqVO subscriptionReqVO) {
		SubscriptionRespVO subscriptionRespVO = new SubscriptionRespVO();
		subscriptionRespVO.setAmount(subscriptionReqVO.getAmount());
		subscriptionRespVO.setSubscriptionType(subscriptionReqVO.getSubscriptionType());
		subscriptionRespVO.setInvoiceDates(this.getInvoiceDates(subscriptionReqVO.getSubscriptionType(), subscriptionReqVO.getStartDate(), subscriptionReqVO.getEndDate()));
		log.info("subscriptionRespVO value : {}", subscriptionRespVO);
		return subscriptionRespVO;
	}

	private List<String> getInvoiceDates(String subscriptionType, String startDate, String endDate) {
		
		LocalDate startLocalDate = LocalDate.parse(startDate, myFormatObj);
		LocalDate endLocalDate = LocalDate.parse(endDate, myFormatObj);
		
		log.info("startLocalDate : {}", startLocalDate);
		log.info("endLocalDate : {}", endLocalDate);
		
		List<String> invoiceDates = new ArrayList<String>();
		
		if (subscriptionType.equalsIgnoreCase("DAILY")) {
			invoiceDates.add(String.valueOf(startLocalDate.format(myFormatObj)));
			return invoiceDates;
		}
		
		if (subscriptionType.equalsIgnoreCase("WEEKLY")) {
			
			invoiceDates.add(String.valueOf(startLocalDate.format(myFormatObj)));
			
			long numberOfWeeks = ChronoUnit.WEEKS.between(startLocalDate, endLocalDate);
			
			for (int i = 0; i < numberOfWeeks; i++) {
				LocalDate newDate = startLocalDate.plusWeeks(1L);
				invoiceDates.add(String.valueOf(newDate.format(myFormatObj)));
			}
			return invoiceDates;
			
		}
		
		if (subscriptionType.equalsIgnoreCase("MONTHLY")) {
			invoiceDates.add(String.valueOf(startLocalDate.format(myFormatObj)));
			
			long numberOfMonths = ChronoUnit.MONTHS.between(startLocalDate, endLocalDate);
			
			for (int i = 0; i < numberOfMonths; i++) {
				LocalDate newDate = startLocalDate.plusMonths(1L);
				invoiceDates.add(String.valueOf(newDate.format(myFormatObj)));
			}
			return invoiceDates;
			
		}
		
		return new ArrayList<String>();
	}
}
