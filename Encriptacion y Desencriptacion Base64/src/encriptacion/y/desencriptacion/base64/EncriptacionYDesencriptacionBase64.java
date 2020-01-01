/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encriptacion.y.desencriptacion.base64;

import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Andres Cordova <github.com/acordova200>
 */
public class EncriptacionYDesencriptacionBase64 {

    private static final String UNICODE_FORMAT = "UTF8";
    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    private KeySpec ks;
    private SecretKeyFactory skf;
    private Cipher cipher;
    byte[] arrayBytes;
    private String miLlavedeEncriptacion;
    private String miEsquemaDeEncriptacion;
    SecretKey key;

    public EncriptacionYDesencriptacionBase64() throws Exception {
        miLlavedeEncriptacion = "abc.cordova@gmail.com_ES";
        miEsquemaDeEncriptacion = DESEDE_ENCRYPTION_SCHEME;
        arrayBytes = miLlavedeEncriptacion.getBytes(UNICODE_FORMAT);
        ks = new DESedeKeySpec(arrayBytes);
        skf = SecretKeyFactory.getInstance(miEsquemaDeEncriptacion);
        cipher = Cipher.getInstance(miEsquemaDeEncriptacion);
        key = skf.generateSecret(ks);
    }

    public String encriptar(String textoSinEncriptar) {
        String textoEncriptado = null;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainText = textoSinEncriptar.getBytes(UNICODE_FORMAT);
            byte[] encritado = cipher.doFinal(plainText);
            textoEncriptado = new String(Base64.encodeBase64(encritado));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return textoEncriptado;
    }

    public String desencriptar(String textoEncriptado) {
        String textoDesencriptado = null;
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] eBytes = textoEncriptado.getBytes();
            byte[] encryptedText = Base64.decodeBase64(eBytes);
            byte[] plainText = cipher.doFinal(encryptedText);
            textoDesencriptado = new String(plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return textoDesencriptado;
    }

    public static void main(String[] args) throws Exception {
        EncriptacionYDesencriptacionBase64 eD = new EncriptacionYDesencriptacionBase64();

        String texto = "acordova";
        String encriptado = eD.encriptar(texto);
        String desencriptado = eD.desencriptar(encriptado);

        System.out.println("Texto a Encriptar: " + texto);
        System.out.println("Texto Encriptado:" + encriptado);
        System.out.println("Texto Desencriptado:" + desencriptado);
    }
}
