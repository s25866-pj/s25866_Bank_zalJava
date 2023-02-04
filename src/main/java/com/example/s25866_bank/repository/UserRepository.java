package com.example.s25866_bank.repository;

import com.example.s25866_bank.exceptions.NotEnoughtMoneyException;
import com.example.s25866_bank.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    private List<User> userList = new ArrayList<>();
    public void create(User user){
        userList.add(user);
    }
    public void deleteAll(){
            userList = new ArrayList<>();
    }


    public Optional<User> findById(int id) {
        Optional <User> user;
        return user= userList.stream().filter(it -> it.getId() == id).findFirst();
    }

    public boolean getWithdral(int id, long paycheck) {
        userList.stream().filter(it -> it.getId() == id).forEach(it -> {
            long temp = it.getMoney()-paycheck;
            if(temp<0){
                throw new NotEnoughtMoneyException("You don't have money, lacking " , temp);
            }else{
                it.setMoney(temp);
            }
        });
    return true;
    }

    public List<User> getAllUsers() {
        return userList;
    }

    public boolean addMoney(int id, long newMoney) {
        userList.stream().filter(it -> it.getId() == id).forEach(it -> {
                it.setMoney(it.getMoney()+newMoney);
        });
        return true;
    }
}
