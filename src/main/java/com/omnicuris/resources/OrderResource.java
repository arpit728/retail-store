package com.omnicuris.resources;

import com.omnicuris.core.OrderService;
import com.omnicuris.envelopes.AddItemResponse;
import com.omnicuris.envelopes.AddOrderResponse;
import com.omnicuris.envelopes.Item;
import com.omnicuris.envelopes.Order;
import io.dropwizard.validation.MinSize;
import jdk.nashorn.internal.objects.annotations.Getter;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.*;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by bugkiller on 08/01/19.
 */

@Path("orders")
public class OrderResource {


    @Inject
    private OrderService orderService;

    @GET
    @Produces(APPLICATION_JSON)
    public List<Order> getOrders() {
        return orderService.getAllOrders();
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public AddOrderResponse createOrder(@NotNull @Valid Order order) {
        return orderService.processOrder(order);
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public List<AddOrderResponse> createOrder(@NotNull @Size(min = 1) List<Order> orders) {
        return orderService.processOrder(orders);
    }
}
