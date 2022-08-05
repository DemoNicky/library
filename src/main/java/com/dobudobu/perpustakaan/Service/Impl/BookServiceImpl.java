package com.dobudobu.perpustakaan.Service.Impl;

import com.dobudobu.perpustakaan.DTO.InsertDataBookWithRakDTO;
import com.dobudobu.perpustakaan.Model.Entity.Book;
import com.dobudobu.perpustakaan.Model.Entity.Rak;
import com.dobudobu.perpustakaan.Model.Repository.BookRepository;
import com.dobudobu.perpustakaan.Model.Repository.RakRepository;
import com.dobudobu.perpustakaan.Service.BookService;
import com.dobudobu.perpustakaan.Service.RakService;
import com.sun.jdi.request.InvalidRequestStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private RakService rakService;

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllData() {
        return bookRepository.findAll();
    }

    @Override
    public Book updateData(String bookId, Book book) {
        Book book1 = bookRepository.findBySecureId(bookId).orElseThrow(() -> new InvalidRequestStateException("Invalid"));
        book1.setJudulBuku(book.getJudulBuku() == null ? book1.getJudulBuku() : book.getJudulBuku());
        book1.setPenulisBuku(book.getPenulisBuku() == null ? book1.getPenulisBuku() : book.getPenulisBuku());
        book1.setPenerbitBuku(book.getPenerbitBuku() == null ? book1.getPenerbitBuku() : book.getPenerbitBuku());
        book1.setTahunTerbit(book.getTahunTerbit() == null ? book1.getTahunTerbit() : book.getTahunTerbit());
        book1.setStok(book.getStok() == null ? book1.getStok() : book.getStok());

        return bookRepository.save(book1);
    }

    @Override
    public void deleteData(String bookId) {
        Book book = bookRepository.findBySecureId(bookId).orElseThrow(() -> new InvalidRequestStateException("invalid"));
        bookRepository.delete(book);
    }

    @Override
    public Iterable<Book> findByName(String name, Pageable page) {
        return bookRepository.findByjudulBukuContainingIgnoreCase(name, page);
    }

    @Override
    public Iterable<Book> saveBatchBook(List<Book> asList) {
        return bookRepository.saveAll(asList);
    }

    @Override
    public Book saveBookWithRak(InsertDataBookWithRakDTO insertDataBookDTO) {
        Rak rak = rakService.findRakByCode(insertDataBookDTO.getRak().getKodeRak());
        if (rak.getKodeRak() == null){
            throw new NullPointerException(String.format("Rak dengan kode " + insertDataBookDTO.getRak().getKodeRak() + "tidak di temukan"));
        }
        Book book = new Book();
        book.setJudulBuku(insertDataBookDTO.getJudulBuku());
        book.setPenulisBuku(insertDataBookDTO.getPenulisBuku());
        book.setPenerbitBuku(insertDataBookDTO.getPenerbitBuku());
        book.setTahunTerbit(insertDataBookDTO.getTahunTerbit());
        book.setStok(insertDataBookDTO.getStok());
        book.setRak(rak);

        return bookRepository.save(book);
    }


}
