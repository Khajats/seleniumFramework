package com.kts.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import com.kts.enums.ConfigProperties;

public final class PropertyUtils {
	private static Properties pro;
	private static final Map<String, String> CONFIGMAP = new HashMap<>();

	private PropertyUtils() {

	}

	static {
		try (FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src\\test\\config.properties");) {
			pro = new Properties();
			pro.load(fis);
			for (Map.Entry<Object, Object> entry : pro.entrySet()) {
				CONFIGMAP.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(ConfigProperties key) throws IOException {

		if (Objects.isNull(CONFIGMAP.get(key.name().toLowerCase()))) {
			throw new RuntimeException("propert name" + key + "is not found pleace check config.property");
		}

		return CONFIGMAP.get(key.name().toLowerCase());

	}
}
