package saeed.sorkh.SimpleBank.data.money_transition;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;
import saeed.sorkh.SimpleBank.data.wallet.WalletE;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "money_transition")
@Getter
@Setter
@NoArgsConstructor
public class MoneyTransitionE {

    @Id
    @Column(nullable = false, updatable = false)
    @UuidGenerator
    private UUID id;

    @Column(name = "creation_date", nullable = false)
    @CreationTimestamp
    private Date creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id", nullable = false)
    private WalletE walletE;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    private Long balance;

    @Column(nullable = false, unique = true)
    private String referenceId;

    //TODO
//    @PrePersist
//    private void initReferenceId() {
//    }

}
