module snakeladder {
    requires javafx.controls;
    requires javafx.fxml;

    opens snakeladder to javafx.fxml;
    exports snakeladder;
}
