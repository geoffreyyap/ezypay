package com.ezypay.ezypay;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ezypay.ezypay.controller.SubscriptionController;
import com.ezypay.ezypay.model.SubscriptionReqVO;
import com.ezypay.ezypay.service.SubscriptionService;
import com.google.gson.Gson;

@WebMvcTest(SubscriptionController.class)
class EzypayApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private SubscriptionService subscriptionService;
	
	@Test
	void testCreateSubscription() throws Exception{
		
		SubscriptionReqVO subscriptionReqVO = new SubscriptionReqVO();
		subscriptionReqVO.setAmount(new BigDecimal(10));
		subscriptionReqVO.setDayOfWeek("tuesday");
		subscriptionReqVO.setDateOfMonth(0);
		subscriptionReqVO.setSubscriptionType("weekly");
		subscriptionReqVO.setStartDate("06/02/2018");
		subscriptionReqVO.setEndDate("27/02/2018");
		
		Gson gson = new Gson();
		String subscriptionReqVOString = gson.toJson(subscriptionReqVO);
		
		String expectedResult = "{\"amount\":10,\"subscriptionType\":\"weekly\",\"invoiceDates\":[\"06/02/2018\",\"13/02/2018\",\"13/02/2018\",\"13/02/2018\"]}";
		
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/ezypay/subscription/create")
				.content(subscriptionReqVOString)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult rMvcResult = this.mockMvc.perform(requestBuilder).andReturn();
		
		
		System.out.println(rMvcResult.getResponse().getContentAsString());
		assertEquals(expectedResult, rMvcResult.getResponse().getContentAsString());
		
	
	}
}
