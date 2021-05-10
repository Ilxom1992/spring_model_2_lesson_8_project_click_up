package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.enums.SystemRoleName;
import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.LoginDto;
import com.example.demo.payload.RegisterDto;
import com.example.demo.repository.PositionRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtFilter;
import com.example.demo.security.JwtProvider;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.xml.ws.Response;
import java.util.Optional;
import java.util.Random;

@Service
public class AuthService
        implements UserDetailsService
{
    final UserRepository userRepository;
    final PositionRepository positionRepository;
    final PasswordEncoder passwordEncoder;
    final JavaMailSender javaMailSender;
    final AuthenticationManager authenticationManager;
    final JwtProvider jwtProvider;

    public AuthService(UserRepository userRepository, PositionRepository
            positionRepository, PasswordEncoder passwordEncoder, JavaMailSender
            javaMailSender, AuthenticationManager authenticationManager, JwtProvider
            jwtProvider) {
        this.userRepository = userRepository;
        this.positionRepository = positionRepository;
        this.passwordEncoder = passwordEncoder;
        this.javaMailSender = javaMailSender;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;

    }


    public ApiResponse register(RegisterDto registerDto){

        if (userRepository.existsByEmail(registerDto.getEmail())){
            return new ApiResponse("Bunday email mavjud",false);
        }
        if (!registerDto.getPassword().equals(registerDto.getPrePassword())){
            return new ApiResponse("parol mos emas",false);
        }
       if (userRepository.existsByUsername(registerDto.getUsername())){
           return new ApiResponse("Bunday user bazada mavjud",false);
       }
User user=new User();
       user.setEmail(registerDto.getEmail());
       user.setUsername(registerDto.getUsername());
       user.setFullName(registerDto.getFullName());
       user.setPassword(passwordEncoder.encode(registerDto.getEmail()));
       user.setSystemRoleName(SystemRoleName.SYSTEM_USER);
        int code = new Random().nextInt(99999);
        user.setEmail(String.valueOf(code).substring(0,4));
        userRepository.save(user);
        sendEmail(user.getEmail(), user.getEmailCode());
        return new ApiResponse("user saved",true);
}

    public UserDetails loadUserByUsername(String username) {
  return userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));
    }
    public Boolean sendEmail(String sendingEmail,String emailCod) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("Test@pdp.com");
            mailMessage.setTo(sendingEmail);
            mailMessage.setSubject("Akkountni Tasdiqlash");
            mailMessage.setText(emailCod);
            javaMailSender.send(mailMessage);
            return true;
        }
        catch (Exception e){
            return false;
        }

    }
    public ApiResponse verifyEmail(String email, String emailCode, String newPassword) {
    Optional<User> optionalUser = userRepository.findByEmailAndEmailCode(email, emailCode);
    if (optionalUser.isPresent()){
        User user= optionalUser.get();
        user.setEnabled(true);
        user.setEmailCode(null);
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return new ApiResponse("Account tasdiqlandi",true);
    }
    return new ApiResponse("Akkount alloqachon tasdiqlangan",false);
}
    public ApiResponse login(LoginDto loginDto) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getUsername(),
                    loginDto.getPassword()));
            User user=(User)authenticate.getPrincipal();
            String token = jwtProvider.generateToken(loginDto.getUsername(), user.getPosition());
            return new ApiResponse("Token",true,token);

        }catch (BadCredentialsException badCredentialsException){
            return new ApiResponse( "Parol yoki lagin hato",false);
        }
    }
}
