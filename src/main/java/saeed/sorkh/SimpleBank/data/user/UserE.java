package saeed.sorkh.SimpleBank.data.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "users")
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

    // name, lastName, username, hashed password and...

}
