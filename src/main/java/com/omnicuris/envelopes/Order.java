package com.omnicuris.envelopes;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.Null;

/**
 * Created by bugkiller on 09/01/19.
 */
@JsonDeserialize(builder = Order.Builder.class)
@Builder(builderClassName = "Builder", toBuilder = true)
@ToString
@Getter
public class Order {

    @Null(message = "External Order Id Not Allowed")
    private Integer id;
    private int itemId;
    @Min(value = 1)
    private int quantity;
    @NonNull
    private String emailId;

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
        public Builder() {
        }
    }
}
