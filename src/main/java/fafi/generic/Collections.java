package fafi.generic;

import java.util.List;

/**
 * src 是原始数据的 List，因为要从这里面读取数据，所以用了上边界限定通配符：<? extends T>，
 * 取出的元素转型为 T。dest 是要写入的目标 List，所以用了下边界限定通配符：<? super T>，
 * 可以写入的元素类型是 T 及其子类型
 */
public class Collections {
    public static <T> void copy(List<? super T> dest, List<? extends T> src)
    {
        for (int i=0; i<src.size(); i++) {
            dest.set(i, src.get(i));
        }
    }
}
