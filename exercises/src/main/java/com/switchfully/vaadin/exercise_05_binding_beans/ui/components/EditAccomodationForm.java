package com.switchfully.vaadin.exercise_05_binding_beans.ui.components;

import com.switchfully.vaadin.domain.Accomodation;
import com.switchfully.vaadin.domain.City;
import com.switchfully.vaadin.domain.StarRating;
import com.switchfully.vaadin.service.CityService;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.ArrayList;
import java.util.List;

import static com.switchfully.vaadin.domain.Accomodation.AccomodationBuilder.cloneAccomodation;

public class EditAccomodationForm extends FormLayout {
    private final CityService cityService;
    private Accomodation accomodation = Accomodation.AccomodationBuilder.accomodation().build();

    private TextField name = new TextField("Name");
    private NativeSelect city = new NativeSelect("City");
    private TextField numberOfRooms = new TextField("Number of rooms");
    private NativeSelect starRating = new NativeSelect("Star Rating");
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private Button cancel = new Button("Cancel");
    private List<AccomadationSavedListener> savedListeners = new ArrayList<>();
    private List<AccomadationDeletedListener> deletedListeners = new ArrayList<>();

    public EditAccomodationForm(CityService cityService) {
        this.cityService = cityService;

        name.setWidth("500px");
        name.setNullRepresentation("");

        city.setContainerDataSource(new BeanItemContainer<>(City.class, cityService.getCities()));
        city.setItemCaptionPropertyId("name");

        starRating.addItems(StarRating.values());

        save.addClickListener(e -> save());
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);

        cancel.addClickListener(e -> cancel());

        delete.addClickListener(e -> delete());
        delete.setStyleName(ValoTheme.BUTTON_DANGER);

        HorizontalLayout buttons = new HorizontalLayout(save, delete, cancel);
        setSizeUndefined();
        buttons.setSpacing(true);
        this.addComponents(name, city, numberOfRooms, starRating, buttons);
    }

    private void delete() {
        this.deletedListeners.forEach(listener -> listener.accomodationDeleted(accomodation));
    }

    public void cancel() {
        setVisible(false);
    }

    public void save(){
        this.savedListeners.forEach(listener -> listener.accomodationSaved(accomodation));
        setVisible(false);
    }

    public void setAccomodation(Accomodation acomodation){
        this.accomodation = cloneAccomodation(acomodation).build();
        BeanFieldGroup.bindFieldsUnbuffered(accomodation, this);
        setVisible(true);
        delete.setVisible(accomodation.isPersisted());
        name.selectAll();
    }

    public Button getSave() {
        return save;
    }

    public void addSavedAccomodationListener(AccomadationSavedListener accomadationSavedListener) {
        this.savedListeners.add(accomadationSavedListener);
    }

    public void addDeletedAccomodationListener(AccomadationDeletedListener accomadationDeletedListener){
        this.deletedListeners.add(accomadationDeletedListener);
    }
}
