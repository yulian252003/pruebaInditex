import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class pruebaSelenium {
    public static void main(String[] args) throws IOException {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        
        driver.manage().window().maximize();
        driver.get("https://www.google.com");
        
        //Buscar en Google la palabra “automatización”
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("automatización");
        searchBox.sendKeys(Keys.ENTER);
        
        //Buscar el link de la Wikipedia resultante
        WebElement wiki = driver.findElement(By.xpath("//h3[normalize-space()='Automatización - Wikipedia, la enciclopedia libre']"));
        wiki.click();
        
        //Comprobar en que año se hizo el primer proceso automatico
        WebElement elemento = driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div[1]/p[33]"));
        String textoCapturado = elemento.getText();
        System.out.println("Texto capturado: " + textoCapturado);
        
        //Realizar un screenshot de la página de la Wikipedia
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile, new File("captura.png"));
        
       try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}
