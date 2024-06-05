package model.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import model.request.Order;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderResponse {

    private boolean success;
    private List<Order> orders;
}
