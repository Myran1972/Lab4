package se.iths.Lab4;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;



public class MyAccontSteps {
	
	private WebDriver driver;
	
	@Before
	public void setup(){
		driver = new FirefoxDriver();
		driver.get("http://www.store.demoqa.com");
	}
	
	@After
	public void teardown(){
		driver.quit();
	}
	
	@Given("^I have opened the registration form$")
	public void i_have_opened_the_registration_form() throws Throwable{ 
		i_have_opened_My_account();
		driver.findElement(By.linkText("Register")).click();
	}

	@When("^I registrer username \"([^\"]*)\"$")
	public void i_registrer_username(String user) {
		driver.findElement(By.id("user_login")).sendKeys(user);
	}

	@When("^I register email \"([^\"]*)\"$")
	public void i_register_email(String email) {
		driver.findElement(By.id("user_email")).sendKeys(email);
	}

	@When("^I Login without an answer to the equation$")
	public void i_Login_without_an_answer_to_the_equation() throws InterruptedException {
		driver.findElement(By.id("wp-submit")).click();
		Thread.sleep(1000);
	}

	@Then("^I will receive an error message like \"([^\"]*)\"$")
	public void i_will_receive_an_error_message_like(String arg1) throws Throwable {
		String error = driver.findElement(By.id("login_error")).getText();
		assertEquals("ERROR: Your answer was incorrect - please try again.", error);
	}

	@Given("^I have opened My account$")
	public void i_have_opened_My_account(){
		driver.findElement(By.id("account")).click();
	}

	@When("^I fill in my username \"([^\"]*)\"$")
	public void i_fill_in_my_username(String user){
		driver.findElement(By.id("log")).sendKeys(user);
	}

	@When("^I fill in my password \"([^\"]*)\"$")
	public void i_fill_in_my_password(String pwd){
	    driver.findElement(By.id("pwd")).sendKeys(pwd);
	}

	@When("^I Login$")
	public void i_Login() throws Throwable{
		driver.findElement(By.id("login")).click();
	    Thread.sleep(5000);
	}

	@Then("^I should get result that \"([^\"]*)\" logged in \"([^\"]*)\"$")
	public void i_should_get_result_that_logged_in(String user, String result){
		if(result.equals("successful")){
			assertEquals("Howdy, "+user, driver.findElement(By.id("wp-admin-bar-my-account")).getText());
		}
		else{
			WebElement error = driver.findElement(By.className("response"));
		    assertEquals(error.getText(), "ERROR: Invalid login credentials.");
		}
	}
}
