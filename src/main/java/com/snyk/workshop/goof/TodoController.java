package com.snyk.workshop.goof;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/")
    public String listTodos(Model model) {
        model.addAttribute("todos", todoRepository.findAll());
        return "index";
    }

    @PostMapping("/add")
    public String addTodo(@RequestParam("text") String text) {
        todoRepository.save(new Todo(text));
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteTodo(@RequestParam("id") long id) {
        todoRepository.deleteById(id);
        return "redirect:/";
    }
}
