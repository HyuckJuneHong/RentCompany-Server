package project.rentcompany.controller.users;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.rentcompany.domain.dto.users.*;
import project.rentcompany.domain.service.driveLicense.DriveLicenseGetService;
import project.rentcompany.domain.service.users.*;
import project.rentcompany.utils.ResponseFormat;

@RestController
@RequestMapping("/rentCompany")
@RequiredArgsConstructor
public class UsersController {

    private final SignInService signInService;

    private final SignUpService signUpService;
    private final UsersGetService usersGetService;

    private final PasswordUpdateService passwordUpdateService;
    private final UsersUpdateService usersUpdateService;
    private final UsersDeleteService usersDeleteService;

    //로그인
    @PostMapping("/signIn")
    public ResponseFormat signIn(@RequestBody SignInDto signInDto){
        return signInService.signIn(signInDto);
    }

    //로그인이 되어있는지 체크
    @PostMapping("/loginCheck")
    public ResponseFormat loginCheck(@RequestBody LoginCheckDto loginCheckDto){
        return signInService.loginCheck(loginCheckDto);
    }

    //회원가입
    @PostMapping("/signUp")
    public ResponseFormat signUp(@RequestBody SignUpDto signUpDto){
        return signUpService.signUp(signUpDto);
    }

    //아이디 중복 여부
    @GetMapping("/signUp/check")
    public ResponseEntity<ResponseFormat> checkIdentity(@RequestParam("identity") String identity){
        return ResponseEntity.ok().body(signUpService.checkIdentity(identity));
    }

    //정보 반환
    @GetMapping("/user")
    public ResponseEntity<ResponseFormat<UsersGetDto>> userGet(@RequestParam("identity") String identity){
        return ResponseEntity.ok().body(usersGetService.userGet(identity));
        //ResponseEntity.ok().body()
        //public ResponseEntity<UsersGetDto> userGet(@RequestParam("identity") String identity){}
    }

    //정보 변경
    @PutMapping("/user/update")
    public ResponseFormat userUpdate(@RequestBody UsersUpdateDto usersUpdateDto){
        return usersUpdateService.userUpdate(usersUpdateDto);
    }

    //비밀번호 변경
    @PutMapping("/user/update/password")
    public ResponseFormat passwordUpdate(@RequestBody passwordUpdateDto usersPasswordDto){
        return passwordUpdateService.passwordUpdate(usersPasswordDto);
    }

    //회원 삭제
    @DeleteMapping("/user/update/delete")
    public ResponseFormat userDelete(@RequestParam("identity") String identity){
        return usersDeleteService.UserDelete(identity);
    }
}
