package lk.zerocode.bookstoreapi.controller;

import lk.zerocode.bookstoreapi.dto.AuthorDTO;
import lk.zerocode.bookstoreapi.dto.BookDTO;
import lk.zerocode.bookstoreapi.model.Author;
import lk.zerocode.bookstoreapi.model.Book;
import lk.zerocode.bookstoreapi.model.LoyaltyPoint;
import lk.zerocode.bookstoreapi.repository.AuthorRepository;
import lk.zerocode.bookstoreapi.repository.BookRepository;
import lk.zerocode.bookstoreapi.repository.LoyaltyPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private LoyaltyPointRepository loyaltyPointRepository;

    @PostMapping("/authors")
    public Author createAuthor(@RequestBody AuthorDTO authorDTO) {
        if (authorDTO.getNic() == null || authorDTO.getNic().isEmpty()) {
            throw new IllegalArgumentException("NIC cannot be null or empty");
        }
        if (authorDTO.getName() == null || authorDTO.getName().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        Author author = new Author();
        author.setNic(authorDTO.getNic());
        author.setName(authorDTO.getName());
        return authorRepository.save(author);
    }

    @PostMapping("/authors/{id}/books")
    public Book addBookToAuthor(@PathVariable Long authorId, @RequestBody BookDTO bookDTO) {
        if (bookDTO.getName() == null || bookDTO.getName().isEmpty()) {
            throw new IllegalArgumentException("Book name cannot be null or empty");
        }
        if (bookDTO.getIsbn() == null || bookDTO.getIsbn().isEmpty()) {
            throw new IllegalArgumentException("ISBN cannot be null or empty");
        }
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        if (optionalAuthor.isEmpty()) {
            throw new RuntimeException("Author not found");
        }
        Author author = optionalAuthor.get();
        lk.zerocode.bookstoreapi.model.Book book = new lk.zerocode.bookstoreapi.model.Book();
        book.setName(bookDTO.getName());
        book.setIsbn(bookDTO.getIsbn());
        book.setAuthor(author);
        lk.zerocode.bookstoreapi.model.Book savedBook = bookRepository.save(book);

        LoyaltyPoint loyaltyPoint = new LoyaltyPoint();
        loyaltyPoint.setPoint(10);
        loyaltyPoint.setAuthor(author);
        loyaltyPointRepository.save(loyaltyPoint);

        return savedBook;
    }
}
