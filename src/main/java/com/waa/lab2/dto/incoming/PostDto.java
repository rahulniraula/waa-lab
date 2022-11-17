package com.waa.lab2.dto.incoming;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private long id;
    private String title;
    private String content;
    private String author;
    private List<CommentDto> comments;
}
