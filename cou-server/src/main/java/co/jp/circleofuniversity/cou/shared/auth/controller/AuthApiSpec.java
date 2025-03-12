package co.jp.circleofuniversity.cou.shared.auth.controller;

import co.jp.circleofuniversity.cou.shared.auth.controller.dto.CreateUserDto;
import co.jp.circleofuniversity.cou.shared.auth.controller.dto.LoginUserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Auth API", description = "User's Authentication API")
public interface AuthApiSpec {
    @Operation(summary = "General Login", description = "Need Email")
    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "401", description = "Wrong Auth")
    @ApiResponse(responseCode = "404", description = "There isn't in Database")
    public ResponseEntity<?> login(
            @Parameter(description = "User Email")
            @RequestBody LoginUserDto loginUserDto
    ) throws MessagingException;

    @Operation(summary = "Email Checker", description = "Need Confirmation Token")
    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "401", description = "Wrong Auth")
    @ApiResponse(responseCode = "404", description = "There isn't in Database")
    public ResponseEntity<?> checkEmail(@RequestParam("token") String token) throws MessagingException;

    @Operation(summary = "General SignUp", description = "User Sign Up")
    @ApiResponse(responseCode = "201", description = "Success")
    @ApiResponse(responseCode = "401", description = "Has same user")
    public ResponseEntity<?> signUp(
            @Parameter(description = "Sign Up")
            @RequestBody CreateUserDto createUserDto
    );
}
