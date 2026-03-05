package example.day05.practice5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public boolean 도서등록( BookDto bookDto){
        BookEntity bookEntity = new BookEntity();
        bookEntity.setBname(bookDto.getBname());
        bookEntity.setBauthor(bookDto.getBauthor());
        bookEntity.setBpublisher(bookDto.getBpublisher());

        BookEntity savedEntity = bookRepository.save(bookEntity);
        if (savedEntity.getBno() >= 1) return true;
        return false;
    }
}
