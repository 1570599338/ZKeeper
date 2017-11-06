package com.lquan.curator.lock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Title   Lock1.java
 * Descr   利用jvm自身的ReentrantLock类进行锁的重入
 * Email   lquan.liu@outlook.com 
 * @author lquan
 * @dates  2017年11月7日 上午3:18:28
 */
public class Lock1 {

	static ReentrantLock reentrantLock = new ReentrantLock();
	static int count = 10;
	public static void genarNo(){
		try {
			reentrantLock.lock();
			count--;
			//System.out.println(count);
		} finally {
			reentrantLock.unlock();
		}
	}
	
	public static void main(String[] args) throws Exception{
		
		final CountDownLatch countdown = new CountDownLatch(1);
		for(int i = 0; i < 10; i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						countdown.await();
						genarNo();
						SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss|SSS");
						System.out.println(sdf.format(new Date()));
						//System.out.println(System.currentTimeMillis());
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
					}
				}
			},"t" + i).start();
		}
		Thread.sleep(50);
		countdown.countDown();

		
	}
}
