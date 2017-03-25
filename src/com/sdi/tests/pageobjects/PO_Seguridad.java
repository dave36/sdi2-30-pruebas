package com.sdi.tests.pageobjects;

import org.openqa.selenium.WebDriver;

import com.sdi.tests.utils.SeleniumUtils;

public class PO_Seguridad {

	public static void testSeguridadUsuarioAutenticado(WebDriver driver,
			String usuario, String contraseña) {
		new PO_AltaForm().rellenaFormularioLogin(driver, usuario, contraseña);
		
		// Esperamos a que se cargue la pagina del usuario
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-usuario", 10);
		
		// Intentamos acceder a la ventana del admin estando registrados
		// como un usuario normal
		driver.get("http://localhost:8280/sdi2-30/admin/principalAdmin.xhtml");

		// Comprobamos que estamos en la ventana del login
		SeleniumUtils.textoPresentePagina(driver, "Inicio de sesión");
		SeleniumUtils.textoPresentePagina(driver, "Usuario");
		SeleniumUtils.textoPresentePagina(driver, "Contraseña");
	}
	
	public static void testSeguridadUsuarioNoAutenticado(WebDriver driver) {
		// Intentamos acceder a la página del usuario sin habernos registrado
		driver.get("http://localhost:8280/sdi2-30/admin/principalUsuario.xhtml");

		// Comprobamos que no podemos acceder y estamos en la ventana de login
		SeleniumUtils.textoPresentePagina(driver, "Inicio de sesión");
		SeleniumUtils.textoPresentePagina(driver, "Usuario");
		SeleniumUtils.textoPresentePagina(driver, "Contraseña");
	}

}
