package net.slipp.service.user;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import net.slipp.domain.user.User;
import net.slipp.repository.user.UserRepository;

import org.junit.Before;
import org.junit.Test;

import com.mysema.query.types.Predicate;

public class UserServiceDslTest {
    private static final String SEARCH_TERM = "";
    
    private UserService userService;

    private UserRepository userRepositoryMock;

    @Before
    public void setUp() {
    	userService = new UserService();

    	userRepositoryMock = mock(UserRepository.class);
    	userService.setUserRepository(userRepositoryMock);
    }

    @Test
    public void search() {
        List<User> expected = new ArrayList<User>();
        when(userRepositoryMock.findAll(any(Predicate.class))).thenReturn(expected);
        
        List<User> actual = userService.findByUserName(SEARCH_TERM);
        
        verify(userRepositoryMock, times(1)).findAll(any(Predicate.class));
        verifyNoMoreInteractions(userRepositoryMock);
        
        assertEquals(expected, actual);
    }
}
