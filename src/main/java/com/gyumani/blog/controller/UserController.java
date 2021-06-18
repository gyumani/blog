package com.gyumani.blog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyumani.blog.config.auth.PrincipalDetail;
import com.gyumani.blog.model.KakaoProfile;
import com.gyumani.blog.model.OAuthToken;
import com.gyumani.blog.model.User;
import com.gyumani.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class UserController {

    @Value("${gyumani.key}")
    private String gyumaniKey;

    @Autowired
    private UserService userService;


    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/auth/joinForm")
    public String joinForm(){
        return "user/joinForm";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm(){
        return "user/loginForm";
    }


    @GetMapping("/auth/kakao/callback")
    public String kakaoCallback(String code){ //데이터를 리턴해주는 컨트롤러 함수가 된다.
        //post 방식으로 key=value 데이터를 요청(카카오톡으로)
        //Retrofit2
        //OkHttp
        //RestTemplate
        
        RestTemplate rt=new RestTemplate();
        
        //HttpHeader 오브젝트 생성
        HttpHeaders headers=new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
    
        
        //HttpBody 오브젝트 생성
        MultiValueMap<String, String> params=new LinkedMultiValueMap<>();
        params.add("grant_type","authorization_code");
        params.add("client_id","a1741b646d4e9c18c88b2eeea8f0ccee");
        params.add("redirect_uri","http://localhost:8000/auth/kakao/callback");
        params.add("code",code);

        //HttpHeader와 HttpBody를 하나의 오브젝트로 담기
        HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest=
            new HttpEntity<>(params, headers);

        //Http 요청하기 -Post방식으로 -그리고 response 변수의 응답을 받음
        ResponseEntity<String> response=rt.exchange(
          "https://kauth.kakao.com/oauth/token",
            HttpMethod.POST,
            kakaoTokenRequest,
            String.class
        );

        //Gson, Json Simple, ObjectMapper
        ObjectMapper objectMapper=new ObjectMapper();
        OAuthToken oauthToken=null;
        try{
            oauthToken=objectMapper.readValue(response.getBody(), OAuthToken.class);
        }catch(JsonMappingException e){
            e.printStackTrace();
        }catch(JsonProcessingException e){
            e.printStackTrace();
        }
        System.out.println("카카오 엑세스 토큰: "+oauthToken.getAccess_token());

        RestTemplate rt2=new RestTemplate();

        //HttpHeader 오브젝트 생성
        HttpHeaders headers2=new HttpHeaders();
        headers2.add("Authorization","Bearer "+oauthToken.getAccess_token());
        headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");


        //HttpHeader와 HttpBody를 하나의 오브젝트로 담기
        HttpEntity<MultiValueMap<String,String>> kakaoProfileRequest2=
                new HttpEntity<>(headers2);

        //Http 요청하기 -Post방식으로 -그리고 response 변수의 응답을 받음
        ResponseEntity<String> response2=rt2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest2,
                String.class
        );

        System.out.println(response2.getBody());

        //Gson, Json Simple, ObjectMapper
        ObjectMapper objectMapper2=new ObjectMapper();
        KakaoProfile kakaoProfile=null;
        try{
            kakaoProfile=objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
        }catch(JsonMappingException e){
            e.printStackTrace();
        }catch(JsonProcessingException e){
            e.printStackTrace();
        }

        User kakaoUser=User.builder()
                .username(kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId())
                .password(gyumaniKey.toString())
                .email(kakaoProfile.getKakao_account().getEmail())
                .oauth("kakao")
                .build();

        //가입자 혹은 비가입자 체크 처리
        User originUser=userService.findMember(kakaoUser.getUsername());

        if(originUser.getUsername() ==null){
            userService.joinMember(kakaoUser);
        }

        //로그인 처리
        Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(),gyumaniKey));
        SecurityContextHolder.getContext().setAuthentication(authentication);


        return "redirect:/";
    }

    @GetMapping("/user/updateForm")
    public String updateForm(@AuthenticationPrincipal PrincipalDetail principal){
        return "user/updateForm";
    }
}
