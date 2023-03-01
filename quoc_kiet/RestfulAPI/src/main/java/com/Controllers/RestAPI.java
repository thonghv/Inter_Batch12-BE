package com.Controllers;

import com.Models.Todo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class RestAPI {
    private List<Todo> todoList = new CopyOnWriteArrayList<>();

    @PostConstruct
    public void init(){
        // Thêm null vào List để bỏ qua vị trí số 0;
        todoList.add(null);
    }

    @GetMapping("/todo")
    public List<Todo> getTodoList() {
        return todoList;
    }

    @GetMapping("/todo/{todoId}")
    public Todo getTodo(@PathVariable(name = "todoId") Integer todoId){
        // @PathVariable lấy ra thông tin trong URL
        // dựa vào tên của thuộc tính đã định nghĩa trong ngoặc kép /todo/{todoId}
        return todoList.get(todoId);
    }

    @PutMapping("/todo/{todoId}")
    public Todo editTodo(@PathVariable(name = "todoId") Integer todoId,
                         @RequestBody Todo todo){
        todoList.set(todoId, todo);
        // Trả về đối tượng sau khi đã edit
        return todo;    }

    @DeleteMapping("/todo/{todoId}")
    public ResponseEntity deleteTodo(@PathVariable(name = "todoId") Integer todoId){
        todoList.remove(todoId.intValue());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/todo")
    public ResponseEntity addTodo(@RequestBody Todo todo) {
        todoList.add(todo);
        // Trả về response với STATUS CODE = 200 (OK)
        // Body sẽ chứa thông tin về đối tượng todo vừa được tạo.
        return ResponseEntity.ok().body(todo);
    }
}
