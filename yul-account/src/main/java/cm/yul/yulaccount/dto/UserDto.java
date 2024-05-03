package cm.yul.yulaccount.dto;



public record UserDto(
    String firstname,
    String lastname,
    String email,
    String password,
    String phoneNumber
) {
}