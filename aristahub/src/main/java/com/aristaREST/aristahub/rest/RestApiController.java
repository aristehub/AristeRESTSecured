package com.aristaREST.aristahub.rest;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/public")
public class RestApiController 
{
    public RestApiController() {}

    @GetMapping("test1")
    public String test1(){
        return "API Test 1";
    }

    @GetMapping("test2")
    public String test2(){
        return "API Test 2";
    }
    //@GetMapping("users")
    //public List<User> users()
    {
    //	return this.userdao.findAll();
    }
}