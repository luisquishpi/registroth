package ec.institutos.utils;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String SHA1 = "SHA-1";
	public static final String MD5 = "MD5";
	public static final String ISO = "iso-8859-1";
	public static final String UTF = "utf-8";
	public static final String key = "webmarket";

	private static String applyAlgorithm(String text, String algorithm, String encode)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md = MessageDigest.getInstance(algorithm);
		byte[] bytes = new byte[40];
		md.update(text.getBytes(encode), 0, text.length());
		bytes = md.digest();
		return convertToHex(bytes);
	}

	private static String convertToHex(byte[] data) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			int halfbyte = (data[i] >>> 4) & 0x0F;
			int two_halfs = 0;
			do {
				if ((0 <= halfbyte) && (halfbyte <= 9)) {
					buf.append((char) ('0' + halfbyte));
				} else {
					buf.append((char) ('a' + (halfbyte - 10)));
				}
				halfbyte = data[i] & 0x0F;
			} while (two_halfs++ < 1);
		}
		return buf.toString();
	}

	private static boolean sonIguales(String noEncripted, String encripted, String algorithm, String encode)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String newEncripted = applyAlgorithm(noEncripted, algorithm, encode);
		return encripted.equals(newEncripted);
	}

	public static String encriptar(String text) {
		try {
			return applyAlgorithm(text, Encrypt.SHA1, Encrypt.ISO);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static boolean sonIguales(String noEncriptado, String encriptado) {
		try {
			return sonIguales(noEncriptado, encriptado, Encrypt.SHA1, Encrypt.ISO);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return false;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return false;
		}
	}
}
