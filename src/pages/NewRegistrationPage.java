package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

/**
 * Created by vytautassugintas on 04/03/16.
 */
public class NewRegistrationPage {

    File driverFile;
    WebDriver webDriver;
    WebDriverWait wait;

    public NewRegistrationPage(File driverFile, WebDriver webDriver){
        this.driverFile = driverFile;
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, 5);
    }

    private WebElement waitForElementById(String id){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
    }

    private WebElement waitForElementByClassName(String className){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(className)));
    }

    public void openNewRegistrationPage(){
        WebElement mainMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'search-form')]")));
        WebElement newRegistrationLink = wait.until(ExpectedConditions.visibilityOf(mainMenu.findElement(By.linkText("Registruokitės konsultacijai internetu"))));
        newRegistrationLink.click();
    }

    public void insertName(String name){
        WebElement nameInput = waitForElementById("inputName");
        nameInput.sendKeys(name);
    }

    public void insertSurname(String surname){
        WebElement surnameInput = waitForElementById("inputSurname");
        surnameInput.sendKeys(surname);
    }

    public void insertPhoneNumber(String phoneNumber){
        WebElement phoneNumberInput = waitForElementById("inputPhone");
        phoneNumberInput.sendKeys(phoneNumber);
    }

    public void insertEmail(String email){
        WebElement emailInput = waitForElementById("inputEmail");
        emailInput.sendKeys(email);
    }

    public void insertBankDepartment(int which){
        Select bankDep = new Select(waitForElementById("inputBank"));
        bankDep.selectByIndex(which);
    }

    public void insertDate(String date){
        WebElement dateInput = waitForElementById("inputDate");
        dateInput.sendKeys(date);
    }

    public void insertTime(String time){
        WebElement timeInput = waitForElementById("inputTime");
        timeInput.sendKeys(time);
    }

    public void insertSubject(int which){
        Select theme = new Select(waitForElementById("inputSubject"));
        theme.selectByIndex(which);
    }

    public void insertComment(String comment){
        WebElement commentsInput = waitForElementById("inputComment");
        commentsInput.sendKeys(comment);
    }

    public void clickSubmitButton(String buttonClass){
        WebElement submitButton = waitForElementByClassName(buttonClass);
        submitButton.click();
    }

    public void clickModalSubmitButton(String buttonClass){
        WebElement modalFooter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'modal-footer')]")));
        WebElement modalSubmitButton = wait.until(ExpectedConditions.visibilityOf(modalFooter.findElement(By.className(buttonClass))));
        modalSubmitButton.click();
    }

}
