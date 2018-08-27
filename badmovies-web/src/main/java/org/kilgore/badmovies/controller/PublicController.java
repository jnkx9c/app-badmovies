package org.kilgore.badmovies.controller;

import javax.validation.Valid;

import org.kilgore.badmovies.entity.User;
import org.kilgore.badmovies.form.RegistrationForm;
import org.kilgore.badmovies.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class PublicController {

	@Autowired 
	private UserDetailsManager userDetailsManager;
	
	@RequestMapping({"/",""})
	public RedirectView splashpage(Model model) {
		return new RedirectView("home");
	}
	
	@RequestMapping(path="/home", method=RequestMethod.GET)
	public ModelAndView home(Model model) {
		model.addAttribute("targetpage","home.jsp");
		return new ModelAndView("public/public_basepage");
	}
	
	@RequestMapping(path="/loginpage", method=RequestMethod.GET)
	public ModelAndView loginpage(Model model) {
		model.addAttribute("targetpage","login.jsp");

		return new ModelAndView("public/public_basepage");
	}	
	
	@RequestMapping(path="/registrationpage", method=RequestMethod.GET)
	public ModelAndView registrationpage( @ModelAttribute("registrationform") RegistrationForm registrationform, Model model) {
		model.addAttribute("targetpage","registration.jsp");
		
		return new ModelAndView("public/public_basepage");
	}
	
	@RequestMapping(path="/register", method=RequestMethod.POST)
	public ModelAndView registrationsubmit(
			@Valid @ModelAttribute("registrationform") RegistrationForm registrationform, 
			BindingResult bindingResult,
			Model model,
			final RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
    		model.addAttribute("targetpage","registration.jsp");
    		model.addAttribute("bindingresult",bindingResult);
    		return new ModelAndView("public/public_basepage");
        }
        
        boolean errorsExist=false;
        //the form passed spring validation..  now do our custom validation.
        //1 check if the username already exists
        try {
	        boolean userExists = userDetailsManager.userExists(registrationform.getEmail());
	        if(userExists) {
	        	errorsExist=true;
	        	model.addAttribute("error_userexists",true);
	        }
        }catch(UsernameNotFoundException unfe) {
        	//gotta catch this exception because its a RunTimeException and will exit the method if thrown.
        	
        }
        
        //2 check if the passwords match
        if(!registrationform.getPassword1().contentEquals(registrationform.getPassword2())) {
        	errorsExist=true;
        	model.addAttribute("error_passwordmatching",true);
        }
        
        if(errorsExist) {
        	model.addAttribute("targetpage","registration.jsp");
        	return new ModelAndView("public/public_basepage");
        }

        
        //at this point, the registration form has been validated..
        //create the user Entity, and send the user to the login page.
        User user = new User();
        user.setAddress(registrationform.getAddress());
        user.setCity(registrationform.getCity());
        user.setFirstName(registrationform.getFirstname());
        user.setLastName(registrationform.getLastname());
        user.setPassword(registrationform.getPassword1());
        user.setState(registrationform.getState());
        user.setUsername(registrationform.getEmail());
        user.setZipcode(registrationform.getZip());
        user.setEnabled(true);
        userDetailsManager.createUser(user);
        redirectAttrs.addFlashAttribute("firstname", registrationform.getFirstname());
        return new ModelAndView("redirect:/loginpage?registrationsuccess=true");
	}

}
