package com.enesd.myshelfbackend.services;

import com.enesd.myshelfbackend.enums.ContentType;
import com.enesd.myshelfbackend.model.entities.User;
import com.enesd.myshelfbackend.model.entities.UserContentActivity;
import com.enesd.myshelfbackend.model.request.CreateContentActivityRequest;
import com.enesd.myshelfbackend.repository.jpa.UserContentActivityRepository;
import com.enesd.myshelfbackend.utils.CustomModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserContentActivityService {

    private final UserContentActivityRepository userContentActivityRepository;

    private final CustomModelMapper modelMapper;

    public void createUserContentActivity(User user, CreateContentActivityRequest createContentActivityRequest) {

    }

    public List<UserContentActivity> getUserContentActivitiesByPagination(User user, ContentType contentType, int pageNo, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by("createdAt").descending());
        return userContentActivityRepository.findAllByUserAndContentType(user, contentType, pageRequest);
    }

    public void deleteUserContentActivity(Long id) {

    }
}
