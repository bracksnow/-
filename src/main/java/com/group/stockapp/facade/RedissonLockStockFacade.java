package com.group.stockapp.facade;

import com.group.stockapp.service.StockService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedissonLockStockFacade {

    private RedissonClient redissonClient;

    private StockService stockService;

    public RedissonLockStockFacade(RedissonClient redissonClient, StockService stockService) {
        this.redissonClient = redissonClient;
        this.stockService = stockService;
    }

    public void decrease(Long id, Long quantity){
        RLock Lock = redissonClient.getLock(id.toString());

        try{
            boolean available  = Lock.tryLock(10, 1, TimeUnit.SECONDS);

            if(!available){
                System.out.println("LOCK 획득 실패");
                return;
            }

            stockService.decrease(id, quantity);

        }catch (InterruptedException e){
            throw new RuntimeException(e);

        }
    }
}
