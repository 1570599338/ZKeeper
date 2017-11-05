package com.lquan.zkclient.base;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;
import org.apache.zookeeper.CreateMode;



public class ZkClientBaseTest {
	// zk  的URL地址
	private final static String zkurl="192.168.207.6:2181";
	// zkclinet的延时时长
	private final static int detime = 5000;

	public static void main(String[] args) {
		// 创建zkclient的连接
		ZkClient zkc = new ZkClient(new ZkConnection(zkurl),detime);
		
		// 创建节点4中形式
		zkc.create("/zcx/zzz", "aaa", CreateMode.PERSISTENT);
		
		
		
		
		// 结束zk
		zkc.close();

	}

}
