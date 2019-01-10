package com.omnicuris.core;

import com.omnicuris.db.ItemDao;
import com.omnicuris.db.OrderDao;
import com.omnicuris.envelopes.AddOrderResponse;
import com.omnicuris.envelopes.Item;
import com.omnicuris.envelopes.Order;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static com.omnicuris.envelopes.Status.FAIL;
import static com.omnicuris.envelopes.Status.SUCCESS;

/**
 * Created by bugkiller on 09/01/19.
 */
@Slf4j
public class OrderService {

    @Inject
    private OrderDao orderDao;

    @Inject
    private ItemDao itemDao;

    public AddOrderResponse processOrder(Order order) {
        validate(order);
        try {
            Order processedOrder = processSingleOrder(order);
            log.info("Order processed successfully, orderId : " + processedOrder.getId());
            return AddOrderResponse.builder()
                                   .order(processedOrder)
                                   .status(SUCCESS)
                                   .build();
        } catch (Exception e) {
            throw new WebApplicationException("Failed to place order", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    private Order processSingleOrder(Order order) {
        Item item = itemDao.getItem(order.getItemId());
        int id = orderDao.add(order);
        itemDao.updateQuantity(item.getId(), item.getQuantity() - order.getQuantity());
        return order.toBuilder().id(id).build();
    }

    public List<AddOrderResponse> processOrder(List<Order> orders) {
        List<AddOrderResponse> addOrderResponseList = new ArrayList<>();
        orders.forEach(order -> {
                           try {
                               validate(order);
                               Order processedOrder = processSingleOrder(order);
                               log.info("Order processed successfully, orderId : " + processedOrder.getId());
                               addOrderResponseList.add(
                                       AddOrderResponse.builder()
                                                       .order(processedOrder)
                                                       .status(SUCCESS)
                                                       .build()
                               );
                           } catch (WebApplicationException wea) {
                               log.error("Failed to process order : " + order.toString(), wea);
                               addOrderResponseList.add(
                                       AddOrderResponse.builder()
                                                       .status(FAIL)
                                                       .message(wea.getMessage())
                                                       .order(order)
                                                       .build()
                               );
                           } catch (Exception e) {
                               log.error("Failed to process order : " + orders, e);
                               throw new WebApplicationException("Failed to place orders", Response.Status.INTERNAL_SERVER_ERROR);
                           }
                       }
        );
        return addOrderResponseList;
    }

    private void validate(Order order) {
        Item item = itemDao.getItem(order.getItemId());
        String message = null;
        if (item == null) {
            message = "Requested item doesn't exist, provide a valid item id.";
        } else if (item.getQuantity() < order.getQuantity()) {
            message = "Item out of stock";
        }
        if (message != null) {
            throw new WebApplicationException(
                    message,
                    Response.ok()
                            .entity(AddOrderResponse.builder().status(FAIL).message(message).build())
                            .build()
            );
        }
    }

    public List<Order> getAllOrders() {
        return orderDao.getAllOrder();
    }
}
