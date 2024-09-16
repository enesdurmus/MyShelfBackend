package com.enesd.myshelfbackend.services;

import com.enesd.myshelfbackend.dto.UserDTO;
import com.enesd.myshelfbackend.dto.UserPageDTO;
import com.enesd.myshelfbackend.model.entities.User;
import com.enesd.myshelfbackend.model.request.UpdateDisplayNameRequest;
import com.enesd.myshelfbackend.repository.jpa.UserRepository;
import com.enesd.myshelfbackend.utils.CustomModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final CustomModelMapper modelMapper;

    @Transactional
    public void updateDisplayName(User user, UpdateDisplayNameRequest updateDisplayNameRequest) {
        userRepository.updateDisplayName(user.getId(), updateDisplayNameRequest.getDisplayName());
    }

    public UserPageDTO getUsersByPagination(int pageNo, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by("createdAt").descending());
        Page<User> pagingUser = userRepository.findAll(pageRequest);
        return new UserPageDTO(
                modelMapper.mapAll(pagingUser.getContent(), UserDTO.class),
                pagingUser.getTotalPages(),
                pagingUser.getTotalElements());
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmailWithRoles(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
