package com.alumpana.resource.management.spring.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * @author Sivakumar ARUMUGAM
 * 
 */

@RestController
@RequestMapping("/rest/user")
@SessionAttributes("roles")
public class UserRestController {

}