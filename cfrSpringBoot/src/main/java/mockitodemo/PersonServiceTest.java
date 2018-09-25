package mockitodemo;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PersonServiceTest {

	private PersonDao mockDao;
	private PersonService personService;

	@Before
	public void setUp() throws Exception {
		// 可以对接口或类模拟Mock对象，创建时可对对象命名
		// mock(Class classToMock);
		// mock(Class classToMock, String name)
		// mock(Class classToMock, Answer defaultAnswer)
		// mock(Class classToMock, MockSettings mockSettings)
		// mock(Class classToMock, ReturnValues returnValues)
		// 模拟PersonDao对象
		mockDao = mock(PersonDao.class);
		// when(mock.someMethod()).thenReturn(value)，设置当mock对象某个方法调用时的返回值
		// when(mock.someMethod()).thenThrow(Throwable)，设置当mock对象某个方法调用时抛出的异常
		// when(mock.someMethod()).then(Answer)，设置当mock对象某个方法调用时执行的回调
		when(mockDao.getPerson(1)).thenReturn(new Person(1, "Person1"));
		// isA()：参数是指定类对象
		when(mockDao.update(isA(Person.class))).thenReturn(true);

		personService = new PersonService(mockDao);
	}

	@Test
	public void testUpdate() throws Exception {
		boolean result = personService.update(1, "new name");
		assertTrue("must true", result);
		// 验证是否执行过一次getPerson(1)
		verify(mockDao, times(1)).getPerson(eq(1));
		// 验证是否执行过一次update
		verify(mockDao, times(1)).update(isA(Person.class));
	}

	@Test
	public void testUpdateNotFind() throws Exception {
		boolean result = personService.update(2, "new name");
		assertFalse("must true", result);
		// 验证是否执行过一次getPerson(1)
		verify(mockDao, times(1)).getPerson(eq(1));
		// 验证是否执行过一次update
		verify(mockDao, never()).update(isA(Person.class));
	}
}