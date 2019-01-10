package com.omnicuris.injection.factory;

import com.omnicuris.db.OrderDao;
import com.omnicuris.envelopes.Order;
import org.glassfish.hk2.api.Factory;
import org.jdbi.v3.core.Jdbi;

import javax.inject.Inject;

/**
 * Created by bugkiller on 09/01/19.
 */
public class OrderDaoFactory implements Factory<OrderDao> {

    @Inject
    private Jdbi database;

    @Override
    public OrderDao provide() {
        database.registerRowMapper(
                Order.class,
                (rs, ctx) -> Order.builder()
                                  .id(rs.getInt("id"))
                                  .itemId(rs.getInt("item_id"))
                                  .quantity(rs.getInt("quantity"))
                                  .emailId(rs.getString("email_id"))
                                  .build()
        );
        return database.onDemand(OrderDao.class);
    }

    @Override
    public void dispose(OrderDao order) {
    }
}
