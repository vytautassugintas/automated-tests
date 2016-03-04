import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.NewRegistrationPage;
import pages.RegistrationOverviewPage;

import java.io.File;

public class RegistrationTest {

    File driverFile;
    WebDriver driver;
    WebDriverWait wait;

    NewRegistrationPage newRegistrationPage;
    RegistrationOverviewPage registrationOverviewPage;

    @Before
    public void setUp() {
        driverFile = new File("/Users/vytautassugintas/Desktop/chromedriver");
        System.setProperty("webdriver.chrome.driver", driverFile.getAbsolutePath());
        driver = new ChromeDriver();
        driver.get("http://betaregistration-kirviai.rhcloud.com/");
        wait = new WebDriverWait(driver, 10);

        newRegistrationPage = new NewRegistrationPage(driverFile, driver);
        registrationOverviewPage = new RegistrationOverviewPage(driverFile, driver);
    }

    @After
    public void closeDriver(){
        driver.quit();
    }

    @Test
    public void newRegistrationTest(){
        newRegistrationPage.openNewRegistrationPage();
        newRegistrationPage.insertName("Lapis");
        newRegistrationPage.insertSurname("Lapinas");
        newRegistrationPage.insertPhoneNumber("860296103");
        newRegistrationPage.insertEmail("vytautas@sugintas.com");
        newRegistrationPage.insertBankDepartment(3);
        newRegistrationPage.insertDate("12-11-2016");
        newRegistrationPage.insertTime("16:00");
        newRegistrationPage.insertSubject(3);
        newRegistrationPage.insertComment("Sveiki, gali būti, kad vėluosiu iki 15min");
        newRegistrationPage.clickSubmitButton("btn");
        newRegistrationPage.clickModalSubmitButton("btn");

        Assert.assertEquals(driver.getCurrentUrl(), "http://betaregistration-kirviai.rhcloud.com/#/");
    }

    @Test
    public void negativeNewRegistrationTest(){
        newRegistrationPage.openNewRegistrationPage();
        newRegistrationPage.insertName("");
        newRegistrationPage.insertSurname("123sda");
        newRegistrationPage.insertPhoneNumber("asd");
        newRegistrationPage.insertEmail("vytautas.com");
        newRegistrationPage.clickSubmitButton("btn");

        Assert.assertEquals(driver.getCurrentUrl(), "http://betaregistration-kirviai.rhcloud.com/#/consultation-registration");
    }

    @Test
    public void registrationOverviewByNumberTest() throws InterruptedException {
        registrationOverviewPage.openOverviewPageByNumber("860296103");

        Assert.assertEquals(registrationOverviewPage.getRegistrationName(), "Lapis");
    }

    @Test
    public void negativeRegistrationOverviewByNumberTest(){
        registrationOverviewPage.openOverviewPageByNumber("90gdf09");

        Assert.assertEquals(driver.getCurrentUrl(), "http://betaregistration-kirviai.rhcloud.com/#/");
    }
}