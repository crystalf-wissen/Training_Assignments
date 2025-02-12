package com;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

class MyInterfaceTest {

	@Test
	void testAbc() {
		MyInterface mi = mock(MyInterface.class);
		//System.out.println(mi);
		mi.abc();
		mi.abc();
		mi.abc();
		verify(mi, times(3)).abc();
	}

}
