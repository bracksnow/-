package com.group.stockapp.service;

import com.group.stockapp.domain.Stock;
import com.group.stockapp.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PessimisticLockStockService {

    @Autowired
    private StockRepository stockRepository;

    public PessimisticLockStockService(StockRepository stockRepository){
        this.stockRepository = stockRepository;
    }

    @Transactional
    //데이터 정합성이 보장되나 성능저하 이슈 존재
    public synchronized void decrease(Long id, Long quantity){

        Stock stock = stockRepository.findByIdWithPessimisticLock(id);
        stock.decrease(quantity);
        stockRepository.save(stock);

    }
}
