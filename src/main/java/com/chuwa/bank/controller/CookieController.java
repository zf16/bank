//package com.chuwa.bank.controller;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("api/v1/cookie")
//public class CookieController {
//
//    @GetMapping("/generate")
//    public ResponseEntity<String> generateCookie(HttpServletResponse response) {
//        // Create a new cookie
//        Cookie cookie = new Cookie("my-cookie", "cookie-value");
//        // Set the cookie's path (optional)
//        cookie.setPath("/");
//        // Add the cookie to the response
//        response.addCookie(cookie);
//        return new ResponseEntity<>("Cookie generated successfully", HttpStatus.OK);
//    }
//
//    @GetMapping("/extract")
//    public ResponseEntity<String> extractCookie(HttpServletRequest request) {
//        // Get all cookies from the request
//        Optional<Cookie[]> optionalCookies = Optional.ofNullable(request.getCookies());
//        if (optionalCookies.isPresent()) {
//            for (Cookie cookie : optionalCookies.get()) {
//                if (cookie.getName().equals("my-cookie")) {
//                    return new ResponseEntity<>("Value of my-cookie" + cookie.getValue(), HttpStatus.OK);
//                }
//            }
//        }
//
//        return new ResponseEntity<>("Cookie not found", HttpStatus.NOT_FOUND);
//    }
//
//}
