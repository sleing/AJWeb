package org.ddd.app.thread.vendor;
public class Demo {
    public static void main(String[] args) {
        Vendor vendor = new Vendor();
        Producer set = new Producer(vendor);
        Consumer get = new Consumer(vendor);
        
        // �����߳�������Ʒ
        new Thread(set).start();
        new Thread(set).start();
        new Thread(set).start();
        new Thread(set).start();
        
        // �����������߳�
        new Thread(get).start();
    }
}