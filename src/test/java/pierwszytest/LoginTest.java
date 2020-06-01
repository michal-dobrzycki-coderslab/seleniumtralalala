package pierwszytest;

import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class LoginTest {
    private WebDriver driver;

    @Before
    public void setUp(){
        // uruchom przeglądarkę
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        this.driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://katalon-test.s3.amazonaws.com/demo-aut/dist/html/form.html");
        // wejdź na stronę jakąśtam
    }

    @Test
    public void loginTest(){
        WebElement firstName = driver.findElement(By.id("first-name"));
        firstName.click();
        // tego się spodziewamy
        String randomName = TestData.getRandomName();
        // wpisujemy randomName do elementu first-name
        firstName.sendKeys(randomName);

        // to jest faktycznie na stronie
        String actualName = firstName.getAttribute("value");

        // zaznacz pierwszy checkbox
        WebElement firstCheckbox = driver.findElement(By.xpath("//input[@type='checkbox']"));
        firstCheckbox.click();


        // klikam submit
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        // znajdź czerwony napis pod LastName
        WebElement lastNameErrorMessage = driver.findElement(By.id("last-name-error"));
        String errorMessage = lastNameErrorMessage.getText();

        // sprawdzam czy input ma wpisany poprawnie tekst
        Assert.assertEquals(randomName, actualName);

        // sprawdzam czy czerwony napis jest równy "This field is required."
        Assert.assertEquals("This field is required.", errorMessage);

        // sprawdzam czy checbkox jest zaznaczony
        Assert.assertTrue("Ojejciu, checkbox miał być zaznaczony a nie jest!!!111oneonenoe!~!!", firstCheckbox.isSelected());

        // zrób zrzut ekranu
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        // Now you can do whatever you need to do with it, for example copy somewhere

        try {
            FileUtils.copyFile(screenshot, new File("c:\\scr\\screenshot.png"));
            // linux: FileUtils.copyFile(screenshot, new File("/home/user/scr/screenshot.png"));
        } catch (Exception e) {
            System.out.println("Nie udało się zapisać pliku na dysku.");
        }

    }

    @After
    public void tearDown() {
        // zamknij przeglądarkę...
    }

}
