package co.jp.circleofuniversity.cou.shared.auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "jwt_tokens")
@Getter @Setter
@NoArgsConstructor
public class JwtToken implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "jwt_token_id")
    private Long id;

    @Column(name = "jwt_token_value")
    private String token;
}
