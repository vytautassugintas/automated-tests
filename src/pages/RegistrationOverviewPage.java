package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

/**
 * Created by vytautassugintas on 04/03/16.
 */
public class RegistrationOverviewPage {

    File driverFile;
    WebDriver webDriver;
    WebDriverWait wait;

    public RegistrationOverviewPage(File driverFile, WebDriver webDriver){
        this.driverFile = driverFile;
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, 5);
    }

    private WebElement waitForElementById(String id){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
    }

    public void openOverviewPageByNumber(String number){
        WebElement mainMenu = webDriver.findElement(By.xpath("//div[contains(@class, 'search-form')]"));
        WebElement overviewButton = mainMenu.findElement(By.linkText("Registracijų peržiūra"));
        overviewButton.click();
        insertNumber(number);
        clickSearchButton();
    }

    public void insertNumber(String number){
        WebElement phoneInput = waitForElementById("inputPhone");
        phoneInput.sendKeys(number);
    }

    public void clickSearchButton(){
        WebElement searchButton = waitForElementById("search");
        searchButton.click();
    }

    public String getRegistrationName(){
        wait.until(ExpectedConditions.urlMatches("http://betaregistration-kirviai.rhcloud.com/#/registration-list"));
        WebElement overviewLink = wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.linkText("Peržiūrėti"))));
        overviewLink.click();
        WebElement name = waitForElementById("modalInputName");
        return name.getText();
    }

}
