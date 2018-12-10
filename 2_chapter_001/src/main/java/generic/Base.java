package generic;

/**
 * @author Egor
 * @version $Id$
 * @since 0.1
 */
public abstract class Base {
    private String id;

    protected Base(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
