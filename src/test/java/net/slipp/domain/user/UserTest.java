package net.slipp.domain.user;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class UserTest {

	@Test
	public void 비밀번호_일치() {
		User user = new User("userId", "password", "name", "javajigi@email.com");
		assertThat(user.matchPassword("password"), is(true)); 
	}
	
	@Test
	public void 비밀번호_불일치() throws Exception {
		User user = new User("userId", "password", "name", "javajigi@email.com");
		assertThat(user.matchPassword("password2"), is(false)); 
	}
	
	@Test
	public void 비밀번호_불일치_null값() throws Exception {
		User user = new User("userId", "password", "name", "javajigi@email.com");
		assertThat(user.matchPassword(null), is(false));
		
		user = new User("userId", null, "name", "javajigi@email.com");
		assertThat(user.matchPassword("password"), is(false));
	}
	
	@Test
	public void 업데이트_테스트() {
		User user = new User("userId", "password", "name", "javajigi@email.com");
		
		String newUserId = "newUserId";
		String newPassword = "newPassword";
		String newName = "newName";
		String newEmail = "newEmail";
		user.update(new User(newUserId, newPassword, newName, newEmail));
		
		assertThat(user.getUserId(), is(newUserId));
		assertThat(user.getPassword(), is(newPassword));
		assertThat(user.getName(), is(newName));
		assertThat(user.getEmail(), is(newEmail));
		
	}
}
