package model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import model.request.Ingredient;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class IngredientResponse {

    private boolean success;
    private List<Ingredient> data;
}
