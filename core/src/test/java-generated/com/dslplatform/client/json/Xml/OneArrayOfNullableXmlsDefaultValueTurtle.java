package com.dslplatform.client.json.Xml;

import com.dslplatform.client.JsonSerialization;
import com.dslplatform.patterns.Bytes;
import java.io.IOException;

public class OneArrayOfNullableXmlsDefaultValueTurtle {
	private static JsonSerialization jsonSerialization;

	@org.junit.BeforeClass
	public static void initializeJsonSerialization() throws IOException {
		jsonSerialization = com.dslplatform.client.StaticJson.getSerialization();
	}

	@org.junit.Test
	public void testDefaultValueEquality() throws IOException {
		final org.w3c.dom.Element[] defaultValue = new org.w3c.dom.Element[0];
		final Bytes defaultValueJsonSerialized = jsonSerialization.serialize(defaultValue);
		final org.w3c.dom.Element[] defaultValueJsonDeserialized = jsonSerialization.deserialize(org.w3c.dom.Element[].class, defaultValueJsonSerialized.content, defaultValueJsonSerialized.length);
		com.dslplatform.ocd.javaasserts.XmlAsserts.assertOneArrayOfNullableEquals(defaultValue, defaultValueJsonDeserialized);
	}

	@org.junit.Test
	public void testBorderValue1Equality() throws IOException {
		final org.w3c.dom.Element[] borderValue1 = new org.w3c.dom.Element[] { null };
		final Bytes borderValue1JsonSerialized = jsonSerialization.serialize(borderValue1);
		final org.w3c.dom.Element[] borderValue1JsonDeserialized = jsonSerialization.deserialize(org.w3c.dom.Element[].class, borderValue1JsonSerialized.content, borderValue1JsonSerialized.length);
		com.dslplatform.ocd.javaasserts.XmlAsserts.assertOneArrayOfNullableEquals(borderValue1, borderValue1JsonDeserialized);
	}

	@org.junit.Test
	public void testBorderValue2Equality() throws IOException {
		final org.w3c.dom.Element[] borderValue2 = new org.w3c.dom.Element[] { com.dslplatform.ocd.test.Utils.stringToElement("<ns3000:NamespacedElement/>") };
		final Bytes borderValue2JsonSerialized = jsonSerialization.serialize(borderValue2);
		final org.w3c.dom.Element[] borderValue2JsonDeserialized = jsonSerialization.deserialize(org.w3c.dom.Element[].class, borderValue2JsonSerialized.content, borderValue2JsonSerialized.length);
		com.dslplatform.ocd.javaasserts.XmlAsserts.assertOneArrayOfNullableEquals(borderValue2, borderValue2JsonDeserialized);
	}

	@org.junit.Test
	public void testBorderValue3Equality() throws IOException {
		final org.w3c.dom.Element[] borderValue3 = new org.w3c.dom.Element[] { com.dslplatform.ocd.test.Utils.stringToElement("<document/>"), com.dslplatform.ocd.test.Utils.stringToElement("<TextElement>some text &amp; &lt;stuff&gt;</TextElement>"), com.dslplatform.ocd.test.Utils.stringToElement("<ElementWithCData>&lt;?xml?&gt;&lt;xml&gt;&lt;!xml!&gt;</ElementWithCData>"), com.dslplatform.ocd.test.Utils.stringToElement("<AtributedElement foo=\"bar\" qwe=\"poi\"/>"), com.dslplatform.ocd.test.Utils.stringToElement("<NestedTextElement><FirstNest><SecondNest>bird</SecondNest></FirstNest></NestedTextElement>"), com.dslplatform.ocd.test.Utils.stringToElement("<ns3000:NamespacedElement/>") };
		final Bytes borderValue3JsonSerialized = jsonSerialization.serialize(borderValue3);
		final org.w3c.dom.Element[] borderValue3JsonDeserialized = jsonSerialization.deserialize(org.w3c.dom.Element[].class, borderValue3JsonSerialized.content, borderValue3JsonSerialized.length);
		com.dslplatform.ocd.javaasserts.XmlAsserts.assertOneArrayOfNullableEquals(borderValue3, borderValue3JsonDeserialized);
	}

	@org.junit.Test
	public void testBorderValue4Equality() throws IOException {
		final org.w3c.dom.Element[] borderValue4 = new org.w3c.dom.Element[] { null, com.dslplatform.ocd.test.Utils.stringToElement("<document/>"), com.dslplatform.ocd.test.Utils.stringToElement("<TextElement>some text &amp; &lt;stuff&gt;</TextElement>"), com.dslplatform.ocd.test.Utils.stringToElement("<ElementWithCData>&lt;?xml?&gt;&lt;xml&gt;&lt;!xml!&gt;</ElementWithCData>"), com.dslplatform.ocd.test.Utils.stringToElement("<AtributedElement foo=\"bar\" qwe=\"poi\"/>"), com.dslplatform.ocd.test.Utils.stringToElement("<NestedTextElement><FirstNest><SecondNest>bird</SecondNest></FirstNest></NestedTextElement>"), com.dslplatform.ocd.test.Utils.stringToElement("<ns3000:NamespacedElement/>") };
		final Bytes borderValue4JsonSerialized = jsonSerialization.serialize(borderValue4);
		final org.w3c.dom.Element[] borderValue4JsonDeserialized = jsonSerialization.deserialize(org.w3c.dom.Element[].class, borderValue4JsonSerialized.content, borderValue4JsonSerialized.length);
		com.dslplatform.ocd.javaasserts.XmlAsserts.assertOneArrayOfNullableEquals(borderValue4, borderValue4JsonDeserialized);
	}
}
