package com.dslplatform.client.json.Ip;

import com.dslplatform.client.JsonSerialization;
import com.dslplatform.patterns.Bytes;
import java.io.IOException;

public class NullableArrayOfNullableIpsDefaultValueTurtle {
	private static JsonSerialization jsonSerialization;

	@org.junit.BeforeClass
	public static void initializeJsonSerialization() throws IOException {
		jsonSerialization = com.dslplatform.client.StaticJson.getSerialization();
	}

	@org.junit.Test
	public void testDefaultValueEquality() throws IOException {
		final java.net.InetAddress[] defaultValue = null;
		final Bytes defaultValueJsonSerialized = jsonSerialization.serialize(defaultValue);
		final java.net.InetAddress[] defaultValueJsonDeserialized = jsonSerialization.deserialize(java.net.InetAddress[].class, defaultValueJsonSerialized.content, defaultValueJsonSerialized.length);
		com.dslplatform.ocd.javaasserts.IpAsserts.assertNullableArrayOfNullableEquals(defaultValue, defaultValueJsonDeserialized);
	}

	@org.junit.Test
	public void testBorderValue1Equality() throws IOException {
		final java.net.InetAddress[] borderValue1 = new java.net.InetAddress[] { null };
		final Bytes borderValue1JsonSerialized = jsonSerialization.serialize(borderValue1);
		final java.net.InetAddress[] borderValue1JsonDeserialized = jsonSerialization.deserialize(java.net.InetAddress[].class, borderValue1JsonSerialized.content, borderValue1JsonSerialized.length);
		com.dslplatform.ocd.javaasserts.IpAsserts.assertNullableArrayOfNullableEquals(borderValue1, borderValue1JsonDeserialized);
	}

	@org.junit.Test
	public void testBorderValue2Equality() throws IOException {
		final java.net.InetAddress[] borderValue2 = new java.net.InetAddress[] { com.dslplatform.ocd.test.TypeFactory.buildIP("ffff::ffff") };
		final Bytes borderValue2JsonSerialized = jsonSerialization.serialize(borderValue2);
		final java.net.InetAddress[] borderValue2JsonDeserialized = jsonSerialization.deserialize(java.net.InetAddress[].class, borderValue2JsonSerialized.content, borderValue2JsonSerialized.length);
		com.dslplatform.ocd.javaasserts.IpAsserts.assertNullableArrayOfNullableEquals(borderValue2, borderValue2JsonDeserialized);
	}

	@org.junit.Test
	public void testBorderValue3Equality() throws IOException {
		final java.net.InetAddress[] borderValue3 = new java.net.InetAddress[] { com.dslplatform.ocd.test.TypeFactory.buildIP("127.0.0.1"), com.dslplatform.ocd.test.TypeFactory.buildIP("0"), com.dslplatform.ocd.test.TypeFactory.buildIP("255.255.255.255"), com.dslplatform.ocd.test.TypeFactory.buildIP("::1"), com.dslplatform.ocd.test.TypeFactory.buildIP("ffff::ffff") };
		final Bytes borderValue3JsonSerialized = jsonSerialization.serialize(borderValue3);
		final java.net.InetAddress[] borderValue3JsonDeserialized = jsonSerialization.deserialize(java.net.InetAddress[].class, borderValue3JsonSerialized.content, borderValue3JsonSerialized.length);
		com.dslplatform.ocd.javaasserts.IpAsserts.assertNullableArrayOfNullableEquals(borderValue3, borderValue3JsonDeserialized);
	}

	@org.junit.Test
	public void testBorderValue4Equality() throws IOException {
		final java.net.InetAddress[] borderValue4 = new java.net.InetAddress[] { null, com.dslplatform.ocd.test.TypeFactory.buildIP("127.0.0.1"), com.dslplatform.ocd.test.TypeFactory.buildIP("0"), com.dslplatform.ocd.test.TypeFactory.buildIP("255.255.255.255"), com.dslplatform.ocd.test.TypeFactory.buildIP("::1"), com.dslplatform.ocd.test.TypeFactory.buildIP("ffff::ffff") };
		final Bytes borderValue4JsonSerialized = jsonSerialization.serialize(borderValue4);
		final java.net.InetAddress[] borderValue4JsonDeserialized = jsonSerialization.deserialize(java.net.InetAddress[].class, borderValue4JsonSerialized.content, borderValue4JsonSerialized.length);
		com.dslplatform.ocd.javaasserts.IpAsserts.assertNullableArrayOfNullableEquals(borderValue4, borderValue4JsonDeserialized);
	}
}
