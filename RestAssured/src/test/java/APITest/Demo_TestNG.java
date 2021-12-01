package APITest;

import static org.hamcrest.CoreMatchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Demo_TestNG {
	public String id;
	public String response;
	
	public static  String baseurl = "https://api.trello.com/" ;
	
	@Test(priority = 1 )
	public void getboard() {
		
		RestAssured.baseURI = baseurl;
		
		Response response =  given().param("key","8fbfa9d2370227cbeab133b66d5aef77")
		.param("token","74c94b4cabcf0445575b4ff8b1063e88b735712d3261d6bb7b13448f9041ab89")
		
		.when()
		.get("1/board/"+id)
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON).and()
		/*.body("name",equalTo("sample")).and()
		.body("id",equalTo("619e17f54cc8c586213619e5")); */
		.extract().response();
		String jsonresponse = response.asString();
		//JsonPath responsebody = new JsonPath(jsonresponse);
		//id = responsebody.getString("id");
		System.out.println(jsonresponse);
}
	//create a trello board
	@Test(priority = 0)
	
	
	public void creatboard()
	//creating a board 
	{
		RestAssured.baseURI = baseurl;
		Response response = given().queryParam("key","8fbfa9d2370227cbeab133b66d5aef77")
		.queryParam("token","74c94b4cabcf0445575b4ff8b1063e88b735712d3261d6bb7b13448f9041ab89")
		.queryParam("name","modified-new").header("Content-Type","application/json")
		
		.when()
		.post("1/boards/")
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();
		
		String jsonresponse = response.asString();
		JsonPath responsebody = new JsonPath(jsonresponse);
		System.out.println(jsonresponse);
		id = responsebody.get("id");
		
	}
	@Test(priority = 2)
	public void deleteboard()
	{
		RestAssured.baseURI = baseurl;
		Response response = given().queryParam("key","8fbfa9d2370227cbeab133b66d5aef77")
		.queryParam("token","74c94b4cabcf0445575b4ff8b1063e88b735712d3261d6bb7b13448f9041ab89")
		.queryParam("name","modified-new")
		
		
		.when()
		.delete("1/boards/"+id)
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();
		
		String jsonresponse = response.asString();
		System.out.println(jsonresponse);
		
	}
}
