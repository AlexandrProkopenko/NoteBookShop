
import controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NBSApp extends Application {


    private Stage mainStage;
    private MainController mainController;

    public void start(Stage primaryStage) throws Exception {
        Logger log = LoggerFactory.getLogger(NBSApp.class);
        log.info("hello world!");
        mainStage = primaryStage;
        mainStage.setTitle("NoteBookShop");
        mainStage.setResizable(false);
        FXMLLoader mainLoader = new FXMLLoader();
        mainLoader.setLocation(getClass().getResource("/fxml/main.fxml"));
        Parent root = mainLoader.load();
        mainStage.setScene(new Scene(root));
        mainController = mainLoader.getController();
        mainController.setMainStage(mainStage);
        mainStage.show();

    }
}
