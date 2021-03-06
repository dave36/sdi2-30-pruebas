package com.sdi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_AltaForm {

	public void rellenaFormulario(WebDriver driver, String pnombre,
			String papellidos, String piduser, String pemail) {
		WebElement nombre = driver.findElement(By.id("form-principal:nombre"));
		nombre.click();
		nombre.clear();
		nombre.sendKeys(pnombre);
		WebElement apellidos = driver.findElement(By
				.id("form-principal:apellidos"));
		apellidos.click();
		apellidos.clear();
		apellidos.sendKeys(papellidos);
		WebElement iduser = driver.findElement(By.id("form-principal:iduser"));
		iduser.click();
		iduser.clear();
		iduser.sendKeys(piduser);
		WebElement idcorreo = driver
				.findElement(By.id("form-principal:correo"));
		idcorreo.click();
		idcorreo.clear();
		idcorreo.sendKeys(pemail);
		// Pulsar el boton de Alta.
		By boton = By.id("form-principal:boton");
		driver.findElement(boton).click();
	}

	public void rellenaFormularioLogin(WebDriver driver, String usuario,
			String contraseña) {
		WebElement campoUsuario = driver
				.findElement(By.id("form-login:nombre"));
		campoUsuario.click();
		campoUsuario.clear();
		campoUsuario.sendKeys(usuario);
		WebElement campoContraseña = driver.findElement(By
				.id("form-login:passw"));
		campoContraseña.click();
		campoContraseña.clear();
		campoContraseña.sendKeys(contraseña);
		// Pulsar el boton de IniciarSesión
		By boton = By.id("form-login:iniciarSesion");
		driver.findElement(boton).click();
	}

	public void rellenaFormularioRegistro(WebDriver driver, String usuario,
			String email, String contraseña, String contraseña2) {
		WebElement campoUsuario = driver.findElement(By
				.id("form-registro:nombre"));
		campoUsuario.click();
		campoUsuario.clear();
		campoUsuario.sendKeys(usuario);
		WebElement campoEmail = driver
				.findElement(By.id("form-registro:email"));
		campoEmail.click();
		campoEmail.clear();
		campoEmail.sendKeys(email);
		WebElement campoContraseña = driver.findElement(By
				.id("form-registro:passw"));
		campoContraseña.click();
		campoContraseña.clear();
		campoContraseña.sendKeys(contraseña);
		WebElement campoRepitaContraseña = driver.findElement(By
				.id("form-registro:passwConf"));
		campoRepitaContraseña.click();
		campoRepitaContraseña.clear();
		campoRepitaContraseña.sendKeys(contraseña2);
		// Pulsar el boton de Registro
		By boton = By.id("form-registro:registrar");
		driver.findElement(boton).click();
	}

	public void rellenaFormularioAñadirTarea(WebDriver driver, String title,
			String comments, String categoria, String fecha)
			throws InterruptedException {
		WebElement campoTitulo = driver.findElement(By
				.id("form-añadirTarea:nombreTarea"));
		campoTitulo.click();
		campoTitulo.clear();
		campoTitulo.sendKeys(title);
		WebElement campoComentario = driver.findElement(By
				.id("form-añadirTarea:comentarioTarea"));
		campoComentario.click();
		campoComentario.clear();
		campoComentario.sendKeys(comments);
		WebElement campoCategoria = driver.findElement(By
				.id("form-añadirTarea:categoriaTarea"));
		campoCategoria.click();
		campoCategoria.clear();
		campoCategoria.sendKeys(categoria);
		if (fecha != "") {
			WebElement campoFecha = driver.findElement(By
					.id("form-añadirTarea:fechaPlaneada_input"));
			campoFecha.click();
			campoFecha.clear();
			campoFecha.sendKeys(fecha);
			// Hacemos click en escape para que se cierre el calendario
			// que previamente hemos desplegado
			campoFecha.sendKeys(Keys.ESCAPE);
			Thread.sleep(500);
		}
		// Pulsar el boton de IniciarSesión
		By boton = By.id("form-añadirTarea:crearTarea");
		driver.findElement(boton).click();
	}

	public void rellenaFormularioEditarTarea(WebDriver driver, String title,
			String comments, String categoria, String fecha)
			throws InterruptedException {
		WebElement campoTitulo = driver.findElement(By
				.id("form-editarTarea:nombreTarea"));
		campoTitulo.click();
		campoTitulo.clear();
		campoTitulo.sendKeys(title);
		WebElement campoComentario = driver.findElement(By
				.id("form-editarTarea:comentarioTarea"));
		campoComentario.click();
		campoComentario.clear();
		campoComentario.sendKeys(comments);
		WebElement campoCategoria = driver.findElement(By
				.id("form-editarTarea:categoriaTarea"));
		campoCategoria.click();
		// Borramos la categoria que haya previamente en la tarea
		campoCategoria.sendKeys(Keys.DELETE);
		campoCategoria.clear();
		campoCategoria.sendKeys(categoria);
		if (fecha != "") {
			WebElement campoFecha = driver.findElement(By
					.id("form-editarTarea:fechaPlaneada_input"));
			campoFecha.click();
			campoFecha.clear();
			campoFecha.sendKeys(fecha);
			// Hacemos click en escape para que se cierre el calendario
			// que previamente hemos desplegado
			campoFecha.sendKeys(Keys.ESCAPE);
			Thread.sleep(500);
		}
		// Pulsar el boton de IniciarSesión
		By boton = By.id("form-editarTarea:editarTarea");
		driver.findElement(boton).click();
	}

}
