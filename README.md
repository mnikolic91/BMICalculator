# Java Swing BMI Calculator

## Introduction
This BMI Calculator is a desktop application developed in Java, employing Swing for the graphical user interface. It demonstrates the application of both the Strategy and Observer design patterns to create a dynamic, responsive UI that calculates and displays the user's Body Mass Index (BMI) based on inputted height and weight metrics. The Strategy pattern allows for flexible calculation methods, while the Observer pattern ensures the UI updates in real-time with the calculation results.

## Features
- **BMI Calculation**: Computes the Body Mass Index (BMI) using user-provided weight and height.
- **Design Pattern Integration**: Implements the Strategy pattern for calculation logic and the Observer pattern for UI updates, enhancing flexibility and responsiveness.
- **Swing GUI**: Utilizes Java Swing to provide an intuitive and user-friendly interface.
- **Unit Conversion**: Supports only kilograms and centimeters.
- **Real-Time Updates**: Dynamically updates the BMI value as user inputs change, thanks to the Observer pattern.
- **Validation**: Includes input validation to ensure that only valid numbers are processed.

## Technologies
- **Programming Language**: Java
- **GUI Framework**: Java Swing
- **Design Patterns**: Strategy and Observer Patterns

## How It Works
- The **Strategy pattern** is used to select the appropriate save and load file strategy.
- The **Observer pattern** is employed to update the BMI value displayed on the GUI whenever the user's weight or height input changes.

## Setup and Installation
1. **Prerequisites**: Ensure Java (JDK 8 or later) is installed on your system.
2. **Clone the Repository**: `git clone [repository-url]`
3. **Compile the Code**: Navigate to the cloned directory and compile the code using your IDE or the command line (`javac`).
4. **Run the Application**: Launch the application from your IDE or use the `java` command to run the compiled classes.

## How to Use
1. Start the application to open the GUI.
2. Enter your height and weight using the preferred units (metric or imperial).
3. The BMI value will automatically update as you type, reflecting your current inputs.
4. Switch between unit systems at any time to see your BMI in different units.

## Contribution
Contributions to improve the BMI Calculator are warmly welcomed. Feel free to fork the project, make enhancements, fix bugs, or add new features. Please submit a pull request with your changes for review.
