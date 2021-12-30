package project.rentcompany.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ResponseFormat<T> {
    private int code;
    private Boolean check;
    private String description;
    private T data;

    public static ResponseFormat ok() {
        return ResponseFormat.builder()
                .code(1)
                .check(true)
                .description(null)
                .data(null)
                .build();
    }
    public static ResponseFormat ok(String description) {
        return ResponseFormat.builder()
                .code(1)//1
                .check(true)//true
                .description(description)
                .data(null)//null
                .build();
    }

    //T ->  User , Boards, Car
    public static <T> ResponseFormat ok(T data) {
        return ResponseFormat.builder()
                .code(1)
                .check(true)
                .description(null)
                .data(data) //Car User Reted, Boards
                .build();
    }//Get 상황 // 유저 개인정보, 자동차 기능정보, 게시판 내용

    public static ResponseFormat fail() {
        return ResponseFormat.builder()
                .code(2)
                .check(false)
                .description(null)
                .data(null)
                .build();
    }
    public static ResponseFormat fail(String description) {
        return ResponseFormat.builder()
                .code(2)
                .check(false)
                .description(description)//문자열 설명
                .data(null) // null
                .build();
    }

}
