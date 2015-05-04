package adoblas.ssl;

/*


 El cliente SSL carga el fichero AlmacenTrust creado  con la herramienta Keytool conteniendo el
 certificado de clave p�blica del servidor


 */

import java.net.*;
import java.io.*;

import javax.net.ssl.*;

import java.security.cert.X509Certificate;
import java.util.*;

public class EchoClient {
	public static void

	main(String[] arstring) {
		try {

			String dirip;

			System.setProperty("javax.net.debug", "ssl");

			System.setProperty("javax.net.ssl.trustStore", "AlmacenTrust");

			// esto no hace falta
			// System.setProperty("javax.net.ssl.keyStorePassword", "oooooo");

			System.out.println("\n\n\n");

			System.out
					.println("--------------------------------------------------------------------------|");
			System.out
					.println("|                  CLIENTE SSL SIN AUTENTICACION                          |");
			System.out
					.println("|        CARGADO CON PROPERTIES EL ALMACEN AlmacenTrust                   |");
			System.out
					.println("|       CON UN CERTIFICADO DE CA  PARA AUTENTICAR AL SERVIDOR             |");
			System.out
					.println("|                   MAQUINA CERTIFICADOS v1.7                             |");
			System.out
					.println("|                   (C) Luis Mengual. Junio 2014                          |");
			System.out
					.println("|                                                                         |");
			System.out
					.println("|                                                                         |");
			System.out
					.println("--------------------------------------------------------------------------|");

			System.out.println("\n\n\n");

			InputStreamReader Flujo = new InputStreamReader(System.in);
			BufferedReader teclado = new BufferedReader(Flujo);
			System.out.print("Direccion IP: ");
			dirip = teclado.readLine();
			// System.out.println("la direccion IP metida es:"+dirip );

			System.out.print("Puerto:");
			Scanner sc = new Scanner(System.in);
			String pto = sc.nextLine();
			int ptoint = Integer.parseInt(pto);

			SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory
					.getDefault();

			SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket(
					dirip, ptoint);
			System.out.println("CREADO SOCKET SSL");

			OutputStream Flujo_salida = sslsocket.getOutputStream();
			InputStream Flujo_entrada = sslsocket.getInputStream();

			DataOutputStream Flujo_s = new DataOutputStream(Flujo_salida);
			DataInputStream Flujo_e = new DataInputStream(Flujo_entrada);

			String string = null;

			SSLSession sesion = sslsocket.getSession();

			System.out.println("getCipherSuite: " + sesion.getCipherSuite());
			System.out.println("PeerHost: " + sesion.getPeerHost());
			System.out.println("protocol: " + sesion.getProtocol());

			System.out.println("Host: " + sesion.getPeerHost());

			System.out.println("\n\n\n");

			System.out
					.println("**********************************************************************");

			System.out.println("CERTIFICADO RECIBIDO DEL SERVIDOR");
			System.out.println("\n");

			System.out.println("Host: " + sesion.getPeerHost());

			X509Certificate certificate = (X509Certificate) sesion
					.getPeerCertificates()[0];

			System.out.println("Propietario: " + certificate.getSubjectDN());
			System.out.println("Emisor: " + certificate.getIssuerDN());
			System.out
					.println("Numero Serie: " + certificate.getSerialNumber());
			System.out.println("to string: " + certificate.toString());

			System.out
					.println("**********************************************************************");
			System.out.println("\n\n\n");

			// IMPRIMIMOS EL CERTIFICADO

			byte[] buf = certificate.getEncoded();
			FileOutputStream os = new FileOutputStream("server.cer");

			os.write(buf);
			os.close();

			/*
			 * String mensaje = "mensaje cliente"; Flujo_s.writeUTF(mensaje);
			 */

			libreria_openssl_098i una_libreria_openssl_098i = new libreria_openssl_098i();
			una_libreria_openssl_098i.crear_peticion();

			File mi_fichero = new File("req.pem");
			long tamano_req = mi_fichero.length();
			System.out.println("Tamano calculado fich req.pem: " + tamano_req
					+ " Bytes");

			// envio longitud fichero req.pem
			System.out.println("envio longitud fichero: " + tamano_req
					+ " Bytes");
			Flujo_s.writeLong(tamano_req);

			// creo espacio para leer fichero
			byte[] buffer_req = new byte[(int) tamano_req];

			// LEEMOS EL FICHERO REQ.PEM
			FileInputStream FicheroReq = new FileInputStream("req.pem");
			int NumBytesLeidos = FicheroReq.read(buffer_req);
			FicheroReq.close();

			// enviamos fichero
			System.out.println("envio  fichero req.pem: ");
			Flujo_s.write(buffer_req);

			long num_recibido_cert_user = Flujo_e.readLong();
			System.out.println("LONG RECIBIDA cert_user.pem....... "
					+ num_recibido_cert_user);

			// creo espacio para leer fichero
			byte[] buffer_cert_user_CL = new byte[(int) num_recibido_cert_user];

			// RECIBIMOS EL FICHERO

			int NumBytesLeidos_SR = 0;
			long long_recibida_fich = 0;

			FileOutputStream Fichero_req_SR = new FileOutputStream(
					"cert_user_CL.pem");

			do

			{

				NumBytesLeidos_SR = Flujo_e.read(buffer_cert_user_CL);

				long_recibida_fich = long_recibida_fich + NumBytesLeidos_SR;

				Fichero_req_SR.write(buffer_cert_user_CL, 0, NumBytesLeidos_SR);

				System.out.println("Recibiendo Fichero ...");

				System.out.println("NumBytesLeidos : " + NumBytesLeidos_SR);
				System.out
						.println("ENVIADO HASTA AHORA: " + long_recibida_fich);

			} while (long_recibida_fich < num_recibido_cert_user);

			System.out.println("FICHERO IMPRESO DE LONGITUD: "
					+ long_recibida_fich);

			Fichero_req_SR.close();

			System.out.println("RECIBIMOS FICHERO cert_user.pem....... ");

			System.out.println("IMPRIMIMOS FICHERO cert_user.pem....... ");

			// libreria_openssl_098i una_libreria_openssl_098i = new
			// libreria_openssl_098i();
			una_libreria_openssl_098i.convertir_p12("cert_user_CL.pem",
					"privkey_user.pem", "ppppp", "cert_user.p12");

			System.out.println("IMPRIMIMOS FICHERO cert_user.p12....... ");

			System.out.println("\n");

			InputStreamReader Flujo2 = new InputStreamReader(System.in);
			BufferedReader teclado2 = new BufferedReader(Flujo2);
			System.out.print("Introducir una Tecla para  Salir:  ");
			String aux_dirip = teclado2.readLine();

		}

		catch (Exception e) {
			System.out.println("ERROR CAPTURADO:" + e.toString());
		}

	}// main

}// public