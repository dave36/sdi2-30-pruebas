package com.sdi.tests.pageobjects;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.sdi.tests.utils.SeleniumUtils;

public class PO_Usuarios {

	public static void testVisualizarUsuarios(WebDriver driver,
			String nombreform, String usuario, String contraseña) {
		// Vamos a rellenar el formulario
		new PO_AltaForm().rellenaFormularioLogin(driver, usuario, contraseña);

		// Esperamos a que se cargue la pagina del admin
		// concretamente el formulario
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-admin", 10);

		// Comprobamos que aparezca el mensaje para el administrador
		SeleniumUtils.textoPresentePagina(driver, usuario);

		// Pinchamos la opción de menu Usuarios
		SeleniumUtils.ClickSubopcionMenuHover(driver,
				"form-cabecera:submenuOpciones",
				"form-cabecera:menuitemUsuarios");

		// Esperamos a que se cargue la pagina del listado de usuarios
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-usuarios", 10);

		// Comprobamos que aparezca el elemento en la tabla
		SeleniumUtils.textoPresentePagina(driver, usuario);
		SeleniumUtils.textoPresentePagina(driver, "user1");
	}

	public static void testModificarStatus(WebDriver driver, String nombreform,
			String usuario, String contraseña, String status)
			throws InterruptedException {
		// Vamos a rellenar el formulario
		new PO_AltaForm().rellenaFormularioLogin(driver, usuario, contraseña);

		// Esperamos a que se cargue la pagina del admin
		// concretamente el formulario
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-admin", 10);

		// Comprobamos que aparezca el mensaje para el administrador
		SeleniumUtils.textoPresentePagina(driver, usuario);

		// Pinchamos la opción de menu Usuarios
		SeleniumUtils.ClickSubopcionMenuHover(driver,
				"form-cabecera:submenuOpciones",
				"form-cabecera:menuitemUsuarios");

		// Esperamos a que se cargue la pagina del listado de usuarios
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver,
				"id", "form-usuarios:tablalistado:0:filaId", 10);

		// Comprobamos que aparezca el elemento en la tabla
		SeleniumUtils.textoPresentePagina(driver, usuario);
		SeleniumUtils.textoPresentePagina(driver, "user1");

		// Clickamos en el primer elemento de la tabla
		elementos.get(0).click();

		// Cambiamos el status del elemento seleccionado
		By boton = By.id("form-usuarios:modificar");
		driver.findElement(boton).click();

		Thread.sleep(500);

		// Obtenemos el valor de la celda que contiene el status del usuario
		// que acabamos de modificar
		List<WebElement> estado = SeleniumUtils.EsperaCargaPagina(driver, "id",
				"form-usuarios:tablalistado:0:filaStatus", 10);

		// Comprobamos que el estado del usuario pasa a DISABLED
		assertEquals(status, estado.get(0).getText());
	}

	public static void testOrdenar(WebDriver driver, String nombreform,
			String usuario, String contraseña, String elementoAOrdenar,
			String valorEsperado) throws InterruptedException {
		// Vamos a rellenar el formulario
		new PO_AltaForm().rellenaFormularioLogin(driver, usuario, contraseña);

		// Esperamos a que se cargue la pagina del admin
		// concretamente el formulario
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-admin", 10);

		// Comprobamos que aparezca el mensaje para el administrador
		SeleniumUtils.textoPresentePagina(driver, usuario);

		// Pinchamos la opción de menu Usuarios
		SeleniumUtils.ClickSubopcionMenuHover(driver,
				"form-cabecera:submenuOpciones",
				"form-cabecera:menuitemUsuarios");

		// Esperamos a que se cargue la pagina del listado de usuarios
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver,
				"id", elementoAOrdenar, 10);

		// Comprobamos que aparezca el elemento en la tabla
		SeleniumUtils.textoPresentePagina(driver, usuario);
		SeleniumUtils.textoPresentePagina(driver, "user1");

		// Clickamos en el nombre para ordenar por login
		elementos.get(0).click();
		Thread.sleep(500);

		// Obtenemos el valor de la celda que el usuario ordenado
		List<WebElement> login = SeleniumUtils.EsperaCargaPagina(driver, "id",
				"form-usuarios:tablalistado:0:filaNombre", 10);

		// Comprobamos que es el esperado
		assertEquals(valorEsperado, login.get(0).getText());
	}

	public static void testBorrarCuenta(WebDriver driver, String nombreform,
			String usuario, String contraseña, String elementoABorrar,
			String valorEsperado) throws InterruptedException {
		// Vamos a rellenar el formulario
		new PO_AltaForm().rellenaFormularioLogin(driver, usuario, contraseña);

		// Esperamos a que se cargue la pagina del admin
		// concretamente el formulario
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-admin", 10);

		// Comprobamos que aparezca el mensaje para el administrador
		SeleniumUtils.textoPresentePagina(driver, usuario);

		// Pinchamos la opción de menu Usuarios
		SeleniumUtils.ClickSubopcionMenuHover(driver,
				"form-cabecera:submenuOpciones",
				"form-cabecera:menuitemUsuarios");

		// Esperamos a que se cargue la pagina del listado de usuarios
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver,
				"id", elementoABorrar, 10);

		// Clickamos en el nombre del usario a borrar
		elementos.get(0).click();

		// Clickamos en el boton de eliminar usuario
		By boton = By.id("form-usuarios:delete");
		driver.findElement(boton).click();

		// Confirmamos el borrado del usuario
		By botonSi = By.id("form-usuarios:si");
		driver.findElement(botonSi).click();
		
		// Esperamos a que se borre el user3
		Thread.sleep(500);

		// Comprobamos que no aparezca el usuario3 en la tabla
		SeleniumUtils.textoNoPresentePagina(driver, "user3");
	}

	public static void testRegistroCorrecto(WebDriver driver, String usuario,
			String email, String contraseña, String contraseña2)
			throws InterruptedException {
		// Clickamos en el link del registro
		SeleniumUtils.ClickLink(driver, "linkRegistro");

		// Esperamos a que avance la página al clickar el link
		Thread.sleep(500);

		// Rellenamos el formulario del registro
		new PO_AltaForm().rellenaFormularioRegistro(driver, usuario, email,
				contraseña, contraseña2);

		// Esperamos a que se cargue la pagina de login
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-login", 10);

		// Comprobamos que estamos en la ventana del login
		// y aparezca el login
		SeleniumUtils.textoPresentePagina(driver, "Inicio de sesión");
		SeleniumUtils.textoPresentePagina(driver, "Usuario");
		SeleniumUtils.textoPresentePagina(driver, "Contraseña");
	}

	public static void testRegistroIncorrecto(WebDriver driver, String usuario,
			String email, String contraseña, String contraseña2,
			String mensajeError) throws InterruptedException {
		// Clickamos en el link del registro
		SeleniumUtils.ClickLink(driver, "linkRegistro");

		// Esperamos a que avance la página al clickar el link
		Thread.sleep(500);

		// Rellenamos el formulario del registro
		new PO_AltaForm().rellenaFormularioRegistro(driver, usuario, email,
				contraseña, contraseña2);

		// Esperamos a que se cargue la pagina de login
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-registro", 10);

		Thread.sleep(500);

		// Comprobamos que se produce un error al estar mal el registro
		SeleniumUtils.textoPresentePagina(driver, mensajeError);
	}

}
