package com.omnicuris.core;

import com.omnicuris.db.ItemDao;
import com.omnicuris.envelopes.*;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import static com.omnicuris.envelopes.Status.FAIL;
import static com.omnicuris.envelopes.Status.SUCCESS;

/**
 * Created by bugkiller on 08/01/19.
 */
@Slf4j
public class ItemService {

    @Inject
    private ItemDao itemDao;

    public AddItemResponse addItem(Item item) {
        Integer id = null;
        Status status;
        try {
            id = itemDao.addItem(item);
            status = SUCCESS;
        } catch (Exception e) {
            log.error("Failed to add item : ", e);
            throw new WebApplicationException("Failed to add item", Response.Status.INTERNAL_SERVER_ERROR);
        }
        return AddItemResponse.builder()
                              .id(id)
                              .status(status)
                              .build();
    }

    public UpdateItemResponse updateItem(Item item, Integer id) {
        try {
            int count = itemDao.update(item, id);
            if (count == 0) {
                throw new WebApplicationException(
                        "Entity not present",
                        Response.status(Response.Status.NOT_FOUND)
                                .entity(UpdateItemResponse.builder().status(FAIL).message("Requested item not present"))
                                .build());
            }
        } catch (Exception e) {
            log.error("Failed to update item : ", e);
            if (e instanceof WebApplicationException) {
                throw e;
            }
            throw new WebApplicationException("Failed to add item", Response.Status.INTERNAL_SERVER_ERROR);
        }
        return UpdateItemResponse.builder().status(SUCCESS).build();
    }

    public Item getItem(Integer id) {
        Item item;
        try {
            item = itemDao.getItem(id);
            if (item == null) {
                throw new WebApplicationException(
                        "Entity not present",
                        Response.serverError()
                                .entity(UpdateItemResponse.builder().status(FAIL).message("Requested item not present"))
                                .build());
            }
        } catch (Exception e) {
            if (e instanceof WebApplicationException) {
                throw e;
            }
            throw new WebApplicationException("Failed to get item, something went wrong", Response.Status.INTERNAL_SERVER_ERROR);
        }
        return item;
    }

    public DeleteItemResponse deleteItem(Integer id) {
        try {
            int count = itemDao.delete(id);
            if (count == 0) {
                throw new WebApplicationException(
                        "Entity not present",
                        Response.status(Response.Status.NOT_FOUND).build());
            }
        } catch (Exception e) {
            if (e instanceof WebApplicationException) {
                throw e;
            }
            throw new WebApplicationException("Failed to delete item, something went wrong", Response.Status.INTERNAL_SERVER_ERROR);
        }
        return DeleteItemResponse.builder().status(SUCCESS).build();
    }
}
