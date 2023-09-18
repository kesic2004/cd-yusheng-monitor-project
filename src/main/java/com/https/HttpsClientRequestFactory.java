package com.https;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.Socket;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

public class HttpsClientRequestFactory extends SimpleClientHttpRequestFactory {
   protected void prepareConnection(HttpURLConnection connection, String httpMethod) {
      try {
         if (!(connection instanceof HttpsURLConnection)) {
            throw new RuntimeException("An instance of HttpsURLConnection is expected");
         }

         HttpsURLConnection httpsConnection = (HttpsURLConnection)connection;
         TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
               return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
         }};
         SSLContext sslContext = SSLContext.getInstance("TLS");
         sslContext.init((KeyManager[])null, trustAllCerts, new SecureRandom());
         httpsConnection.setSSLSocketFactory(new HttpsClientRequestFactory.MyCustomSSLSocketFactory(sslContext.getSocketFactory()));
         httpsConnection.setHostnameVerifier(new HostnameVerifier() {
            public boolean verify(String s, SSLSession sslSession) {
               return true;
            }
         });
         super.prepareConnection(httpsConnection, httpMethod);
      } catch (Exception var6) {
         var6.printStackTrace();
      }

   }

   private static class MyCustomSSLSocketFactory extends SSLSocketFactory {
      private final SSLSocketFactory delegate;

      public MyCustomSSLSocketFactory(SSLSocketFactory delegate) {
         this.delegate = delegate;
      }

      public String[] getDefaultCipherSuites() {
         return this.delegate.getDefaultCipherSuites();
      }

      public String[] getSupportedCipherSuites() {
         return this.delegate.getSupportedCipherSuites();
      }

      public Socket createSocket(final Socket socket, final String host, final int port, final boolean autoClose) throws IOException {
         Socket underlyingSocket = this.delegate.createSocket(socket, host, port, autoClose);
         return this.overrideProtocol(underlyingSocket);
      }

      public Socket createSocket(final String host, final int port) throws IOException {
         Socket underlyingSocket = this.delegate.createSocket(host, port);
         return this.overrideProtocol(underlyingSocket);
      }

      public Socket createSocket(final String host, final int port, final InetAddress localAddress, final int localPort) throws IOException {
         Socket underlyingSocket = this.delegate.createSocket(host, port, localAddress, localPort);
         return this.overrideProtocol(underlyingSocket);
      }

      public Socket createSocket(final InetAddress host, final int port) throws IOException {
         Socket underlyingSocket = this.delegate.createSocket(host, port);
         return this.overrideProtocol(underlyingSocket);
      }

      public Socket createSocket(final InetAddress host, final int port, final InetAddress localAddress, final int localPort) throws IOException {
         Socket underlyingSocket = this.delegate.createSocket(host, port, localAddress, localPort);
         return this.overrideProtocol(underlyingSocket);
      }

      private Socket overrideProtocol(final Socket socket) {
         if (!(socket instanceof SSLSocket)) {
            throw new RuntimeException("An instance of SSLSocket is expected");
         } else {
            ((SSLSocket)socket).setEnabledProtocols(new String[]{"TLSv1"});
            return socket;
         }
      }
   }
}
