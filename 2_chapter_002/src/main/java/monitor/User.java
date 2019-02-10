package monitor;

public class User {
    private int id;
    private int amount;

    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    @Override
    public boolean equals(Object object) {
        boolean result = false;
        if (object == this) {
            result = true;
        } else if (object != null && getClass() == object.getClass()) {
            User user = (User) object;
            result = this.id == user.id;
        }
        return result;
    }
    @Override
    public int hashCode() {
        return this.id;
    }
}
