package com.dobudobu.perpustakaan.Service;

import com.dobudobu.perpustakaan.DTO.InsertDataBookWithRakDTO;
import com.dobudobu.perpustakaan.Model.Entity.Book;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {

    Book saveBook(Book book);

    List<Book> getAllData();

    Book updateData(String bookId, Book book);

    void deleteData(String bookId);

    Iterable<Book> findByName(String name, Pageable page);

    Iterable<Book> saveBatchBook(List<Book> asList);

    Book saveBookWithRak(InsertDataBookWithRakDTO insertDataBookDTO);
}
