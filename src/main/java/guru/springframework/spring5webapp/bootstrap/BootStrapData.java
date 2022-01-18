package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author("Eric", "Evans");
        Book book = new Book("Domain driven design", "ohsgyast7654qwf8qw");
        Publisher publisher1 = new Publisher("Publisher1", "addres of publisher 1", "Bucharest", "RO", "172517");
        publisherRepository.save(publisher1); //save of the publisher must be before setPublisher in book bc that method actually updates the db and if cannot find the publisher in db it will throw error

        eric.getBooks().add(book);
        book.getAuthors().add(eric);
        book.setPublisher(publisher1);
        publisher1.getBooks().add(book);

        authorRepository.save(eric);
        bookRepository.save(book);
        publisherRepository.save(publisher1);

        System.out.println("Started in Bootstrap");
        System.out.println("No. of books: " + bookRepository.count());
        System.out.println("No. of publishers: " + publisherRepository.count());
    }
}
