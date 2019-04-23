package io.find;

import java.io.File;

public class Args {
    private String parent;
    private String target;
    private String parameter;
    private boolean parameterIsRegEx;
    /**
     * Конструктор Args. Проверка валидности введенных пользователем параметров и инициализация полей.
     */
    public Args(String parent, String target, String parameter, String parameterIsRegEx) {
        boolean badInput = false;
        if (!new File(parent).exists()) {
            System.out.println("-d, directory is incorrect!");
            badInput = true;
        }
        if (!new File(target).exists()) {
            System.out.println("-o, output is incorrect!");
            badInput = true;
        }
        if (!parameterIsRegEx.equals("false") && !parameterIsRegEx.equals("true")) {
            System.out.println("-r, regex is incorrect!");
            badInput = true;
        }
        if (badInput) {
            showInstruction();
        } else {
            this.parent = parent;
            this.target = target;
            this.parameter = parameter;
            this.parameterIsRegEx = Boolean.valueOf(parameterIsRegEx);
        }
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
    /**
     * Method showInstruction. Показать инструкцию ввода параметров.
     */
    private static void showInstruction() {
        StringBuilder directory = new StringBuilder(System.lineSeparator())
                .append("Instructions:")
                .append(System.lineSeparator())
                .append("-d, directory       This option must be a path that contains the required files")
                .append(System.lineSeparator())
                .append("                    ")
                .append("For example: -d C:\\Program Files");
        StringBuilder output = new StringBuilder(
                "-o, output          This option must be a target path for creating result file foundFiles.txt")
                .append(System.lineSeparator())
                .append("                    ")
                .append("For example: -o C:\\Users\\Public");
        StringBuilder parameter = new StringBuilder(
                "-n, parameter       This option must be a full name, a mask or a regular expression for search")
                .append(System.lineSeparator())
                .append("                    ")
                .append("For example: -n *.exe");
        StringBuilder regex = new StringBuilder(
                "-r, regex           This option must be true, if parameter for search is regular expression,")
                .append(System.lineSeparator())
                .append("                    ")
                .append("or false, if parameter for search is mask or full name (not regular expression)")
                .append(System.lineSeparator())
                .append("                    ")
                .append("For example: -r false");
        StringBuilder example = new StringBuilder("Example for input:")
                .append(System.lineSeparator())
                .append("java -jar 2_chapter_002.jar -d C:\\Program Files -o C:\\Users\\Public -n *.exe -r false");
        StringBuilder instruction = new StringBuilder(directory)
                .append(System.lineSeparator())
                .append(output)
                .append(System.lineSeparator())
                .append(parameter)
                .append(System.lineSeparator())
                .append(regex)
                .append(System.lineSeparator())
                .append(example)
                .append(System.lineSeparator());
        System.out.println(instruction);
    }
}
