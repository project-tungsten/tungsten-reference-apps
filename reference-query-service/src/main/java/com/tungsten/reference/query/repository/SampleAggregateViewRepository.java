package com.tungsten.reference.query.repository;

import com.tungsten.reference.query.view.SampleAggregateView;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SampleAggregateViewRepository extends PagingAndSortingRepository<SampleAggregateView, String> {
}
