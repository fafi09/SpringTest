package fafi.Impl;

public class CheckArithtectArgServiceImpl implements CheckArithtectArgService {
    @Override
    public boolean checkArithArg(int a) {
        return a > 0 ? true: false;
    }
}
