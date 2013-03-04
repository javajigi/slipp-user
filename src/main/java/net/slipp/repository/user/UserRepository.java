package net.slipp.repository.user;

import java.util.List;

import net.slipp.domain.user.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.mysema.query.types.Predicate;

public interface UserRepository extends JpaRepository<User, Long>, QueryDslPredicateExecutor<User> {
	
	public User findByUserId(String userId);

	public User findByUserIdAndPassword(String userId, String password);
	
	@Modifying
	@Query("update User o set o.password = :password where o.userId = :userId")
	public void updatePassword(String password, String userId);
}
