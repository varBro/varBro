package com.varbro.varbro.repository.production;

import com.varbro.varbro.model.production.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {

    @Query(value = "SELECT * FROM batch b WHERE extract(month from b.date) = ?1 AND extract(year from b.date) = ?2 ORDER BY extract(day from b.date)",
            nativeQuery = true)
    List<Batch> findBatchesByMonthAndYearOrderedByDay(int month, int year);
}
