package constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserData {

    USER_1("amy@yandex.ru", "secretAmysPass#", "Amy"),
    USER_2("gloria@yandex.ru", "NobodyKnows@", "Gloria"),
    USER_3("newman@ya.ru", "cookaracha768", "Paul");

    private final String email;
    private final String password;
    private final String name;
}