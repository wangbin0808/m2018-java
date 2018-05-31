package com.jrj.wx.json.Scheduling;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @author bin.wang
 * 这个类主要是往奖品池里面存放奖品的
 *
 */
@Slf4j
@Service
public class PrizePoolScheduling {
	//key 1、2、3分别表示一二三等奖，value表示奖品的数量
	private Map<Integer, Integer> prizePool = new ConcurrentHashMap<Integer, Integer>(16);

	//初始化
	public PrizePoolScheduling() {
		this.prizePool.put(Integer.valueOf(1), Integer.valueOf(0));
		this.prizePool.put(Integer.valueOf(2), Integer.valueOf(0));
		this.prizePool.put(Integer.valueOf(3), Integer.valueOf(0));
	}

	/**
	 * 0 01 0 17,21,25,29 JUN ? 这个表示6月的17,21,25,29号的00:01都会触发这个
	 */
	@Scheduled(cron = "0 01 0 1,3,5 JUN ?")
	public void setFristPrizePool() {
		System.out.println(new Date());
		log.debug("---setFristPrizePool---"+new Date());
		this.prizePool.put(Integer.valueOf(1), Integer.valueOf(getPrizePool(Integer.valueOf(1)).intValue() + 1));
	}

	/**
	 * 0 01 0 3,7,11,15 JUL ? 这个表示7月的3,7,11,15号的00:01都会触发这个
	 */
	@Scheduled(cron = "0 01 0 2,4 JUN ?")
	public void setFristPrizePoolJUL() {
		System.out.println(new Date());
		log.debug("---setFristPrizePoolJUL---"+new Date());
		this.prizePool.put(Integer.valueOf(1), Integer.valueOf(getPrizePool(Integer.valueOf(1)).intValue() + 1));
	}

	/**
	 * 0 01 0 0/1 * ? 表示 每月的每一天的00:01分钟都会触发
	 */
	@Scheduled(cron = "0 01 0 0/1 * ?")
	public void setSecondPrizePool() {
		log.debug("---setSecondPrizePool---"+new Date());
		this.prizePool.put(Integer.valueOf(2), Integer.valueOf(getPrizePool(Integer.valueOf(2)).intValue() + 2));
	}

	/**
	 * 0 01 0 0/1 * ? 表示 每月的每一天的00:01分钟都会触发
	 */
	@Scheduled(cron = "0 01 0 0/1 * ?")
	public void setThreePrizePool() {
		log.debug("---setThreePrizePool---"+new Date());
		this.prizePool.put(Integer.valueOf(3), Integer.valueOf(getPrizePool(Integer.valueOf(3)).intValue() + 4));
	}

	/**
	 * 这个表示6月的6-13号的23:30都会触发这个
	 */
	@Scheduled(cron = "0 30 23 6-13 JUN ?")
	public void setPrizePool() {
		log.debug("---setPrizePool---"+new Date());
		this.prizePool.put(Integer.valueOf(1), Integer.valueOf(0));
		this.prizePool.put(Integer.valueOf(2), Integer.valueOf(0));
		this.prizePool.put(Integer.valueOf(3), Integer.valueOf(0));
	}

	public Integer getPrizePool(Integer key) {
		log.debug("---getPrizePool---"+new Date());
		return (Integer) this.prizePool.get(key);
	}

	public void setPrizePool(Integer key, Integer prize) {
		log.debug("---setPrizePool---"+new Date());
		this.prizePool.put(key, prize);
	}
}
