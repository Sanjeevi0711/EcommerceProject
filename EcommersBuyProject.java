package bestBuy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EcommersBuyProject {
static ChromeDriver driver=null;
	
	@BeforeMethod
	public static void setUp()
	{
		 driver = new ChromeDriver();
		 driver.manage().window().maximize();
		
	}
	@Test(priority = 0)
	//1. Navigate to the website
	public static void Navigate() throws InterruptedException
	{
		
	        
	        ((WebDriver) driver).get("https://www.bestbuy.com/"); // Replace with your desired URL
	        
	        //Get the page title
	        String pageTitle = ((WebDriver) driver).getTitle();
	        
	        // Assert that the page title contains the expected text
	        AssertJUnit.assertFalse(pageTitle.contains("https://www.bestbuy.com/"));
	        Thread.sleep(3000);
	        WebElement canada=((WebDriver) driver).findElement(By.xpath("(//*[text()='Canada'])[1]"));
	        
	        canada.click();
	        Thread.sleep(3000);
	}
	
	
	
	
	
	//3.SignUP
	@Test(priority = 1)
	    public static void signUp()
	   {
		   //3.SignUP
	       WebElement acc=((WebDriver) driver).findElement(By.xpath("//span[text()='Account']"));
	       
	        acc.click();
	        
	        WebElement acc1=((WebDriver) driver).findElement(By.xpath(" //span[text()='Create an account']"));
		    acc1.click();
		    
	        
	      
	       WebElement firstName=((WebDriver) driver).findElement(By.xpath(" //input[@id='firstName']"));
	       firstName.sendKeys("Sanjeevi");
	       WebElement lastName=((WebDriver) driver).findElement(By.xpath(" //input[@id='lastName']"));
	       lastName.sendKeys("E");
	       WebElement email=((WebDriver) driver).findElement(By.xpath(" //input[@id='email']"));
	       email.sendKeys("sanjeevi.pommuraj@gmail.com");
	       WebElement password=((WebDriver) driver).findElement(By.xpath(" //input[@id='password']"));
	       password.sendKeys("sanjeevipommuraj@123");
	       WebElement checkBox=((WebDriver) driver).findElement(By.xpath(" //input[@id='newsletter']"));
	       checkBox.click();
	       WebElement createAccount=((WebDriver) driver).findElement(By.xpath("//span[text()='Create Account']"));
	       createAccount.click();
	   }
	
    //3.Sign in
	@Test(priority = 2)
	    public void signin() throws InterruptedException
	    {
	    	
	      
	       WebElement signIn=((WebDriver) driver).findElement(By.xpath(" //a[normalize-space()='Sign in']"));
	       signIn.click();
	       WebElement userName=((WebDriver) driver).findElement(By.xpath(" //input[@id='username']"));
	       userName.sendKeys("sanjeevi.pommuraj@gmail.com");
	     
	        
	       WebElement pass=((WebDriver) driver).findElement(By.xpath("//input[@id='password']"));
	        pass.sendKeys("sanjeevipommuraj@123");
	        WebElement sign=((WebDriver) driver).findElement(By.xpath("(//span[text()='Sign In'])[2]"));
	        sign.click();
	       Thread.sleep(5000);
	
	}
	@Test(priority = 3)
	    public static void titleValidation()
	    {
	       // 4. Define the menu items as WebElement objects
      
	       WebElement shopMenuItem = ((WebDriver) driver).findElement(By.xpath("/(//span[@data-automation='x-shop'])[1]"));
	        WebElement powerUPSaleMenuItem = ((WebDriver) driver).findElement(By.xpath("//span[@data-automation='x-power-up sale']"));
	        WebElement DealsMenuItem = ((WebDriver) driver).findElement(By.xpath("//span[@data-automation='x-deals']"));
	        WebElement outletMenuItem = ((WebDriver) driver).findElement(By.xpath("//span[@data-automation='x-outlet']"));
	        WebElement servicesMenuItem = ((WebDriver) driver).findElement(By.xpath("//span[@data-automation='x-services']"));
	        
	        // Click on each menu item and validate the page title
	        clickMenuItemAndValidateTitle((WebDriver) driver, shopMenuItem, "Shop Page Title");
	        clickMenuItemAndValidateTitle((WebDriver) driver, powerUPSaleMenuItem, "PoweupSale Page Title");
	        clickMenuItemAndValidateTitle((WebDriver) driver, DealsMenuItem, "Deals Page Title");
	        clickMenuItemAndValidateTitle((WebDriver) driver, outletMenuItem, "outlet Page Title");
	        clickMenuItemAndValidateTitle((WebDriver) driver, servicesMenuItem, "service Page Title");
	        
	   

	      
	        
	    }
	@Test(priority = 4)

	    public static void clickMenuItemAndValidateTitle(WebDriver driver, WebElement menuItem, String expectedTitle) {
	        menuItem.click();
	        String actualTitle = driver.getTitle();
	        
	        if (actualTitle.equals(expectedTitle)) {
	            System.out.println("Title validation passed: " + actualTitle);
	        } else {
	            System.out.println("Title validation failed. Expected: " + expectedTitle + ", Actual: " + actualTitle);
	        }
	    }
	@Test(priority = 5)
	  //5.Write a code to validate the bottom links on the homepage.
	    public void validateBottomLinks() {
	        // Locate the bottom links using a CSS selector or another appropriate method
	        // Adjust the selector based on your HTML structure
	        By bottomLinksSelector = By.cssSelector("footer a");

	        // Find all the bottom links
	        java.util.List<WebElement> bottomLinks = ((WebDriver) driver).findElements(bottomLinksSelector);

	        for (WebElement link : bottomLinks) {
	            // Get the URL of each link
	            String url = link.getAttribute("href");

	            // Verify that the link's href is not empty and it does not contain "javascript:void(0)"
	            if (url != null && !url.isEmpty() && !url.contains("javascript:void(0)")) {
	                // Navigate to the link's URL
	                ((WebDriver) driver).get(url);

	                // Check if the current URL is the same as the expected URL
	                String currentUrl = ((WebDriver) driver).getCurrentUrl();
	                String linkText = link.getText(); // Get the link text for better reporting
	                System.out.println("Validating link: " + linkText);
	                org.testng.Assert.assertEquals(currentUrl, url, "Link URL does not match: " + linkText);
	            }
	        }
	    }

	      
	       
	       //6.Write code to search for and add an item to the shopping cart.
	@Test(priority = 6)
	       public void searchAndAddToCart() {
	    	   
	       
	       WebElement searchBox = ((WebDriver) driver).findElement(By.xpath("//input[@placeholder='Search Best Buy']")); 
	        searchBox.sendKeys("Apple Airpod Pro"); 
	        searchBox.submit();

	        // Locate the item in the search results and click on it
	        WebElement itemLink = ((WebDriver) driver).findElement(By.xpath("//div[normalize-space()='Apple AirPods Pro (2nd generation) Noise Cancelling True Wireless Earbuds with USB-C MagSafe Charging Case']")); 
	        itemLink.click();

	        // Add the item to the shopping cart
	        WebElement addToCartButton = ((WebDriver) driver).findElement(By.xpath("//div[@class='productActionContainer_1F_KM']//button[@type='submit']")); 
	        addToCartButton.click();
	       }
	       
	   //  7.  Write a code to select and add an item from (Menu à Shop by Department).
	@Test(priority = 7)
	       public void selectAndAddItemFromShopByDepartment() {
	            // Locate the "Shop by Department" menu and hover over it
	            WebElement shopByDepartmentMenu = ((WebDriver) driver).findElement(By.xpath("//span[@data-automation='x-shop']")); 
	            Actions actions = new Actions((WebDriver) driver);
	            actions.moveToElement(shopByDepartmentMenu).build().perform();

	            // Locate the specific department you want to select (Cell Phones and Accessories)
	            WebElement cellphoneDepartment = ((WebDriver) driver).findElement(By.xpath("//a[normalize-space()='Cell Phones and Accessories']")); // Replace with the actual department name or locator
	            cellphoneDepartment.click();
	            WebElement rogers = ((WebDriver) driver).findElement(By.xpath("//div[@class='_6ueV3 _1VtFJ']//a[@title='Rogers'][normalize-space()='Rogers']")); // Replace with the actual department name or locator
	            rogers.click();
	            WebElement cellphoneitem = ((WebDriver) driver).findElement(By.xpath("//div[normalize-space()='Rogers Apple iPhone 15 Pro Max 256GB - Natural Titanium - Monthly Financing']")); // Replace with the actual department name or locator
	            cellphoneitem.click();
	         // Add the item to the shopping cart
		        WebElement addToCartButton1 = ((WebDriver) driver).findElement(By.xpath("//div[@class='productActionContainer_1F_KM']//button[@type='submit']")); 
		        addToCartButton1.click();	       
		        }
	        
	   //8.Write a code to select and add an item from (Menu à Brands à Select Any Brand)
	@Test(priority = 8)
	       public void selectAndAddItemFromShopBrand() {
	    	   WebElement shopByBrandMenu = ((WebDriver) driver).findElement(By.xpath("//span[@data-automation='x-shop']")); 
	            Actions actions = new Actions((WebDriver) driver);
	            actions.moveToElement(shopByBrandMenu).build().perform();
	            WebElement brand = ((WebDriver) driver).findElement(By.xpath("//a[normalize-space()='Brands']")); // Replace with the actual department name or locator
	            brand.click();
	            WebElement ab = ((WebDriver) driver).findElement(By.xpath("//a[normalize-space()='A B']")); // Replace with the actual department name or locator
	            ab.click();
	            WebElement acer = ((WebDriver) driver).findElement(By.xpath("//a[normalize-space()='Acer']"));
	            acer.click();
	            
	            WebElement selectedItem = ((WebDriver) driver).findElement(By.xpath("//body/div[@id='root']/div[@class='container_3ohj7']/div[@class='x-page-content container_3Sp8P']/div[@class='singleColumn_BCnZ4 singleColumn']/div[@class='container_zUj6w x-search-page']/main/div/div/div[@class='productsContainer_2xEUC']/div[@class='productListingContainer_1Iyio']/div[@class='productList_31W-E']/ul[@class='ecomm-webapp-10553 list_3khgt ecomm-webapp-10554 materialOverride_OOX2O']/div[@class='productsRow_DcaXn row_1mOdd']/div[1]/div[1]/a[1]/div[1]/div[1]/div[2]/div[1]"));
	            selectedItem.click();
	            WebElement addToCartButton2 = ((WebDriver) driver).findElement(By.xpath("//button[@fdprocessedid='avjnz']//div[@class='addToCartLabel_YZaVX']")); 
	            addToCartButton2.click();	   
	       }
	     
	    // 9.  Write code to navigate to the checkout page and fill out the form with dummy payment information.
	@Test(priority = 9)
          public void navigateToCheckoutAndFillForm() {
        	// Find and click on the shopping cart or checkout button
              WebElement cartElement = ((WebDriver) driver).findElement(By.xpath("//span[@class='counter']"));
              cartElement.click();
              WebElement checkoutButton = ((WebDriver) driver).findElement(By.xpath("//a[@class='button_E6SE9 primary_1oCqK continueToCheckout_3Dgpe regular_1jnnf']//span[@class='content_3Dbgg']"));
              checkoutButton.click();

             WebElement firstName1=((WebDriver) driver).findElement(By.xpath(" //input[@id='firstName']"));
             firstName1.sendKeys("Sanjeevi");
   	       WebElement lastName2=((WebDriver) driver).findElement(By.xpath(" //input[@id='lastName']"));
   	        lastName2.sendKeys("E");
   	       WebElement phoneNumber=((WebDriver) driver).findElement(By.xpath(" //input[@id='lastName']"));
   	    phoneNumber.sendKeys("1234567891");
	       
   	       WebElement Address=((WebDriver) driver).findElement(By.xpath(" //input[@id='addressLine1']"));
   	    Address.sendKeys("Door no:123,canada");
   	      WebElement city=((WebDriver) driver).findElement(By.xpath("//input[@id='city']"));
   	    city.sendKeys("york");
   	       WebElement  postalcode=((WebDriver) driver).findElement(By.xpath(" //input[@id='postalCode']"));
   	     postalcode.sendKeys("X06");
   	       WebElement Continue=((WebDriver) driver).findElement(By.xpath("//span[normalize-space()='Continue']"));
   	    Continue.click();
          }
	@Test(priority = 10)
        // 10. Write code to verify that the order was placed successfully by checking the resulting web page for the order confirmation message.
    public static void navigateToCheckoutAndFillForm1() {
         

         // Click the "Place Order" or "Submit" button
         WebElement placeOrderButton = ((WebDriver) driver).findElement(By.id("place-order-button"));
         placeOrderButton.click();

         // Verify the confirmation message
         WebElement confirmationMessage = ((WebDriver) driver).findElement(By.id("order-confirmation-message"));

      
     }
         
          
      
  @AfterMethod

public void tearDown()

	{

	((WebDriver) driver).quit();

	}

}
	


