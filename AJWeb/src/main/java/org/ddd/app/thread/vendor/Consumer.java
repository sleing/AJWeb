package org.ddd.app.thread.vendor;
public class Consumer implements Runnable {

    private Vendor vendor;

    public Consumer(Vendor vendor) {
        this.vendor = vendor;
    }

    @Override
    public void run() {
        while(true){
            vendor.consumers();
            try {
            	Thread.sleep( (int)(Math.random()*5000) );
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}