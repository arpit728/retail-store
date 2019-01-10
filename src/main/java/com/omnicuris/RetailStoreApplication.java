package com.omnicuris;

import com.omnicuris.injection.RetailStoreModule;
import com.omnicuris.resources.ItemResource;
import com.omnicuris.resources.OrderResource;
import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Jdbi;

public class RetailStoreApplication extends Application<RetailStoreConfiguration> {

    public static void main(final String[] args) throws Exception {
        new RetailStoreApplication().run(args);
    }

    @Override
    public String getName() {
        return "RetailStore";
    }

    @Override
    public void initialize(final Bootstrap<RetailStoreConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<RetailStoreConfiguration>() {
            @Override
            public PooledDataSourceFactory getDataSourceFactory(RetailStoreConfiguration configuration) {
                return configuration.getDatabase();
            }
        });
        // TODO: application initialization
    }

    @Override
    public void run(final RetailStoreConfiguration configuration,
                    final Environment environment) {
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDatabase(), "retail-store-db");
        environment.jersey().register(ItemResource.class);
        environment.jersey().register(OrderResource.class);
        environment.jersey().getResourceConfig().register(new RetailStoreModule(environment, jdbi, configuration));
    }

}
