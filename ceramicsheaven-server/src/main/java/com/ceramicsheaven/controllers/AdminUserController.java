package com.ceramicsheaven.controllers;

import com.ceramicsheaven.config.JwtProvider;
import com.ceramicsheaven.exceptions.UserException;
import com.ceramicsheaven.model.Order;
import com.ceramicsheaven.model.User;
import com.ceramicsheaven.repositories.UserRepository;
import com.ceramicsheaven.responses.AdminOrdersAndUsers;
import com.ceramicsheaven.responses.ApiResponse;
import com.ceramicsheaven.services.OrderService;
import com.ceramicsheaven.services.UserService;
import com.ceramicsheaven.services.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin")
public class AdminUserController {

    private UserRepository userRepository;
    private OrderService orderService;

    private UserServiceImplementation userServiceImplementation;

    @Autowired
    public AdminUserController(UserRepository userRepository, OrderService orderService, UserServiceImplementation userServiceImplementation) {
        this.userRepository = userRepository;
        this.orderService = orderService;
        this.userServiceImplementation = userServiceImplementation;
    }

    @PostMapping("/allUsers")
    public  ResponseEntity<List<User>> getUsers(){
       List<User> users = userRepository.findAll();
       return new ResponseEntity<>(users, HttpStatus.ACCEPTED);
   }

   @DeleteMapping("/deleteUser/{userId}")
    public  ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId) throws UserException {

        String res = userServiceImplementation.deleteUser(userId);
        ApiResponse response = new ApiResponse();
        response.setMessage(res);
        response.setStatus(true);
        return new ResponseEntity<ApiResponse>(response,HttpStatus.ACCEPTED);
   }

    @GetMapping("/totalData")
    public ResponseEntity<AdminOrdersAndUsers> getTotalSales(){
        AdminOrdersAndUsers data = orderService.getTotalData();
        return  new ResponseEntity<AdminOrdersAndUsers>(data,HttpStatus.OK);
    }
}
