package com.catofdestruction;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * a custom class loader(for Hello.xlass) named HelloClassLoader
 *
 * @author wangxinyu
 * @date 2020/10/21
 */
public class HelloClassLoader extends ClassLoader {

    private static final String FILE_NAME = "Hello.xlass";
    private static final String CLASS_NAME = "Hello";
    private static final String METHOD_NAME = "hello";

    public static void main(String[] args) {
        try {
            Class<?> aClass = new HelloClassLoader().findClass(CLASS_NAME);
            Object obj = aClass.newInstance();
            Method method = aClass.getMethod(METHOD_NAME);
            method.invoke(obj);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException |
                InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = decodeFromFile(new File(this.getClass().getResource(FILE_NAME).getPath()));
        return defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] decodeFromFile(File file) {
        int length = (int) file.length();
        byte[] bytes = new byte[length];
        try {
            System.out.printf("read %s bytes from %s %n", new FileInputStream(file).read(bytes), file.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < length; ++i) {
            bytes[i] = (byte) (255 - bytes[i]);
        }
        return bytes;
    }
}
