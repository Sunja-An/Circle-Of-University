package co.jp.circleofuniversity.cou.shared.auth.controller.dto;

public record ResponseTokenDto(
        String accessToken,
        String refreshToken
) {
    public static ResponseTokenDto of(String accessToken, String refreshToken){
        return new ResponseTokenDto(accessToken, refreshToken);
    }

}
