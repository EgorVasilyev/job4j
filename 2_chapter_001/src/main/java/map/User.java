package map;
import java.util.Calendar;
import java.util.Objects;

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
/*     @Override
     public int hashCode() {
         final int prime = 31;
         int result = 13;
         result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
         result = prime * result + this.children;
         result = prime * result + ((this.birthday == null) ? 0 : this.birthday.hashCode());
         return result;
     }*/
     @Override
     public boolean equals(Object obj) {
         if (obj == this) {
             return true;
         }
         if (obj == null || obj.getClass() != this.getClass()) {
             return false;
         }
         User user = (User) obj;
         return Objects.equals(this.name, user.name) && this.children == user.children
                 && this.birthday == user.birthday;
     }
}