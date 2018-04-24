package com.switchfully.vaadin.exercise_03_live_filtering.ui;

import com.switchfully.vaadin.domain.Accomodation;
import com.switchfully.vaadin.service.AccomodationService;
import com.vaadin.annotations.Theme;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@SpringUI
@Theme("valo")
public class ExerciseUI extends UI {

    private Grid grid = new Grid();

    private AccomodationService accomodationService;

    @Autowired
    public ExerciseUI(AccomodationService accomodationService) {
        this.accomodationService = accomodationService;
    }

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout mainLayout = new VerticalLayout(grid);

        BeanItemContainer<Accomodation> container =
                new BeanItemContainer<>(Accomodation.class, accomodationService.getAccomodations());

        List<Accomodation> filteredAccomodations = accomodationService.getAccomodations().stream()
                .filter(a -> a.getName().contains(""))
                .collect(toList());
        BeanItemContainer<Accomodation> filteredContainer =
                new BeanItemContainer<Accomodation>(Accomodation.class, filteredAccomodations);

        container.addNestedContainerProperty("city.name");

        grid.setColumns("name", "starRating", "city.name");

        grid.getColumn("city.name").setHeaderCaption("City");

        grid.setContainerDataSource(container);

        // TODO Exercise 3: Add a filter TextField to the top of the grid to filter the list of accomodations by name.
        TextField filter = new TextField("Filter by name...");
        filter.addTextChangeListener(e -> e.getText());

        // TODO Exercise 3: Add a button next to the filter TextField to clear the filter.
        Button xButton = new Button("X");
//        xButton.addClickListener(filter.clear());
        HorizontalLayout searchField = new HorizontalLayout(filter, xButton);

        mainLayout.setMargin(true);
        setContent(mainLayout);
    }

}