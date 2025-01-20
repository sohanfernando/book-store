package lk.zerocode.bookstoreapi.repository;

import lk.zerocode.bookstoreapi.model.Author;
import lk.zerocode.bookstoreapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
