/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.peerialismtest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
/**
 *
 * @author em
 */
public class TextRipper {
    private static String URL, ID;
    

    TextRipper(String URL, String ID) {
        this.URL = URL;
        this.ID = ID;
        
        
    }
    
    public static String getInformation(){
        WebDriver driver = new HtmlUnitDriver();
        driver.get(URL);
            WebElement element = driver.findElement(By.id(ID));
            return element.getText();
        
        
        
        
    }
    
    
    
}
