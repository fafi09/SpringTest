package fafi.Impl;
public class OrderServiceImpl implements OrderService {
    @Override
    public int add(int a, int b) {
        return a+b;
    }
    @Override
    public void doPrint() {
        System.out.println("------1111111------打印HelloWorldImpl1-----1111111------");
        return ;
    }
}
