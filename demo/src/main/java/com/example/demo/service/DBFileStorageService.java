package com.example.demo.service;

import com.example.demo.model.DBFile;
import com.example.demo.model.Recipe;
import com.example.demo.repository.DBFileRepository;
import com.example.demo.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;


@Service
public class DBFileStorageService {

    @Autowired
    private DBFileRepository dbFileRepository;
    public DBFileStorageService(DBFileRepository dbFileRepository) {
        this.dbFileRepository = dbFileRepository;
    }

    //upload image and store in DB
    public DBFile storeFile(MultipartFile file) {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if(fileName.contains("..")) {
                throw new RuntimeException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());
            return dbFileRepository.save(dbFile);
        } catch (IOException exception) {
            throw new RuntimeException("Could not store file " + fileName + ". Please try again!", exception);
        }
    }

    //Get image by ID (Download)
    public DBFile getFile(String fileId) {
        return dbFileRepository.findById(fileId)
                .orElseThrow(() -> new RuntimeException("File with id: " + fileId + " is not found."));
    }

}
