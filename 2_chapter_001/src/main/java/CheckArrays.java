import java.util.Comparator;
import java.util.TreeSet;

public class CheckArrays <T>{
    public static <T extends Comparable<? super T>> boolean equals(T[] arrayA, T[] arrayB) {
        TreeSet<T> check = new TreeSet<T>();
        boolean result = true;
        if(arrayA.length == arrayB.length) {
            for(int i = 0 ; i < arrayA.length; i++) {
                if(check.add(arrayA[i]) && check.add(arrayB[i])) {
                    result = false;
                    break;
                }
                check.clear();
            }
        } else {
            result = false;
        }
        return result;
    }
    public static <T> boolean equals(T[] arrayA, T[] arrayB, Comparator<? super T> comparatorForT) {
        TreeSet<T> check = new TreeSet<T>(comparatorForT);
        boolean result = true;
        if(arrayA.length == arrayB.length) {
            for(int i = 0 ; i < arrayA.length; i++) {
                if(check.add(arrayA[i]) && check.add(arrayB[i])) {
                    result = false;
                    break;
                }
                check.clear();
            }
        } else {
            result = false;
        }
        return result;
    }
}
