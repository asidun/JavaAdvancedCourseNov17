package com.flowergarden.run;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

@RunWith(MockitoJUnitRunner.class) 
public class ExamplesMockitoTests {

	@Mock
	List<String> mockedList;

	@Test
	public void executeMockedMethodsTest() {


		mockedList.add(5, "one");
		mockedList.clear();

		 when(mockedList.get(anyInt())).thenReturn("first", "second");
		 //when(mockedList.get(1)).thenThrow(new RuntimeException());

		 System.out.println(mockedList.get(1));
		 System.out.println(mockedList.get(1));
		 System.out.println(mockedList.get(1));

		// verification
		verify(mockedList).add(anyInt(), eq("one"));
		verify(mockedList).clear();
	}

}
