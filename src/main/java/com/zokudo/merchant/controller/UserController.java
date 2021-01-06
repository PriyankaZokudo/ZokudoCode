package com.zokudo.merchant.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class UserController {

@RequestMapping(value= {"/login","/"}, method=RequestMethod.GET)
public ModelAndView login() {
 ModelAndView model = new ModelAndView();

 model.setViewName("/login");
 return model;
}

 
 @RequestMapping(value = {"/forgot-password"},method = RequestMethod.GET)
 public ModelAndView forgotPassword() {
    ModelAndView model = new ModelAndView();
    model.setViewName("/user/forgot_password");
    return model;
 }

 @RequestMapping(value= {"/access_denied"}, method=RequestMethod.GET)
 public ModelAndView accessDenied() {
  ModelAndView model = new ModelAndView();
  model.setViewName("/errors/access_denied");
  return model;
 }
}