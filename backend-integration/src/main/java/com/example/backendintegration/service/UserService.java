package com.example.backendintegration.service;

import com.example.backendintegration.entity.Address;
import com.example.backendintegration.entity.User;
import com.example.backendintegration.repository.AddressRepository;
import com.example.backendintegration.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;
    public User addUser(User u) {
        userRepository.save(u);
        u = userRepository.refresh(u);
        return u;
    }
    public User getUser(String username) {
        return userRepository.findUser(username);
    }
    public Address addAddress(Address address) {
        addressRepository.save(address);
        return addressRepository.refresh(address);
    }
    public List<Address> listAddresses(int uid) {
        return addressRepository.list(uid);
    }
    // 先查询其与用户的关系，再修改
    public Address updateAddress(Address address) {
        return Optional.ofNullable(addressRepository.find(address.getUser().getId(), address.getId()))
                .or(() -> {
                    log.debug("---------userService------");
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN, "无权限");
                })
                .map(a -> addressRepository.save(address))
                .get();
    }
}
