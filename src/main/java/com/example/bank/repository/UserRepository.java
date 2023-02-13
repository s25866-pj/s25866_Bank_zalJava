package com.example.bank.repository;

import com.example.bank.exceptions.NotEnoughtMoneyException;
import com.example.bank.exceptions.UserValidationException;
import com.example.bank.model.User;
import net.minidev.json.JSONArray;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@Repository
public class UserRepository {
    private List<User> userList = new ArrayList<>();

    public void create(User user){
        if(!checkPESEL(user)){
            throw new UserValidationException("Wrong Pesel");
        }
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

    public void saveDataToDB() {
        try {
            FileWriter myFile = new FileWriter("src/main/java/com/example/s25866_bank/repository/DB.json");
            JSONArray jsontemp = new JSONArray();
            jsontemp.add(userList);
            myFile.write(String.valueOf(jsontemp));
            jsontemp=new JSONArray();
            myFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> readFromDataBase() {
        File myDatabase = new File("src/main/java/com/example/s25866_bank/repository/DB.json");

        List<User> temp =new ArrayList<>();
        try {
            Scanner myReader = new Scanner(myDatabase);
            while(myReader.hasNextLine()){
//                temp.add();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        userList=temp;
        temp =new ArrayList<>();
        return userList;
    }
    public boolean checkPESEL(User user){
        ArrayList <Integer> tempPesel = longToArray(user.getPesel());
        if(tempPesel.size()!=11) {
            throw new UserValidationException("Wrong PESEL, Length");
        }
        if(!peselValidationCheck(tempPesel)){
            throw new UserValidationException("Wrong PESEL, control number not match");
        }

        return true;
    }

    private boolean peselValidationCheck(ArrayList<Integer> tempPesel) {
        int peselSum=0;
        for (int i = 0; i < tempPesel.size(); i++) {
            if(i==0 ||i==4||i==8){
                peselSum+=tempPesel.get(i);
            }
            if(i==1 ||i==5||i==9){
                peselSum+=tempPesel.get(i)*3;
            }
            if(i==2 ||i==6){
                peselSum+=tempPesel.get(i)*7;
            }
            if(i==3 ||i==7){
                peselSum+=tempPesel.get(i)*9;
            }
        }
        peselSum = peselSum%10;
        peselSum =  10-peselSum;
        peselSum = peselSum%10;

        if(peselSum == tempPesel.get(10)){
            return true;
        }
        else{
            return false;
        }
    }

    public ArrayList<Integer> longToArray(long number){
        ArrayList <Integer> tempArray = new ArrayList<Integer>();
        while (number>0){
            tempArray.add((int)(number%10));
            number = number/10;
        }

        return tempArray;
    }
}
