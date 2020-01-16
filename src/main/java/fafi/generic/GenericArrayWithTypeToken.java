package fafi.generic;

import java.lang.reflect.Array;

/**
 * 在构造器中传入了 Class<T> 对象，通过 Array.newInstance(type, sz) 创建一个数组，
 * 这个方法会用参数中的 Class 对象作为数组元素的组件类型。这样创建出的数组的元素类型便不再是 Object，而是 T。
 * 这个方法返回 Object 对象，需要把它转型为数组。不过其他操作都不需要转型了，包括 rep() 方法，
 * 因为数组的实际类型与 T[] 是一致的。这是比较推荐的创建泛型数组的方法。
 * @param <T>
 */
public class GenericArrayWithTypeToken<T> {
    private T[] array;
    @SuppressWarnings("unchecked")
    public GenericArrayWithTypeToken(Class<T> type, int sz) {
        array = (T[]) Array.newInstance(type, sz);
    }
    public void put(int index, T item) {
        array[index] = item;
    }
    public T get(int index) { return array[index]; }
    // Expose the underlying representation:
    public T[] rep() { return array; }
    public static void main(String[] args) {
        GenericArrayWithTypeToken<Integer> gai =
                new GenericArrayWithTypeToken<Integer>(
                        Integer.class, 10);
        // This now works:
        Integer[] ia = gai.rep();
    }
}
