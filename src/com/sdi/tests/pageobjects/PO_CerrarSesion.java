package com.sdi.tests.pageobjects;

import org.openqa.selenium.WebDriver;

import com.sdi.tests.utils.SeleniumUtils;

public class PO_CerrarSesion {

	public static void testCerrarSesion(WebDriver driver, String usuario,
			String contraseña) {
		new PO_AltaForm().rellenaFormularioLogin(driver, usuario, contraseña);

		// Esperamos a que cargue la página del admin
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-admin", 10);
		
		// Seleccionamos la opción de menu para cerrar sesión
		SeleniumUtils.ClickSubopcionMenuHover(driver,
				"form-cabecera:configuracion", "form-cabecera:cerrarSesion");
		
		// Esperamos a que cargue la página del login cuando cierra sesión
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-login", 10);
		
		// Comprobamos que estamos en la página de login
		SeleniumUtils.textoPresentePagina(driver, "usuario");
		SeleniumUtils.textoPresentePagina(driver, "contraseña");
		SeleniumUtils.textoPresentePagina(driver, "Iniciar sesión");
	}

}
