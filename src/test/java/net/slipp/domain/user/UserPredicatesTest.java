package net.slipp.domain.user;

import org.junit.Assert;
import org.junit.Test;

import com.mysema.query.types.Predicate;

public class UserPredicatesTest {
	private static final String SEARCH_TERM = "현기";
    private static final String EXPECTED_PREDICATE_STRING = "startsWithIgnoreCase(user.name,현기)";

    @Test
    public void lastNameLike() {
        Predicate predicate = UserPredicates.nameIsLike(SEARCH_TERM);
        String predicateAsString = predicate.toString();
        Assert.assertEquals(EXPECTED_PREDICATE_STRING, predicateAsString);
    }
}
