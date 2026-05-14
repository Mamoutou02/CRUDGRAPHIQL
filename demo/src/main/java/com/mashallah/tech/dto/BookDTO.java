package com.mashallah.tech.dto;

import lombok.Data;

@Data
public class BookDTO {
    private Long id;
    private String title;
    private Integer pageCount;
    private AuthorDTO author;
}