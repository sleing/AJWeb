package org.ddd.app.thread.vendor;
public class Vendor {
    // 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
    private int count;

    // 锟斤拷锟斤拷锟斤拷锟斤拷锟�
    private final int MAX_COUNT = 10;

    public synchronized void production() {
        while (count >= MAX_COUNT) {
            try {
                System.out.println(Thread.currentThread().getName() + "商品已满，等待.....");
                // 锟斤拷时锟斤拷锟斤拷锟竭筹拷全锟斤拷锟斤拷锟斤拷却锟阶刺�
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷品
        count++;
        System.out.println(Thread.currentThread().getName() + "生产一个商品，总共：" + count);
        this.notifyAll();

    }

    public synchronized void consumers() {
        while (count <= 0) {
            try {
                System.out.println(Thread.currentThread().getName() + "没有商品，请等待...");
                this.wait();// blocking 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count--;
        System.out.println(Thread.currentThread().getName() + "消耗一个商品，总共:" + count);
        this.notifyAll();
    }
}