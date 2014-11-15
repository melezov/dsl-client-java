package com.dslplatform.client.json;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class BoolConverter {
	public static void serializeNullable(final Boolean value, final Writer sw) throws IOException {
		if (value == null) 
			sw.write("null");
		else if (value)
			sw.write("true");
		else
			sw.write("false");
	}

	public static void serialize(final boolean value, final Writer sw) throws IOException {
		if (value)
			sw.write("true");
		else
			sw.write("false");
	}

	public static boolean deserialize(final JsonReader reader) throws IOException {
		if (reader.wasTrue()) {
			return true;
		} else if (reader.wasFalse()) {
			return false;
		}
		throw new IOException("Found invalid boolean value at: " + reader.positionInStream());
	}

	private static JsonReader.ReadObject<Boolean> BooleanReader = new JsonReader.ReadObject<Boolean>() {
		@Override
		public Boolean read(JsonReader reader) throws IOException {
			return deserialize(reader);
		}
	};

	public static ArrayList<Boolean> deserializeCollection(final JsonReader reader) throws IOException {
		return reader.deserializeCollection(BooleanReader);
	}

	public static ArrayList<Boolean> deserializeNullableCollection(final JsonReader reader) throws IOException {
		return reader.deserializeNullableCollection(BooleanReader);
	}
}
