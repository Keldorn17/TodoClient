package com.keldorn.todoclient.controller;

import com.keldorn.todoclient.dto.CompletedToggleRequest;
import com.keldorn.todoclient.dto.TodoRequest;
import com.keldorn.todoclient.dto.TodoResponse;
import com.keldorn.todoclient.service.TodoUiService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@Slf4j
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

    @GetMapping("/{id}/edit")
    @ResponseBody
    public ResponseEntity<TodoResponse> getTodoForEdit(@PathVariable Long id, HttpServletRequest request) {
        try {
            TodoResponse todo = todoUiService.getTodoById(id, request);
            return ResponseEntity.ok(todo);
        } catch (HttpClientErrorException.NotFound ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
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
//        TodoResponse todo = todoUiService.getTodoById(id, request);
//        todoRequest.setCompleted(todo.completed());
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

    @PostMapping("/{id}/completed/toggle")
    public String toggleCompleted(@PathVariable Long id, HttpServletRequest request) {
        TodoResponse response = todoUiService.getTodoById(id, request);
        CompletedToggleRequest completedToggleRequest = new CompletedToggleRequest(!response.completed());

        todoUiService.patchGenericTodo(id, completedToggleRequest, request);
        return "redirect:/todos";
    }

    @PostMapping("/{id}/delete")
    public String deleteTodo(@PathVariable Long id, HttpServletRequest request) {
        todoUiService.deleteTodo(id, request);
        return "redirect:/todos";
    }
}
