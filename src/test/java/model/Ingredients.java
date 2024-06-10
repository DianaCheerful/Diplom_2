package model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredients {

    private List<String> ingredients;

    public Ingredients(String ingredient) {
        this.ingredients = Collections.singletonList(ingredient);
    }
}