package services;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.junit.Assert;

import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;

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

        //JsonPath() method
        JsonPath jsonPath=response.jsonPath();
        firstName= jsonPath.getString("data.firstName");
        email= jsonPath.getString("data.email");


    }



    public void verifyBody(int statuscode){
        Assert.assertEquals(statuscode, response.statusCode());
        Assert.assertEquals("Lion", firstName);
        Assert.assertEquals("ayazilitas@gmail.com", email);

        //method 1: asString().contains() metodu

        Assert.assertTrue(response.body().asString().contains("Lion"));

        //Method3: path() metodu
       String actualFirstName= response.path("data.firstName");
       Assert.assertEquals("Lion", actualFirstName);

       //Method4: De serilization to collection
        Map<String,Object>login=response.as(Map.class);
        System.out.println(login);

     //Method5
        System.out.println(((Map<String,Object>)login.get("data")).get("firstName"));
        Assert.assertEquals("Lion", ((Map<String,Object>)login.get("data")).get("firstName"));
    }




public void hamcrestLogin(){

        String loginBody="{\n" +
                "  \"email\": \"ayazilitas@gmail.com\",\n" +
                "  \"password\": \"Test123456!\"\n" +
                "}";

        RestAssured.given().contentType(ContentType.JSON)
                .body(loginBody)
                .when().log().all()
                .post("auth/login")
                .then().statusCode(200)
                .and().contentType(equalTo("application/json"))
                .body("data.firstName", equalTo("Lion"),
                        "data.lastName",equalTo("Writittenstone"),
                        "data.roles[0]",equalTo("USER"));



    }
    }

