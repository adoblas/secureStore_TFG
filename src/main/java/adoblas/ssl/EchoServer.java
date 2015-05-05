package adoblas.ssl;

/*

 El servidor carga el fichero AlmacenSR donde est� un certificado autofirmado creado por el servidor con
 la herramienta Keytool (key entry)

 keytool -genkey -alias CertificadoAutofirmado  -keyalg RSA -validity "100" -keystore AlmacenSR -keypass oooooo -storepass oooooo

 El servidor exporta el certificado con Keytool

 keytool -export -alias CertificadoAutofirmado -keystore AlmacenSR -rfc -file CertAutofirmado.cer

 y finalmente crea un almacen trust con el certificado de clave publica (sin clave privada)

 keytool -import -alias CertificadoAutofirmado -file CertAutofirmado.cer -keystore AlmacenTrust

 El fichero AlmacenTrust  se le da al cliente



 */

import java.net.*; // Para ServerSocket
import java.nio.file.Paths;
import java.util.*;

import javax.net.ssl.*; // Para SSLSockets

import org.slf4j.Logger;

import java.io.*; //ENTRADA SALIDA

public class EchoServer implements Runnable {

	public static void main() {
		PrintStream p;

		System.out.println("\n\n");

		System.out
				.println("-----------------------------------------------------------------------------|");
		System.out
				.println("|                  SERVIDOR SSL SIN AUTENTICACION DE CLIENTE                 |");
		System.out
				.println("|        CARGADO CON PROPERTIES EL ALMACEN DEL SERVIDOR  (almacenSR)         |");
		System.out
				.println("|                      CON UN CERTIFICADO FIRMADO   CA                       |");
		System.out
				.println("|                           ESTABLECIMIENTO CHIPHERSUITES                    |");
		System.out
				.println("|                        MAQUINA CERTIFICADOS v1.7                           |");
		System.out
				.println("|                     (C) Luis Mengual. Junio 2014                           |");
		System.out
				.println("-----------------------------------------------------------------------------|");

		System.out.println("\n");

		System.setProperty("javax.net.debug", "ssl");

		System.setProperty("javax.net.ssl.keyStore",
				System.getProperty("user.dir")
						+ "/src/main/resources/AlmacenSR");

		// System.setProperty(
		// "javax.net.ssl.keyStore",
		// ClassLoader.getSystemClassLoader().getResourceAsStream("AlmacenSR").toString());

		System.setProperty("javax.net.ssl.keyStorePassword", "oooooo");

		SSLServerSocketFactory sslserversocketfactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();

		SSLServerSocket sslserversocket = null;
		try {
			sslserversocket = (SSLServerSocket) sslserversocketfactory.createServerSocket(9999);
			System.out.println("********* SOCKET CREADO ***************");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("********* ERROR AL CREAR SOCKETFACTORY ***************");
		}

		System.out.println("SERVIDOR ECO ESPERANDO PTO 9999 ....... ");

		// OBTENEMOS TODAS LA FAMILIAS DE CIFRADORES

		String[] cifradores = sslserversocket.getSupportedCipherSuites();
		for (int i = 0; i < cifradores.length; i++) {
			System.out.println("support:  " + cifradores[i]);
		}
		
		System.out.print("***** Familia Negociada en la sesion....");

		System.out.print("Selecionar familia: (s/n)");
		Scanner sc1 = new Scanner(System.in);
		String str1 = sc1.nextLine();

		String s = "s";
		String n = "n";

		
		if (s.equals(str1))

		{
			System.out.print("Familia Cifradores:");
			Scanner sc = new Scanner(System.in);
			String familia_cifradores_str = sc.nextLine();
			int familia_cifradores = Integer.parseInt(familia_cifradores_str);

			// SELECCIONAMOS UNA OPCION DE CIFRADORES
			// String [] mycifrador = {cifradores[2]};

			String[] mycifrador = { cifradores[familia_cifradores] };

			sslserversocket.setEnabledCipherSuites(mycifrador);
			System.out.println("\n");

			// OBTENEMOS LA OPCION SELECCIONADA
			String[] cifradores_enable = sslserversocket
					.getEnabledCipherSuites();
			for (int i = 0; i < cifradores_enable.length; i++) {
				System.out.println("enable: " + cifradores_enable[i]);

			}

		}
		// if

		else {

			System.out.println("Familia Negociada en la Sesion \n");

		}

		// SERVIDOR SIN AUTENTICACION DEL CLIENTE

		// sslserversocket.setNeedClientAuth(true);

		System.out.println("\n");

		while (true)

		{

			try {//SSL_RSA_WITH_RC4_128_SHA

				SSLSocket sslsocket = (SSLSocket) sslserversocket.accept();

				OutputStream Flujo_salida = sslsocket.getOutputStream();
				InputStream Flujo_entrada = sslsocket.getInputStream();

				DataOutputStream Flujo_s = new DataOutputStream(Flujo_salida);
				DataInputStream Flujo_e = new DataInputStream(Flujo_entrada);

				sslsocket.getSupportedCipherSuites();

				SSLSession sesion = sslsocket.getSession();

				System.out.println("Host: " + sesion.getPeerHost());
				System.out
						.println("getCipherSuite: " + sesion.getCipherSuite());
				System.out.println("PacketBufferSize: "
						+ sesion.getPacketBufferSize());

				// SERVIDOR SIN AUTENTICACION DEL CLIENTE

				/*
				 * String mensaje_rev = Flujo_e.readUTF();
				 * System.out.println(mensaje_rev);
				 */

				long long_req = Flujo_e.readLong();

				System.out.println("LONGITUD_REQ RECIBIDA: " + long_req
						+ " Bytes");

				// creo espacio para leer fichero
				byte[] buffer_req_SR = new byte[(int) long_req];

				// RECIBIMOS EL FICHERO

				/*
				 * int NumBytes_req_SR =0; NumBytes_req_SR
				 * =Flujo_e.read(buffer_req_SR);
				 * 
				 * 
				 * 
				 * System.out.println("RECIBIDO FICHERO: "+NumBytes_req_SR+
				 * " Bytes");
				 * 
				 * //IMPRIMIMOS EL FICHERO EN EL SERVIDOR
				 * 
				 * System.out.println("IMPRIMIMOS FICHERO req_recv.pem: ");
				 * FileOutputStream Fichero_req_SR= new
				 * FileOutputStream("req_recv.pem");
				 * 
				 * Fichero_req_SR.write(buffer_req_SR); Fichero_req_SR.close();
				 */

				int NumBytesLeidos_SR = 0;
				long long_recibida_fich = 0;

				FileOutputStream Fichero_req_SR = new FileOutputStream(
						"req_recv.pem");

				do

				{

					NumBytesLeidos_SR = Flujo_e.read(buffer_req_SR);

					long_recibida_fich = long_recibida_fich + NumBytesLeidos_SR;

					Fichero_req_SR.write(buffer_req_SR, 0, NumBytesLeidos_SR);

					System.out.println("Recibiendo Fichero ...");

					System.out.println("NumBytesLeidos : " + NumBytesLeidos_SR);
					System.out.println("ENVIADO HASTA AHORA: "
							+ long_recibida_fich);

				} while (long_recibida_fich < long_req);

				System.out.println("FICHERO IMPRESO DE LONGITUD: "
						+ long_recibida_fich);

				Fichero_req_SR.close();

				libreria_openssl_098i una_libreria_openssl_098i = new libreria_openssl_098i();
				una_libreria_openssl_098i.crear_firma("cacert.p12", "ppppp",
						"req_recv.pem", "cert_user.pem");

				// Meter codigo busqueda archivo en carpeta CA

				FileWriter FichSalida = new FileWriter("log_certs.txt");
				BufferedWriter fs = new BufferedWriter(FichSalida);

				ArrayList<String> Resultado = new ArrayList<String>();

				String tmp;
				String sDirectorio = System.getProperty("user.dir")
						+ "/src/main/resources/CA/newcerts";

				File ftemp = new File(".");
				File[] ficherostemp = ftemp.listFiles();

				System.out.println("ficherostemp.length" + ficherostemp.length);

				File f = new File(sDirectorio);

				File[] ficheros = f.listFiles();

				System.out.println("ficheros.length" + ficheros.length);
				int x;
				for (x = 0; x < ficheros.length; x++) {
					// System.out.println(ficheros[x].getName());
					tmp = ficheros[x].getName();
					System.out.println(tmp);
					Resultado.add(tmp);

					// System.out.println(x);
				}

				System.out.println("\n");
				System.out.println("Total Ficheros:" + x);

				// System.out.println("Imprimo ArrayList\n");

				Iterator<String> it = Resultado.iterator();
				while (it.hasNext()) {
					// System.out.println(it.next());
					fs.write(it.next());
					fs.write("\n");
				}

				fs.write("\n");
				fs.write("Total Ficheros: " + x);

				fs.close();
				int aux = x - 1;
				System.out.println("Imprimo ultimo fichero: "
						+ ficheros[aux].getName());

				// codigo buscar certificado en carpeta newcerts

				//String mycert = ".\\CA\\newcerts\\" + ficheros[aux].getName();
				String mycert = System.getProperty("user.dir") + "/src/main/resources/CA/newcerts/" + ficheros[aux].getName();
				
				
				System.out.println("mycert: " + mycert);

				// File mi_fichero = new File ( "cert_user.pem" );

				File mi_fichero = new File(mycert);

				long tamano_cert_user = mi_fichero.length();
				System.out.println("tamano calculadofich cert_user.pem: "
						+ tamano_cert_user);

				// envio longitud fichero

				Flujo_s.writeLong(tamano_cert_user);
				System.out.println("ENVIO LONGITUD FICHERO cert_user.pem: "
						+ tamano_cert_user);

				// creo espacio para leer fichero
				byte[] buffer_cert_user = new byte[(int) tamano_cert_user];

				// LEEMOS EL FICHERO cert_user.pem

				// FileInputStream Fichero_cert_user = new
				// FileInputStream("cert_user.pem");

				FileInputStream Fichero_cert_user = new FileInputStream(mycert);
				int NumBytesLeidos_cert_user = Fichero_cert_user
						.read(buffer_cert_user);
				Fichero_cert_user.close();
				System.out.println("LEEMOS FICHERO cert_user.pem ");
				// enviamos fichero

				Flujo_s.write(buffer_cert_user);
				System.out.println("ENVIAMOS FICHERO cert_user.pem ");

			}// try

			catch (Exception e) {
				System.out.println("ERROR CAPTURADO:" + e.toString());
			}

		}// while

	}// main

	@Override
	public void run() {
		main();

	}
}// public