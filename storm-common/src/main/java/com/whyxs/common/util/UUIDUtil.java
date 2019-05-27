package com.whyxs.common.util;

import java.util.UUID;

public class UUIDUtil {
	
	public static String get32UUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

}
