package com.broeckel.bierkompressor;

/**
 * Created by Matti Borchers on 10.07.13.
 */
public class TestSingleton {
    private static TestSingleton ourInstance = new TestSingleton();

    public static TestSingleton getInstance() {
        return ourInstance;
    }

    private TestSingleton() {
    }
}
