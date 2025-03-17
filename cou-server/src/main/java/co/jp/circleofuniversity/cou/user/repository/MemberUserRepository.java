package co.jp.circleofuniversity.cou.user.repository;

import co.jp.circleofuniversity.cou.user.entity.MemberUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberUserRepository extends JpaRepository<MemberUser, Long> {
    Optional<MemberUser> findByEmail(String email);
}
