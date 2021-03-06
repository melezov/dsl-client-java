package com.dslplatform.client;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Assert;
import org.junit.Test;

import com.dslplatform.ocd.javaasserts.DecimalAsserts;

public class GuardsTests {
	private static final BigDecimal[] PIES = new BigDecimal[] {
		new BigDecimal("3.14159265358979323846264"),
		new BigDecimal("2.14159265358979323846264"),
		new BigDecimal("1.14159265358979323846264"),
		null,
		new BigDecimal("-1.14159265358979323846264"),
		new BigDecimal("-2.14159265358979323846264"),
		new BigDecimal("-3.14159265358979323846264")
	};

	@Test
	public void testArrayScaling() {
		final BigDecimal[] original = PIES;
		final BigDecimal[] result = Guards.setScale(original, 3);
		Guards.checkScale(result, 3);

		DecimalAsserts.assertOneArrayOfNullableEquals(result, new BigDecimal[] {
				new BigDecimal("3.1411").setScale(3, RoundingMode.CEILING),
				new BigDecimal("2.1415").setScale(3, RoundingMode.HALF_EVEN),
				new BigDecimal("1.1424").setScale(3, RoundingMode.HALF_UP),
				null,
				new BigDecimal("-1.1411").setScale(3, RoundingMode.FLOOR),
				new BigDecimal("-2.1425").setScale(3, RoundingMode.HALF_DOWN),
				new BigDecimal("-3.1420").setScale(3, RoundingMode.UNNECESSARY)});
	}

	@Test
	public void testListScaling() {
		final List<BigDecimal> original = Arrays.asList(PIES);
		final List<BigDecimal> result = Guards.setScale(original, 3);
		Guards.checkScale(result, 3);

		DecimalAsserts.assertOneListOfNullableEquals(result, Arrays.asList(
				new BigDecimal("3.1411").setScale(3, RoundingMode.CEILING),
				new BigDecimal("2.1415").setScale(3, RoundingMode.HALF_EVEN),
				new BigDecimal("1.1424").setScale(3, RoundingMode.HALF_UP),
				null,
				new BigDecimal("-1.1411").setScale(3, RoundingMode.FLOOR),
				new BigDecimal("-2.1425").setScale(3, RoundingMode.HALF_DOWN),
				new BigDecimal("-3.1420").setScale(3, RoundingMode.UNNECESSARY)));
	}

	@Test
	public void testSetScaling() {
		final Set<BigDecimal> original = new HashSet<BigDecimal>(Arrays.asList(PIES));
		final Set<BigDecimal> result = Guards.setScale(original, 3);
		Guards.checkScale(result, 3);

		DecimalAsserts.assertOneSetOfNullableEquals(result, new HashSet<BigDecimal>(Arrays.asList(
				new BigDecimal("1.1424").setScale(3, RoundingMode.HALF_UP),
				new BigDecimal("-2.1425").setScale(3, RoundingMode.HALF_DOWN),
				new BigDecimal("-1.1411").setScale(3, RoundingMode.FLOOR),
				null, // order intentionally shuffled
				new BigDecimal("-3.1420").setScale(3, RoundingMode.UNNECESSARY),
				new BigDecimal("3.1411").setScale(3, RoundingMode.CEILING),
				new BigDecimal("2.1415").setScale(3, RoundingMode.HALF_EVEN))));

	}

	@Test(expected=NullPointerException.class)
	public void testNullableQueueElementScaling() {
		final Queue<BigDecimal> original = new LinkedList<BigDecimal>(Arrays.asList(PIES));
		Guards.setScale(original, 3);
	}

	@Test
	public void testQueueScaling() {
		final List<BigDecimal> temp = new ArrayList<BigDecimal>(Arrays.asList(PIES));
		temp.set(3, BigDecimal.ZERO); // convert null to zero
		final Queue<BigDecimal> original = new ArrayDeque<BigDecimal>(temp);
		final Queue<BigDecimal> result = Guards.setScale(original, 3);
		Guards.checkScale(result, 3);

		DecimalAsserts.assertOneQueueOfOneEquals(result, new ArrayDeque<BigDecimal>(Arrays.asList(
				new BigDecimal("3.1411").setScale(3, RoundingMode.CEILING),
				new BigDecimal("2.1415").setScale(3, RoundingMode.HALF_EVEN),
				new BigDecimal("1.1424").setScale(3, RoundingMode.HALF_UP),
				new BigDecimal("0.0009").setScale(3, RoundingMode.DOWN),
				new BigDecimal("-1.1411").setScale(3, RoundingMode.FLOOR),
				new BigDecimal("-2.1425").setScale(3, RoundingMode.HALF_DOWN),
				new BigDecimal("-3.1420").setScale(3, RoundingMode.UNNECESSARY))));
	}

