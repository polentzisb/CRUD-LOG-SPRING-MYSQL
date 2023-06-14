package com.polentzi.tvshow.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.polentzi.tvshow.models.LoginUser;
import com.polentzi.tvshow.models.Show;
import com.polentzi.tvshow.models.User;
import com.polentzi.tvshow.services.ShowService;
import com.polentzi.tvshow.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class MainController {
    private final ShowService showService;
    private final UserService userService;

    public MainController(ShowService showService, UserService userService) {
        this.showService = showService;
        this.userService = userService;
    }

    @RequestMapping("/")
    public String index(Model viewModel) {
        viewModel.addAttribute("user", new User());
        viewModel.addAttribute("login", new LoginUser());
        return "logreg.jsp";
    }

    @PostMapping("/registration")
    public String register(@Valid @ModelAttribute("user") User user,
                           BindingResult result,
                           Model viewModel) {
        if (result.hasErrors()) {
            viewModel.addAttribute("login", new LoginUser());
            return "logreg.jsp";
        }

        User registeredUser = userService.registerUser(user, result);
        viewModel.addAttribute("login", new LoginUser());
        if (registeredUser != null) {
            viewModel.addAttribute("successRegister", "Thank you for registering. Please log in.");
        }
        return "logreg.jsp";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("login") LoginUser loginuser,
                        BindingResult result,
                        Model viewModel,
                        HttpSession session) {
        if (result.hasErrors()) {
            viewModel.addAttribute("user", new User());
            return "logreg.jsp";
        }

        if (userService.authenticateUser(loginuser.getEmail(), loginuser.getPassword(), result)) {
            User user = userService.findByEmail(loginuser.getEmail());
            session.setAttribute("userID", user.getId());
            return "redirect:/dashboard";
        } else {
            viewModel.addAttribute("user", new User());
            return "logreg.jsp";
        }
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model viewModel) {
        Long userId = (Long) session.getAttribute("userID");
        if (userId == null) {
            return "redirect:/";
        }

        User user = userService.findUserById(userId);
        viewModel.addAttribute("usuario", user);
        viewModel.addAttribute("shows", showService.allShows());
        return "dashboard.jsp";
    }

    @RequestMapping("/shows/new")
    public String index1(@ModelAttribute("show") Show show) {
        return "new.jsp";
    }

    @RequestMapping(value = "/shows/new", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("show") Show show,
                         BindingResult result,
                         Model model) {
        if (result.hasErrors()) {
            return "redirect:/shows/new";
        } else {
            showService.createShow(show);
            return "redirect:/dashboard";
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(@RequestParam("search") String search, HttpSession session) {
        session.setAttribute("searchWord", search);
        return "redirect:/search/" + search;
    }


    @RequestMapping(value = "/shows/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Long id, Model model) {
        Show show = showService.findShow(id);
        model.addAttribute("show", show);
        return "show.jsp";
    }

    @RequestMapping("/shows/{id}/delete")
    public String destroy(@PathVariable("id") Long id) {
        showService.deleteShow(id);
        return "redirect:/dashboard";
    }

    @RequestMapping(value = "/shows/{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Long id, Model model) {
        Show show = showService.findShow(id);
        model.addAttribute("show", show);
        return "edit.jsp";
    }

    @RequestMapping(value = "/shows/{id}/edit", method = RequestMethod.POST)
    public String update(@PathVariable("id") Long id,
                         @Valid @ModelAttribute("show") Show updatedShow,
                         BindingResult result,
                         Model model) {
        if (result.hasErrors()) {
            return "edit.jsp";
        } else {
            Show existingShow = showService.findShow(id);
            existingShow.setTitle(updatedShow.getTitle());
            existingShow.setNetwork(updatedShow.getNetwork());
            existingShow.setRating(updatedShow.getRating());

            showService.updateShow(existingShow);
            return "redirect:/dashboard";
        }
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    
}
