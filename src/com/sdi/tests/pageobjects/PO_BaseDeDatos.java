package com.sdi.tests.pageobjects;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.sdi.tests.utils.SeleniumUtils;

import static org.junit.Assert.assertEquals;

public class PO_BaseDeDatos {

	private static Properties propiedades = new Properties();
	private static InputStream entrada;
	private static String URL = "jdbc/:hsqldb/:hsql/://localhost";
	private static String user = "sa";
	private static String password = "";

	public static void connect() {
		try {
			entrada = new FileInputStream("database.properties");
			propiedades.load(entrada);

			// obtenemos las propiedades
			URL = propiedades.getProperty("DATABASE_URL");
			user = propiedades.getProperty("DATABASE_USER");
			password = propiedades.getProperty("DATABASE_PASSWORD");

		} catch (IOException ex) {
			ex.printStackTrace();

		} finally {
			if (entrada != null) {
				try {
					entrada.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void testComprobarDatosEnLaBase(WebDriver driver)
			throws SQLException, InterruptedException {
		new PO_AltaForm().rellenaFormularioLogin(driver, "admin1", "admin1");

		// Esperamos a que cargue la página del admin
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-admin", 10);

		// Clickamos en la opción de cargar la base de datos
		SeleniumUtils.ClickSubopcionMenuHover(driver,
				"form-cabecera:submenuOpciones",
				"form-cabecera:menuitemRecargarBD");

		Thread.sleep(500);

		SeleniumUtils.ClickSubopcionMenuHover(driver,
				"form-cabecera:submenuOpciones",
				"form-cabecera:menuitemUsuarios");
		
		// Esperamos a que cargue la página del listado
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-usuarios", 10);
		
		SeleniumUtils.textoPresentePagina(driver, "user1");
		SeleniumUtils.textoPresentePagina(driver, "user2");
		SeleniumUtils.textoPresentePagina(driver, "user3");
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, user, password);
	}

	public static void testCompruebaUsuarios() throws SQLException {
		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement("SELECT login FROM TUSERS");
		ResultSet rs = ps.executeQuery();

		List<String> usuarios = new ArrayList<>();
		while (rs.next()) {
			usuarios.add(rs.getString("login"));
		}
		assertEquals(true, usuarios.get(0).contains("admin1"));
		assertEquals(true, usuarios.get(1).contains("user1"));
	}

}
