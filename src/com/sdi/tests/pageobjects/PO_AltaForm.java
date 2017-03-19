package com.sdi.tests.pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class PO_AltaForm {

	
	
   public void rellenaFormulario(WebDriver driver, String pnombre, String papellidos, String piduser, String pemail)
   {
		WebElement nombre = driver.findElement(By.id("form-principal:nombre"));
		nombre.click();
		nombre.clear();
		nombre.sendKeys(pnombre);
		WebElement apellidos = driver.findElement(By.id("form-principal:apellidos"));
		apellidos.click();
		apellidos.clear();
		apellidos.sendKeys(papellidos);
		WebElement iduser = driver.findElement(By.id("form-principal:iduser"));
		iduser.click();
		iduser.clear();
		iduser.sendKeys(piduser);
		WebElement idcorreo = driver.findElement(By.id("form-principal:correo"));
		idcorreo.click();
		idcorreo.clear();
		idcorreo.sendKeys(pemail);
		//Pulsar el boton de Alta.
		By boton = By.id("form-principal:boton");
		driver.findElement(boton).click();	   
   }
   
   public void rellenaFormularioLogin(WebDriver driver, String usuario, String contraseña)
   {
		WebElement campoUsuario = driver.findElement(By.id("form-login:nombre"));
		campoUsuario.click();
		campoUsuario.clear();
		campoUsuario.sendKeys(usuario);
		WebElement campoContraseña = driver.findElement(By.id("form-login:passw"));
		campoContraseña.click();
		campoContraseña.clear();
		campoContraseña.sendKeys(contraseña);
		//Pulsar el boton de IniciarSesión
		By boton = By.id("form-login:iniciarSesion");
		driver.findElement(boton).click();	   
   }
	
	
	
}
