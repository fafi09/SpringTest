package fafi.generic;

/**
 * 上面的代码只修改了第一行，
 * 把 <T> 改成了 <T extends Integer>，那么不用调用 rep()，在创建泛型数组的时候就会报错
 * @param <T>
 */
public class GenericArray <T> { /*<T extends Integer> {*/
    private T[] array;
    @SuppressWarnings("unchecked")
    public GenericArray(int sz) {
        array = (T[])new Object[sz];   // 创建泛型数组
    }
    public void put(int index, T item) {
        array[index] = item;
    }
    public T get(int index) { return array[index]; }
    // Method that exposes the underlying representation:
    public T[] rep() { return array; }     //返回数组 会报错
    public static void main(String[] args) {
        GenericArray<Integer> gai =
                new GenericArray<Integer>(10);
        // This causes a ClassCastException:
        //! Integer[] ia = gai.rep();
        // This is OK:
        Object[] oa = gai.rep();
    }
}
