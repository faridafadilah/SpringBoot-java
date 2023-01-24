package com.thymeleaf.learnthymeleaf.controller;

import org.springframework.core.io.Resource;

import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thymeleaf.learnthymeleaf.model.FileInfo;
import com.thymeleaf.learnthymeleaf.service.FileStorageService;

@Controller
public class FileController {
  @Autowired
  FileStorageService storageService;

  @GetMapping("/")
  public String homepage() {
    return "redirect:/images";
  }

  @GetMapping("/images")
  public String getListImages(Model model) {
    List<FileInfo> imageInfo = storageService.loadAll().map(path-> {
      String filename = path.getFileName().toString();
      String url = MvcUriComponentsBuilder.fromMethodName(FileController.class, "getImage", path.getFileName().toString()).build().toString();
      return new FileInfo(filename, url);
    }).collect(Collectors.toList());

    model.addAttribute("images", imageInfo);
    return "images";
  }

  @GetMapping("/images/new")
  public String newFile(Model model) {
    return "upload_form";
  }

  @PostMapping("/images/upload") 
  public String uploadFile(Model model, @RequestParam("file") MultipartFile file) {
    String message = "";

    try {
      storageService.save(file);
      message = "Uploaded the images successfully: " + file.getOriginalFilename();
      model.addAttribute("message", message);
    } catch (Exception e) {
      message = "Could not upload the images: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
      model.addAttribute("message", message);
    }
    return "upload_form";
  }

  @GetMapping("/images/{filename:.+}")
  public ResponseEntity<Resource> getImage(@PathVariable String filename) {
    Resource file = storageService.load(filename);

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
  }

  @GetMapping("/images/delete/{filename:.+}")
  public String deleteImage(@PathVariable String filename, Model model, RedirectAttributes redirectAttributes) {
    try {
      boolean existed = storageService.delete(filename);

      if (existed) {
        redirectAttributes.addFlashAttribute("message", "Delete the image successfully: " + filename);
      } else {
        redirectAttributes.addFlashAttribute("message", "The image does not exist!");
      }
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("message", "Cloud not delete the image: " + filename + ".Error: " + e.getMessage());
    }
    return "redirect:/images";
  }

  @GetMapping("/images/edit/{filename:.+}")
  public String editPage(@PathVariable String filename, Model model, RedirectAttributes redirectAttributes) {
    try {
      boolean existed = storageService.delete(filename);

      if (!existed) {
        redirectAttributes.addFlashAttribute("message", "The image does not exist!");
      }
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("message", "Cloud not delete the image: " + filename + ".Error: " + e.getMessage());
    }
    return "edit_form";
  }

  @PostMapping("/images/update")
  public String editFile(Model model, @RequestParam("file") MultipartFile file) {
    String message = "";

    try {
      storageService.save(file);
      message = "Uploaded the images successfully: " + file.getOriginalFilename();
      model.addAttribute("message", message);
    } catch (Exception e) {
      message = "Could not upload the images: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
      model.addAttribute("message", message);
    }
    return "edit_form";
  }
}
