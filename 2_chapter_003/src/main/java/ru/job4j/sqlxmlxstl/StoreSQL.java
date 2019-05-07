package ru.job4j.sqlxmlxstl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Class StoreSQL. Генерация данных в SQLLite.
 */
public class StoreSQL implements AutoCloseable {
    private File xmlFile;
    private Connection connect;
    private static final Logger LOG = LogManager.getLogger(StoreSQL.class.getName());
    /**
     * Constructor StoreSQL.
     * @param config Объект, содержащий настройки для подключения к базе.
     */
    public StoreSQL(Config config, File targetForXML) {
        this.xmlFile = targetForXML;
        config.init();
/*      //for postgres with Config of app.properties
        try {
            Class.forName(this.config.get("driver-class-name"));
            this.connect = DriverManager.getConnection(
                    this.config.get("url"),
                    this.config.get("username"),
                    this.config.get("password")
            );
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }*/

        //for sqlite with Config of appSQLite.properties
        try {
            this.connect = DriverManager.getConnection(config.get("url"));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }
    /**
     * Method generate. Генерирует в базе данных n записей
     * @param size Количество записей
     */
    public void generate(int size) {
        createEntryTable();
        if (size > 0) {
            try (PreparedStatement stForQuery = connect.prepareStatement(
                    "insert into entry(field) values (?);")) {
                for (int i = 1; i <= size; i++) {
                    stForQuery.setInt(1, i);
                    stForQuery.addBatch();
                }
                stForQuery.executeBatch();
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }
    /**
     * Method load. Возвращает лист объектов Entry со значениями записей из таблицы entry в БД
     * @return лист объектов Entry
     */
    public List<Entry> load() {
        List<Entry> entries = new LinkedList<>();
        ResultSet result;
        try (Statement stForQuery = connect.createStatement()) {
            result = stForQuery.executeQuery("select * from entry;");
            while (result.next()) {
                entries.add(new Entry(result.getInt("field")));
            }
            result.close();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return entries;
    }
    /**
     * Method close. Завершает соединение
     */
    @Override
    public void close() throws Exception {
        if (connect != null) {
            connect.close();
        }
    }
    /**
     * Method createEntryTable. Создание таблицы Entry, если отсутствует. Удаляет все записи из таблицы.
     */
    private void createEntryTable() {
        try (Statement stForCreateEntry = this.connect.createStatement();
             Statement stForClearEntry = this.connect.createStatement()) {
            stForCreateEntry.execute("create table if not exists entry(\n"
                    + "\tfield integer"
                    + ");");
            stForClearEntry.execute("delete from entry;");
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
    /**
     * Method showSumOfFields. Выводит арифметическую сумму значений всех атрибутов field в консоль
     */
    public void showSumOfFields() {
        int sum = 0;
        List<Integer> listValues = SaxParseXML.parseToListInteger(this.xmlFile.getPath());
        for (int value : listValues) {
            sum += value;
        }
        System.out.println("sum = " + sum);
    }
    /**
     * Method fieldsToXML. Генерация XML из данных базы
     */
    public void fieldsToXML() throws JAXBException {
        List<Entry> list = this.load();
        StoreXML storeXML = new StoreXML(this.xmlFile);
        storeXML.save(list);
    }
    /**
     * Method convertXMLtoXLTS. Конвертирует XML в XLTS
     * @param targetXLTS конечный XLTS файл
     * @param scheme схема для конвертации
     */
    public void convertXMLtoXLTS(File targetXLTS, File scheme) {
        ConvertXSTL.convert(this.xmlFile, targetXLTS, scheme);
    }

    public static void main(String[] args) throws Exception {
        String path = System.getProperty("java.io.tmpdir");
        String fs = File.separator;

        File targetForXML = new File(path + fs + "XMLtarget.xml");
        File targetXLTS = new File(path + fs + "XLTStarget.xlts");
        File scheme = new File(StoreSQL.class.getClassLoader().getResource("scheme.xsl").getPath());

        try (StoreSQL storeSQL = new StoreSQL(new Config(), targetForXML)) {
            storeSQL.generate(4);
            storeSQL.fieldsToXML();
            storeSQL.convertXMLtoXLTS(targetXLTS, scheme);
            storeSQL.showSumOfFields();
        }
    }
}