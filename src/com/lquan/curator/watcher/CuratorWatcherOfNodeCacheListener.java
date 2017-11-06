package com.lquan.curator.watcher;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Title   CuratorWatcherOfNodeCacheListener.java
 * Descr   亲自手动敲一遍NodeCacheListener的实现
 * Email   lquan.liu@outlook.com 
 * @author lquan
 * @dates  2017年11月6日 上午4:06:52
 */
public class CuratorWatcherOfNodeCacheListener {
	//	连接url
	private final static String url="192.168.207.6:2181";
	// session_time的延时时间
	private final static int Session_out=5000;
	
	
	public static void main(String[] args) {
		// 1,curator的连接策略 即：重试策略：初试时间为1s 重试10次
		 RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);
		 // 2,通过工厂创建连接，因为curator框架使用的链式变成风格
		CuratorFramework cf = CuratorFrameworkFactory.builder()
		 						.connectString(url)
		 						.connectionTimeoutMs(Session_out)
		 						.retryPolicy(retryPolicy)
		 						.build();
		// 3.建立连接
		cf.start();
		

	}

}
