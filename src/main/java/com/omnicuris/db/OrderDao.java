package com.omnicuris.db;

import com.omnicuris.envelopes.Order;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface OrderDao {

    @SqlUpdate("INSERT INTO " +
               "`order` (item_id, quantity, email_id) " +
               "VALUES (:o.itemId, :o.quantity, :o.emailId);")
    @GetGeneratedKeys
    int add(@BindBean("o") Order order);

    @SqlQuery("SELECT * FROM order where id = :id;")
    Order getOrder(@Bind("id") int id);

    @SqlQuery("SELECT * FROM order;")
    List<Order> getAllOrder();

    @SqlUpdate("DELETE FROM order WHERE id = :id;")
    int deleteOrder(@Bind("id") int id);

}
