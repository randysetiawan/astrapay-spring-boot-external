package com.astrapay.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Note {

    private Long id;

    @NotBlank(message = "Title cannot be blank")
    private String noteTitle;

    @NotBlank(message = "Content cannot be blank")
    private String noteContent;
}
