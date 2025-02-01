package saeed.sorkh.SimpleBank.data.money_transition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MoneyTransitionRepository extends JpaRepository<MoneyTransitionE, UUID> {

    Optional<MoneyTransitionE> findByReferenceId(String referenceId);
}
