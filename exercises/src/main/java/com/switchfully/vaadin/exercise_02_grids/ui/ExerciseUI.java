package com.switchfully.vaadin.exercise_02_grids.ui;

import com.switchfully.vaadin.domain.Accomodation;
import com.switchfully.vaadin.service.AccomodationService;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringUI
public class ExerciseUI extends UI {

    private AccomodationService accomodationService;

    @Autowired
    public ExerciseUI(AccomodationService accomodationService) {
        this.accomodationService = accomodationService;
    }

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout mainLayout = new VerticalLayout();

        // TODO Exercise 2: Show the list of accomodations from accomodationService.getAccomodations() in a Grid.
        List<Accomodation> accomodations = accomodationService.getAccomodations();
        BeanItemContainer<Accomodation> container = new BeanItemContainer<Accomodation>(Accomodation.class, accomodations);
        Grid grid = new Grid(container);
        grid.removeColumn("id");
        grid.removeColumn("description");
        grid.removeColumn("imagePath");
        grid.removeColumn("numberOfRooms");
        grid.removeColumn("dateCreated");
        grid.removeColumn("persisted");
        grid.removeColumn("bookings");
        mainLayout.addComponent(grid);
//        grid.setColumnOrder("name", "description", "number of rooms", );
        // Use BeanItemContainer as the ContainerDataSource for the Grid.

        // Try to only show the following properties of an accomodation:
        // - Name
        // - Star Rating
        // - City Name

        mainLayout.setMargin(true);
        setContent(mainLayout);
    }

}