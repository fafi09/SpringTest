package fafi.generic;

import java.util.Arrays;
import java.util.List;

/**
 * 在上面的例子中，flist 的类型是 List<? extends Fruit>，泛型参数使用了受限制的通配符，所以我们失去了向其中加入任何类型对象的例子，最后一行代码无法编译。
 *
 * 但是 flist 却可以调用 contains 和 indexOf 方法，它们都接受了一个 Apple 对象做参数。如果查看 ArrayList 的源代码，可以发现 add() 接受一个泛型类型作为参数，但是 contains 和 indexOf 接受一个 Object 类型的参数，下面是它们的方法签名：
 *
 * public boolean add(E e)
 * public boolean contains(Object o)
 * public int indexOf(Object o)
 * 所以如果我们指定泛型参数为 <? extends Fruit> 时，add() 方法的参数变为 ? extends Fruit，编译器无法判断这个参数接受的到底是 Fruit 的哪种类型，所以它不会接受任何类型。
 *
 * 然而，contains 和 indexOf 的类型是 Object，并没有涉及到通配符，所以编译器允许调用这两个方法。这意味着一切取决于泛型类的编写者来决定那些调用是 “安全” 的，并且用 Object 作为这些安全方法的参数。如果某些方法不允许类型参数是通配符时的调用，这些方法的参数应该用类型参数，比如 add(E e)。
 */
public class CompilerIntelligence {
    public static void main(String[] args) {
        List<? extends Fruit> flist =
                Arrays.asList(new Apple());
        Apple a = (Apple)flist.get(0); // No warning
        flist.contains(new Apple()); // Argument is ‘Object’
        flist.indexOf(new Apple()); // Argument is ‘Object’

        //flist.add(new Apple());   无法编译
    }
}
