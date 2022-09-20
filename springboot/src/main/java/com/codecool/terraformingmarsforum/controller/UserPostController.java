package com.codecool.terraformingmarsforum.controller;

import com.codecool.terraformingmarsforum.model.AppUser;
import com.codecool.terraformingmarsforum.model.UserPost;
import com.codecool.terraformingmarsforum.service.UserPostService;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/user-posts")
@RequiredArgsConstructor
public class UserPostController {

    private final UserPostService userPostService;

    @Data
    @Builder
    public static final class CreateUserPostRequest {

        @NonNull
        private String description;
        @NonNull
        private Long userId;

        public UserPost convertToUserPost() {
            return UserPost.builder()
                    .description(description)
                    .user(AppUser.builder().id(userId).build())
                    .build();
        }
    }

    @Data
    @Builder
    public static final class UpdateUserPostRequest {

        @NonNull
        private String description;

        public UserPost convertToUserPost() {
            return UserPost.builder()
                    .description(description)
                    .build();
        }
    }

    @GetMapping
    public ResponseEntity<CollectionModel<UserPost>> getAllUserPosts() {
        List<UserPost> userPosts = userPostService.getAllUserPosts();

        userPosts.forEach(userPost -> {
            Link selfLink = linkTo(methodOn(UserPostController.class).getUserPostById(userPost.getId())).withSelfRel();
            userPost.add(selfLink);
        });
        Link selfLink = linkTo(methodOn(UserPostController.class).getAllUserPosts()).withSelfRel();
        CollectionModel<UserPost> userPostCollectionModel = CollectionModel.of(userPosts, selfLink);

        return ResponseEntity.ok(userPostCollectionModel);
    }

    @PostMapping
    public ResponseEntity<UserPost> createUserPost(@RequestBody CreateUserPostRequest createUserPostRequest) {
        UserPost userPost = userPostService.addUserPost(createUserPostRequest.convertToUserPost());
        return ResponseEntity
                .created(URI.create(String.format("/api/user-posts/%d", userPost.getId())))
                .body(userPost);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUserPost(
            @PathVariable Long id,
            @RequestBody UpdateUserPostRequest updateUserPostRequest) {
        UserPost userPost = updateUserPostRequest.convertToUserPost();
        userPostService.updateUserPost(id, userPost);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserPost(@PathVariable Long id) {
        userPostService.deleteUserPost(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserPost> getUserPostById(@PathVariable Long id) {
        UserPost userPost = userPostService.getUserPostById(id);

        Link selfLink = linkTo(methodOn(UserPostController.class).getUserPostById(id)).withSelfRel();
        Link userPostsLink = linkTo(methodOn(UserPostController.class).getAllUserPosts()).withRel("all");
        userPost.add(selfLink).add(userPostsLink);

        return ResponseEntity.ok(userPost);
    }
}
