package me.kazechin.statics;

import org.junit.Test;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class StaticMethod {

	@Test
	public void mockStaticMethod() {

		assertEquals("bar", Foo.bar("test"));

		MockedStatic<Foo> mockStatic = mockStatic(Foo.class);

		// Foo::bar -> lambda
		// when 要求传入一个lambda apply
		mockStatic.when(() -> Foo.bar("test")).thenReturn("foo");

		assertEquals("foo", Foo.bar("test"));
	}

	@Test
	public void testMockConstruction() {

		// 什么场景下需要mock构造函数？
		try(MockedConstruction mocked = mockConstruction(Foo.class)) {
			Foo foo = new Foo();
			when(foo.test()).thenReturn("foo");

			assertEquals("foo", foo.test());

			assertEquals(false, new Foo().isReal());
		}

		assertEquals(true, new Foo().isReal());
	}


}

class Foo {

	private boolean isReal = false;

	public Foo() {
		this.isReal = true;
	}

	public static String bar(String name) {
		return "bar";
	}

	public String test() {
		return "bar";
	}

	public boolean isReal() {
		return isReal;
	}

}
