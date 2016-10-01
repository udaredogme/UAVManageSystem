package com.zlion.service;

import com.zlion.model.Admin;
import com.zlion.model.User;
import com.zlion.repository.AdminRepository;
import com.zlion.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by zzs on 2016/9/3.
 */
@Service
public class AdminService {

    private AdminRepository adminRepository;
    private UserRepository userRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository, UserRepository userRepository){
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
    }

    //检验用户是否存在数据库中
    @Transactional
    public boolean checkUserExist(String username){
        User user = userRepository.findByUsername(username);
        if (user == null)
            return false;
        else
            return true;
    }

//  检验用户Id 是否存在数据库中
    @Transactional
    public boolean checkUserExistById(Long id){
        User user = userRepository.findByUserId(id);
        if (user == null)
            return false;
        else
            return true;
    }

    @Transactional
    public void saveUser(User user) throws Exception{
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long id){
        userRepository.delete(id);
    }

    @Transactional
    public void updateUserPwd(Long id, String password){
        userRepository.updateUserPwd(id, password);
    }


    @Transactional
    public List<User> getUserList(){
        List<User> userList = userRepository.findAll();
        return userList;
    }

    @Transactional
    public boolean loginValidate(HttpSession session, String username, String password){
        Admin admin = adminRepository.findByUsernameAndPwd(username, password);
        if (admin == null)
            return false;
        else{
            session.setAttribute("admin", admin);
            session.setAttribute("adminId", admin.getId());
            return true;
        }
    }

//    public void authRegConfirm(String userId){
//        userRepository.
//    }

    public User getUserByUserId(Long userId){
        return userRepository.findOne(userId);
    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

}