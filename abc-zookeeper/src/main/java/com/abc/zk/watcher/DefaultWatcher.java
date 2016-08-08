package com.abc.zk.watcher;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

public class DefaultWatcher implements Watcher{

	public void process(WatchedEvent event) {
		System.out.println("watcher,path"+event.getPath());
		System.out.println("watcher,state"+event.getState());
		System.out.println("watcher,type"+event.getType());
	}

}
