package com.dogtiger.challus.service;

import com.dogtiger.challus.dto.SigninReqDto;
import com.dogtiger.challus.dto.SignupReqDto;
import com.dogtiger.challus.entity.User;
import com.dogtiger.challus.jwt.JwtProvider;
import com.dogtiger.challus.repository.UserMapper;
import com.dogtiger.challus.security.PrincipalProvider;
import com.dogtiger.challus.security.PrincipalUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final PrincipalProvider principalProvider;
    private final JwtProvider jwtProvider;

    public boolean signup(SignupReqDto signupReqDto) {
        User user = signupReqDto.toUserEntity(passwordEncoder);
        return userMapper.saveUser(user) > 0;
    }

    public String signin(SigninReqDto signinReqDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(signinReqDto.getEmail(), signinReqDto.getPassword());

        Authentication authentication = principalProvider.authenticate(authenticationToken);

        return jwtProvider.generateToken(authentication);
    }

    public List<Map<String, Object>> getMembersCount() {
        List<Map<String, Object>> result = userMapper.getMembersCount();
        for (Map<String, Object> entry : result) {
        }
        return result;
    }
}