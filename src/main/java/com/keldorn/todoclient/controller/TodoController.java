package com.keldorn.todoclient.controller;

import com.keldorn.todoclient.dto.TodoRequest;
import com.keldorn.todoclient.service.TodoUiService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoUiService todoUiService;

    @GetMapping
    public String listTodos(Model model, HttpServletRequest request) {
        model.addAttribute("todos", todoUiService.getTodos(request));
        model.addAttribute("newTodo", new TodoRequest());
        return "todos/list";
    }

    @PostMapping
    public String createTodo(@ModelAttribute("newTodo") TodoRequest requestDto,
                             HttpServletRequest request) {
        todoUiService.createTodo(requestDto, request);
        return "redirect:/todos";
    }

    @PostMapping("/{id}/update")
    public String updateTodo(@PathVariable Long id,
                             @ModelAttribute("editTodo") TodoRequest todoRequest,
                             HttpServletRequest request) {
        todoUiService.updateTodo(id, todoRequest, request);
        return "redirect:/todos";
    }

    @PostMapping("/{id}/patch")
    public String patchTodo(@PathVariable Long id,
                             @ModelAttribute("editTodo") TodoRequest todoRequest,
                             HttpServletRequest request) {
        todoUiService.patchTodo(id, todoRequest, request);
        return "redirect:/todos";
    }

    @PostMapping("/{id}/delete")
    public String deleteTodo(@PathVariable Long id, HttpServletRequest request) {
        todoUiService.deleteTodo(id, request);
        return "redirect:/todos";
    }
}
