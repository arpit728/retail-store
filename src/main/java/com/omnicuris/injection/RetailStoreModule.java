package com.omnicuris.injection;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.omnicuris.RetailStoreConfiguration;
import com.omnicuris.core.ItemService;
import com.omnicuris.core.OrderService;
import com.omnicuris.db.ItemDao;
import com.omnicuris.db.OrderDao;
import com.omnicuris.injection.factory.ItemDaoFactory;
import com.omnicuris.injection.factory.OrderDaoFactory;
import io.dropwizard.setup.Environment;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.jdbi.v3.core.Jdbi;

import javax.inject.Singleton;

/**
 * Created by bugkiller on 08/01/19.
 */
public class RetailStoreModule extends AbstractBinder {

    private final Environment environment;

    private final Jdbi database;

    private final RetailStoreConfiguration configuration;

    public RetailStoreModule(Environment environment, Jdbi jdbi, RetailStoreConfiguration configuration) {
        this.environment = environment;
        this.database = jdbi;
        this.configuration = configuration;
    }

    @Override
    protected void configure() {
        bind(environment).to(Environment.class);
        bind(environment.getObjectMapper()).to(ObjectMapper.class);
        bind(configuration).to(RetailStoreConfiguration.class);
        bind(database).to(Jdbi.class);

        bindFactory(OrderDaoFactory.class).to(OrderDao.class).in(Singleton.class);
        bindFactory(ItemDaoFactory.class).to(ItemDao.class).in(Singleton.class);
        bind(ItemService.class).to(ItemService.class).in(Singleton.class);
        bind(OrderService.class).to(OrderService.class).in(Singleton.class);
    }
}
