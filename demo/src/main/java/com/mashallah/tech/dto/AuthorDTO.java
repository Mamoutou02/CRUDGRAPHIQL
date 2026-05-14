package com.mashallah.tech.dto;

import lombok.Data;
import java.util.List;

@Data
public class AuthorDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private List<BookDTO> books;
}