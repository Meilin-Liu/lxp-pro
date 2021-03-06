package com.lxp.pro.lxpproweb.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lxp.pro.lxpproweb.entity.User;
import com.lxp.pro.lxpproweb.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author plutosteven
 * @since 2019-12-06
 */
@RestController
@RequestMapping("/api/pro/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/demo")
    public String save(@RequestBody String loginInfo) {
        System.out.println(loginInfo);
        return "demo";
    }

    @GetMapping("/list")
    public List<User> list() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // == select * form where id = 1
        wrapper.eq("id", 1);

        wrapper.like("username", "lut");

        return userService.list();
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable Integer id, @RequestBody User user) {
        user.setId(id);
        userService.updateById(user);
    }

    @DeleteMapping("/remove/{id}")
    public void remove(@PathVariable Integer id) {
        userService.removeById(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody User user) {
        userService.save(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",user.getUsername()).eq("password", user.getPassword());
        int count = userService.count(wrapper);
//        select count(1) from user where username = #{username} and password = #{password}
        if (count == 1) {
            return "success";
        } else {
            return "error";
        }
    }

}

