package net.jayanth.springcloudgcp.dao;

import net.jayanth.springcloudgcp.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
}
