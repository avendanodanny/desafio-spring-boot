package com.tecnova.demo.service;

import com.tecnova.demo.model.User;
import com.tecnova.demo.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    IUserService userService;

    /**
     * Method for authenticate user for id and password
     * @param id user
     * @param password user
     * @return token or exception if credentials are not valids
     */
    public String authenticate(Long id, String password) {
        // Aquí iría la lógica para validar las credenciales contra tu base de datos
        Optional<User> user = this.userService.getUserByIdAndPasswd(id, password);
        if (!user.isEmpty()) {  // Ejemplo
            return JwtTokenUtil.generateToken("" + id);
        } else {
            throw new RuntimeException("Credenciales inválidas");
        }
    }

}
