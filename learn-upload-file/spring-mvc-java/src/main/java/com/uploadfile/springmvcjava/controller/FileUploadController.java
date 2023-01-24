package com.uploadfile.springmvcjava.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.uploadfile.springmvcjava.model.FormDataWithFile;

@Controller
public class FileUploadController {
  private static String UPLOAD_LOCATION="D:/mytemp/";

    @RequestMapping(value = "/fileUpload", method = RequestMethod.GET)
    public String displayForm() {

        return "fileUploadForm";
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String submit(@RequestParam("file") final MultipartFile file, final ModelMap modelMap) throws IOException {
      FileCopyUtils.copy(file.getBytes(), new File(UPLOAD_LOCATION + file.getOriginalFilename()));
        modelMap.addAttribute("file", file);
        return "fileUploadView";
    }

    @RequestMapping(value = "/uploadMultiFile", method = RequestMethod.POST)
    public String submit(@RequestParam("files") final MultipartFile[] files, final ModelMap modelMap) {

        modelMap.addAttribute("files", files);
        return "fileUploadView";
    }

    @RequestMapping(value = "/uploadFileWithAddtionalData", method = RequestMethod.POST)
    public String submit(@RequestParam final MultipartFile file, @RequestParam final String name, @RequestParam final String email, final ModelMap modelMap) {

        modelMap.addAttribute("name", name);
        modelMap.addAttribute("email", email);
        modelMap.addAttribute("file", file);
        return "fileUploadView";
    }

    @RequestMapping(value = "/uploadFileModelAttribute", method = RequestMethod.POST)
    public String submit(@ModelAttribute final FormDataWithFile formDataWithFile, final ModelMap modelMap) {

        modelMap.addAttribute("formDataWithFile", formDataWithFile);
        return "fileUploadView";
    }

}
