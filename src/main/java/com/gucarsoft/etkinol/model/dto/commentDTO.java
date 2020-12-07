package com.gucarsoft.etkinol.model.dto;

import com.gucarsoft.etkinol.model.Comment;
import com.gucarsoft.etkinol.model.Post;
import lombok.Data;

@Data
public class commentDTO {
    private Post post;
    private Comment comment;
}
