package com.example.demo.exception;

public class RecipeNotFoundException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public RecipeNotFoundException(String recipeName) {
            super("Cannot find recipe " + recipeName);
        }
}
