package nonblocking;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Optional;

public class NonBlocking {
    private ConcurrentHashMap<Integer, Base> cache = new ConcurrentHashMap<Integer, Base>();
    /**
     * Метод add. Добавление элемента в коллекцию.
     * @param model Элемент.
     */
    public void add(Base model) {
        cache.putIfAbsent(model.id, model);
    }
    /**
     * Метод update. Обновление элемента в коллекции.
     * @param model Элемент.
     */
    public void update(Base model) {
        this.cache.computeIfPresent(model.id, (Integer k, Base v) -> {
                    System.out.format("this occurs in the %s:\n", Thread.currentThread().getName());
                    System.out.format("version from CACHE = %s\n", v.version);
                    System.out.format("version of model-PARAMETR = %s\n", model.version);
                    if (v.equals(model)) {
                        ++model.version;
                        System.out.format("update id = %s from ver.%s to %s by %s\n\n",
                                v.id, v.version, model.version, Thread.currentThread().getName());
                    } else {
                        System.out.format("model with id = %s is DID NOT update by %s\n\n",
                                model.id, Thread.currentThread().getName());
                        throw new OptimisticException("Data already updated by another thread!");
                    }
                    return model;
                }
        );
    }
    /**
     * Метод delete. Удаление элемента из коллекции.
     * @param model Элемент.
     */
   /* public void delete(Base model){
        System.out.println(this.cache.computeIfPresent(model.id, (Integer k, Base v) -> {
            Base result = model;
            if (model.equals(v)) {
                result = new Base(model.id, ++model.version);
                this.cache.remove(model.id);
                System.out.println("deleted id = " + v.id + " by " + Thread.currentThread().getName());
            }
            return result;
        }));
        if (!this.cache.contains(model)){
            System.out.println(true);
            // System.out.println("id = " + model.id + " is did not delete by " + Thread.currentThread().getName());
            // throw new OptimisticException("Data already deleted by another thread!");
        }
    }*/
    /**
     * Метод size. Размер коллекции.
     * @return model Элемент.
     */
    public int size() {
        // System.out.println("array=" + cache.toString());
        return this.cache.size();
    }
    /**
     * Метод get. Получение элемента коллекции.
     * @return Элемент.
     */
    public Base get(int id) {
        //System.out.println("get id=" + id);
        Optional<Base> res = Optional.of(this.cache.get(id));
        return res.orElseGet(null);
    }
}
