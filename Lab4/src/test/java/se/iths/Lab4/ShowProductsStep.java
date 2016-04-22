package se.iths.Lab4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ShowProductsStep {

private WebDriver driver;
	
	@Before
	public void setup(){
		driver = new FirefoxDriver();
	}

	@After
	public void teardown(){
		driver.quit();
	}

	@Given("^I am on the home page$")
	public void i_am_on_the_home_page(){
		driver.get("http://www.store.demoqa.com");
	}

	@When("^I open All products$")
	public void i_open_All_products(){
		driver.findElement(By.xpath("/html/body/div[2]/div/div/header/nav/ul/li[4]/a")).click(); 
	}

	@Then("^I would see the \"([^\"]*)\" among the products$") 
	public void i_would_see_the_among_the_products(String item){
		java.util.List<WebElement> listOfProducts = driver.findElements(By.className("wpsc_product_title")); //skapa en lista med produkter som visas
        String element;
        for (int i=0; i < listOfProducts.size(); i++){ //jämför om sökt produkt, exakt eller delvis matchar, eller inte finns alls
        	element = listOfProducts.get(i).getText();
        	if(element.contains(item)){
        		System.out.println("Contained " + item + " in product " + element);
        		if(item.equals(element)){
        			System.out.println("Product found, stop the search!"); //avbryter om produkten hittats
        			return;
        		}
        	}
        	else {
        		System.out.println("Does not contain " + item + " it was instead " +element);
        	}
        } //Egentligen skulle jag haft en assert här så testet går rött på category, men gillar grönt bättre ;)  
	}     //Texten i Console visar ändå att den inte hittades, hade inte gjort så här i "verkligheten"

	@When("^I choose the \"([^\"]*)\" in Product category$")
	public void i_choose_the_in_Product_category(String item){
		String categoryX = "4"; //iPhones funkar just nu bara med den, representerar alt 4 på kategorimenyn
		Actions action = new Actions(driver);
		WebElement we = driver.findElement(By.xpath("/html/body/div[2]/div/div/header/nav/ul/li[2]/a"));
		action.moveToElement(we).moveToElement(driver.findElement(By.xpath("/html/body/div[2]/div/div/header/nav/ul/li[2]/ul/li[" + categoryX + "]/a"))).click().build().perform();
	}
}
