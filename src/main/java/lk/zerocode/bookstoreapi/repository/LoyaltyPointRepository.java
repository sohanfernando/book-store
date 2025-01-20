package lk.zerocode.bookstoreapi.repository;

import lk.zerocode.bookstoreapi.model.LoyaltyPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoyaltyPointRepository extends JpaRepository<LoyaltyPoint, Long> {
}
