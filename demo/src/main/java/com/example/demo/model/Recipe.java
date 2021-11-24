package com.example.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recipeId;

    @Column(length = 100, nullable = false)
    private String recipeName;

    @Column(length = 5000, nullable = false)
    private String recipeIngredient;

    @Column(length = 5000, nullable = false)
    private String recipeDescription;

    @Column(nullable= false)
    private boolean recipeIsPrivate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(
            targetEntity = Comment.class,
            mappedBy = "recipes",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Comment> comments;

    public long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeIngredient() {
        return recipeIngredient;
    }

    public void setRecipeIngredient(String recipeIngredient) {
        this.recipeIngredient = recipeIngredient;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    public boolean isRecipeIsPrivate() {
        return recipeIsPrivate;
    }

    public void setRecipeIsPrivate(boolean recipeIsPrivate) {
        this.recipeIsPrivate = recipeIsPrivate;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
