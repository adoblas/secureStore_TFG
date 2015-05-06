package adoblas.ssl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.Principal;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class Client {
  public Client() throws Exception {
    SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();

    String hostName = "127.0.0.1";
    String fileName = System.getProperty("user.dir") + "/src/main/resources/AlmacenTrust";

    SSLSocket sslsock = (SSLSocket) factory.createSocket(hostName, 443);

    SSLSession session = sslsock.getSession();
    X509Certificate cert;
    try {
      cert = (X509Certificate) session.getPeerCertificates()[0];
    } catch (SSLPeerUnverifiedException e) {
      System.err.println(session.getPeerHost() + " did not present a valid certificate.");
      return;
    }

    System.out.println(session.getPeerHost() + " has presented a certificate belonging to:");
    Principal p = cert.getSubjectDN();
    System.out.println("\t[" + p.getName() + "]");
    System.out.println("The certificate bears the valid signature of:");
    System.out.println("\t[" + cert.getIssuerDN().getName() + "]");

    System.out.print("Do you trust this certificate (y/n)? ");
    System.out.flush();
    BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
    if (Character.toLowerCase(console.readLine().charAt(0)) != 'y')
      return;

    PrintWriter out = new PrintWriter(sslsock.getOutputStream());

    out.print("GET " + fileName + " HTTP/1.0\r\n\r\n");
    out.flush();

    BufferedReader in = new BufferedReader(new InputStreamReader(sslsock.getInputStream()));
    String line;
    while ((line = in.readLine()) != null)
      System.out.println(line);

    sslsock.close();
  }
}
