package co.jp.circleofuniversity.cou.user.entity;

import co.jp.circleofuniversity.cou.shared.auth.entity.ConfirmationToken;
import co.jp.circleofuniversity.cou.shared.domain.Role;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class MemberUser extends User {
    private final Role role = Role.NORMAL;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", unique = true, nullable = false)
    private Long id;

    @OneToOne(targetEntity = ConfirmationToken.class, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "member_token", referencedColumnName = "token_id")
    private ConfirmationToken token;

    public MemberUser(String email, ConfirmationToken token) {
        super(email);
        this.token = token;
    }
}
