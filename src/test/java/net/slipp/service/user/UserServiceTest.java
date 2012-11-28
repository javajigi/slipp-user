package net.slipp.service.user;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.slipp.domain.user.User;

import org.junit.Test;

public class UserServiceTest {
	@Test
	public void 정상적으로_회원가입한다() throws Exception {
		UserService userService = new UserService();
		User user = new User("userId", "password", "name", "javajigi@email.com");
		User joinedUser = userService.join(user);
		assertThat(joinedUser, is(user));
	}
}
