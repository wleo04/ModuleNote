package com.example.moduleNote.common.util;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JpaRandomKeyGenerator implements IdentifierGenerator, Configurable {

	private String nextKey(int len) {
		long time = System.currentTimeMillis();
		long s1 = new SecureRandom().nextLong();
		long s2 = new SecureRandom().nextLong();
		long s3 = new SecureRandom().nextLong();

		String temp = Long.toHexString(((time & s1) | s2) ^ s3).toUpperCase();

		for (int i = temp.length(); i < len; i++)
			temp += "0";

		return temp.length() > len ? temp.substring(0, len) : temp;
	}

	private int keySize = 16;

	@Override
	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
		try {
			keySize = Integer.parseInt(params.getProperty("key_size"));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			keySize = 16;
		}
	}

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object obj) throws HibernateException {
		return nextKey(keySize);
	}
}
