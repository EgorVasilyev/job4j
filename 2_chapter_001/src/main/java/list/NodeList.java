package list;
/**
 * @author Egor
 * @version 1
 * @since 16.12.2018
 */

/**
 * Класс SimpleArrayList.
 */
public class NodeList<T> {
    /**
     * Head node.
     */
    private final Node<T> first;

    /**
     * Creates node list.
     *
     * @param first First element of the list.
     */
    public NodeList(Node<T> first) {
        this.first = first;
    }

    public boolean hasCycle() {
        boolean result = false;
        if (this.first != null) {
            Node slow, fast;
            slow = this.first;
            fast = this.first;
            while (slow.next != null && fast.next!= null) {
                slow = slow.next;
                fast = fast.next.next;
                if (fast == null) {
                    break;
                }
                if (slow == fast) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}
