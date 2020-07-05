package com.varbro.varbro.service.production;

import com.varbro.varbro.model.production.Batch;
import com.varbro.varbro.repository.production.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatchService {

    @Autowired
    BatchRepository batchRepository;

    public void saveBatch(Batch batch) {
        batchRepository.save(batch);
    }

    public void saveBatches(Iterable<Batch> batches) {
        batchRepository.saveAll(batches);
    }

    public List<Batch> getBatchesByMonthAndYearOrderedByDay(String month, int year) {
        return batchRepository.findBatchesByMonthAndYearOrderedByDay(month, year);
    }
}
