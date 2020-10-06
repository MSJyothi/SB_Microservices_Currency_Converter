package com.jocodes.microservices.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.jocodes.microservices.currencyconversionservice.bean.CurrencyConvertionBean;
import com.jocodes.microservices.currencyconversionservice.proxy.CurrencyExchangeServiceProxy;

@RestController
public class CurrencyConverterController {
	
	public Logger logger= LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CurrencyExchangeServiceProxy proxy;

	@GetMapping("/currency-converter-rest/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConvertionBean convertCurrencyRest(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {
		
		Map<String,String> uriVariables= new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		
		ResponseEntity<CurrencyConvertionBean> responseEntity= new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
				CurrencyConvertionBean.class,
				uriVariables);
		
		CurrencyConvertionBean response= responseEntity.getBody();

		return new CurrencyConvertionBean(response.getId(), from, to, response.getConversionMultiple(),
				quantity, quantity.multiply(response.getConversionMultiple()),
				response.getPort());

	}
	

	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConvertionBean convertCurrency(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {
		
		
		CurrencyConvertionBean response=proxy.getCurrencyExchangeValue(from, to);
		
		logger.info("{}", response);

		return new CurrencyConvertionBean(response.getId(), from, to, response.getConversionMultiple(),
				quantity, quantity.multiply(response.getConversionMultiple()),
				response.getPort());

	}

}
