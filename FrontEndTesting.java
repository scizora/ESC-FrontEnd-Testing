package Test;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriver.*;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;

import java.util.Collections;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.*;

public class FrontEndTesting {
	WebDriver driver, driver2;
	WebElement firstName, email, submit;
	Select dropdown;
	AgentTesting agentTesting;
	WebDriverWait wait, wait2;
	final static String WEBSITEURL = "http://192.168.56.1:8888";
	final static String CLEARQUEUEURL = "http://13.76.87.194:3032/scheduler/clearreqs";
	long time;
	//cannot
	
	@Before //
	public void initDriver() throws Exception {
		FirefoxOptions options = new FirefoxOptions();
		options.setAcceptInsecureCerts(true);
		options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Noah Lim Ren Dong\\eclipse-workspace\\SeleniumFramework\\drivers\\geckodriver\\geckodriver.exe");
		driver = new FirefoxDriver(options);
		driver2 = new FirefoxDriver(options);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 20);		
		driver2.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait2 = new WebDriverWait(driver2, 20);
		agentTesting = new AgentTesting(driver2, "UserFirstName");
		driver.get(WEBSITEURL);
		firstName = driver.findElement(By.id("username_input"));
		email = driver.findElement(By.id("email_input"));
		dropdown = new Select(driver.findElement(By.id("category_input")));
		submit = driver.findElement(By.className("requestbutton"));

	}

	@After
	public void safeDriverQuit() {
		driver.quit();
		driver2.quit();
	}

	
	// Test 1 : Insert FirstName, Email and connect to agent
	@Test
	public void testFrontPageSuccessful() throws Exception {
		System.out.println("Starting Test " + new Object() {
		}.getClass().getEnclosingMethod().getName());
		// Use either by index or visible text
		dropdown.selectByIndex(0);
		// fill up name boxes
		firstName.sendKeys("UserFirstName");//Testing"+Long.toString(time)+"@gmail.com
		email.sendKeys("Testing"+Long.toString(time)+Long.toString(time)+"@gmail.com");
		submit.click();
		// Send button
	
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("wfLoading"))));
		WebElement send = driver.findElement(By.className("sendbutton"));
		boolean sendEnabled = send.isEnabled();
		assertTrue(sendEnabled);
		Thread.sleep(2000);
		driver.get(CLEARQUEUEURL);
		Thread.sleep(2000);
	}

	// Firstname field left blank
	@Test
	public void testFrontPageInvalidName1() throws Exception {
		System.out.println("Starting Test " + new Object() {
		}.getClass().getEnclosingMethod().getName());
		// Use either by index or visible text
		dropdown.selectByIndex(0);
		// fill up name boxes
		firstName.sendKeys("");
		email.sendKeys("Testing"+Long.toString(time)+"@t.com");
		// submit
		submit.click();
		Boolean submitted = !driver.getCurrentUrl().equals(WEBSITEURL);
		assertTrue(submitted);
	}

	// Special Characters and Symbols in First Name field
	@Test
	public void testFrontPageInvalidName2() throws Exception {
		System.out.println("Starting Test " + new Object() {
		}.getClass().getEnclosingMethod().getName());
		// Use either by index or visible text
		dropdown.selectByIndex(0);
		// fill up name boxes
		firstName.sendKeys("A@asgdjfm!");
		time=System.currentTimeMillis();
		email.sendKeys("Testing"+Long.toString(time)+"@gmail.com");
		// submit
		submit.click();
		Boolean submitted = !driver.getCurrentUrl().equals(WEBSITEURL);
		assertTrue(submitted);
	}

	// Very Long First Name
	@Test
	public void testFrontPageInvalidName3() throws Exception {
		System.out.println("Starting Test " + new Object() {
		}.getClass().getEnclosingMethod().getName());
		// Use either by index or visible text
		dropdown.selectByIndex(0);
		String reallyLongFirstName = String.join("", Collections.nCopies(1000, "s"));
		// fill up name boxes
		firstName.sendKeys(reallyLongFirstName);
		time=System.currentTimeMillis();
		email.sendKeys("Testing"+Long.toString(time)+"@gmail.com");
		// submit
		submit.click();
		Boolean submitted = !driver.getCurrentUrl().equals(WEBSITEURL);
		assertTrue(submitted);
	}

	// Invalid Email
	@Test
	public void testFrontPageEmail1() throws Exception {
		System.out.println("Starting Test " + new Object() {
		}.getClass().getEnclosingMethod().getName());
		// Use either by index or visible text
		dropdown.selectByIndex(0);
		// fill up name boxes
		firstName.sendKeys("UserFirstName");
		time=System.currentTimeMillis();
		email.sendKeys("Testing"+Long.toString(time)+"@gmail.com");
		// submit
		submit.click();
		Boolean submitted = !driver.getCurrentUrl().equals(WEBSITEURL);
		assertTrue(submitted);
	}

	@Test
	public void testFrontPageEmail2() throws Exception {
		System.out.println("Starting Test " + new Object() {
		}.getClass().getEnclosingMethod().getName());
		// Use either by index or visible text
		dropdown.selectByIndex(0);
		// fill up name boxes
		firstName.sendKeys("UserFirstName");
		time=System.currentTimeMillis();
		email.sendKeys("Testing"+Long.toString(time)+"@gmail.com");
		// submit
		submit.click();
		Boolean submitted = !driver.getCurrentUrl().equals(WEBSITEURL);
		assertTrue(submitted);
	}

	@Test
	public void testFrontPageEmail3() throws Exception {
		System.out.println("Starting Test " + new Object() {
		}.getClass().getEnclosingMethod().getName());
		// Use either by index or visible text
		dropdown.selectByIndex(0);
		// fill up name boxes
		firstName.sendKeys("UserFirstName");
		email.sendKeys("tes@!ting@.com");
		// submit
		submit.click();
		Boolean submitted = !driver.getCurrentUrl().equals(WEBSITEURL);
		assertTrue(submitted);
	}

	@Test
	public void testFrontPageEmail4() throws Exception {
		System.out.println("Starting Test " + new Object() {
		}.getClass().getEnclosingMethod().getName());
		// Use either by index or visible text
		dropdown.selectByIndex(0);
		// fill up name boxes
		firstName.sendKeys("UserFirstName");
		email.sendKeys("testing@.comm");
		// submit
		submit.click();
		Boolean submitted = !driver.getCurrentUrl().equals(WEBSITEURL);
		assertTrue(submitted);
	}

	@Test
	public void testFrontPageEmail5() throws Exception {
		System.out.println("Starting Test " + new Object() {
		}.getClass().getEnclosingMethod().getName());
		// Use either by index or visible text
		dropdown.selectByIndex(0);
		// fill up name boxes
		firstName.sendKeys("UserFirstName");
		email.sendKeys("");
		// submit
		submit.click();
		Boolean submitted = !driver.getCurrentUrl().equals(WEBSITEURL);
		assertTrue(submitted);
	}
	

	/* Tests if agent receives the EXACT message sent from the user */
	
	@Test
	public void testAgentReceived1() throws Exception {
		System.out.println("Starting Test " + new Object() {
		}.getClass().getEnclosingMethod().getName());
		Boolean messageReceived;
		// Set message to be sent
		String message = "I need help!";
		// Use either by index or visible text
		dropdown.selectByIndex(0);
		// fill up name boxes
		time=System.currentTimeMillis();
		String name="Celpfak";
		firstName.sendKeys(name);
		String emailAdd="Testing"+Long.toString(time)+"@gmail.com";
		email.sendKeys(emailAdd); // this one cannot send plsssss
		//change to one that can connect and send
		//email.sendKeys("Testing"+Long.toString(time)+"@gmail.com");
		// submit
		submit.click();
		// Send button
		Thread.sleep(10000);
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("wfLoading"))));
		WebElement send = driver.findElement(By.className("sendbutton"));
	   //lingy was here
		//i tried, but cannot connect properly yeah...
		System.out.println("i'm here");
		System.out.println(send);
		WebElement usermsg = driver.findElement(By.id("sendchatarea"));
		usermsg.sendKeys(message);
		Thread.sleep(5000); //so long
		send.click();
		Thread.sleep(5000);
		messageReceived = agentTesting.receivedMessage(message, emailAdd);
		assertTrue(messageReceived);
		Thread.sleep(2000);
		driver.get(CLEARQUEUEURL);
		Thread.sleep(2000);
	}
	
	
	/*
	@Test
	public void testAgentReceived2() throws Exception {
		System.out.println("Starting Test " + new Object() {
		}.getClass().getEnclosingMethod().getName());
		Boolean messageReceived;
		// Set message to be sent
		String message = "!@#$%^&*()_+1234567890-=,./;[]\\";
		// Use either by index or visible text
		dropdown.selectByIndex(0);
		// fill up name boxes
		firstName.sendKeys("UserFirstName");
		time=System.currentTimeMillis();
		email.sendKeys("Testing"+Long.toString(time)+"@gmail.com");
		// submit
		submit.click();
		// Send button
		WebElement send = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//button[@class='btn btn-primary px-3' and contains(.,'Send')]")));
		WebElement usermsg = driver.findElement(By.id("usermsg"));
		usermsg.sendKeys(message);
		send.click();
		messageReceived = agentTesting.receivedMessage(message);
		assertTrue(messageReceived);
		Thread.sleep(2000);
		driver.get(CLEARQUEUEURL);
		Thread.sleep(2000);
	}

	@Test
	public void testAgentReceived3() throws Exception {
		System.out.println("Starting Test " + new Object() {
		}.getClass().getEnclosingMethod().getName());
		Boolean messageReceived;
		// Set message to be sent
		String message = " ";
		// Use either by index or visible text
		dropdown.selectByIndex(0);
		// fill up name boxes
		firstName.sendKeys("UserFirstName");
		time=System.currentTimeMillis();
		email.sendKeys("Testing"+Long.toString(time)+"@gmail.com");
		// submit
		submit.click();
		// Send button
		WebElement send = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//button[@class='btn btn-primary px-3' and contains(.,'Send')]")));
		WebElement usermsg = driver.findElement(By.id("usermsg"));
		usermsg.sendKeys(message);
		send.click();
		messageReceived = agentTesting.receivedMessage(message);
		assertTrue(messageReceived);
		Thread.sleep(2000);
		driver.get(CLEARQUEUEURL);
		Thread.sleep(2000);
	}

	@Test
	public void testAgentReceived4() throws Exception {
		System.out.println("Starting Test " + new Object() {
		}.getClass().getEnclosingMethod().getName());
		Boolean messageReceived;
		// Set message to be sent
		String message = String.join("", Collections.nCopies(1025, "d"));
		// Use either by index or visible text
		dropdown.selectByIndex(0);
		// fill up name boxes
		firstName.sendKeys("UserFirstName");
		time=System.currentTimeMillis();
		email.sendKeys("Testing"+Long.toString(time)+"@gmail.com");
		// submit
		submit.click();
		// Send button
		WebElement send = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//button[@class='btn btn-primary px-3' and contains(.,'Send')]")));
		WebElement usermsg = driver.findElement(By.id("usermsg"));
		usermsg.sendKeys(message);
		send.click();
		messageReceived = agentTesting.receivedMessage(message);
		assertTrue(messageReceived);
		Thread.sleep(2000);
		driver.get(CLEARQUEUEURL);
		Thread.sleep(2000);
	}
	*/

	/* Tests if the user receives the EXACT message from the agent */ 
	@Test
	public void testUserReceived1() throws Exception {
		System.out.println("Starting Test " + new Object() {
		}.getClass().getEnclosingMethod().getName());
		Boolean messageReceivedAgent;
		// Set message to be sent
		String message = "I need help!";
		// Use either by index or visible text
		dropdown.selectByIndex(0);
		// fill up name boxes
		time=System.currentTimeMillis();
		String name="Celpfak";
		firstName.sendKeys(name);
		String emailAdd="Testing"+Long.toString(time)+"@gmail.com";
		email.sendKeys(emailAdd); // this one cannot send plsssss
		//change to one that can connect and send
		//email.sendKeys("Testing"+Long.toString(time)+"@gmail.com");
		// submit
		submit.click();
		// Send button
		Thread.sleep(10000);
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("wfLoading"))));
		WebElement send = driver.findElement(By.className("sendbutton"));
	    //lingy was here
	    //student id 1003785 +project pts to lingy ty
	    //+project pts not written by lingy
		//i tried, but cannot connect properly yeah...
		System.out.println("i'm here");
		System.out.println(send);
		WebElement usermsg = driver.findElement(By.id("sendchatarea"));
		usermsg.sendKeys(message);
		Thread.sleep(5000); //so long
		send.click();
		Thread.sleep(5000);
		messageReceivedAgent = agentTesting.receivedMessage(message, emailAdd);
		assertTrue(messageReceivedAgent);
		String agentMessage="What can I help you with?";
		agentTesting.sendMessage(agentMessage);
		WebElement messageReceivedClient = driver.findElement(By.className("yourchat"));
		assertTrue(messageReceivedClient.getText().equals(agentMessage));
		Thread.sleep(2000);
		driver.get(CLEARQUEUEURL);
		Thread.sleep(2000);
	}

	/*
	@Test
	public void testUserReceived2() throws Exception {
		System.out.println("Starting Test " + new Object() {
		}.getClass().getEnclosingMethod().getName());
		Boolean messageReceived;
		// Set message to be sent
		String message = "!@#$%^&*()_+1234567890-=,./;[]\\";
		// Use either by index or visible text
		dropdown.selectByIndex(0);
		// fill up name boxes
		firstName.sendKeys("UserFirstName");
		String emailAdd="Testing"+Long.toString(time)+"@gmail.com";
		email.sendKeys(emailAdd);
		// submit
		submit.click();
		// Send button
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//button[@class='btn btn-primary px-3' and contains(.,'Send')]")));
		agentTesting.sendMessage(message, emailAdd);
		String userChatmessageXpath =
				String.format("//h3[@class='text-left' and contains(text(),'%s')]", message);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(userChatmessageXpath)));
			messageReceived = true;
		} catch (TimeoutException e) {
			messageReceived = false;
		}
		assertTrue(messageReceived);
	}

	@Test
	public void testUserReceived3() throws Exception {
		System.out.println("Starting Test " + new Object() {
		}.getClass().getEnclosingMethod().getName());
		Boolean messageReceived;
		// Set message to be sent
		String message = " ";
		// Use either by index or visible text
		dropdown.selectByIndex(0);
		// fill up name boxes
		firstName.sendKeys("UserFirstName");
		time=System.currentTimeMillis();
		String emailAdd="Testing"+Long.toString(time)+"@gmail.com";
		email.sendKeys(emailAdd);
		// submit
		submit.click();
		// Send button
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//button[@class='btn btn-primary px-3' and contains(.,'Send')]")));
		agentTesting.sendMessage(message, emailAdd);
		String userChatmessageXpath =
				String.format("//h3[@class='text-left' and contains(text(),'%s')]", message);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(userChatmessageXpath)));
			messageReceived = true;
		} catch (TimeoutException e) {
			messageReceived = false;
		}
		assertTrue(messageReceived);
	}

	@Test
	public void testUserReceived4() throws Exception {
		System.out.println("Starting Test " + new Object() {
		}.getClass().getEnclosingMethod().getName());
		Boolean messageReceived;
		// Set message to be sent
		String message = String.join("", Collections.nCopies(1025, "d"));
		// Use either by index or visible text
		dropdown.selectByIndex(0);
		// fill up name boxes
		firstName.sendKeys("UserFirstName");
		time=System.currentTimeMillis();
		String emailAdd="Testing"+Long.toString(time)+"@gmail.com";
		email.sendKeys(emailAdd);
		// submit
		submit.click();
		// Send button
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//button[@class='btn btn-primary px-3' and contains(.,'Send')]")));
		agentTesting.sendMessage(message, emailAdd);
		String userChatmessageXpath =
				String.format("//h3[@class='text-left' and contains(text(),'%s')]", message);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(userChatmessageXpath)));
			messageReceived = true;
		} catch (TimeoutException e) {
			messageReceived = false;
		}
		assertTrue(messageReceived);
	}
	*/
}
