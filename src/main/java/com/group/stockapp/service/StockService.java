package com.group.stockapp.service;

import com.group.stockapp.domain.Stock;
import com.group.stockapp.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public StockService(StockRepository stockRepository){
        this.stockRepository = stockRepository;
    }

    //@Transactional
    //synchronized 사용할 경우 해당 코드 블럭은 1개의 쓰레드만 접근
    //다만 메서드가 종료하고 커밋하기 이전 race condition이 발생할 가능성 존재
    public synchronized void decrease(Long id, Long quantity){

        Stock stock = stockRepository.findById(id).orElseThrow();
        stock.decrease(quantity);

        stockRepository.saveAndFlush(stock);

    }
}
