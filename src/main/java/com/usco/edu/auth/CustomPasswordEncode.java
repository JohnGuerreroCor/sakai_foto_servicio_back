package com.usco.edu.auth;

import java.security.MessageDigest;

import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomPasswordEncode implements PasswordEncoder {

	@Override
	public String encode(CharSequence password) {
		MessageDigest mdSha = null;
		MessageDigest mdMd5 = null;
		String result = "";
		try {
			mdSha = MessageDigest.getInstance("SHA1");
			mdMd5 = MessageDigest.getInstance("md5");
			byte[] byteshashed = mdMd5.digest(mdSha.digest(password.toString().getBytes()));

			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteshashed.length; i++) {
				sb.append(Integer.toString((byteshashed[i] & 0xff) + 0x100, 16).substring(1));
			}
			result = sb.toString().toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public boolean matches(CharSequence password, String encodedPassword) {
		return encode(password).equals(encodedPassword) ? true : false;
	}

}