package com.omnicuris.envelopes;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by bugkiller on 09/01/19.
 */
@JsonDeserialize(builder = UpdateItemResponse.Builder.class)
@Builder(builderClassName = "Builder")
@ToString
@Getter
public class UpdateItemResponse {
    private Status status;
    private String message;

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
        public Builder() {
        }
    }
}
