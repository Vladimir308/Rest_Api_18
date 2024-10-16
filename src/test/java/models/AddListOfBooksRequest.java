package models;

import lombok.Data;

import java.util.List;

@Data
public class AddListOfBooksRequest {
    private String userId;
    private List<ListOfIsbns> collectionOfIsbns;
    private String isbn;
}