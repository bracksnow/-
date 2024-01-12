package com.group.stockapp.facade;

import com.group.stockapp.repository.RedisLockRepository;
import com.group.stockapp.service.StockService;
import org.springframework.stereotype.Component;

@Component
public class LettuceLockStockFacade {

    private final RedisLockRepository redisLockRepository;

    private final StockService stockService;

    public LettuceLockStockFacade(RedisLockRepository redisLockRepository, StockService stockService) {
        this.redisLockRepository = redisLockRepository;
        this.stockService = stockService;
    }

    public void decrease(Long id, Long quantity) throws InterruptedException{
        while(!redisLockRepository.unlock(id)){
            Thread.sleep(100);
        }
        try{
            stockService.decrease(id, quantity);
        } finally {
            redisLockRepository.unlock(id);
        }

    }
}
