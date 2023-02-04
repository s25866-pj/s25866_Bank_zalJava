package com.example.s25866_bank.service;

import com.example.s25866_bank.exceptions.UserValidationException;
import com.example.s25866_bank.model.Response;
import com.example.s25866_bank.model.User;
import com.example.s25866_bank.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.s25866_bank.model.Response.status.*;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void create(User user) throws UserValidationException {
        if(isValidId(user.getId())){
            throw new UserValidationException("Wrong Id");
        }
        if(isValidName(user.getName())){
            throw new UserValidationException("Name is required");
        }
        if(isValidSurrname(user.getSurrname())){
            throw new UserValidationException("Surrame is required");
        }
        if(isValidMoney(user.getMoney())){
            throw new UserValidationException("Some money are required");
        }
        userRepository.create(user);
    }

    private boolean isValidMoney(long money) {
       return money ==0;
    }

    private boolean isValidSurrname(String surrname) {
        return surrname == null || surrname.isEmpty();
    }

    private boolean isValidName(String name) {
        return name == null ||name.isEmpty();
    }

    private boolean isValidId(int id) {
        return id == 0;
    }

    public void deteleAll() {
        userRepository.deleteAll();
    }

    public Response.status getWithdral(int id, long paycheck) {
        if(userRepository.getWithdral(id, paycheck)){
            return ACCEPTED;
        }
        return DECLINED;

    }

    public List<User> getAllUsers() {
       return userRepository.getAllUsers();

    }

    public Response.status addMoney(int id, long newMoney) {
        userRepository.addMoney(id, newMoney);
        return ACCEPTED;


    }
}
