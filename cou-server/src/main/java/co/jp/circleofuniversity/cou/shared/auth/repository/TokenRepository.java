package co.jp.circleofuniversity.cou.shared.auth.repository;

import co.jp.circleofuniversity.cou.shared.auth.entity.JwtToken;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<JwtToken, Long> {
}
