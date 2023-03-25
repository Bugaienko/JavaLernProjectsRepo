package ua.bugaienko.springBootApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.bugaienko.springBootApp.models.Book;
import ua.bugaienko.springBootApp.models.Person;


import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {

    List<Book> findByReader(Person reader);

}
