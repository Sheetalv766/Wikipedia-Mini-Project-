
package apTests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
// Selenium Imports
import java.util.logging.Level;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.BrowserType;
///
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestCases {
    RemoteWebDriver driver;

    public TestCases() throws MalformedURLException {
        System.out.println("Constructor: TestCases");

        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(BrowserType.CHROME);
        driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);


        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public void testCase01() {
        System.out.println("Start Test case: testCase01");
        driver.get("https://www.wikipedia.org/");

        String currentUrl = driver.getCurrentUrl();
        // expected title in url
        String expectedTitle = "wikipedia";
        if (currentUrl.contains(expectedTitle)) {
            System.out.println("Url contains the expected title : " + expectedTitle);
        } else {
            System.out.println("Not contain : " + currentUrl);
        }
        System.out.println("end Test case: testCase01");

    }

    public void testCase02() {
        System.out.println("Start Test case: testCase02");
        // Navigate to the Wikipedia homepage
        driver.get("https://www.wikipedia.org/");

        // Verify if the header text is "Wikipedia".
        WebElement header =
                driver.findElement(By.xpath("//*[@id='www-wikipedia-org']/div[1]/h1/span"));
        if (header.getText().equals("Wikipedia")) {
            System.out.println("Header text : " + header.getText());
        } else {
            System.out.println("Not found");
        }

        // Confirm that the footer links contain "Terms of Use" and "Privacy Policy".
        WebElement footerLinks1 = driver.findElement(By.xpath("//a[text()='Terms of Use']"));
        WebElement footerLinks2 = driver.findElement(By.xpath("//a[text()='Privacy Policy']"));
        System.out.println(footerLinks1.getText());
        System.out.println(footerLinks2.getText());
        if (footerLinks1.getText().equals("Terms of Use")
                && footerLinks2.getText().equals("Privacy Policy")) {

            System.out.println("Contain links");
        } else {
            System.out.println("Not contain links");
        }
        System.out.println("end Test case: testCase02");
    }

    public void testCase03() throws InterruptedException {
        System.out.println("Start Test case: testCase03");

        driver.get("https://www.wikipedia.org/");

        WebElement searchText = driver.findElement(By.xpath("//*[@id='searchInput']"));
        searchText.sendKeys("apple");
        Thread.sleep(5000);

        WebElement searchResultInc =
                driver.findElement(By.xpath("//*[@id='typeahead-suggestions']/div/a[2]"));
        searchResultInc.click();

        WebElement founder = driver.findElement(By
                .xpath("//*[@id='mw-content-text']/div[1]/table[1]/tbody/tr[9]/td/div/ul/li[1]/a"));
        if (founder.getText().equals("Steve Jobs")) {
            System.out.println("Listed");
        } else {
            System.out.println("Not listed");
        }
        System.out.println("end Test case: testCase03");
    }

    public void testCase04() throws InterruptedException {
        System.out.println("Start Test case: testCase04");

        driver.get("https://www.wikipedia.org/");
        WebElement searchText = driver.findElement(By.xpath("//*[@id='searchInput']"));
        searchText.sendKeys("microsoft");
        Thread.sleep(5000);

        WebElement searchResultLink =
                driver.findElement(By.xpath("//*[@id='typeahead-suggestions']/div/a[1]"));
        searchResultLink.click();
        Thread.sleep(2000);

        WebElement founder = driver.findElement(By
                .xpath("//*[@id='mw-content-text']/div[1]/table[1]/tbody/tr[8]/td/div/ul/li[1]/a"));

        if (founder.getText().contains("Bill Gates")) {
            System.out.println("Found");

            founder.click();
            Thread.sleep(3000);

            String url = driver.getCurrentUrl();
            if (url.contains("Bill_Gates")) {
                System.out.println("Contains");
            } else {
                System.out.println("Not contains");
            }
        } else {
            System.out.println("Not found");
        }

        System.out.println("end Test case: testCase04");
    }

    public void testCase05() throws InterruptedException {
        System.out.println("Start Test case: testCase05");

        driver.get("https://en.wikipedia.org/");

        WebElement mainMenu =
                driver.findElement(By.xpath("//*[@id='vector-main-menu-dropdown-checkbox']"));
        mainMenu.click();
        Thread.sleep(3000);

        WebElement aboutWiki = driver.findElement(By.xpath("//*[@id='n-aboutsite']/a"));
        aboutWiki.click();
        Thread.sleep(3000);

        String urlOpened = driver.getCurrentUrl();
        if (urlOpened.contains("About")) {
            System.out.println("Opened");
        } else {
            System.out.println("Not opened");
        }
        System.out.println("end Test case: testCase05");
    }
}
