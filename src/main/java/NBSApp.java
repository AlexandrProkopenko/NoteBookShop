import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NBSApp extends Application {

    private Stage mainStage;

    public void start(Stage primaryStage) throws Exception {

        mainStage = primaryStage;
        mainStage.setTitle("NoteBookShop");
        mainStage.setResizable(false);
        Parent root = FXMLLoader.load(
                getClass().getResource("/view/main.fxml")
        );
        mainStage.setScene(new Scene(root));
        mainStage.show();

    }
}
