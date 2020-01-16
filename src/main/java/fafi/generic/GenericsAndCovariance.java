package fafi.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * flist 的类型是 List<? extends Fruit>，我们可以把它读作：一个类型的 List，
 * 这个类型可以是继承了 Fruit 的某种类型。注意，这并不是说这个 List 可以持有 Fruit 的任意类型。
 * 通配符代表了一种特定的类型，它表示 “某种特定的类型，但是 flist 没有指定”。这样不太好理解，
 * 具体针对这个例子解释就是，flist 引用可以指向某个类型的 List，只要这个类型继承自 Fruit，
 * 可以是 Fruit 或者 Apple，比如例子中的 new ArrayList<Apple>，但是为了向上转型给 flist，
 * flist 并不关心这个具体类型是什么。
 *
 * 如上所述，通配符 List<? extends Fruit> 表示某种特定类型 ( Fruit 或者其子类 ) 的 List，
 * 但是并不关心这个实际的类型到底是什么，反正是 Fruit 的子类型，Fruit 是它的上边界。
 * 那么对这样的一个 List 我们能做什么呢？其实如果我们不知道这个 List 到底持有什么类型，
 * 怎么可能安全的添加一个对象呢？在上面的代码中，
 * 向 flist 中添加任何对象，无论是 Apple 还是 Orange 甚至是 Fruit 对象，编译器都不允许，
 * 唯一可以添加的是 null。所以如果做了泛型的向上转型
 * (List<? extends Fruit> flist = new ArrayList<Apple>())，
 * 那么我们也就失去了向这个 List 添加任何对象的能力，即使是 Object 也不行。
 *
 * 另一方面，如果调用某个返回 Fruit 的方法，这是安全的。因为我们知道，
 * 在这个 List 中，不管它实际的类型到底是什么，但肯定能转型为 Fruit，所以编译器允许返回 Fruit。
 */
public class GenericsAndCovariance {
    public static void main(String[] args) {
        // Wildcards allow covariance:
        List<? extends Fruit> flist = new ArrayList<Apple>();
        // Compile Error: can’t add any type of object:
        // flist.add(new Apple());
        // flist.add(new Fruit());
        // flist.add(new Object());
        flist.add(null); // Legal but uninteresting
        // We know that it returns at least Fruit:
        Fruit f = flist.get(0);



    }
}
