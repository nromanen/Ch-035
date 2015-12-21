package com.crsms.service;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

@Service
public class EncryptServiceImpl implements EncryptService {

	@Override
	public String encrypt(long encript) {
		return new String(Base64.encodeBase64(String.valueOf(encript).getBytes()));
	}

	@Override
	public long decrypt(String encrypted) {
		return Long.valueOf(new String(Base64.decodeBase64(encrypted.getBytes())));
	}

}
