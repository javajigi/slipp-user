package net.slipp.repository.user;

import net.slipp.domain.user.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = { "classpath:slipp-user-repository.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void test_사용자저장(){
		userRepository.save(new User());
	}

}
