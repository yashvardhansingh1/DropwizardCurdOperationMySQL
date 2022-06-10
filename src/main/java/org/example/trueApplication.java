package org.example;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.example.resources.EmployeeResource;
import org.example.service.EmployeeService;
import org.skife.jdbi.v2.DBI;

import javax.sql.DataSource;

public class trueApplication extends Application<trueConfiguration> {

    private static final String SQL = "sql";
    private static final String DROPWIZARD_MYSQL_SERVICE = "Dropwizard MySQL Service";

    public static void main(final String[] args) throws Exception {
        new trueApplication().run(args);
    }

    @Override
    public String getName() {
        return "true";
    }

    @Override
    public void initialize(final Bootstrap<trueConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final trueConfiguration config,
                    final Environment environment) {
        // TODO: implement application
        final DataSource dataSource =
                config.getDataSourceFactory().build(environment.metrics(), SQL);
        DBI dbi = new DBI(dataSource);
        environment.jersey().register(new EmployeeResource(dbi.onDemand(EmployeeService.class)));
    }

}
