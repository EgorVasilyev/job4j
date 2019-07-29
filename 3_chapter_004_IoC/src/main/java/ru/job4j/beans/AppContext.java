package ru.job4j.beans;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppContext {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext(
                        "applicationContext.xml"
                );

        XmlBean xmlBean = context.getBean("xmlBean", XmlBean.class);
        //or context.getBean(XmlBean.class) if singleton

        AnnotationBean annotationBean = context.getBean("annotationBean", AnnotationBean.class);
        //or context.getBean(AnnotationBean.class) if singleton

        ConfigBean configBean = context.getBean("configBean", ConfigBean.class);
        //or context.getBean(ConfigBean.class) if singleton

        System.out.println(XmlBean.class.getName() + ": " + xmlBean.getName());
        System.out.println(AnnotationBean.class.getName() + ": " + annotationBean.getName());
        System.out.println(ConfigBean.class.getName() + ": " + configBean.getName());
    }
}
