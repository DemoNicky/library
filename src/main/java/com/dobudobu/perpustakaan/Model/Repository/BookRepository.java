package com.dobudobu.perpustakaan.Model.Repository;

import com.dobudobu.perpustakaan.Model.Entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    public Optional<Book> findBySecureId(String BookId);

    Page<Book> findByjudulBukuContainingIgnoreCase(String name, Pageable page);
}
