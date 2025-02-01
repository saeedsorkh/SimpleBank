package saeed.sorkh.SimpleBank.data.wallet;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;
import saeed.sorkh.SimpleBank.data.user.UserE;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "wallets")
@Getter
@Setter
@NoArgsConstructor
public class WalletE {

    @Id
    @Column(nullable = false, updatable = false)
    @UuidGenerator
    private UUID id;

    @Column(name = "creation_date", nullable = false)
    @CreationTimestamp
    private Date creationDate;

    @Column(nullable = false, columnDefinition = "BIGINT DEFAULT 0")
    private Long balance = 0L;

    @OneToOne(mappedBy = "wallet", fetch = FetchType.LAZY)
    private UserE user;

}
