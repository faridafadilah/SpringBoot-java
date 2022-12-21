package com.contact.springboot.contactapp.contact;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@SessionAttributes("name")
public class ContactController {
  private ContactService contactService;

  public ContactController(ContactService contactService) {
    super();
    this.contactService = contactService;
  }

  @RequestMapping("contacts")
  public String listAllContacts(ModelMap model) {
    List<Contact> contacts = contactService.findAll();
    model.addAttribute("contacts", contacts);
    return "contacts";
  }

  @RequestMapping(value = "add-contact", method = RequestMethod.GET)
  public String addContactPage(ModelMap model) {
    String username = getLoggedInUsername(model);
    Contact contact = new Contact(0, username, "", "");
    model.put("contact", contact);
    return "form-contact";
  }

  @RequestMapping(value = "add-contact", method = RequestMethod.POST)
  public String addNewContact(ModelMap model, @Valid Contact contact, BindingResult result) {
    if(result.hasErrors()) {
      return "form-contact";
    }
    contactService.save(contact);
    return "redirect:contacts";
  }

  @RequestMapping("delete-contact")
  public String deleteContact(@RequestParam Long id) {
    contactService.deleteById(id);
    return "redirect:contacts";
  }
  
  @RequestMapping(value = "edit-contact", method = RequestMethod.GET)
  public String editContactPage(@RequestParam Long id, ModelMap model) {
    Contact contact = contactService.findById(id).get();
    model.addAttribute("contact", contact);
    return "form-contact";
  }

  @RequestMapping(value = "edit-contact", method = RequestMethod.POST)
  public String editContact(ModelMap model, @Valid Contact contact, BindingResult result) {
    if(result.hasErrors()) {
      return "form-contact";
    }
    contactService.save(contact);
    return "redirect:contacts";
  }

  private String getLoggedInUsername(ModelMap model) {
		Authentication authentication = 
				SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
}
