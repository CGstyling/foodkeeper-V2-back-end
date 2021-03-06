package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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

    private String recipeFile;

    @Column(nullable= false)
    private boolean recipeIsPrivate;

    @Column(nullable= false, columnDefinition = "boolean default false")
    private boolean blockRecipe = false;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User user;

    @JsonIgnore
    @OneToMany(
            targetEntity = Comment.class,
            mappedBy = "recipes",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Comment> comments;


    public Long getRecipeId() {
        return recipeId;
    }
    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }
    public String getRecipeName() {
        return this.recipeName;
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
    public String getRecipeFile() {
        return recipeFile;
    }
    public void setRecipeFile(String recipeFile) {
        this.recipeFile = recipeFile;
    }
    public boolean isRecipeIsPrivate() {
        return recipeIsPrivate;
    }
    public void setRecipeIsPrivate(boolean recipeIsPrivate) {
        this.recipeIsPrivate = recipeIsPrivate;
    }
    public boolean isBlockRecipe() {
        return blockRecipe;
    }
    public void setBlockRecipe(boolean blockRecipe) {
        this.blockRecipe = blockRecipe;
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
    public List<Comment> getComments() {
        return comments;
    }
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
    @Override
    public int hashCode() {
        return Objects.hash(recipeId);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Recipe other = (Recipe) obj;
        return Objects.equals(recipeId, other.recipeId);
    }
}
