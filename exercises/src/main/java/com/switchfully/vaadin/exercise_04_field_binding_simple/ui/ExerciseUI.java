package com.switchfully.vaadin.exercise_04_field_binding_simple.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.data.Property;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.event.FieldEvents;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import static com.vaadin.ui.AbstractTextField.TextChangeEventMode.EAGER;
import static com.vaadin.ui.AbstractTextField.TextChangeEventMode.LAZY;

@SpringUI
@Theme("valo")
public class ExerciseUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        // TODO Exercise 4: Create a TextField and a Label, both bound to the same 'name' Property.
        // TODO Exercise 4: Add a button to commit the field.
        // TODO Exercise 4: Clicking the button should update the Label with the value in the TextField.

        // TODO Exercise 4 (Extra): Add a checkbox to hide the button and make the TextField auto-commit.

        Property myProperty = new ObjectProperty<String>("Radisson");
        TextField field = new TextField("Name", myProperty);

        Label label = new Label(myProperty);
        Button updateButton = new Button("Update");
        updateButton.addClickListener(e -> field.commit());
        CheckBox autoUpdate = new CheckBox("Auto commit?");
        autoUpdate.addValueChangeListener(e -> {
            updateButton.setVisible(!autoUpdate.getValue());
            field.addTextChangeListener(event -> myProperty.setValue(event.getText()));
        });


        VerticalLayout mainLayout = new VerticalLayout(field, label, autoUpdate, updateButton);
        mainLayout.setMargin(true);
        setContent(mainLayout);
    }

}