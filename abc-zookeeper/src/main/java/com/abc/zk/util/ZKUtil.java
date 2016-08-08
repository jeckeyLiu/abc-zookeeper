package com.abc.zk.util;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import com.abc.zk.service.ZKService;
import com.abc.zk.watcher.DefaultWatcher;

public class ZKUtil {
	
	private ZooKeeper zooKeeper = null;
	
	private void init(){
		if(zooKeeper==null){
			zooKeeper = ZKService.instance(new DefaultWatcher());
		}
		if(zooKeeper==null){
			throw new RuntimeException("zookeeper初始化失败！！！");
		}
	}
	/**
	 * @description 创建路径
	 * @param path 路径
	 * @param data 路径数据
	 * @param createMode 路径类型
	 * @return
	 * @author liujunjie
	 * @date 2016年8月8日 下午4:26:01
	 */
	public String createPath(String path ,String data,CreateMode createMode){
		init();
		try {
			if(zooKeeper.exists("/zk_test1", false)==null){
				return zooKeeper.create(path,data.getBytes(), Ids.OPEN_ACL_UNSAFE, createMode);
			}
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "";
	} 
	
	/**
	 * @description 获取路径的值
	 * @param path 路径
	 * @param watch 是否监听
	 * @param stat 状态，返回最终的路径状态信息
	 * @return
	 * @author liujunjie
	 * @date 2016年8月8日 下午4:26:44
	 */
	public String getData(String path,boolean watch,Stat stat){
		init();
		try {
			byte[] data = zooKeeper.getData(path, watch,stat);
			return new String(data);
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @description 更改路径值
	 * @param path 路径
	 * @param value 值
	 * @param version 版本，默认为-1，如果版本与当前版本不一致则不修改
	 * @return
	 * @author liujunjie
	 * @date 2016年8月8日 下午4:28:08
	 */
	public Stat setData(String path,String value,Integer version){
		init();
		try {
			if(version==null){
				version = -1;
			}
			return zooKeeper.setData(path, value.getBytes(), version);
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		ZKUtil zk = new ZKUtil();
		Stat stat = new Stat();
		String aa = zk.createPath("/zk_test1", "test", CreateMode.PERSISTENT);
		String bb = zk.getData("/zk_test1", true,stat);
		zk.setData("/zk_test1", "change", 5);
		System.out.println(aa);
		System.out.println(bb);
	}

}
