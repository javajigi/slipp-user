package net.slipp.domain.user;

import com.mysema.query.types.Predicate;

public class UserPredicates {
    public static Predicate nameIsLike(final String searchTerm) {
        QUser user = QUser.user;
        return user.name.startsWith(searchTerm);
    }
}
