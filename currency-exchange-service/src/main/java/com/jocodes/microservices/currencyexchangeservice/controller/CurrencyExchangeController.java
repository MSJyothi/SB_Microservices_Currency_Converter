package com.jocodes.microservices.currencyexchangeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jocodes.microservices.currencyexchangeservice.bean.ExchangeValue;
import com.jocodes.microservices.currencyexchangeservice.repository.ExchangeValueRepository;

@RestController
public class CurrencyExchangeController {
	
	public Logger logger= LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Environment environment;

	@Autowired
	private ExchangeValueRepository repository;

	@GetMapping("currency-exchange/from/{from}/to/{to}")
	public ExchangeValue getCurrencyExchangeValue(@PathVariable String from, @PathVariable String to) {

		ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		logger.info("{}", exchangeValue);

		return exchangeValue;

	}

}
