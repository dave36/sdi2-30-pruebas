package com.sdi.tests.Tests;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import com.sdi.tests.pageobjects.PO_AltaForm;
import com.sdi.tests.utils.SeleniumUtils;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SDI2_30Tests {

	WebDriver driver;
	List<WebElement> elementos = null;

	public SDI2_30Tests() {
	}

	@Before
	public void run() {
		// Este código es para ejecutar con la versión portale de Firefox 46.0
		File pathToBinary = new File("S:\\firefox\\FirefoxPortable.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		driver = new FirefoxDriver(ffBinary, firefoxProfile);
		driver.get("http://localhost:8280/sdi2-30/");
		// Este código es para ejecutar con una versión instalada de Firex 46.0
		// driver = new FirefoxDriver();
		// driver.get("http://localhost:8180/sdi2-n");
	}

	@After
	public void end() {
		// Cerramos el navegador
		driver.quit();
	}

	// PRUEBAS
	// ADMINISTRADOR
	// PR01: Autentificar correctamente al administrador.
	// @Test
	// public void prueba01() {
	// testLoginParametros("form-login", "administrador1", "administrador1");
	// }

	public void testLoginParametros(String nombreform, String usuario,
			String contraseña) {
		// Vamos a rellenar el formulario
		new PO_AltaForm().rellenaFormularioLogin(driver, usuario, contraseña);

		// Esperamos a que se cargue la pagina del admin
		// concretamente el formulario
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-admin", 10);

		// Comprobamos que aparezca el mensaje para el administrador
		SeleniumUtils.textoPresentePagina(driver, usuario);
	}

	// PR02: Fallo en la autenticación del administrador por introducir mal el
	// login.
	@Test
	public void prueba02() {
		testLoginErroneoParametros("form-login", "administradormal",
				"administrador1");
	}

	// PR03: Fallo en la autenticación del administrador por introducir mal la
	// password.
	@Test
	public void prueba03() {
		testLoginErroneoParametros("form-login", "administrador1",
				"administradormal");
	}

	public void testLoginErroneoParametros(String nombreform, String usuario,
			String contraseña) {
		// Vamos a rellenar el formulario
		new PO_AltaForm().rellenaFormularioLogin(driver, usuario, contraseña);

		// Esperamos a que se cargue la pagina del admin
		// concretamente el formulario
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-admin", 10);

		// Comprobamos que aparezca el mensaje para el administrador
		SeleniumUtils.textoPresentePagina(driver, "error");
	}

	// PR04: Probar que la base de datos contiene los datos insertados con
	// conexión correcta a la base de datos.
	@Test
	public void prueba04() {
		assertTrue(false);
	}

	// PR05: Visualizar correctamente la lista de usuarios normales.
	// @Test
	// public void prueba05() {
	// testVisualizarUsuarios("form-login", "administrador1", "administrador1");
	// }

	public void testVisualizarUsuarios(String nombreform, String usuario,
			String contraseña) {
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
		SeleniumUtils.textoPresentePagina(driver, "usuario1");
	}

	// PR06: Cambiar el estado de un usuario de ENABLED a DISABLED. Y tratar de
	// entrar con el usuario que se desactivado.
	@Test
	public void prueba06() {
		testModificarStatus("form-login", "administrador1", "administrador1",
				"DISABLED");
		testLoginErroneoParametros("form-login", "usuario1", "usuario1");
	}

	public void testModificarStatus(String nombreform, String usuario,
			String contraseña, String status) {
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
		SeleniumUtils.textoPresentePagina(driver, "usuario1");

		// Clickamos en el primer elemento de la tabla
		elementos.get(0).click();

		// Cambiamos el status del elemento seleccionado
		By boton = By.id("form-usuarios:modificar");
		driver.findElement(boton).click();

		// Obtenemos el valor de la celda que contiene el status del usuario
		// que acabamos de modificar
		List<WebElement> estado = SeleniumUtils.EsperaCargaPagina(driver, "id",
				"form-usuarios:tablalistado:0:filaStatus", 10);

		// Comprobamos que el estado del usuario pasa a DISABLED
		assertEquals(status, estado.get(0).getText());
	}

	// PR07: Cambiar el estado de un usuario a DISABLED a ENABLED. Y Y tratar de
	// entrar con el usuario que se ha activado.
	@Test
	public void prueba07() {
		testModificarStatus("form-login", "administrador1", "administrador1",
				"ENABLED");
		testLoginParametros("form-login", "usuario1", "usuario1");
	}

	// PR08: Ordenar por Login
	@Test
	public void prueba08() throws InterruptedException {
		testOrdenar("form-login", "administrador1", "administrador1",
				"form-usuarios:tablalistado:headerNombre", "administrador1");
	}

	public void testOrdenar(String nombreform, String usuario,
			String contraseña, String elementoAOrdenar, String valorEsperado)
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
				"id", elementoAOrdenar, 10);

		// Comprobamos que aparezca el elemento en la tabla
		SeleniumUtils.textoPresentePagina(driver, usuario);
		SeleniumUtils.textoPresentePagina(driver, "usuario1");

		// Clickamos en el nombre para ordenar por login
		elementos.get(0).click();
		Thread.sleep(500);

		// Obtenemos el valor de la celda que contiene el status del usuario
		// que acabamos de modificar
		List<WebElement> login = SeleniumUtils.EsperaCargaPagina(driver, "id",
				"form-usuarios:tablalistado:0:filaNombre", 10);

		// Comprobamos que el estado del usuario pasa a DISABLED
		assertEquals(valorEsperado, login.get(0).getText());
	}

	// PR09: Ordenar por Email
	@Test
	public void prueba09() throws InterruptedException {
		testOrdenar("form-login", "administrador1", "administrador1",
				"form-usuarios:tablalistado:headerEmail", "administrador1");
	}

	// PR10: Ordenar por Status
	@Test
	public void prueba10() throws InterruptedException {
		testOrdenar("form-login", "administrador1", "administrador1",
				"form-usuarios:tablalistado:headerStatus", "usuario1");
	}

	// PR11: Borrar una cuenta de usuario normal y datos relacionados.
	@Test
	public void prueba11() throws InterruptedException {
		testBorrarCuenta("form-login", "administrador1", "administrador1",
				"form-usuarios:tablalistado:2:filaId", "");
	}

	public void testBorrarCuenta(String nombreform, String usuario,
			String contraseña, String elementoABorrar, String valorEsperado)
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
				"id", elementoABorrar, 10);

		// Clickamos en el nombre para ordenar por login
		elementos.get(0).click();

		// Cambiamos el status del elemento seleccionado
		By boton = By.id("form-usuarios:delete");
		driver.findElement(boton).click();

		// Esperamos a que se borre el usuario3
		Thread.sleep(500);

		// Comprobamos que no aparezca el usuario3 en la tabla
		SeleniumUtils.textoNoPresentePagina(driver, "usuario3");
	}

	// PR12: Crear una cuenta de usuario normal con datos válidos.
	@Test
	public void prueba12() throws InterruptedException {
		testRegistroCorrecto("usuarioprueba1", "usuarioprueba1@gmail.com",
				"usuarioprueba1", "usuarioprueba1");
	}

	public void testRegistroCorrecto(String usuario, String email,
			String contraseña, String contraseña2) throws InterruptedException {
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
		SeleniumUtils.textoPresentePagina(driver, "Iniciar sesión");
		SeleniumUtils.textoPresentePagina(driver, "Usuario");
		SeleniumUtils.textoPresentePagina(driver, "Contraseña");
	}

	// PR13: Crear una cuenta de usuario normal con login repetido.
	@Test
	public void prueba13() throws InterruptedException {
		testRegistroIncorrecto("usuarioprueba1", "usuarioprueba1@gmail.com",
				"usuarioprueba1", "usuarioprueba1");
	}

	public void testRegistroIncorrecto(String usuario, String email,
			String contraseña, String contraseña2) throws InterruptedException {
		// Clickamos en el link del registro
		SeleniumUtils.ClickLink(driver, "linkRegistro");

		// Esperamos a que avance la página al clickar el link
		Thread.sleep(500);

		// Rellenamos el formulario del registro
		new PO_AltaForm().rellenaFormularioRegistro(driver, usuario, email,
				contraseña, contraseña2);

		// Esperamos a que se cargue la pagina de login
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-login", 10);

		// Comprobamos que se produce un error al estar repetido el usuario
		SeleniumUtils.textoPresentePagina(driver, "error");

	}

	// PR14: Crear una cuenta de usuario normal con Email incorrecto.
	@Test
	public void prueba14() throws InterruptedException {
		testRegistroIncorrecto("usuariopruebaemail", "usuarioprueba1@gmail.com", 
				"usuariopruebaemail1", "usuariopruebaemail1");
	}

	// PR15: Crear una cuenta de usuario normal con Password incorrecta.
	@Test
	public void prueba15() throws InterruptedException {
		testRegistroIncorrecto("usuarioprueba1", "emailincorrecto",
				"usuarioprueba1", "usuarioprueba2");
	}

	// USUARIO
	// PR16: Comprobar que en Inbox sólo aparecen listadas las tareas sin
	// categoría y que son las que tienen que. Usar paginación navegando por las
	// tres páginas.
	@Test
	public void prueba16() {
		assertTrue(false);
	}

	// PR17: Funcionamiento correcto de la ordenación por fecha planeada.
	@Test
	public void prueba17() {
		assertTrue(false);
	}

	// PR18: Funcionamiento correcto del filtrado.
	@Test
	public void prueba18() {
		assertTrue(false);
	}

	// PR19: Funcionamiento correcto de la ordenación por categoría.
	@Test
	public void prueba19() {
		assertTrue(false);
	}

	// PR20: Funcionamiento correcto de la ordenación por fecha planeada.
	@Test
	public void prueba20() {
		assertTrue(false);
	}

	// PR21: Comprobar que las tareas que no están en rojo son las de hoy y
	// además las que deben ser.
	@Test
	public void prueba21() {
		assertTrue(false);
	}

	// PR22: Comprobar que las tareas retrasadas están en rojo y son las que
	// deben ser.
	@Test
	public void prueba22() {
		assertTrue(false);
	}

	// PR23: Comprobar que las tareas de hoy y futuras no están en rojo y que
	// son las que deben ser.
	@Test
	public void prueba23() {
		assertTrue(false);
	}

	// PR24: Funcionamiento correcto de la ordenación por día.
	@Test
	public void prueba24() {
		assertTrue(false);
	}

	// PR25: Funcionamiento correcto de la ordenación por nombre.
	@Test
	public void prueba25() {
		assertTrue(false);
	}

	// PR26: Confirmar una tarea, inhabilitar el filtro de tareas terminadas, ir
	// a la pagina donde está la tarea terminada y comprobar que se muestra.
	@Test
	public void prueba26() {
		assertTrue(false);
	}

	// PR27: Crear una tarea sin categoría y comprobar que se muestra en la
	// lista Inbox.
	@Test
	public void prueba27() {
		assertTrue(false);
	}

	// PR28: Crear una tarea con categoría categoria1 y fecha planeada Hoy y
	// comprobar que se muestra en la lista Hoy.
	@Test
	public void prueba28() {
		assertTrue(false);
	}

	// PR29: Crear una tarea con categoría categoria1 y fecha planeada posterior
	// a Hoy y comprobar que se muestra en la lista Semana.
	@Test
	public void prueba29() {
		assertTrue(false);
	}

	// PR30: Editar el nombre, y categoría de una tarea (se le cambia a
	// categoría1) de la lista Inbox y comprobar que las tres pseudolista se
	// refresca correctamente.
	@Test
	public void prueba30() {
		assertTrue(false);
	}

	// PR31: Editar el nombre, y categoría (Se cambia a sin categoría) de una
	// tarea de la lista Hoy y comprobar que las tres pseudolistas se refrescan
	// correctamente.
	@Test
	public void prueba31() {
		assertTrue(false);
	}

	// PR32: Marcar una tarea como finalizada. Comprobar que desaparece de las
	// tres pseudolistas.
	@Test
	public void prueba32() {
		assertTrue(false);
	}

	// PR33: Salir de sesión desde cuenta de administrador.
	@Test
	public void prueba33() {
		assertTrue(false);
	}

	// PR34: Salir de sesión desde cuenta de usuario normal.
	@Test
	public void prueba34() {
		assertTrue(false);
	}

	// PR35: Cambio del idioma por defecto a un segundo idioma. (Probar algunas
	// vistas)
	@Test
	public void prueba35() {
		assertTrue(false);
	}

	// PR36: Cambio del idioma por defecto a un segundo idioma y vuelta al
	// idioma por defecto. (Probar algunas vistas)
	@Test
	public void prueba36() {
		assertTrue(false);
	}

	// PR37: Intento de acceso a un URL privado de administrador con un usuario
	// autenticado como usuario normal.
	@Test
	public void prueba37() {
		assertTrue(false);
	}

	// PR38: Intento de acceso a un URL privado de usuario normal con un usuario
	// no autenticado.
	@Test
	public void prueba38() {
		assertTrue(false);
	}

}