package com.omnicuris.envelopes;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by bugkiller on 09/01/19.
 */
@JsonDeserialize(builder = Order.Builder.class)
@Builder(builderClassName = "Builder", toBuilder = true)
@ToString
@Getter
public class Order {

    private Integer id;
    private int itemId;
    private int quantity;
    private String emailId;

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
        public Builder() {
        }
    }
}
