package map;
import java.util.Calendar;

 /**
 * @author Egor Vasilyev (deseng_pgs@mail.ru)
 * @version 1
 * @since 19.12.2018
 */
public class User {
    /**
     * Name.
     */
    private String name;
    /**
     * children - number the children the User has.
     */
    private int children;
    /**
     * birthday.
     */
    private Calendar birthday;
    /**
     * constructor.
     * @param name -
     * @param children -
     * @param birthday -
     */
    User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }
     /**
      * setBirthday.
      * @param calendar - birthday
      */
     public void setBirthday(Calendar calendar) {
         this.birthday = calendar;
     }
}