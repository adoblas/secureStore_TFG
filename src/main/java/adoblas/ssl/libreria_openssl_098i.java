package adoblas.ssl;


// crear_firma( "cacert.p12",  "ppppp", "req.pem", "cert_user.pem");
//convertir_p12( "cert_user.pem", "privkey_user.pem", "ppppp", "cert_user.p12");
//crear_peticion();

public class libreria_openssl_098i {

	/*
	 * 
	 * convertir_p12( "cert_user.pem", "privkey_user.pem", "ppppp",
	 * "cert_user.p12");
	 * 
	 * Funci�n que genera un fichero en formato p.12 conteniendo el certificado
	 * de usuario y su clave privada
	 * 
	 * PARAMETROS: Certificado de usuario en formato pem(certpem); Clave privada
	 * del usuario en formato pem(priveypem), Clave para proteger el fichero
	 * .p12 (clave), Certificado de Usuario formato .p12 (certp12);
	 */

	public native void convertir_p12(String certpem, String priveypem,
			String clave, String certp12);

	static {
		System.loadLibrary("libreria_openssl_098i");

	}

	/*
	 * 
	 * crear_firma( "cacert.p12", "ppppp", "req.pem", "cert_user.pem");
	 * 
	 * Funci�n que genera un certificado de usuario firmado por una CA a partir
	 * de un fichero "req.pem" que contiene la
	 * "Certificate Signing Request, CSR"
	 * 
	 * 
	 * PARAMETROS: Certificado CA en formato *.p12(namep12); Clave fichero p.12
	 * anterior(claveca), fichero req.pem solicitud usuario (nameReq), fichero
	 * certificado usuario firmado por CA (certuser);
	 */

	public native void crear_firma(String namep12, String claveca,
			String nameReq, String certuser);

	static {
		System.loadLibrary("libreria_openssl_098i");
	}

	/*
	 * 
	 * crear_peticion();
	 * 
	 * Funci�n que genera una "Certificate Signing Request, CSR". La imprime en
	 * un fichero "req.pem". Tambien genera los ficheros privkey_user.pem y
	 * publickey_user.pem correspondientes a la clave privada y p�blica del
	 * usuario
	 */

	public native void crear_peticion();

	static {
		System.loadLibrary("libreria_openssl_098i");
	}

	public static void main(String[] args) {

		libreria_openssl_098i una_libreria_openssl_098i = new libreria_openssl_098i();
		una_libreria_openssl_098i.convertir_p12("cert_user.pem",
				"privkey_user.pem", "ppppp", "cert_user.p12");

	}

}