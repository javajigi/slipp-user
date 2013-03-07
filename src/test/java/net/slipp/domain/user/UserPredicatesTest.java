package net.slipp.domain.user;

import org.junit.Assert;
import org.junit.Test;

import com.mysema.query.types.Predicate;

public class UserPredicatesTest {
	private static final String SEARCH_TERM = "김현";
    private static final String EXPECTED_PREDICATE_STRING = "startsWith(user.name,김현)";

    @Test
    public void lastNameLike() {
        Predicate predicate = UserPredicates.nameIsLike(SEARCH_TERM);
        String predicateAsString = predicate.toString();
        Assert.assertEquals(EXPECTED_PREDICATE_STRING, predicateAsString);
    }
}
