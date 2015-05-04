package de.hsweingarten.dapro.application.guice;

import com.google.inject.AbstractModule;
import de.hsweingarten.dapro.application.translations.ITranslationProvider;
import de.hsweingarten.dapro.application.translations.ResourceBundleTranslationProvider;
import de.hsweingarten.dapro.command.LoadCarsCommand;
import de.hsweingarten.dapro.command.LoadColumnValuesCommand;
import de.hsweingarten.dapro.service.DatabaseService;
import de.hsweingarten.dapro.service.IDatabaseService;
import de.hsweingarten.dapro.service.IMySQLService;
import de.hsweingarten.dapro.service.MySQLService;
import de.hsweingarten.dapro.view.overview.CarRentalOverviewMediator;
import de.hsweingarten.dapro.view.overview.CarRentalOverviewView;
import de.hsweingarten.dapro.view.overview.ICarRentalOverviewMediator;
import de.hsweingarten.dapro.view.overview.ICarRentalOverviewView;

import javax.inject.Singleton;

/**
 * Guice module which maps the injections.
 */
public class CarRentalManagerModule extends AbstractModule {

    /**
     * Initializes the Mapping
     */
    @Override
    protected void configure() {
        bind(CarRentalManagerModule.class).toInstance(this);

        mapViews();
        mapMediators();
        mapCommands();
        mapServices();
        mapInfrastructure();
    }

    /**
     * Maps the Views
     */
    private void mapViews() {
        bind(ICarRentalOverviewView.class).to(CarRentalOverviewView.class);
    }

    /**
     * Maps the Mediators
     */
    private void mapMediators() {
        bind(ICarRentalOverviewMediator.class).to(CarRentalOverviewMediator.class);
    }

    /**
     * Maps the Commands
     */
    private void mapCommands() {
        bind(ICommandProvider.class).to(CommandProvider.class);

        bind(LoadCarsCommand.class);
        bind(LoadColumnValuesCommand.class);
    }

    /**
     * Maps the Services
     */
    private void mapServices() {
        bind(IDatabaseService.class).to(DatabaseService.class);
        bind(IMySQLService.class).to(MySQLService.class).in(Singleton.class);
    }

    /**
     * Maps the Infrastructures
     */
    private void mapInfrastructure() {
        bind(ITranslationProvider.class).to(ResourceBundleTranslationProvider.class).in(Singleton.class);
    }
}
