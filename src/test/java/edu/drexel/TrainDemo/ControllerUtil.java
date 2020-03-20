package edu.drexel.TrainDemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class ControllerUtil {
    public static void verifyControllerHasPrefix(Class Controller, String prefix) {
        Method[] methods = Controller.getDeclaredMethods();

        for (Method method : methods) {
            Annotation[] methodAnnotations = method.getDeclaredAnnotations();
            for (Annotation methodAnnotation : methodAnnotations) {
                Class annotationType = methodAnnotation.annotationType();
                if (annotationType == GetMapping.class || annotationType == PostMapping.class) {
                    String value = extractValueFromAnnotation(methodAnnotation);
                    assert value.startsWith(prefix);
                }
            }
        }
    }

    public static String extractValueFromAnnotation(Annotation annotation) {
        String annotationAsString = annotation.toString();
        if (!annotationAsString.contains("{")) {
            return java8ExtractValueFromAnnotation(annotationAsString);
        }
        return java11ExtractValueFromAnnotation(annotationAsString);
    }

    public static String java11ExtractValueFromAnnotation(String annotationAsString) {
        String value = annotationAsString.split("value=\\{\"")[1].split("\"}")[0];
        return value;
    }

    public static String java8ExtractValueFromAnnotation(String annotationAsString) {
        String value = annotationAsString.split("value=\\[")[1].split("]")[0];
        return value;
    }
}
