package edu.drexel.TrainDemo.user;

import edu.drexel.TrainDemo.user.controllers.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class UserControllerTest {

    @Test
    public void test_user_controller_endpoints_start_with_user() {
        Method[] methods = UserController.class.getDeclaredMethods();

        for (Method method : methods) {
            Annotation[] methodAnnotations = method.getDeclaredAnnotations();
            for (Annotation methodAnnotation : methodAnnotations) {
                Class annotationType = methodAnnotation.annotationType();
                if (annotationType == GetMapping.class || annotationType == PostMapping.class) {
                    String value = extractValueFromAnnotation(methodAnnotation);
                    assert value.contains("/user");
                }
            }
        }
    }

    public String extractValueFromAnnotation(Annotation annotation) {
        String annotationAsString = annotation.toString();
        String value = annotationAsString.split("value=\\{\"")[1].split("\"}")[0];
        return value;
    }
}
