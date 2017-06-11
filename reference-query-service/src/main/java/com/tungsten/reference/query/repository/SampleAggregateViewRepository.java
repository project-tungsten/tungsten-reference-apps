package com.tungsten.reference.query.repository;

import com.tungsten.reference.query.view.SampleAggregateView;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource
public interface SampleAggregateViewRepository extends PagingAndSortingRepository<SampleAggregateView, String> {

  @RestResource(exported = false)
  @Override
  SampleAggregateView save(SampleAggregateView entity);

  @RestResource(exported = false)
  @Override
  void delete(String id);
}
