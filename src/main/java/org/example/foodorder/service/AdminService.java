
package org.example.foodorder.service;

import lombok.AllArgsConstructor;
import org.example.foodorder.model.User;
import org.example.foodorder.repository.AuthRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class AdminService {

    private final AuthRepository authRepository;

    public List<User> getUsers() {
        return authRepository.findAll();
    }
}
