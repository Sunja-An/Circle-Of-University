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

@Getter @Setter
@NoArgsConstructor
@MappedSuperclass
public class User {
    @Column(name = "user_email", unique = true, nullable = false)
    private String email;

    @Column(name = "user_username", nullable = false)
    private String username = "";

    @Column(name = "user_profile")
    private String profile;

    @Column(name = "user_gender")
    private Gender gender;

    @Column(name = "user_birth")
    private Date birth;

    @Column(name = "user_first_kanjimei", nullable = false)
    @Max(value = 15)
    @Min(value = 0)
    private String firstKanjiname = "";

    @Column(name = "user_last_kanjimei", nullable = false)
    @Max(value = 15)
    @Min(value = 0)
    private String lastKanjiname = "";

    @Column(name = "user_first_katamei", nullable = false)
    @Max(value = 20)
    @Min(value = 0)
    private String firstKataname = "";

    @Column(name = "user_last_katamei", nullable = false)
    @Max(value = 20)
    @Min(value = 0)
    private String lastKataname = "";

    @Column(name = "user_instagram_link")
    private String instagramLink;

    @Column(name = "user_X_link")
    private String xLink;

    @Column(name = "user_line_link")
    private String lineLink;

    public User(
            String email
    ){
        this.email = email;
    }

    public User(
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

    public static User of(CreateUserDto createUserDto){
        return new User(
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
