package APITest;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

public class eCommerce {
	public String accessToken;
	public String id;
	
	public static String baseurl = "https://ecommerceservice.herokuapp.com";
	@Test(priority = 0 , enabled = false) // // even if method present that won't run 
	public void sign_up()
	{
		RestAssured.baseURI = baseurl;
		
		String requestbody = "{\r\n"
				+ "	\"email\": \"oscar176@gmail.com\",\r\n"
				+ "	\"password\": \"asif@123\"\r\n"
				+ "}\r\n"
				+ "";
		
		//this time i want to know my response is in my console
		Response response = given()
				.header("content-type","application/json")
				.body(requestbody)
				
				
				.when()
				.post("/user/signup")
				
				.then()
				.assertThat().statusCode(201).and().contentType(ContentType.JSON)
				.extract().response();
			//System.out.println(response.asString()); // to get the output as a String
		
		//basically the output format by default will be string cannot read
		// i have to convert it to JSON
		
		String jsonresponse = response.asString();
		//if i want to convert from nirmal string to json format
		JsonPath responsebody = new JsonPath(jsonresponse);
		System.out.println(responsebody.get("message"));
	}
	
	//to get the accessToken 
	@Test(priority = 1) 
	public void login()
	{
		RestAssured.baseURI = baseurl;
		
		String requestbody = "{\r\n"
				+ "	\"email\": \"oscar176@gmail.com\",\r\n"
				+ "	\"password\": \"asif@123\"\r\n"
				+ "}\r\n"
				+ "";
		
		Response response = given()
				.header("content-type","application/json")
				.body(requestbody)
				
				.when()
				.post("/user/login")
				
				.then()
				.assertThat().statusCode(200).and().contentType(ContentType.JSON)
				.extract().response();
		
		String jsonresponse = response.asString();
		//if i want to convert from nirmal string to json format
		JsonPath responsebody = new JsonPath(jsonresponse);
		System.out.println(responsebody.get("accessToken"));
		// = response.get("accessToken");
		accessToken = responsebody.get("accessToken");
	}
	// to get the access token and ID
	
	@Test(priority = 2) 
	public void get()
	{
		RestAssured.baseURI = baseurl;
	
		
		Response response = given()
				.header("content-Type","application/json")
				.header("Authorization","bearer "+accessToken)
		
				
				.when()
				.get("/user")
				
				.then()
				.assertThat().statusCode(200).and().contentType(ContentType.JSON)
				.extract().response();
		
		String jsonresponse = response.asString();
		//if i want to convert from nirmal string to json format
		JsonPath responsebody = new JsonPath(jsonresponse);
		//System.out.println(responsebody.get("users[47]._id"));
		id = responsebody.get("users[64]._id");

}
	
	//delete user
	
	@Test(priority = 3)
	public void delete()
	{
		RestAssured.baseURI = baseurl;
	
		
		Response response = given()
				.header("content-Type","application/json")
				.header("Authorization","bearer "+accessToken)
		
				
				.when()
				.delete("/user/"+id)
				
				.then()
				.assertThat().statusCode(200).and().contentType(ContentType.JSON)
				.extract().response();
		
		String jsonresponse = response.asString();
		//if i want to convert from nirmal string to json format
		JsonPath responsebody = new JsonPath(jsonresponse);
		System.out.println(responsebody.get("message"));
		//id = responsebody.get("users[87]._id");

}
}
