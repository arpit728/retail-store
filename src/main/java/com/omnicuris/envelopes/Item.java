package com.omnicuris.envelopes;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by bugkiller on 08/01/19.
 */
@JsonDeserialize(builder = Item.Builder.class)
@Builder(builderClassName = "Builder")
@ToString
@Getter
public class Item {
    private int id;
    private int price;
    private int quantity;
    private String name;
    private String description;

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
        public Builder() {
        }
    }

}
