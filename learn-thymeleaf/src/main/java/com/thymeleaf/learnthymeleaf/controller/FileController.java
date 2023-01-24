package com.thymeleaf.learnthymeleaf.controller;

import org.springframework.core.io.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.thymeleaf.learnthymeleaf.service.FileStorageService;

@Controller
public class FileController {
  @Autowired
  FileStorageService storageService;

  @GetMapping("/")
  public String homepage() {
    return "redirect:/files";
  }

  @GetMapping("/files/new")
  public String newFile(Model model) {
    return "upload_form";
  }

  @PostMapping("/files/upload") 
  public String uploadFile(Model model, @RequestParam("file") MultipartFile file) {
    String message = "";

    try {
      storageService.save(file);
      message = "Uploaded the file successfully: " + file.getOriginalFilename();
      model.addAttribute("message", message);
    } catch (Exception e) {
      message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
      model.addAttribute("message", message);
    }
    return "upload_form";
  }

  @GetMapping("/files/{filename:.+}")
  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
    Resource file = storageService.load(filename);

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
  }
}
