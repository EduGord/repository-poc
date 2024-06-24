package com.example.repository_poc.controller;

import com.example.repository_poc.service.JpaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jpa/users")
public class JpaUserController extends BaseUserController<JpaUserService> {

    public JpaUserController(@Autowired JpaUserService userService) {
        super(userService);
    }
}
