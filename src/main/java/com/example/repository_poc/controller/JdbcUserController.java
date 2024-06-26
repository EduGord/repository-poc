package com.example.repository_poc.controller;

import com.example.repository_poc.service.JdbcUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jdbc/users")
public class JdbcUserController extends BaseUserController<JdbcUserService> {

    @Autowired
    public JdbcUserController(JdbcUserService userService) {
        super(userService);
    }
}
