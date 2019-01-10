package com.omnicuris.injection.factory;

import com.omnicuris.db.ItemDao;
import com.omnicuris.envelopes.Item;
import org.glassfish.hk2.api.Factory;
import org.jdbi.v3.core.Jdbi;

import javax.inject.Inject;

/**
 * Created by bugkiller on 09/01/19.
 */
public class ItemDaoFactory implements Factory<ItemDao> {

    @Inject
    private Jdbi database;

    @Override
    public ItemDao provide() {
        database.registerRowMapper(
                Item.class,
                (rs, ctx) ->
                        Item.builder()
                            .id(rs.getInt("id"))
                            .price(rs.getInt("price"))
                            .name(rs.getString("item_name"))
                            .description(rs.getString("description"))
                            .quantity(rs.getInt("quantity"))
                            .build()
        );

        return database.onDemand(ItemDao.class);
    }

    @Override
    public void dispose(ItemDao itemDao) {
    }
}
