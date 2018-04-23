package com.switchfully.vaadin.exercise_05_binding_beans.ui.components;

import com.switchfully.vaadin.domain.Accomodation;
import com.switchfully.vaadin.service.AccomodationService;
import com.switchfully.vaadin.service.CityService;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.List;

public class AccomodationAdmin extends CustomComponent {

    private Grid grid = new Grid();

    private AccomodationService accomodationService;
    private CityService cityService;
    private TextField filter;
    private BeanItemContainer<Accomodation> container;

    public AccomodationAdmin(AccomodationService accomodationService, CityService cityService) {
        this.accomodationService = accomodationService;
        this.cityService = cityService;

        List<Accomodation> accomodations = accomodationService.getAccomodations();
        populateGrid(accomodations);
        CssLayout filtering = createFilterComponent();

        // TODO Exercise 5: Add a 'New Accomodation' button.
        // TODO Exercise 5: Create an EditAccomodationForm and add it to the right of the grid to add a new accomodation.
        // TODO Exercise 5: When selecting an accomodation in the grid, load it in the EditAccomodationForm to update it.
        // TODO Exercise 5: Add a 'Delete' button to the form to delete an accomodation.
        // TODO Exercise 5: Add a 'Cancel' button to the form to close the form.
        // TODO Exercise 5 (Extra): Add ta DateField for creationDate to the form.

        HorizontalLayout toolbar = new HorizontalLayout(filtering);
        toolbar.setSpacing(true);

        VerticalLayout mainLayout = new VerticalLayout(toolbar, grid);
        mainLayout.setMargin(true);
        setCompositionRoot(mainLayout);
    }

    private CssLayout createFilterComponent() {
        filter = new TextField();
        filter.setInputPrompt("Filter by name...");
        filter.addTextChangeListener(e -> {
            container.removeAllContainerFilters();
            container.addContainerFilter(new SimpleStringFilter("name", e.getText(), true, false));
        });

        Button clearFilterTextBtn = new Button(FontAwesome.TIMES);
        clearFilterTextBtn.setDescription("Clear the current filter");
        clearFilterTextBtn.addClickListener(e -> {
            filter.clear();
            container.removeAllContainerFilters();
        });

        CssLayout filtering = new CssLayout();
        filtering.addComponents(filter, clearFilterTextBtn);
        filtering.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        return filtering;
    }

    private void populateGrid(List<Accomodation> accomodations) {
        container = new BeanItemContainer<>(Accomodation.class, accomodations);

        container.addNestedContainerProperty("city.name");

        grid.setColumns("name", "starRating", "city.name");

        grid.getColumn("city.name").setHeaderCaption("City");

        grid.setContainerDataSource(container);
    }

}
