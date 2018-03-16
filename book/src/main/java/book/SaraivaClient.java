package book;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SaraivaClient {

	public SaraivaJson consultarSaraiva(final String sku) {
        String url = new StringBuilder("https://api.saraiva.com.br/sc/produto/pdp/").append(sku).append("/0/0/1/").toString();
		
		RestTemplate restTemplate = new RestTemplate();
        
        return restTemplate.getForObject(url, SaraivaJson.class);
	}
}
