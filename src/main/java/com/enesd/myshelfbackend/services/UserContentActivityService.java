package com.enesd.myshelfbackend.services;

import com.enesd.myshelfbackend.dto.UserContentActivityDTO;
import com.enesd.myshelfbackend.enums.ContentType;
import com.enesd.myshelfbackend.model.entities.User;
import com.enesd.myshelfbackend.model.entities.UserContentActivity;
import com.enesd.myshelfbackend.model.request.CreateContentActivityRequest;
import com.enesd.myshelfbackend.repository.jpa.MediaContentEntityRepository;
import com.enesd.myshelfbackend.repository.jpa.UserContentActivityRepository;
import com.enesd.myshelfbackend.utils.CustomModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserContentActivityService {

    private final UserContentActivityRepository userContentActivityRepository;
    private final MediaContentEntityRepository mediaContentEntityRepository;

    private final CustomModelMapper modelMapper;

    public UserContentActivityDTO createUserContentActivity(User user, CreateContentActivityRequest createContentActivityRequest) {
        UserContentActivity userContentActivity = new UserContentActivity();
        userContentActivity.setUser(user);
        userContentActivity.setActivityType(createContentActivityRequest.getActivityType());
        userContentActivity.setContentType(createContentActivityRequest.getContentType());
        userContentActivity.setContentId(mediaContentEntityRepository.getReferenceById(createContentActivityRequest.getContentId()));
        userContentActivityRepository.save(userContentActivity);
        return modelMapper.map(userContentActivity, UserContentActivityDTO.class);
    }

    public List<UserContentActivityDTO> getUserContentActivitiesByPagination(User user, ContentType contentType, int pageNo, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by("createdAt").descending());
        return modelMapper.mapAll(userContentActivityRepository.findAllByUserAndContentType(user, contentType, pageRequest), UserContentActivityDTO.class);
    }

    public void deleteUserContentActivity(Long id) {

    }
}
