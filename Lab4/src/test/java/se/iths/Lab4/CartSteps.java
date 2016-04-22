package se.iths.Lab4;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class CartSteps {

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

	@Given("^I added \"([^\"]*)\" to cart$")
	public void i_added_to_cart(String item) throws Throwable {
		WebElement search = driver.findElement(By.name("s")); 
        search.sendKeys(item+"\n");driver.findElement(By.linkText(item)).click();
		driver.findElement(By.className("input-button-buy")).click();
		Thread.sleep(5000);
	}

	@When("^I go to checkout$")
	public void i_go_to_checkout() throws Throwable {
		driver.findElement(By.linkText("Go to Checkout")).click();
		Thread.sleep(500);
	}

	@Then("^the checkout will show (\\d+) \"([^\"]*)\"$")
	public void the_checkout_will_show(int quantity, String item){
		assertEquals("Checkout", driver.findElement(By.className("entry-title")).getText());
        assertEquals(item,driver.findElement(By.linkText(item)).getText());   
        assertEquals(Integer.toString(quantity), driver.findElement(By.name("quantity")).getAttribute("value"));
	}

	@Given("^I add \"([^\"]*)\" to cart$")
	public void i_add_to_cart(String item) throws Throwable {
		WebElement search = driver.findElement(By.name("s")); 
        search.sendKeys(item+"\n");
        i_added_to_cart(item);
        driver.findElement(By.className("continue_shopping")).click();
	}
	
	@When("^I remove \"([^\"]*)\"$")
	public void i_remove(String item) throws Throwable {
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/article/div/div[2]/div[1]/table/tbody/tr[3]/td[6]/form/input[4]")).click();
	}

	@Then("^I would see that the quantity next to the cart equals (\\d+)$")
	public void i_would_see_that_the_quantity_next_to_the_cart_equals(int quantity) throws Throwable {
		assertEquals(Integer.toString(quantity),driver.findElement(By.className("count")).getText());
	}

	@When("^I change the quantity to \"([^\"]*)\" in checkout$")
	public void i_change_the_quantity_to(String newQuantity) throws Throwable {
		driver.findElement(By.linkText("Go to Checkout")).click();
		Thread.sleep(500);driver.findElement(By.name("quantity")).clear();
		driver.findElement(By.name("quantity")).sendKeys(newQuantity);
		driver.findElement(By.name("submit")).click();
        assertEquals(newQuantity,driver.findElement(By.className("count")).getText());  
	}
	
	@When("^I continue and fill in the correct billing details$")
	public void i_continue_and_fill_in_the_correct_billing_details() throws Throwable {
		driver.findElement(By.linkText("Go to Checkout")).click();
		driver.findElement(By.className("step2")).click(); 
        Thread.sleep(2000);
      //email
        driver.findElement(By.name("collected_data[9]")).sendKeys("kalle@mail.com");
        //firstname 
        driver.findElement(By.name("collected_data[2]")).sendKeys("Kalle");
        //lastname 
        driver.findElement(By.name("collected_data[3]")).sendKeys("Anka");
        //adress 
        driver.findElement(By.name("collected_data[4]")).sendKeys("Ankgatan 5");
        //city 
        driver.findElement(By.name("collected_data[5]")).sendKeys("Ankeborg");
        //state 
        driver.findElement(By.name("collected_data[6]")).sendKeys("Disneyland");
        //country 
        Select drpCountry = new Select(driver.findElement(By.name("collected_data[7][0]")));
        drpCountry.selectByVisibleText("Sweden");
        //postnr 
        //ej obligatoriskt tydligen, men borde ju vara om man tänker på postgången
        driver.findElement(By.name("collected_data[8]")).sendKeys("12345");
        //phone 
        driver.findElement(By.name("collected_data[18]")).sendKeys("070-6584174");
        //checkruta för samma lev.adress, inte heller obligatorisk, men "undefined" fält med State/Province är markerad *, dock går köp igenom utan att fylla i
        driver.findElement(By.id("shippingSameBilling")).click();
	}

	@When("^I go on with purchase$")
	public void i_go_on_with_purchase() throws Throwable {
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/article/div/div[2]/div[2]/div/form/div[4]/div/div/span/input")).click();
	}

	@Then("^I would get a message with Thank you$")
	public void i_would_get_a_message_with_Thank_you() throws Throwable {
		 assertEquals("Thank you, your purchase is pending. You will be sent an email once the order clears.", driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/article/div/div[2]/p[1]")).getText());
	}

	@When("^I continue and fill in billing details$")
	public void i_continue_and_fill_in_billing_details() throws Throwable {
		driver.findElement(By.linkText("Go to Checkout")).click();
		driver.findElement(By.className("step2")).click(); 
        Thread.sleep(2000);
		driver.findElement(By.name("collected_data[2]")).sendKeys("Kalle");
        driver.findElement(By.name("collected_data[3]")).sendKeys("Anka");
        driver.findElement(By.name("collected_data[4]")).sendKeys("Ankgatan 5");
        driver.findElement(By.name("collected_data[5]")).sendKeys("Ankeborg");
        driver.findElement(By.name("collected_data[6]")).sendKeys("Disneyland");
        Select drpCountry = new Select(driver.findElement(By.name("collected_data[7][0]")));
        drpCountry.selectByVisibleText("Sweden");
        driver.findElement(By.name("collected_data[18]")).sendKeys("070-6584174");
	}

	@When("^I go on with purchase without email$")
	public void i_go_on_with_purchase_without_email() throws Throwable {
		i_go_on_with_purchase();
	}

	@Then("^I will return to checkout$")
	public void i_will_return_to_checkout() throws Throwable {
		driver.findElement(By.className("step2")).click(); 
        Thread.sleep(2000);
	}

	@Then("^when I continue again I would see the message Please enter a valid email\\.$")
	public void when_I_continue_again_I_would_see_the_message_Please_enter_a_valid_email() throws Throwable {
		String message = driver.findElement(By.className("validation-error")).getText(); 
        assertEquals("Please enter a valid email.", message);     
	}
}
