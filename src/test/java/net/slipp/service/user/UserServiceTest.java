package net.slipp.service.user;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.slipp.domain.user.User;

import org.junit.Test;

public class UserServiceTest {
	@Test
	public void 정상적으로_회원가입한다() throws Exception {
		UserService userService = new UserService();
		User user = new User("userId1", "password", "name", "javajigi@email.com");
		User joinedUser = userService.join(user);
		assertThat(joinedUser, is(user));
	}
	
	@Test(expected=ExistedUserException.class)
	public void 이미_존재하는_사용자_아이디로_회원가입한다() throws Exception {
		UserService userService = new UserService();
		User user1 = new User("userId2", "password", "name", "javajigi@email.com");
		User joinedUser = userService.join(user1);
		assertThat(joinedUser, is(user1));
		
		User user2 = new User("userId2", "password2", "name2", "javajigi@email.com2");
		userService.join(user2);
	}
}
