package com.binarybeasts.voyalsuper.selenium;


import com.binarybeasts.voyalsuper.model.dto.ProductDto;
import com.binarybeasts.voyalsuper.model.enums.ProductCategory;
import com.binarybeasts.voyalsuper.service.ProductService;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class PreciosClarosScrapping {

    @Autowired
    private ProductService productService;

    public static void main(String args[]){
        System.setProperty("webdriver.chrome.driver","/Users/valentinratti/Desktop/Informatica/2020/2C/Ing.Software/voyalsuper/src/main/resources/chromedriver");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        try {
            driver.get("https://google.com/ncr");
            driver.findElement(By.name("q")).sendKeys("cheese" + Keys.ENTER);
            ProductDto dto = new ProductDto("Descripcion", ProductCategory.ALIMENTOS_CONGELADOS, "1234567890", "asd.com");
            //WebElement firstResult = wait.until(presenceOfElementLocated(By.cssSelector("h3>div")));
            //System.out.println(firstResult.getAttribute("textContent"));

        } finally {
            driver.quit();
        }
    }

    private class Api{

        @Autowired
        private ProductService productService;

        private void addProduct(ProductDto dto){
            productService.addProduct(dto);
        }

    }



}

