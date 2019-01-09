package com.omnicuris.envelopes;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by bugkiller on 09/01/19.
 */
@JsonDeserialize(builder = DeleteItemResponse.Builder.class)
@Builder(builderClassName = "Builder")
@ToString
@Getter
public class DeleteItemResponse {

    private Status status;

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
        public Builder() {
        }
    }
}
