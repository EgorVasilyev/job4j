package io.find;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindFile {
    private String parent;
    private String target;
    private String parameter;
    private boolean parameterIsRegEx;
    private Pattern pt;

    public FindFile(Args arguments) {
        this.parent = arguments.directory();
        this.target = arguments.output();
        this.parameter = arguments.name();
        this.parameterIsRegEx = arguments.regex();
        this.pt = parameterIsRegexOrMask(parameter, parameterIsRegEx);
    }

    /**
     * Method files. Поиск файлов по каталогу.
     */
    public void files() throws IOException {
        Queue<File> queue = new LinkedList<File>();
        File root = new File(parent);
        queue.offer(root);
        try (BufferedWriter bufferWriter = new BufferedWriter(
                new FileWriter(target + File.separator + "foundFiles.txt"))) {
            while (!queue.isEmpty()) {
                File checkableFile = queue.poll();
                if (checkableFile.isDirectory()) {
                    //System.out.println("search in the directory:    " + checkableFile.getAbsolutePath());
                    for (File innerFile : checkableFile.listFiles()) {
                        queue.offer(innerFile);
                    }
                }
                if (requiredName(checkableFile, pt, parameter)) {
                    //System.out.println("found file or directory:    " + checkableFile.getAbsolutePath());
                    bufferWriter.write(checkableFile.getAbsolutePath());
                    bufferWriter.newLine();
                }
            }
        }
    }
    /**
     * Method parameterIsRegexOrMask. Проверка, является ли параметр для поиска регулярным выражением или маской.
     * @param parameter Параметр поиска - имя файла, регулярное выражение или маска
     * @param parameterIsRegEx Утверждение от пользователя, что параметр является/не является регулярым выражением
     * @return Скомпилированное представление регулярного выражения
     */
    private Pattern parameterIsRegexOrMask(String parameter, boolean parameterIsRegEx) {
        Pattern result = null;
        if (parameterIsRegEx) {
            result = Pattern.compile(parameter);
        } else {
            if (parameter.contains("*")) {
                StringBuilder regexBuilder = new StringBuilder("^");
                for (int i = 0; i < parameter.length(); i++) {
                    if (parameter.charAt(i) == '*') {
                        regexBuilder.append(".*");
                    } else {
                        regexBuilder.append(parameter.charAt(i));
                    }
                }
                regexBuilder.append("$");
                result = Pattern.compile(regexBuilder.toString());
            }
        }
        //System.out.println("pt в методе маска или регулярка - " + result);
        return result;
    }

    /**
     * Method requiredName. Проверка имени файла на соответствие регулярному выражению или искомому имени
     * @param checkableFile Проверяемый файл
     * @param pt Скомпилированное представление регулярного выражения
     * @param parameter Параметр поиска - полное имя файла
     */
    private boolean requiredName(File checkableFile, Pattern pt, String parameter) {
        boolean result;
        if (pt != null) {
            Matcher m = pt.matcher(checkableFile.getName());
            result = m.matches();
        } else {
           // System.out.println("проверка имени - " + checkableFile.getName());
            result = checkableFile.getName().equals(parameter);
            if (!result && checkableFile.getName().contains(".")) {
                result = checkableFile.getName()
                        .substring(0, checkableFile.getName()
                                .lastIndexOf(".")
                        ).equals(parameter);
                //System.out.println("проверка имени с точкой - " + checkableFile.getName().substring(0, checkableFile.getName().lastIndexOf(".")));
            }
        }
        return result;
    }
}
