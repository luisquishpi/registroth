package ec.institutos.utils;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

public class Seguridad {

	public static final String ENCRYPTION_SCHEME = "AES";
	private static Cipher cipher;
	private static final String ALGO = "AES";
	private static final byte[] keyValue = new byte[] { 'S', 'i', 's', 't', 'e', 'm', 'a', 'R', 'E', 'G', 'I', 'S', 'T',
			'R', 'O', 'S' };

	public Seguridad() {
	}

	public static String encode(String value) throws Exception {
		byte[] encrypted;
		try {
			cipher = Cipher.getInstance(ENCRYPTION_SCHEME);
			Key key = generateKey();
			KeyGenerator keyGen = KeyGenerator.getInstance(ENCRYPTION_SCHEME);
			keyGen.init(128);
			System.out.println("-->" + key);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			encrypted = cipher.doFinal(value.getBytes());
		} catch (Exception ex) {
			return null;
		}
		return byteArrayToHexString(encrypted);
	}

	public static String decode(String message) throws Exception {
		byte[] decrypted;
		try {
			cipher = Cipher.getInstance(ENCRYPTION_SCHEME);
			Key key = generateKey();
			cipher.init(Cipher.DECRYPT_MODE, key);
			decrypted = cipher.doFinal(hexStringToByteArray(message));
		} catch (Exception ex) {
			return null;
		}
		return new String(decrypted);
	}

	private static String byteArrayToHexString(byte[] b) {
		StringBuffer sb = new StringBuffer(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			int v = b[i] & 0xff;
			if (v < 16) {
				sb.append('0');
			}
			sb.append(Integer.toHexString(v));
		}
		return sb.toString().toUpperCase();
	}

	private static byte[] hexStringToByteArray(String s) {
		byte[] b = new byte[s.length() / 2];
		for (int i = 0; i < b.length; i++) {
			int index = i * 2;
			int v = Integer.parseInt(s.substring(index, index + 2), 16);
			b[i] = (byte) v;
		}
		return b;
	}

	private static Key generateKey() throws Exception {
		Key key = new SecretKeySpec(keyValue, ALGO);
		return key;
	}

}
