import org.junit.jupiter.api.Test;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.*;

public class APITest {
  @Test
  public void getCapitalOfCountryTest() {
    APIConfig apiConfig = ConfigFactory.create(APIConfig.class);
    get(apiConfig.url().concat("tallinn/").concat(apiConfig.token())).then().statusCode(200);
  }
}
