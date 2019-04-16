package io.find;

public class Args {
    private String parent;
    private String target;
    private String parameter;
    private boolean parameterIsRegEx;

    public Args(String parent, String target, String parameter, boolean parameterIsRegEx) {
        this.parent = parent;
        this.target = target;
        this.parameter = parameter;
        this.parameterIsRegEx = parameterIsRegEx;
    }

    /**
     * Method directory. Получение директории, где надо искать файлы.
     *
     * @return Файл.
     */
    public String directory() {
        return this.parent;
    }
    /**
     * Method output. Получение директории для размещения файла со списком найденных файлов.
     *
     * @return Файл.
     */
    public String output() {
        return this.target;
    }
    /**
     * Method name. Параметр для поиска - имя, маска или регулярное выражение
     *
     * @return параметр.
     */
    public String name() {
        return this.parameter;
    }
    /**
     * Method regex. Утверждение от пользователя, что параметр является регулярным выражением
     *
     * @return true/false.
     */
    public boolean regex() {
        return this.parameterIsRegEx;
    }
    @Override
    public String toString() {
        return "Args{" + "directory='" + parent + '\''
                + ", output=" + target + '\''
                + ", name='" + parameter
                + ", regex=" + parameterIsRegEx + '}';
    }
}
