package me.kazechin;

import org.junit.Test;
import org.mockito.internal.creation.MockSettingsImpl;
import org.mockito.internal.creation.bytebuddy.ByteBuddyMockMaker;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class ByteBuddyMockMakerTest {

	@Test
	public void testCreateMock() {
		ByteBuddyMockMaker mockMaker = new ByteBuddyMockMaker();
		MockSettingsImpl settings = new MockSettingsImpl();
		settings.extraInterfaces(List.class);
		settings.setTypeToMock(Map.class);

		Class mockType = mockMaker.createMockType(settings);

		List<Class> interfaces = Arrays.asList(mockType.getInterfaces());
		assertTrue(interfaces.stream().anyMatch(clazz -> "Map".equals(clazz.getSimpleName())));
		assertTrue(interfaces.stream().anyMatch(clazz -> "List".equals(clazz.getSimpleName())));
		assertTrue(interfaces.stream().anyMatch(clazz -> "MockAccess".equals(clazz.getSimpleName())));

	}

}
