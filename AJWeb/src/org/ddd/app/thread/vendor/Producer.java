package org.ddd.app.thread.vendor;
public class Producer implements Runnable {

    private Vendor vendor;

    public Producer(Vendor vendor) {
        this.vendor = vendor;
    }

    @Override
    public void run() {
        while(true){
            vendor.production();
            try {
                Thread.sleep( (int)(Math.random()*5000) );
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}