package me.kazechin;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import org.junit.Test;

import java.util.List;

public class ByteBuddyTest {

	@Test
	public void test() {
		ByteBuddy byteBuddy = new ByteBuddy();
		DynamicType.Unloaded<List> make = byteBuddy.subclass(List.class).make();
		System.out.println(make);
	}
}
