package fafi.generic;

import java.util.List;

/**
 * writeTo 方法的参数 apples 的类型是 List<? super Apple>，它表示某种类型的 List，
 * 这个类型是 Apple 的基类型。也就是说，我们不知道实际类型是什么，但是这个类型肯定是 Apple 的父类型。
 * 因此，我们可以知道向这个 List 添加一个 Apple 或者其子类型的对象是安全的，这些对象都可以向上转型为 Apple。
 * 但是我们不知道加入 Fruit 对象是否安全，因为那样会使得这个 List 添加跟 Apple 无关的类型。
 */
public class SuperTypeWildcards {
    static void writeTo(List<? super Apple> apples) {
        apples.add(new Apple());
        apples.add(new Jonathan());
        // apples.add(new Fruit()); // Error
    }
}
