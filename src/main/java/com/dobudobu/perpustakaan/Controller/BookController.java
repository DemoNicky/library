package com.dobudobu.perpustakaan.Controller;

import com.dobudobu.perpustakaan.DTO.*;
import com.dobudobu.perpustakaan.Model.Entity.Book;
import com.dobudobu.perpustakaan.Service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@RestController
//@Controller
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("v1/book")
    public ResponseEntity<ResponseData<Book>> insertData(@RequestBody @Valid InsertDataBookDTO insertDataBookDTO,
                                                          Errors errors){
        ResponseData<Book> responseData = new ResponseData<>();

        if (errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Book book = modelMapper.map(insertDataBookDTO, Book.class);
        responseData.setPayload(bookService.saveBook(book));
        responseData.setStatus(true);
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("v1/book/get")
    public List<Book> getAllBook(){
        return bookService.getAllData();
    }

    @PutMapping("v1/book/{id}")
    public ResponseEntity<ResponseData<Book>> updateData(@RequestBody @Valid UpdateDataBookDTO updateDataBookDTO,
                                                         @PathVariable("id") String bookId, Errors errors){
        ResponseData<Book> responseData = new ResponseData<>();

        if (errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Book book = modelMapper.map(updateDataBookDTO, Book.class);
        responseData.setPayload(bookService.updateData(bookId ,book));
        responseData.setStatus(true);
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("v1/book/{id}")
    public ResponseEntity<Void> deleteData(@PathVariable("id") String bookId){
        bookService.deleteData(bookId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("v1/book/{size}/{page}")
    public Iterable<Book> findByName(@RequestBody SearchDataDTO name, @PathVariable("size") int size,
                                     @PathVariable("page") int page){
        Pageable pageable = PageRequest.of(page, size);
        return bookService.findByName(name.getSearchKey(), pageable);
    }

    @PostMapping("v1/book/batchsave")
    public ResponseEntity<ResponseData<Iterable<Book>>> insertBatch(@RequestBody Book[] insertBook){
        ResponseData<Iterable<Book>> responseData = new ResponseData<>();

        responseData.setPayload(bookService.saveBatchBook(Arrays.asList(insertBook)));
        responseData.setStatus(true);
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("v1/book/save")
    public ResponseEntity<ResponseData<Book>> insertBook(@RequestBody @Valid InsertDataBookWithRakDTO insertDataBookDTO,
                                                         Errors errors) {
        ResponseData<Book> responseData = new ResponseData<>();

        responseData.setPayload(bookService.saveBookWithRak(insertDataBookDTO));
        responseData.setStatus(true);
        return ResponseEntity.ok(responseData);

    }
}
