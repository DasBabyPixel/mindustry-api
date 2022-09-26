package de.dasbabypixel.mindustryapi.logic.compiler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

public class ProgramInfo {

	private static final Gson gson = new Gson();

	private final JsonObject json;

	public ProgramInfo(InputStream in) throws JsonSyntaxException, IOException {
		json = gson.fromJson(new String(toBytes(in), StandardCharsets.UTF_8), JsonObject.class);
	}

	public String getMain() {
		return json.get("main").getAsString();
	}

	private static byte[] toBytes(InputStream in) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int read;
		byte[] data = new byte[1024];
		while ((read = in.read(data)) != -1) {
			out.write(data, 0, read);
		}
		return out.toByteArray();
	}
}