	@Test
	public void testLinkedListScaling() {
		final LinkedList<BigDecimal> original = new LinkedList<BigDecimal>(Arrays.asList(PIES));
		final LinkedList<BigDecimal> result = Guards.setScale(original, 3);
		Guards.checkScale(result, 3); // special checkScale for LinkedList so that it doesn't invoke RandomAccess version from List instead

		DecimalAsserts.assertOneLinkedListOfNullableEquals(result, new LinkedList<BigDecimal>(Arrays.asList(
				new BigDecimal("3.1411").setScale(3, RoundingMode.CEILING),
				new BigDecimal("2.1415").setScale(3, RoundingMode.HALF_EVEN),
				new BigDecimal("1.1424").setScale(3, RoundingMode.HALF_UP),
				null,
				new BigDecimal("-1.1411").setScale(3, RoundingMode.FLOOR),
				new BigDecimal("-2.1425").setScale(3, RoundingMode.HALF_DOWN),
				new BigDecimal("-3.1420").setScale(3, RoundingMode.UNNECESSARY))));
	}

	@Test
	@SuppressWarnings("serial")
	public void testStackScaling() {
		final Stack<BigDecimal> original = new Stack<BigDecimal>() {{ addAll(Arrays.asList(PIES)); }};
		final Stack<BigDecimal> result = Guards.setScale(original, 3);
		Guards.checkScale(result, 3);

		DecimalAsserts.assertOneStackOfNullableEquals(result, new Stack<BigDecimal>() {{ addAll(Arrays.asList(
				new BigDecimal("3.1411").setScale(3, RoundingMode.CEILING),
				new BigDecimal("2.1415").setScale(3, RoundingMode.HALF_EVEN),
				new BigDecimal("1.1424").setScale(3, RoundingMode.HALF_UP),
				null,
				new BigDecimal("-1.1411").setScale(3, RoundingMode.FLOOR),
				new BigDecimal("-2.1425").setScale(3, RoundingMode.HALF_DOWN),
				new BigDecimal("-3.1420").setScale(3, RoundingMode.UNNECESSARY))); }});
	}

	@Test
	public void testVectorScaling() {
		final Vector<BigDecimal> original = new Vector<BigDecimal>(Arrays.asList(PIES));
		final Vector<BigDecimal> result = Guards.setScale(original, 3);
		Guards.checkScale(result, 3);

		DecimalAsserts.assertOneVectorOfNullableEquals(result, new Vector<BigDecimal>(Arrays.asList(
				new BigDecimal("3.1411").setScale(3, RoundingMode.CEILING),
				new BigDecimal("2.1415").setScale(3, RoundingMode.HALF_EVEN),
				new BigDecimal("1.1424").setScale(3, RoundingMode.HALF_UP),
				null,
				new BigDecimal("-1.1411").setScale(3, RoundingMode.FLOOR),
				new BigDecimal("-2.1425").setScale(3, RoundingMode.HALF_DOWN),
				new BigDecimal("-3.1420").setScale(3, RoundingMode.UNNECESSARY))));
	}

	@Test
	public void checkDateTime() {
		final Set<DateTime> at2 = new HashSet<DateTime>();
		at2.add(DateTime.parse("2016-04-17T09:43:14.530+02:00"));
		final Set<DateTime> atMin2 = new HashSet<DateTime>();
		atMin2.add(DateTime.parse("2016-04-17T05:43:14.530-02:00"));
		final Set<DateTime> zagreb = new HashSet<DateTime>();
		zagreb.add(new DateTime(2016, 4, 17, 9, 43, 14, 530, DateTimeZone.forID("Europe/Zagreb")));
		final Set<DateTime> paris1 = new HashSet<DateTime>();
		paris1.add(new DateTime(2016, 4, 17, 9, 43, 14, 530, DateTimeZone.forID("Europe/Paris")));
		final Set<DateTime> paris2 = new HashSet<DateTime>();
		paris2.add(new DateTime(2016, 4, 17, 9, 43, 14, 530, DateTimeZone.forID("Europe/Paris")));

		Assert.assertFalse(Guards.compareDateTime(at2, atMin2));
		Assert.assertTrue(Guards.compareDateTime(at2, at2));
		Assert.assertTrue(Guards.compareDateTime(at2, zagreb));
		Assert.assertTrue(Guards.compareDateTime(zagreb, paris1));
		Assert.assertTrue(Guards.compareDateTime(paris1, paris2));
	}
}
