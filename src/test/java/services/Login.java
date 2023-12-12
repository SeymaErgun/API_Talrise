package services;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

public class Login {
    Response response;
    static String firstName;
    static String email;
    static String token;

    public void loginRequest(){

        String loginBody="{\n" +
                "  \"email\": \"ayazilitas@gmail.com\",\n" +
                "  \"password\": \"Test123456!\"\n" +
                "}";
        response= RestAssured.given().contentType(ContentType.JSON).body(loginBody).log().all().when().post("auth/login").prettyPeek();
        firstName=response.jsonPath().getString("data.firstName");
        email=response.jsonPath().getString("data.email");
    }



    public void verifyBody(int statuscode){
        Assert.assertEquals(statuscode, response.statusCode());
        Assert.assertEquals("Lion", firstName);
        Assert.assertEquals("ayazilitas@gmail.com", email);
    }

}
