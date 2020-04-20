package Test;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//cos specific for sandbox yeah
public class AgentTesting {
  final static String AGENT1_USERNAME = "jason_chow@mymail.sutd.edu.sg"; //change 2628836781@qq.com
  final static String AGENT1_PASSWORD = "Rainbow1!"; //SUTDzsz19991226!
  String customerFirstName;
  String customerName;
  WebDriver driver;
  WebDriverWait wait;
  Boolean agentLoggedIn, customerSelected;
  ArrayList<String> tabs;

  //gg wait
  public AgentTesting(WebDriver driver, String customerFirstName) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, 30);
    this.customerFirstName = customerFirstName;
    this.customerName = customerFirstName;
    this.tabs = new ArrayList<String>(driver.getWindowHandles());
    this.agentLoggedIn = false;
    this.customerSelected = false;
  }

  private void openNewTab() {
    ((JavascriptExecutor) driver).executeScript("window.open('about:blank','_blank');");
    tabs = new ArrayList<String>(driver.getWindowHandles());
    driver.switchTo().window(tabs.get(tabs.size() - 1));
  }

  private void switchToFirstTab() {
    driver.switchTo().window(tabs.get(0));
  }

  private void closeAgentTab() {
    driver.close();
    switchToFirstTab();
  }

  private void agentLogin() throws InterruptedException {
	//they auto redirect
	  driver.get("https://web-sandbox.openrainbow.com/");
    //driver.get("http://127.0.0.1:11111");
	WebElement usernameField = driver.findElement(By.id("username"));
    //WebElement usernameField = driver.findElement(By.id("userid"));
    usernameField.sendKeys(AGENT1_USERNAME);
    
    WebElement submitButton =
        driver.findElement(By.xpath("//square-button[@label-dyn='continue']"));
    wait.until(ExpectedConditions.elementToBeClickable(submitButton));
    Thread.sleep(1500);
    submitButton.click();
    WebElement passwordField = wait.until(
        ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='password']")));
    wait.until(ExpectedConditions.elementToBeClickable(passwordField));
    passwordField.sendKeys(AGENT1_PASSWORD);
    /*
    WebElement passwordField = driver.findElement(By.id("pswrd"));
    passwordField.sendKeys(AGENT1_PASSWORD);
    Thread.sleep(1500);
    */
    WebElement connectButton = wait.until(
        ExpectedConditions.elementToBeClickable(By.xpath("//square-button[@label-dyn='connect']")));
    Thread.sleep(500);
    connectButton.click();
    //cse help i t so dead
    /*
    WebElement loginButton = driver.findElement(By.id("bttn"));
    loginButton.click();
    */

    wait.until(ExpectedConditions
        .urlToBe("https://web-sandbox.openrainbow.com/app/1.70.4/index.html#/main/home/home"));
    String dismissModalButtonXpath =
        "//button[@class='buttonTour' and contains(.,'Remind me later')]";
    WebElement dismissModalButton = wait
        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dismissModalButtonXpath)));
    Thread.sleep(1500);
    dismissModalButton.click();
    agentLoggedIn = true;
  }

  private void getCustomer(String emailAdd) {
    //String customerNameXpath =
    //    String.format("//div[@id='cell' and contains(., '%s')]", customerName);
    WebElement contactName = driver.findElement(By.xpath("//span[contains(text(),emailAdd)]"));
    contactName.click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("chattextarea")));
    wait.until(ExpectedConditions.elementToBeClickable(By.id("chattextarea")));
    customerSelected = true;
  }

  public Boolean receivedMessage(String message, String emailAdd) throws InterruptedException, CustomException {
    //openNewTab();
    agentLogin();
    if (!agentLoggedIn)
      throw new CustomException("Agent not logged in");
    getCustomer(emailAdd);
    if (!customerSelected)
      throw new CustomException("Customer not selected");
    String messageXpath = String.format("//div[contains(text(),'I need help!')]", message);
    Boolean messageReceived = driver.findElements(By.xpath(messageXpath)).size() > 0;
    System.out.println("this");
    System.out.println(messageReceived);
    //closeAgentTab();
    return messageReceived;
  }

  public void sendMessage(String message) throws InterruptedException, CustomException {
    //openNewTab();
    //agentLogin();
    if (!agentLoggedIn)
      throw new CustomException("Agent not logged in");
    //getCustomer(emailAdd);
    if (!customerSelected)
      throw new CustomException("Customer not selected");
    WebElement chatTextArea = driver.findElement(By.id("chattextarea"));
    //???????????????????????????????????
    //WHAT IS THIS chat text area not the ele dafuq?
    //WHY NOT WORKING
    //WHAT EVEN
    //WHAT ODD
    //ISEEDEADPEOPLE GREEDISGOOD 999999999999999 dead
    //need git push
    chatTextArea.sendKeys(message);
    chatTextArea.sendKeys(Keys.RETURN);
    Thread.sleep(2000);
    //closeAgentTab();
  }

}
