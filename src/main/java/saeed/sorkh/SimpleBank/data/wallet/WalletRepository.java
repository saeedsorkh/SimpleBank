package saeed.sorkh.SimpleBank.data.wallet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WalletRepository extends JpaRepository<WalletE, UUID> {

    Optional<WalletE> findByUserId(Long id);

}
