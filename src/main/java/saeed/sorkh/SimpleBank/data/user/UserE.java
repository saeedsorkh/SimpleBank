package saeed.sorkh.SimpleBank.data.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import saeed.sorkh.SimpleBank.data.wallet.WalletE;

import java.util.Date;

@Entity
@Table(name = "users",
        indexes = {
                @Index(name = "inx_users_full", columnList = "wallet_id, creation_date")
        }
)
@Getter
@Setter
@NoArgsConstructor
public class UserE {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "user_primary_sequence",
            sequenceName = "user_primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_primary_sequence"
    )
    private Long id;

    @Column(name = "creation_date", nullable = false)
    @CreationTimestamp
    private Date creationDate;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "wallet_id", referencedColumnName = "id", unique = true)
    private WalletE wallet;

    // name, lastName, username, hashed password and...

}
