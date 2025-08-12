package telecom_api;
import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ContactListApplication {
	
	public static String baseURL = "https://thinking-tester-contact-list.herokuapp.com";
	public static String usertoken;
	public static String LoginToken;
	public static String contactId;
	public static String email = "user" + System.currentTimeMillis() + "@yopmail.com";
	public static String password = "myPassword";
	
	ExtentReports extent;
    ExtentTest test;

    @BeforeTest
    public void setupReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }
	
	
	@Test(priority=1)
	public void testAddNewUser() {
		
		test = extent.createTest("Test Case 1: Add New User");
		String payload = "{\n" +
                "  \"firstName\": \"Arun\",\n" +
                "  \"lastName\": \"Sankar\",\n" +
                "  \"email\": \"" + email + "\",\n" +
                "  \"password\": \"" + password + "\"\n" +
                "}";
		
		System.out.println("Payload:\n" + payload);
		Response response = given().contentType(ContentType.JSON).body(payload).when().post(baseURL+"/users");
		
		Assert.assertEquals(response.statusCode(),201);
		
		response.then().log().body();
		
		 usertoken = response.jsonPath().getString("token");
		 
		 test.log(Status.PASS, "User created successfully.");

}
	@Test(priority=2,dependsOnMethods = "testAddNewUser")
	public void testGetUserProfile() {
		
		test = extent.createTest("Test Case 2: Get User Profile");
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + usertoken)
            .when()
                .get(baseURL+"/users/me");

        // Assert status code 200 OK
        Assert.assertEquals(response.statusCode(), 200, "Expected 200 OK");

        // Log response
        response.then().log().all();
        
        test.log(Status.PASS, "User Profile received successfully.");
	}
	
	@Test(priority = 3)
	public void testUpdateUser() {
	   
		test = extent.createTest("Test Case 3: Update User");
	    String payload = "{\n" +
	            "  \"firstName\": \"Arun\",\n" +
	            "  \"lastName\": \"Prasad\",\n" +
	            "  \"email\": \""+email+"\",\n" +
	            "  \"password\": \""+password+"\"\n" +
	            "}";

	    Response response = given()
	            .contentType(ContentType.JSON)
	            .header("Authorization", "Bearer " + usertoken)
	            .body(payload)
	        .when()
	            .patch(baseURL+"/users/me");

	    // Validate status code
	    Assert.assertEquals(response.statusCode(), 200, "Expected 200 OK");

	    // Log response
	    response.then().log().all();
	    
	    test.log(Status.PASS, "User Profile Updated successfully.");
	}
	
	@Test(priority = 4)
	public void testLoginUser() {
		
		test = extent.createTest("Test Case 4: Login User");

	    String payload = "{\n" +
	            "  \"email\": \""+email+"\",\n" +
	            "  \"password\": \""+password+"\"\n" +
	            "}";

	    Response response = given()
	            .contentType(ContentType.JSON)
	            .body(payload)
	        .when()
	            .post(baseURL+"/users/login");

	    // Assert status code 200
	    Assert.assertEquals(response.statusCode(), 200, "Expected 200 OK");

	    // Extract new token from response
	    LoginToken = response.jsonPath().getString("token");
	    System.out.println("New Token after login: " + LoginToken);

	    // Log response
	    response.then().log().all();
	    
	    test.log(Status.PASS, "User Profile Logged in successfully.");
	}
	
	@Test(priority = 5)
    public void addContact() {
		
		
		test = extent.createTest("Test Case 5: Add Contact");
		
		
        String payload = "{\n" +
                "  \"firstName\": \"John\",\n" +
                "  \"lastName\": \"Doe\",\n" +
                "  \"birthdate\": \"1970-01-01\",\n" +
                "  \"email\": \"jdoe@fake.com\",\n" +
                "  \"phone\": \"8005555555\",\n" +
                "  \"street1\": \"1 Main St.\",\n" +
                "  \"street2\": \"Apartment A\",\n" +
                "  \"city\": \"Anytown\",\n" +
                "  \"stateProvince\": \"KS\",\n" +
                "  \"postalCode\": \"12345\",\n" +
                "  \"country\": \"USA\"\n" +
                "}";

        Response res = given()
                .header("Authorization", "Bearer " + LoginToken)
                .contentType(ContentType.JSON)
                .body(payload)
            .when()
                .post(baseURL + "/contacts");

        Assert.assertEquals(res.statusCode(), 201);
        contactId = res.jsonPath().getString("_id");
        System.out.println("Contact ID: " + contactId);
        
        test.log(Status.PASS, "User Contact Added successfully.");
    }

    @Test(priority = 6)
    public void getContactList() {
    	
    	
    	test = extent.createTest("Test Case 6: Get Contact List");
        given()
                .header("Authorization", "Bearer " + LoginToken)
            .when()
                .get(baseURL + "/contacts")
            .then()
                .statusCode(200);
        
        test.log(Status.PASS, "User Contact List received successfully.");
    }

    @Test(priority = 7)
    public void getContact() {
    	
    	test = extent.createTest("Test Case 7: Get Contact");
        given()
                .header("Authorization", "Bearer " + LoginToken)
            .when()
                .get(baseURL + "/contacts/" + contactId)
            .then()
                .statusCode(200);
        
        test.log(Status.PASS, "User Contact received successfully.");
    }

    @Test(priority = 8)
    public void updateContactPUT() {
    	
    	test = extent.createTest("Test Case 8: Update Contact");
    	
    	
        String payload = "{\n" +
                "  \"firstName\": \"Amy\",\n" +
                "  \"lastName\": \"Miller\",\n" +
                "  \"birthdate\": \"1992-02-02\",\n" +
                "  \"email\": \"amiller@fake.com\",\n" +
                "  \"phone\": \"8005554242\",\n" +
                "  \"street1\": \"13 School St.\",\n" +
                "  \"street2\": \"Apt. 5\",\n" +
                "  \"city\": \"Washington\",\n" +
                "  \"stateProvince\": \"QC\",\n" +
                "  \"postalCode\": \"A1A1A1\",\n" +
                "  \"country\": \"Canada\"\n" +
                "}";

        Response res = given()
                .header("Authorization", "Bearer " + LoginToken)
                .contentType(ContentType.JSON)
                .body(payload)
            .when()
                .put(baseURL + "/contacts/" + contactId);

        Assert.assertEquals(res.statusCode(), 200);
        Assert.assertEquals(res.jsonPath().getString("email"), "amiller@fake.com");
        
        test.log(Status.PASS, "User Contact Updated successfully.");
    }

    @Test(priority = 9)
    public void updateContactPATCH() {
    	
    	
    	test = extent.createTest("Test Case 9: Update Contact Partially");
    	
        String payload = "{\n" +
                "  \"firstName\": \"Anna\"\n" +
                "}";

        Response res = given()
                .header("Authorization", "Bearer " + LoginToken)
                .contentType(ContentType.JSON)
                .body(payload)
            .when()
                .patch(baseURL + "/contacts/" + contactId);

        Assert.assertEquals(res.statusCode(), 200);
        Assert.assertEquals(res.jsonPath().getString("firstName"), "Anna");
        
        test.log(Status.PASS, "User Contact Updated successfully.");
    }

    @Test(priority = 10)
    public void logoutUser() {
    	
    	test = extent.createTest("Test Case 10: Logout User");
    	
        given()
                .header("Authorization", "Bearer " + LoginToken)
            .when()
                .post(baseURL + "/users/logout")
            .then()
                .statusCode(200);
        
        test.log(Status.PASS, "User Logged Out successfully.");
    }
    
    @AfterTest
    public void tearDownReport() {
        extent.flush();
    }
    
    
    
    
    
    
    
}