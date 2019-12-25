package fafi.Impl;

import fafi.annotation.Compensable;

public class CaptialServiceImpl implements CaptialService {
    @Override
    @Compensable
    public void doRecord() {
        System.out.println("dorecord...");
    }
}
