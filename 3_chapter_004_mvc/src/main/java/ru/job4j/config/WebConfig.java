package ru.job4j.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    static {
        System.out.println();
        System.out.println(333333333);
        System.out.println();
    }
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
                SpringRootConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
                SpringWebConfig.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{
                "/"
        };
    }

/*    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setForceEncoding(true);
        encodingFilter.setEncoding(StandardCharsets.UTF_8.name());
        return new Filter[]{
                encodingFilter
        };
    }*/

/*    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        int size = 8 * 1024 * 1024 * 10;
        File uploadDirectory = new File(System.getProperty("java.io.tmpdir"));
        MultipartConfigElement multipartConfigElement =
                new MultipartConfigElement(uploadDirectory.getAbsolutePath(),
                        size, size * 2, size / 2);
        registration.setMultipartConfig(multipartConfigElement);
    }*/
}
