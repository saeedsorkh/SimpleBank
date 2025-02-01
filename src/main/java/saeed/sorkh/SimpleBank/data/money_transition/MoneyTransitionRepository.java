package saeed.sorkh.SimpleBank.data.money_transition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MoneyTransitionRepository extends JpaRepository<MoneyTransitionE, UUID> {

    Optional<MoneyTransitionE> findByReferenceId(String referenceId);

    @Query(nativeQuery = true, value = """
            SELECT SUB_QUERY.DEPOSIT + ABS(SUB_QUERY.WITHDRAW) AS TOTAL, SUB_QUERY.DEPOSIT AS DEPOSIT, ABS(SUB_QUERY.WITHDRAW) AS WITHDRAW
                FROM (
                    SELECT COALESCE((SELECT SUM(mt.amount) FROM MONEY_TRANSITION mt WHERE mt.amount > 0), 0) AS DEPOSIT,
                           COALESCE((SELECT sum(mt.amount) FROM MONEY_TRANSITION mt WHERE mt.amount < 0), 0) AS WITHDRAW
                    ) AS SUB_QUERY
            """)
    Object calculateAllTransitionAmounts();

}
