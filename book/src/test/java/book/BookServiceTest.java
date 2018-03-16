package book;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = WebEnvironment.NONE)
class BookServiceTest {

    @Autowired
    private TestRestTemplate restTemplate;
    

    public void consultaTest() {
        assertThat(this.restTemplate.getForObject("http://localhost/book/9731880",
                String.class)).contains("{\"sku\":\"sku\",\"name\":\"name\",\"brand\":\"brand\",\"price\":0.0}");
    }
}
