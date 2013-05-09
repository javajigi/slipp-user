package net.slipp.domain.user;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {

	@Test
	public void matchPassword() {
		User user = new User("userId", "password", "name", "javajigi@email.com");
		assertThat(user.matchPassword("password"), is(true)); 
	}
	
	@Test
	public void doesnotmatchPassword() throws Exception {
		User user = new User("userId", "password", "name", "javajigi@email.com");
		assertThat(user.matchPassword("password2"), is(false)); 
	}
	
	@Test
	public void doesnotmatchPassword_isnull() throws Exception {
		User user = new User("userId", "password", "name", "javajigi@email.com");
		assertThat(user.matchPassword(null), is(false));
		
		user = new User("userId", null, "name", "javajigi@email.com");
		assertThat(user.matchPassword("password"), is(false));
	}
}
