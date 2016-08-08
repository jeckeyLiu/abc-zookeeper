package com.abc.zk.service;

import java.io.IOException;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class ZKService {
	
	private static String host = "192.168.8.59:2181,192.168.8.59:2182,192.168.8.59:2183";
	
	private static ZooKeeper zooKeeper = null;
	
	public static ZooKeeper instance(Watcher watcher){
		
		try {
			zooKeeper = new ZooKeeper(host,5000,watcher);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return zooKeeper;
	}
}
