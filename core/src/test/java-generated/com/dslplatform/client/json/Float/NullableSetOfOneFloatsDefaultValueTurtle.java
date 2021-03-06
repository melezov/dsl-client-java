package com.dslplatform.client.json.Float;

import com.dslplatform.client.JsonSerialization;
import com.dslplatform.patterns.Bytes;
import java.io.IOException;

public class NullableSetOfOneFloatsDefaultValueTurtle {
	private static JsonSerialization jsonSerialization;

	@org.junit.BeforeClass
	public static void initializeJsonSerialization() throws IOException {
		jsonSerialization = com.dslplatform.client.StaticJson.getSerialization();
	}

	@org.junit.Test
	public void testDefaultValueEquality() throws IOException {
		final java.util.Set<Float> defaultValue = null;
		final Bytes defaultValueJsonSerialized = jsonSerialization.serialize(defaultValue);
		final java.util.List<Float> deserializedTmpList = jsonSerialization.deserializeList(Float.class, defaultValueJsonSerialized.content, defaultValueJsonSerialized.length);
		final java.util.Set<Float> defaultValueJsonDeserialized = deserializedTmpList == null ? null : new java.util.HashSet<Float>(deserializedTmpList);
		com.dslplatform.ocd.javaasserts.FloatAsserts.assertNullableSetOfOneEquals(defaultValue, defaultValueJsonDeserialized);
	}

	@org.junit.Test
	public void testBorderValue1Equality() throws IOException {
		final java.util.Set<Float> borderValue1 = new java.util.HashSet<Float>(java.util.Arrays.asList(0.0f));
		final Bytes borderValue1JsonSerialized = jsonSerialization.serialize(borderValue1);
		final java.util.List<Float> deserializedTmpList = jsonSerialization.deserializeList(Float.class, borderValue1JsonSerialized.content, borderValue1JsonSerialized.length);
		final java.util.Set<Float> borderValue1JsonDeserialized = deserializedTmpList == null ? null : new java.util.HashSet<Float>(deserializedTmpList);
		com.dslplatform.ocd.javaasserts.FloatAsserts.assertNullableSetOfOneEquals(borderValue1, borderValue1JsonDeserialized);
	}

	@org.junit.Test
	public void testBorderValue2Equality() throws IOException {
		final java.util.Set<Float> borderValue2 = new java.util.HashSet<Float>(java.util.Arrays.asList(Float.POSITIVE_INFINITY));
		final Bytes borderValue2JsonSerialized = jsonSerialization.serialize(borderValue2);
		final java.util.List<Float> deserializedTmpList = jsonSerialization.deserializeList(Float.class, borderValue2JsonSerialized.content, borderValue2JsonSerialized.length);
		final java.util.Set<Float> borderValue2JsonDeserialized = deserializedTmpList == null ? null : new java.util.HashSet<Float>(deserializedTmpList);
		com.dslplatform.ocd.javaasserts.FloatAsserts.assertNullableSetOfOneEquals(borderValue2, borderValue2JsonDeserialized);
	}

	@org.junit.Test
	public void testBorderValue3Equality() throws IOException {
		final java.util.Set<Float> borderValue3 = new java.util.HashSet<Float>(java.util.Arrays.asList(0.0f, -1.2345E-10f, 1.2345E20f, -1E-5f, Float.NaN, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY));
		final Bytes borderValue3JsonSerialized = jsonSerialization.serialize(borderValue3);
		final java.util.List<Float> deserializedTmpList = jsonSerialization.deserializeList(Float.class, borderValue3JsonSerialized.content, borderValue3JsonSerialized.length);
		final java.util.Set<Float> borderValue3JsonDeserialized = deserializedTmpList == null ? null : new java.util.HashSet<Float>(deserializedTmpList);
		com.dslplatform.ocd.javaasserts.FloatAsserts.assertNullableSetOfOneEquals(borderValue3, borderValue3JsonDeserialized);
	}
}
