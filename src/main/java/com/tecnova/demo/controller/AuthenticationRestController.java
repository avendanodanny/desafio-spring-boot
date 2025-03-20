package com.tecnova.demo.controller;

import com.tecnova.demo.service.IAuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationRestController {

    private static Logger logger = LoggerFactory.getLogger(AuthenticationRestController.class);

    @Autowired
    private IAuthenticationService authenticationService;

    @Operation(summary = "Method to authenticate and return the JWT", description = "method to authenticate and return the JWT", tags={ "auth" }	)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authenticate correctly"),
            @ApiResponse(responseCode = "500", description = "No Authenticate")
    })
    @PostMapping("/login")
    public String login(@RequestParam Long id, @RequestParam String password) {
        logger.info("llega al método login...");
        return authenticationService.authenticate(id, password);
    }
}
