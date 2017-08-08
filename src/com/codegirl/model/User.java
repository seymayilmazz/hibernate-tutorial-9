package com.codegirl.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Şeyma Yılmaz on 6.8.2017.
 */
@Entity
@NamedQueries(
        {
                @NamedQuery(name = "User.findAll", query = "from User"),
                @NamedQuery(name = "User.findByName", query = "from User where firstName = :name")
        }
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;
    private String lastName;
    private int age;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Account account;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment>comments = new ArrayList<>();

    @ManyToMany(mappedBy = "whoLiked")
    private List<Post> postLikes = new ArrayList<>();

    @ManyToMany(mappedBy = "whoLiked")
    private List<Comment> commentLikes = new ArrayList<>();

    /* friendship */
    @ManyToMany
    @JoinTable(
            name = "friendship",
            joinColumns = @JoinColumn(name = "following_id"),
            inverseJoinColumns = @JoinColumn(name = "follower_id")
    )
    private List<User> followers = new ArrayList<>();

    @ManyToMany(mappedBy = "followers")
    private List<User> followings = new ArrayList<>();

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Post> getPostLikes() {
        return postLikes;
    }

    public void setPostLikes(List<Post> postLikes) {
        this.postLikes = postLikes;
    }

    public List<Comment> getCommentLikes() {
        return commentLikes;
    }

    public void setCommentLikes(List<Comment> commentLikes) {
        this.commentLikes = commentLikes;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public List<User> getFollowings() {
        return followings;
    }

    public void setFollowings(List<User> followings) {
        this.followings = followings;
    }
}
