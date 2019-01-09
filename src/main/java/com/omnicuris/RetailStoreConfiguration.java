package com.omnicuris;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import lombok.Getter;


@Getter
public class RetailStoreConfiguration extends Configuration {
    DataSourceFactory database;
}
