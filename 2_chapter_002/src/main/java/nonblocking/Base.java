package nonblocking;

import java.util.Objects;

public class Base {
    int id;
    int version;

    public Base(int id) {
        this.id = id;
        this.version = 0;
    }

    public Base(int id, int version) {
        this.id = id;
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Base base = (Base) o;
        return this.id == base.id
                && this.version == base.version;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version);
    }

    @Override
    public String toString() {
        return "Base{"
                + "id=" + id
                + ", version=" + version
                + '}';
    }
}
