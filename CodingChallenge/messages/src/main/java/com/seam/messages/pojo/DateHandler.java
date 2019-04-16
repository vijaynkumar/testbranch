package com.seam.messages.pojo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

class DateHandler extends StdDeserializer<Date> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DateHandler() {
		this(null);
	}

	public DateHandler(Class<?> clazz) {
		super(clazz);
	}

	@Override
	public Date deserialize(JsonParser jsonparser, DeserializationContext context) throws IOException {
		String date = jsonparser.getText().trim();
		try {
			String pattern = "yyyy-mm-dd HH:mm:ss.SSSZ";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.getDefault());

			return simpleDateFormat.parse(date);
		} catch (Exception e) {
			return null;
		}
	}
}
