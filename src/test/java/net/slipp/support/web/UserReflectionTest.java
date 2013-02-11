package net.slipp.support.web;

import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

public class UserReflectionTest {

	@Test
	public void handlerMappingTest() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setRequestURI("/user/Login.do");
		
		assertEquals(UserLoginHandler.class.getName(), getHandlerName(request));
		
		request.setRequestURI("/user/Join.do");
		assertEquals(UserJoinHandler.class.getName(), getHandlerName(request));
	}

	private String getHandlerName(MockHttpServletRequest request) {
		StringBuffer clsName = new StringBuffer("net.slipp.support.web.User");
		clsName.append(
				request.getRequestURI().substring(
						request.getRequestURI().lastIndexOf("/")+1
					  , request.getRequestURI().lastIndexOf("."))).append("Handler");
		return clsName.toString();
	}

}
