package com.switchfully.vaadin.exercise_01_basic_layouts.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
public class ExerciseUI extends UI {

    @Autowired
    public ExerciseUI() {
    }

    @Override
    protected void init(VaadinRequest request) {
        // TODO Exercise 1: Using VerticalLayout and HorizontalLayout, create a button layout resembling the buttons of an old school cellphone.
        VerticalLayout content = new VerticalLayout();
        content.setWidth("500px");
        setContent(content);

        HorizontalLayout firstRow = new HorizontalLayout();
        content.addComponent(firstRow);
        HorizontalLayout secondRow = new HorizontalLayout();
        content.addComponent(secondRow);
        HorizontalLayout thirdRow = new HorizontalLayout();
        content.addComponent(thirdRow);
        HorizontalLayout fourthRow = new HorizontalLayout();
        content.addComponent(fourthRow);

        Button oneButton = new Button("1");
        Button twoButton = new Button("2");
        Button threeButton = new Button("3");
        firstRow.addComponent(oneButton);
        firstRow.addComponent(twoButton);
        firstRow.addComponent(threeButton);

        Button fourButton = new Button("4");
        Button fiveButton = new Button("5");
        Button sixButton = new Button("6");
        secondRow.addComponent(fourButton);
        secondRow.addComponent(fiveButton);
        secondRow.addComponent(sixButton);

        Button sevenButton = new Button("7");
        Button eightButton = new Button("8");
        Button nineButton = new Button("9");
        thirdRow.addComponent(sevenButton);
        thirdRow.addComponent(eightButton);
        thirdRow.addComponent(nineButton);

        Button starButton = new Button("*");
        Button zeroButton = new Button("0");
        Button hashButton = new Button("#");
        fourthRow.addComponent(starButton);
        fourthRow.addComponent(zeroButton);
        fourthRow.addComponent(hashButton);

        // Use the Button component to create the buttons:
        // Button one = new Button("1");

        // Don't forget to set your main layout using setContent(myLayout)

    }

}