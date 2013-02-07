package net.slipp.web;

import java.beans.PropertyEditor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.mock.web.MockHttpServletRequest;

public class ReflectionTest {
    private static Logger log = LoggerFactory.getLogger(ReflectionTest.class);

    @SuppressWarnings("serial")
    private static Map<Class<?>, PropertyEditor> defaultEditors = new HashMap<Class<?>, PropertyEditor>() {
        {
            put(boolean.class, new CustomBooleanEditor(false));
            put(Boolean.class, new CustomBooleanEditor(true));

            put(byte.class, new CustomNumberEditor(Byte.class, false));
            put(Byte.class, new CustomNumberEditor(Byte.class, true));
            put(int.class, new CustomNumberEditor(Integer.class, false));
            put(Integer.class, new CustomNumberEditor(Integer.class, true));
            put(long.class, new CustomNumberEditor(Long.class, false));
            put(Long.class, new CustomNumberEditor(Long.class, true));
        }
    };

    @Test
    public void populateFromRequestToUser() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("userId", "test");
        request.addParameter("name", "슬립");
        request.addParameter("userNo", "12356");
        request.addParameter("age", "35");

        MockUser user = new MockUser();
        Field[] fields = MockUser.class.getDeclaredFields();
        for (Field field : fields) {
            log.debug("field name : {}", field.getName());
            field.setAccessible(true);

            String value = request.getParameter(field.getName());
            if (field.getType() == String.class) {
                field.set(user, value);
                continue;
            }

            PropertyEditor propertyEditor = defaultEditors.get(field.getType());
            if (propertyEditor != null) {
                propertyEditor.setAsText(value);
                field.set(user, propertyEditor.getValue());
            }
        }

        log.debug("User : {}", user);
    }

    private class MockUser {
        private long userNo;
        private Integer age;
        private String userId;
        private String name;

        @Override
        public String toString() {
            return "MockUser [userNo=" + userNo + ", age=" + age + ", userId=" + userId + ", name=" + name + "]";
        }
    }
}
