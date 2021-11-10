package com.example.project.controllers;


import com.example.project.models.Answers;
import com.example.project.models.Questions;
import com.example.project.models.Users;
import com.example.project.repositories.AnswersRepository;
import com.example.project.repositories.QuestionsRepository;
import com.example.project.repositories.UsersRepository;
import com.example.project.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UsersContoller {

    Users you = new Users();

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UsersService usersService;

    @Autowired
    private QuestionsRepository questionsRepository;

    @Autowired
    private AnswersRepository answersRepository;

    @GetMapping("/create")
    public String showForm(Model model) {
        model.addAttribute("users", new Users());
        return "create";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute Users users, Model model) {
        model.addAttribute("users", users);
        String firstname = users.getFirstname();
        String lastname = users.getLastname();
        String email = users.getEmail();
        String password = users.getPassword();
        String role = users.getRole();
        Users newuser = new Users(firstname, lastname, email, password, role);
        usersRepository.save(newuser);
        return "messageCreated";
    }

    @GetMapping("/login")
    public String showLogin(Model model) {
        model.addAttribute("users", new Users());
        return "login";
    }

    @PostMapping("/login")
    public String login( String email, String password, Model model){
        you = usersRepository.findAllByEmailAndPassword(email, password);
        if(you == null){
            return "messageNotLoginned";
        }
        model.addAttribute("users", you);
        return "main";
    }
    @GetMapping("/delete")
    public String showDelete(Model model) {
        model.addAttribute("users", you);
        return "deleteAcc";
    }

    @PostMapping("/delete")
    public String deleteAcc( String password){

        if(password.equals(you.getPassword())){
            usersRepository.delete(you);

            return "messageDeletedAcc";
        }
        return "messageNotLoginned";
    }


    @GetMapping("/mainPage")
    public String blogMain(Model model) {
        Iterable<Questions> questions = questionsRepository.findAll();
        model.addAttribute("questions", questions);
        return "all_questions";
    }

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model, Model model2) {
        if (!questionsRepository.existsById(id)){
            return "redirect:/mainPage";
        }

        Optional<Questions> questions = questionsRepository.findById(id);
        ArrayList<Questions> res = new ArrayList<>();
        questions.ifPresent(res::add);



        List<Answers> answers = answersRepository.findAnswersByAquestion_id(id);
        model.addAttribute("answers", answers);
        model2.addAttribute("questions",res);
        return "question_info";
    }

    @GetMapping("/askQuestion")
    public String blogAdd(Model model) {
        return "addPost";
    }

    @PostMapping("/askQuestion")
    public String askQuestion(@ModelAttribute Questions questions, Model model) {
        model.addAttribute("questions", questions);
        String question = questions.getQuestion();
        String title = questions.getTitle();
        Long author = you.getId();
        Questions newPost = new Questions(title, question, author);
        questionsRepository.save(newPost);
        return "messageNewPost";
    }

    @GetMapping("/comment/{id}")
    public String showComment(@PathVariable(value = "id") Long id, Model model2,Model model) {
        Optional<Questions> questions = questionsRepository.findById(id);
        ArrayList<Questions> res = new ArrayList<>();
        questions.ifPresent(res::add);
        model2.addAttribute("questions",res);
        return "showComment";
    }

    @PostMapping("/addComment")
    public long addComment(Long id, String answer) {
        Long avtor_id = you.getId();
        Answers newComment = new Answers(answer, id, avtor_id);
        answersRepository.save(newComment);
        return id;
    }
}
/*  */