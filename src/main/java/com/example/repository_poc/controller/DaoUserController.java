package com.example.repository_poc.controller;


import com.example.repository_poc.service.DaoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dao/users")
public class DaoUserController extends BaseUserController<DaoUserService> {

    public DaoUserController(@Autowired DaoUserService daoUserService) {
        super(daoUserService);
    }
}
