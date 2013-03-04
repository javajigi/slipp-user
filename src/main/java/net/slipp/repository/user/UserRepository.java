package net.slipp.repository.user;

import net.slipp.domain.user.User;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
	public User findByUserId(String userId);

	public User findByUserIdAndPassword(String userId, String password);
	
	@Modifying
	@Query("update User o set o.password = :password where o.userId = :userId")
	public void updatePassword(String password, String userId);
}
