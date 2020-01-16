package fafi.generic;

/**
 * 现在内部数组的呈现不是 T[] 而是 Object[]，当 get() 被调用的时候数组的元素被转型为 T，
 * 这正是元素的实际类型。不过调用 rep() 还是会报错， 因为数组的实际类型依然是Object[]，
 * 终究不能转换为其它类型。
 * 使用 Object[] 代替 T[] 的好处是让我们不会忘记数组运行期的实际类型，以至于不小心引入错误。
 * @param <T>
 */
public class GenericArray2<T> {
    private Object[] array;
    public GenericArray2(int sz) {
        array = new Object[sz];
    }
    public void put(int index, T item) {
        array[index] = item;
    }
    @SuppressWarnings("unchecked")
    public T get(int index) { return (T)array[index]; }
    @SuppressWarnings("unchecked")
    public T[] rep() {
        return (T[])array; // Warning: unchecked cast
    }
    public static void main(String[] args) {
        GenericArray2<Integer> gai =
                new GenericArray2<Integer>(10);
        for(int i = 0; i < 10; i ++) {
            gai.put(i, i);
        }
        for(int i = 0; i < 10; i ++) {
            System.out.print(gai.get(i) + " ");
        }
        System.out.println();
        try {
            Integer[] ia = gai.rep();
        } catch(Exception e) { System.out.println(e); }
    }
}
/* Output: (Sample)
0 1 2 3 4 5 6 7 8 9
java.lang.ClassCastException: [Ljava.lang.Object; cannot be cast to [Ljava.lang.Integer;
*///:~
