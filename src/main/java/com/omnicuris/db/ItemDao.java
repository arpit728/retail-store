package com.omnicuris.db;

import com.omnicuris.envelopes.Item;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface ItemDao {

    @SqlUpdate("INSERT INTO item (item_name, description, price) VALUES (:i.name, i.description, i.price);")
    @GetGeneratedKeys
    int addItem(@BindBean("i") Item item);

    @SqlQuery("SELECT * FROM item WHERE id = :id")
    Item getItem(@Bind("id") int id);

    @SqlUpdate("UPDATE item " +
               "set name = COALESCE(:i.name, name), " +
               "description = COALESCE(:i.description, description), " +
               "price = COALESCE(:i.price, price)," +
               "quantity = COALESCE(:i.quantity, quantity)" +
               "WHERE id = :id;")
    int update(@BindBean("i") Item item, @Bind("id") int id);

    @SqlUpdate("UPDATE item " +
               "SET quantity = :q)" +
               "WHERE id = :id;")
    void updateQuantity(@Bind("id") int id, @Bind("q") int quantity);

    @SqlQuery("DELETE FROM item WHERE id = :id;")
    int delete(@Bind("id") int id);
}
