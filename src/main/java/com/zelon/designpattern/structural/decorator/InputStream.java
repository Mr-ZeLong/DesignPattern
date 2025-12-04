package com.zelon.designpattern.structural.decorator;

import java.io.IOException;

/*
装饰器模式
一、使用场景
对原有业务功能进行增强，但是过多的增强功能会导致继承树结构复杂，使用装饰器模式，用组合替代继承，

二、实现方式
装饰器类需要跟原始类有相同的抽象类或者接口，原始类通过构造函数传入装饰器类，这样原始类可以嵌套多个装饰器。
在装饰器内部，一般情况下需要重写原始类中的所有方法，才能将在嵌套多个装饰器的情况下，
将请求委派给原始类或上一个装饰器类，才不会让之前的装饰器类的功能增强失效，因为我们不知道之前的装饰器类到底增强了哪个方法。
注意：如果不想每次都重写所有方法，可以加一个中间类继承原始类，在中间类中重写所有方法，将所有请求委派给原始类，
其他的装饰器类都继承中间类，这样装饰器类只要重写需要增强的方法，其他方法不用重写，也会委派给原始类或上一个装饰器类对象执行。

三、与代理模式的区别？
二者都是结构性设计模式，都能够增加额外的功能，但是代理模式是附加一些与原有业务逻辑无关的非业务性功能，
而装饰器模式是在原有业务功能上进行增强，是业务相关的。
 */

public abstract class InputStream {
    //...
    public int read(byte b[]) throws IOException {
        return read(b, 0, b.length);
    }

    public int read(byte b[], int off, int len) throws IOException {
        //...
        return 0;
    }

    public long skip(long n) throws IOException {
        //...
        return 0;
    }

    public int available() throws IOException {
        return 0;
    }

    public void close() throws IOException {}

    public synchronized void mark(int readlimit) {}

    public synchronized void reset() throws IOException {
        throw new IOException("mark/reset not supported");
    }

    public boolean markSupported() {
        return false;
    }
}

class BufferedInputStream extends InputStream {
    protected volatile InputStream in;

    protected BufferedInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read(byte[] b) throws IOException {
        return in.read(b);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        // todo：实现缓存功能增强
        return in.read(b, off, len);
    }

    @Override
    public long skip(long n) throws IOException {
        return in.skip(n);
    }

    @Override
    public int available() throws IOException {
        return in.available();
    }

    @Override
    public void close() throws IOException {
        in.close();
    }

    @Override
    public synchronized void mark(int readlimit) {
        in.mark(readlimit);
    }

    @Override
    public synchronized void reset() throws IOException {
        in.reset();
    }

    @Override
    public boolean markSupported() {
        return in.markSupported();
    }
}


class DataInputStream extends InputStream {
    protected volatile InputStream in;

    protected DataInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return in.read(b, off, len);
    }

    @Override
    public long skip(long n) throws IOException {
        return in.skip(n);
    }

    @Override
    public int read(byte[] b) throws IOException {
        return in.read(b);
    }

    @Override
    public int available() throws IOException {
        return in.available();
    }

    @Override
    public void close() throws IOException {
        in.close();
    }

    @Override
    public synchronized void mark(int readlimit) {
        in.mark(readlimit);
    }

    @Override
    public synchronized void reset() throws IOException {
        in.reset();
    }

    @Override
    public boolean markSupported() {
        return in.markSupported();
    }

    //...实现读取基本类型数据的接口
}