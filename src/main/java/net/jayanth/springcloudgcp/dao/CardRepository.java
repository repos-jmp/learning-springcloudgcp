package net.jayanth.springcloudgcp.dao;

import net.jayanth.springcloudgcp.domain.Card;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends PagingAndSortingRepository<Card, Long> {
}
