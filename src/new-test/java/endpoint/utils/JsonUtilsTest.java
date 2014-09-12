package endpoint.utils;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import endpoint.repository.models.basic.DataObject;

public class JsonUtilsTest {

	private static final String DATA_OBJECT_JSON = "{intValue : 1, longValue : 1, doubleValue : 1.1, booleanValue : true, dateValue : '2013/12/26 23:55:01', stringValue : object1}";

	@Test
	public void testFrom() {
		DataObject object = JsonUtils.from(null, DATA_OBJECT_JSON, DataObject.class);
		object.assertObject("object1", 1, 1l, 1.1, true, "2013/12/26 23:55:01");
	}

	@Test
	public void testFromArray() {
		String json = String.format("[%s, %s, %s]", DATA_OBJECT_JSON, DATA_OBJECT_JSON, DATA_OBJECT_JSON);

		List<DataObject> objects = JsonUtils.fromList(null, json, DataObject.class);

		assertEquals(3, objects.size());

		objects.get(0).assertObject("object1", 1, 1l, 1.1, true, "2013/12/26 23:55:01");
		objects.get(0).assertObject("object1", 1, 1l, 1.1, true, "2013/12/26 23:55:01");
		objects.get(0).assertObject("object1", 1, 1l, 1.1, true, "2013/12/26 23:55:01");
	}

	@Test
	public void testMapWithLongKey() {
		Map<Long, String> map = new HashMap<Long, String>();

		map.put(1l, "xpto1");
		map.put(2l, "xpto2");

		String json = JsonUtils.to(map);

		map = JsonUtils.fromMap(null, json, Long.class, String.class);

		assertEquals("xpto1", map.get(1l));
		assertEquals("xpto2", map.get(2l));
	}

	@Test
	public void testMapWithComplexObjectValue() {
		Map<Long, DataObject> map = new HashMap<Long, DataObject>();

		map.put(1l, new DataObject("xpto1"));
		map.put(2l, new DataObject("xpto2"));

		String json = JsonUtils.to(map);

		map = JsonUtils.fromMap(null, json, Long.class, DataObject.class);

		assertEquals("xpto1", map.get(1l).getStringValue());
		assertEquals("xpto2", map.get(2l).getStringValue());
	}

	@Test
	public void testMapWithListOfComplexObjectValue() throws NoSuchFieldException, SecurityException {
		Map<Long, List<DataObject>> map = new HashMap<Long, List<DataObject>>();

		map.put(1l, Arrays.asList(new DataObject("xpto1"), new DataObject("xpto2")));
		map.put(2l, Arrays.asList(new DataObject("xpto3"), new DataObject("xpto4")));

		String json = JsonUtils.to(map);

		map = JsonUtils.fromMapList(null, json, Long.class, DataObject.class);

		assertEquals("xpto1", map.get(1l).get(0).getStringValue());
		assertEquals("xpto2", map.get(1l).get(1).getStringValue());
		assertEquals("xpto3", map.get(2l).get(0).getStringValue());
		assertEquals("xpto4", map.get(2l).get(1).getStringValue());
	}
}