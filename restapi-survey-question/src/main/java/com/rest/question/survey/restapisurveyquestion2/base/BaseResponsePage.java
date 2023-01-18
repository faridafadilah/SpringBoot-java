package com.rest.question.survey.restapisurveyquestion2.base;


import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponsePage<T> {
    private Integer totalPage;
    private Integer currentPage;
    private Long totalElement;
    private T content;
}
 
