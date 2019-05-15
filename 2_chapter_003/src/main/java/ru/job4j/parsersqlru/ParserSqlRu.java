package ru.job4j.parsersqlru;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.TreeSet;

/**
 * Class ParserSqlRu. Приложение парсер заходит на сайт sql.ru в раздел работа и собирает Java вакансии.
 */
public class ParserSqlRu {
    //объект для работы с базой данных sql
    private VacancySQL vacancySQL;
    //отсортированная по дате коллекция вакансий
    private TreeSet<Vacancy> vacancies;
    //логирование
    private static final Logger LOG = LogManager.getLogger(ParserSqlRu.class.getName());
    //дата, после которой нужно собрать все вакансии
    private Date finishDate;
    /**
     * Constructor ParserSqlRu.
     */
    public ParserSqlRu() {
        this.vacancySQL = new VacancySQL();
        this.vacancySQL.init();
        Comparator<Vacancy> comparator = Comparator.comparing(Vacancy::getCreated);
        this.vacancies = new TreeSet<>(comparator);
    }
    /**
     * Method start. Подключение к сайту, запуск парсинга и добавления вакансий в базу данных
     */
    public void start() {
        try {
            Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
            if (doc != null) {
                //получение списка имен и дат вакансий из лог-файла
                this.readDateFromDataBase();
                //получение количества страниц
                int numberOfPages = this.getNumbersOfPages(doc);
                //перебор по страницам
                for (int page = 1; page <= numberOfPages; page++) {
                    if (!this.parseHtml(page, doc)) {
                        break;
                    }
                }
                for (Vacancy vacancy : this.vacancies) {
                    this.sendVacancyToSQLDB(vacancy);
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
    /**
     * Method readNameAndDateFromLog. Чтение лог-файла, получение списка имен вакансий и даты последней вакансии
     */
    private void readDateFromDataBase() throws ParseException{
        String lastDate = vacancySQL.getLastDate();
        //логическая переменная, указывает, первый ли это запуск программы
        if (lastDate == null) {
            //проверяем вакансии с начала года
            this.finishDate = this.getDate("1 янв 19, 0:0");
        } else {
            //проверяем вакансии с даты последней добавленной вакансии
            this.finishDate = this.getDate(lastDate);
        }
    }
    /**
     * Method parseHtml. Парсинг html
     * @param page Номер страницы на сайте
     * @param doc Document doc
     * @return разрешение на продолжение парсинга
     */
    private boolean parseHtml(int page, Document doc) throws ParseException, IOException {
        if (page != 1) {
            doc = Jsoup.connect("https://www.sql.ru/forum/job-offers/" + page).get();
        }
        //все таблицы
        Elements tables = doc.getElementsByTag("table");
        if (tables == null) {
            return true;
        }
        //первая (и здесь единственная) таблица с классом class="forumTable"
        Element forumTable = tables.select("table.forumTable").first();
        if (forumTable == null) {
            return true;
        }
        //строки <tr> таблицы tablesCalledForumTable
        Elements rows = forumTable.select("tr");
        if (rows == null) {
            return true;
        }
        for (Element row : rows) {
            //элемент с тегом <a href="some link"> c link и name
            Element firstAHref = row.select("td > a[href]").first();
            if (firstAHref == null) {
                continue;
            }
            //(текст) name из aHref
            String name = firstAHref.text();
            if (name == null || !onlyJava(name)) {
                continue;
            }
            //элемент с тегом <td class="altCol"> с датой
            Element elementWithDate = row.select("td.altCol").last();
            if (elementWithDate == null) {
                continue;
            }
            //текст из элемента elementWithDate
            String textWithDate = elementWithDate.text();
            //парсинг даты из текста
            Date date = this.parseTextToDate(textWithDate);
            //проверка даты
            if (date.compareTo(this.finishDate) <= 0) {
                return false;
            }
            //(ссылка) link из aHref
            String link = firstAHref.attr("abs:href");
            if (link == null) {
                continue;
            }
            //текст вакансии
            String text = this.getTextFromLink(link);
            this.vacancies.add(new Vacancy(name, link, date, text));
        }
        return true;
    }
    /**
     * Method sendVacancyToSQLDB. Генерация данных в БД Postgresql, логирование вакансий
     * @param vacancy Вакансия
     */
    private void sendVacancyToSQLDB(Vacancy vacancy) {
        this.vacancySQL.add(vacancy);
        LOG.info("Name of vacancy: " + vacancy.getName());
        LOG.info("Date of vacancy: " + this.getStringDate(vacancy.getCreated()));
    }
    /**
     * Method getTextFromLink. Получение текста вакансии
     * @param link Ссылка
     * @return текст вакансии
     */
    private String getTextFromLink(String link) throws IOException {
        String textOfVacancy = null;
        Document doc = Jsoup.connect(link).get();
        if (doc != null) {
            //элемент с текстом из ссылки
            Element elementWithText = doc.select("td.msgBody").get(1);
            //текст из ссылки с сохранением переноса строки
            textOfVacancy = this.br2nl(elementWithText.toString());
        }
        return textOfVacancy;
    }
    /**
     * Method br2nl. Получение текста из html с сохранением перехода на следующую строку
     * @param html html текст
     * @return строки с сохранением перехода
     */
    private String br2nl(String html) {
        Document document = Jsoup.parse(html);
        document.outputSettings(new Document.OutputSettings().prettyPrint(false));
        document.select("br").append("\n");
        document.select("p").prepend("\n\n");
        String s = document.html();
        return Jsoup.clean(s, "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));
    }
    /**
     * Method onlyJava. Проверка строки на наличие слова java и отсутствия слова javascript
     * @param name имя вакансии
     * @return true/false
     */
    private boolean onlyJava(String name) {
        return !name.toLowerCase().contains("javascript")
                && !name.toLowerCase().contains("java script")
                && name.toLowerCase().contains("java");
    }
    /**
     * Method parseTextToDate. Получение даты из строки
     * @param textWithDate строка с датой
     * @return дата
     */
    private Date parseTextToDate(String textWithDate) throws ParseException {
        if (textWithDate.toLowerCase().contains("сегодня")) {
            String today = this.getRelativeShortStringDate(0);
            textWithDate = textWithDate.replace("сегодня", today);
        }
        if (textWithDate.toLowerCase().contains("вчера")) {
            String yesterday = this.getRelativeShortStringDate(-1);
            textWithDate = textWithDate.replace("вчера", yesterday);
        }
        return this.getDate(textWithDate);
    }
    /**
     * Method getRelativeShortStringDate. Получение строки из даты
     * @param amount относительное смещение от сегодняшней даты(в днях)
     * @return строчное представление даты
     */
    private String getRelativeShortStringDate(int amount) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, amount);
        Date date = cal.getTime();
        return new SimpleDateFormat("d MMM yy").format(date);
    }
    /**
     * Method getStringDate. Получение строки из даты в формате d MMM yy, H:m
     * @param date дата
     * @return строчное представление даты
     */
    private String getStringDate(Date date) {
        return new SimpleDateFormat("d MMM yy, H:m").format(date);
    }
    /**
     * Method getDate. Получение строки из даты
     * @param stringWithDate строка с датой
     * @return дата
     */
    private Date getDate(String stringWithDate) throws ParseException {
        return new SimpleDateFormat("d MMM yy, H:m").parse(stringWithDate);
    }
    /**
     * Method getNumbersOfPages. Получение количества страниц
     * @param doc doc
     * @return число страниц
     */
    private int getNumbersOfPages(Document doc) {
        int pages = 1;
        Element listWithPages = doc.select("td[style=text-align:left]").last();
        Element num = listWithPages.select("a[href]").last();
        if (num != null) {
            pages = Integer.valueOf(num.text());
        }
        return pages;
    }

    public static void main(String[] args) {
        ParserSqlRu parserSqlRu = new ParserSqlRu();
        parserSqlRu.start();
    }
}
