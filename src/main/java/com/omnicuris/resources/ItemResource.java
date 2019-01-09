package com.omnicuris.resources;

import com.omnicuris.core.ItemService;
import com.omnicuris.envelopes.AddItemResponse;
import com.omnicuris.envelopes.DeleteItemResponse;
import com.omnicuris.envelopes.Item;
import com.omnicuris.envelopes.UpdateItemResponse;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by bugkiller on 08/01/19.
 */
@Path("items")
public class ItemResource {

    @Inject
    private ItemService itemService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addItem(Item item) {
        AddItemResponse response = itemService.addItem(item);
        return Response.ok(response)
                       .status(Response.Status.CREATED)
                       .build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateItem(Item item, @NotNull @PathParam("id")Integer id) {
        UpdateItemResponse response = itemService.updateItem(item, id);
        return Response.ok(response)
                       .status(Response.Status.CREATED)
                       .build();
    }

    @Path("/{id}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Item getItem(@NotNull @PathParam("id") Integer id) {
        return itemService.getItem(id);
    }

    @Path("/{id}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteItem(@NotNull @PathParam("id") Integer id) {
        DeleteItemResponse deleteItemResponse = itemService.deleteItem(id);
        return Response.ok(deleteItemResponse).build();
    }

}
