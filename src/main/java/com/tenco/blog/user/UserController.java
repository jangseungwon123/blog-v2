package com.tenco.blog.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    // 요청되어 오는 주소 - /join-form
    @GetMapping("/join-form")
    public String join_form() {
        return  "user/join-form";
    }

    @GetMapping("/login-form")
    public String loginForm(){
        // 뷰 반환값이 뷰(파일) 이름이 됨 (뷰 리졸버가 실제 파일 경로를 찾아 감)
        return "user/login-form";
    }

    // 주소 설계 : http://localhost:8080/user/update-form
    @GetMapping("/user/update-form")
    public String updateForm(){
        return "user/update-form";
    }

    @GetMapping("/logout")
    public String logout(){
        // "redirect: " 스프링에서 접두사를 사용하면 다른 URL로 리다이렉트 됨
        // 즉 리다이렉트 한다는것은 뷰를 렌더링하지 않고 브라우저가 제 요청을
        // 다시 하게끔 유도하는 것이다.
        return "redirect:/";
    }

}
