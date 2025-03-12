package co.jp.circleofuniversity.cou.user.entity;

import co.jp.circleofuniversity.cou.shared.auth.controller.dto.CreateUserDto;
import co.jp.circleofuniversity.cou.shared.auth.entity.ConfirmationToken;
import co.jp.circleofuniversity.cou.shared.domain.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
    private Long id;

    @Column()
    private String email;

    @Column(name = "user_username", unique = true, nullable = false)
    private String username;

    @Column(name = "user_profile")
    private String profile;

    @Column(name = "user_gender", nullable = false)
    private Gender gender;

    @Column(name = "user_birth", nullable = false)
    private Date birth;

    @Column(name = "user_first_kanjimei", nullable = false)
    @Max(value = 15)
    @Min(value = 0)
    private String firstKanjiname;

    @Column(name = "user_last_kanjimei", nullable = false)
    @Max(value = 15)
    @Min(value = 0)
    private String lastKanjiname;

    @Column(name = "user_first_katamei", nullable = false)
    @Max(value = 20)
    @Min(value = 0)
    private String firstKataname;

    @Column(name = "user_last_katamei", nullable = false)
    @Max(value = 20)
    @Min(value = 0)
    private String lastKataname;

    @Column(name = "user_instagram_link")
    private String instagramLink;

    @Column(name = "user_X_link")
    private String xLink;

    @Column(name = "user_line_link")
    private String lineLink;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "token_id")
    private ConfirmationToken token;

    public User(
            ConfirmationToken token,
            String email,
            String username,
            Date birth,
            Gender gender,
            String profile,
            String firstKanjiname,
            String lastKanjiname,
            String firstKataname,
            String lastKataname,
            String instagramLink,
            String xLink,
            String lineLink
    ) {
        this.token = token;
        this.email = email;
        this.username = username;
        this.birth = birth;
        this.gender = gender;
        this.profile = profile;
        this.firstKanjiname = firstKanjiname;
        this.lastKanjiname = lastKanjiname;
        this.firstKataname = firstKataname;
        this.lastKataname = lastKataname;
        this.instagramLink = instagramLink;
        this.xLink = xLink;
        this.lineLink = lineLink;
    }

    public static User of(CreateUserDto createUserDto, ConfirmationToken token){
        return new User(
                token,
                createUserDto.email(),
                createUserDto.username(),
                createUserDto.birth(),
                createUserDto.gender(),
                createUserDto.profile(),
                createUserDto.firstKanjiname(),
                createUserDto.lastKanjiname(),
                createUserDto.firstKataname(),
                createUserDto.lastKataname(),
                createUserDto.instagramLink(),
                createUserDto.xLink(),
                createUserDto.lineLink()
        );
    }
}
