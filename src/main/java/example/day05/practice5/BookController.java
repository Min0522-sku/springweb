package example.day05.practice5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/book")
    public boolean 도서등록(@RequestBody BookDto bookDto){
        boolean result = bookService.도서등록(bookDto);
        return result;
    }
}
