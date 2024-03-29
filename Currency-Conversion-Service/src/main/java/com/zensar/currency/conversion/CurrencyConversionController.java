package com.zensar.currency.conversion;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController 
{
	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to,@PathVariable BigDecimal quantity)
	{
		Map<String,String> urlVariables = new HashMap<>();
		urlVariables.put("from", from);
		urlVariables.put("to", to);
		
		ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency_exchange/from/{from}/to/{to}",CurrencyConversionBean.class,urlVariables);
		CurrencyConversionBean response = responseEntity.getBody();
		
		return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(),quantity,quantity.multiply(response.getConversionMultiple()),response.getPort());
		
	}
}
