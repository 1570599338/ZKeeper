package com.lquan.zkeeper.base;

import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * Zookeeper 作为一个分布式的服务框架，
 * 主要用来解决分布式集群中应用系统的一致性问题，
 * 它能提供基于类似于文件系统的目录节点树方式的数据存储，
 * 但是 Zookeeper 并不是用来专门存储数据的，
 * 它的作用主要是用来维护和监控你存储的数据的状态变化。
 * 通过监控这些数据状态的变化，从而可以达到基于数据的集群管理
 * zk的原生API进行操作zk
 * @author lquan
 *
 */
public class ZKBase {
	// zk的地址
	private static String urlString ="192.168.207.6:2181";
	// zk的session超时时长
	private final static int sessionTimeout =2000;//ms
	
	
	
	
	
		
	public static void main(String[] args) throws Exception {
		
	
		// 创建一个zk的服务器连接
		ZooKeeper zk = new ZooKeeper(urlString, sessionTimeout, new Watcher() {
			
			@Override
			public void process(WatchedEvent event) {
				System.out.println("已经触发了" + event.getType() + "事件！"); 
				
				//  获取事件的状态
				KeeperState keeperState = event.getState();
				// 获取事件的类型
				EventType eventType = event.getType();
				// 如果是建立连接
				if (KeeperState.SyncConnected == keeperState) {
					// 判断事件类型
					if (EventType.None == eventType) {
						System.out.println("zk  建立连接");
					}
					// 创建节点
					if(EventType.NodeCreated == eventType){
						System.out.println("zk 创建节点："+event.getPath());
					}
				}
				
				System.out.println("**********事件状态："+keeperState+"  事件类型："+eventType);
				
				
				
			}
		});
		
		// exists 方法意义在于无论节点是否存在，都可以进行注册watcher，能够堆节点的创建，
		// 删除和修改进行监听，但是其自节点发送各种变化，都不会通知客户端。
		Stat aa = zk.exists("/lquan/lquan8", true);
		//System.out.println("****"+aa.toString());
		// 创建一个节点 lquan quan
		String  zkc = zk.create("/lquan/lquan8", "lquanc".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		System.out.println("create:"+ zkc);
		
		
		// 获取节点值
//		String zkgetdata = new String(zk.getData("/lquan/lquan2", true, null));
//		System.out.println("获取数据"+zkgetdata);
		
		// 获取自节点
//		List<String> zkList = zk.getChildren("/lquan", null);
//		System.out.println("zk list:"+zkList);
		
		// 设置子节点
//		zk.setData("/lquan/lquan4", "quanaaxd".getBytes(), -1);
		
//		 zk.create("/testRootPath/testChildPathTwo", "testChildDataTwo".getBytes(), 
//				   Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT); 
//		System.out.println(new String(zk.getData("/testRootPath/testChildPathTwo",true,null))); 
		
//		zk.delete("/lquan/lquan5", -1);
		
		
		
		
		zk.close();
		
				

	}
	
	
}
