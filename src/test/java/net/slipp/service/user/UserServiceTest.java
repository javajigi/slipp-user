package net.slipp.service.user;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.slipp.domain.user.User;

import org.junit.Test;

public class UserServiceTest {
	@Test
	public void join_normal() throws Exception {
		UserService userService = new UserService();
		User user = new User("userId1", "password", "name", "javajigi@email.com");
		User joinedUser = userService.join(user);
		assertThat(joinedUser, is(user));
	}
	
	@Test(expected=ExistedUserException.class)
	public void join_existed_user() throws Exception {
		UserService userService = new UserService();
		User user1 = new User("userId2", "password", "name", "javajigi@email.com");
		User joinedUser = userService.join(user1);
		assertThat(joinedUser, is(user1));
		
		User user2 = new User("userId2", "password2", "name2", "javajigi@email.com2");
		userService.join(user2);
	}
	
	@Test
	public void findByUserId() throws Exception {
		User user3 = new User("userId3", "password2", "name2", "javajigi@email.com2");
		UserService userService = new UserService();
		userService.join(user3);
		User user = userService.findByUserId(user3.getUserId());
		assertThat(user, is(user3));
	}
	
	@Test
	public void login_success() throws Exception {
		String userId = "admin";
		String password = "password";
		UserService userService = new UserService();
		User user = userService.login(userId, password);
		assertThat(user, is(notNullValue()));
	}
	
	@Test(expected=PasswordMismatchException.class)
	public void login_fail_password_mismath() throws Exception {
		String userId = "admin";
		String password = "password2";
		UserService userService = new UserService();
		userService.login(userId, password);
	}
	
	@Test(expected=PasswordMismatchException.class)
	public void login_fail_doesnot_existed_user() throws Exception {
		String userId = "dontexisted";
		String password = "password2";
		UserService userService = new UserService();
		userService.login(userId, password);
	}
}
