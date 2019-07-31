package ru.job4j.beans;

import org.springframework.stereotype.Component;

//bean annotation
@Component
public class AnnotationBean {
    public String getName() {
        return "annotation bean";
    }
}
