package com.sdi.tests.pageobjects;

import org.openqa.selenium.WebDriver;

import com.sdi.tests.utils.SeleniumUtils;

public class PO_Login {

	public static void testLoginParametros(WebDriver driver, String nombreform, 
			String usuario,	String contraseña) {
		// Vamos a rellenar el formulario
		new PO_AltaForm().rellenaFormularioLogin(driver, usuario, contraseña);

		// Esperamos a que se cargue la pagina del admin
		// concretamente el formulario
		SeleniumUtils.EsperaCargaPagina(driver, "id", nombreform, 10);

		// Comprobamos que aparezca el mensaje para el administrador
		SeleniumUtils.textoPresentePagina(driver, usuario);
	}
	
	public static void testLoginErroneoParametros(WebDriver driver, 
			String nombreform, String usuario, String contraseña, 
			String textoPresente)
			throws InterruptedException {
		// Vamos a rellenar el formulario
		new PO_AltaForm().rellenaFormularioLogin(driver, usuario, contraseña);

		// Esperamos a que se cargue la pagina del admin
		// concretamente el formulario
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-login", 10);

		Thread.sleep(500);

		// Comprobamos que aparezca el mensaje para el administrador
		SeleniumUtils.textoPresentePagina(driver, textoPresente);
	}
	
}
