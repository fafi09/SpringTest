package fafi.generic;

/**
 * 数组能追踪元素的实际类型，这个类型是在数组创建的时候建立的。上面被注释掉的一行代码：
 * gia = (Generic<Integer>[])new Object[SIZE]，数组在创建的时候是一个 Object 数组，
 * 如果转型便会报错。成功创建泛型数组的唯一方式是创建一个类型擦除的数组，然后转型，
 * 如代码： gia = (Generic<Integer>[])new Generic[SIZE]。
 * gia 的 Class 对象输出的名字是 Generic[]。
 *
 * 我个人的理解是：由于类型擦除，所以 Generic<Integer> 相当于初始类型 Generic，
 * 那么 gia = (Generic<Integer>[])new Generic[SIZE] 中的转型其实还是转型为 Generic[]，
 * 看上去像没转，但是多了编译器对参数的检查和自动转型，
 * 向数组插入 new Object() 和 new Generic<Double>() 均会报错，
 * 而 gia[0] 取出给 Generic<Integer> 也不需要我们手动转型。
 */
public class ArrayOfGeneric {
    static final int SIZE = 100;
    static Generic<Integer>[] gia;
    public static void main(String[] args) {
        gia = (Generic<Integer>[])new Generic[SIZE];
        System.out.println(gia.getClass().getSimpleName());
        Generic<Integer> g = gia[0];
    }
}
