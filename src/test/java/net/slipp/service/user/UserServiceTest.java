package net.slipp.service.user;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import net.slipp.dao.user.TestUserDao;
import net.slipp.dao.user.UserDao;
import net.slipp.domain.user.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserServiceTest {
	private UserService userService;
	private User user;
	
	@Before
	public void setUp() throws SQLException, ExistedUserException {
		UserDao userDao = new TestUserDao();
		userService = new UserService(userDao);
		
		user = new User("userId1", "password", "name", "javajigi@email.com");
		userService.join(user);
	}
	
	@After
	public void tearDown() throws SQLException {
		userService.deleteAllUser();
	}
	
	@Test(expected=ExistedUserException.class)
	public void 이미_존재하는_사용자_아이디로_회원가입한다() throws Exception {
		userService.join(user);
	}
	
	@Test
	public void 사용자_조회() throws Exception {
		assertThat(userService.findByUserId(user.getUserId()), is(user));
	}
	
	@Test
	public void 로그인_성공() throws Exception {
		User login_user = userService.login(user.getUserId(), user.getPassword());
		assertThat(login_user, is(notNullValue()));
	}
	
	@Test(expected=PasswordMismatchException.class)
	public void 로그인_비밀번호가_달라서_실패() throws Exception {
		userService.login("admin", "password2");
	}
	
	@Test(expected=PasswordMismatchException.class)
	public void 로그인_존재하지_않는_사용자라서_실패() throws Exception {
		String userId = "dontexisted";
		String password = "password2";
		userService.login(userId, password);
	}
}
