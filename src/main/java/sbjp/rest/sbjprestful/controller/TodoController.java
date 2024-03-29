package sbjp.rest.sbjprestful.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sbjp.rest.sbjprestful.entities.Todo;
import sbjp.rest.sbjprestful.payload.request.TodoRequest;
import sbjp.rest.sbjprestful.payload.request.TodoSearchRequest;
import sbjp.rest.sbjprestful.payload.response.TodoReponse;
import sbjp.rest.sbjprestful.services.TodoService;

@CrossOrigin(origins = "http://localhost:6661/")
@RestController(value="todoAPIofWeb")
@RequestMapping("/api/v1/todos") 
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	@GetMapping()
	public ResponseEntity<List<TodoReponse>> getAll() {
		try {
			return new ResponseEntity<>(todoService.getAll(), HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{todoId}")
	public ResponseEntity<TodoReponse> getTodoById(@PathVariable("todoId") int todoId) {
		try {
			return new ResponseEntity<>(todoService.getById(todoId), HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping()
	public ResponseEntity<?> create(@RequestBody TodoRequest todoRequest) {
		try {
			System.out.println(todoRequest.toString());
			if (todoService.add(todoRequest)) {
				return new ResponseEntity<>("Created!", HttpStatus.CREATED);
			}
			return new ResponseEntity<>("Create faile!", HttpStatus.BAD_GATEWAY);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/{todoId}")
	public ResponseEntity<String> update(@PathVariable("todoId") int todoId, @RequestBody TodoRequest request) {
		try {
			if (todoService.findById(todoId) == null) {
				return new ResponseEntity<>("No user found!", HttpStatus.BAD_GATEWAY);
			}

			if (todoService.update(todoId,request)) {
				return new ResponseEntity<>("Updated!", HttpStatus.OK);
			}
			return new ResponseEntity<>("Update faile!", HttpStatus.BAD_GATEWAY);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{todoId}")
	public ResponseEntity<String> delete(@PathVariable("todoId") int todoId) {
		try {
			if (todoService.findById(todoId)== null) {
				return new ResponseEntity<>("No todo found!", HttpStatus.BAD_GATEWAY);
			}

			if (todoService.delete(todoId)) {
				return new ResponseEntity<>("Deleted!", HttpStatus.OK);
			}
			return new ResponseEntity<>("Delete faile!", HttpStatus.BAD_GATEWAY);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
