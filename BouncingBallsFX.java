import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;

public class BouncingBallsFX extends Application {

    private BorderPane pane = new BorderPane();
    private Pane p = new Pane();
    private FlowPane flow = new FlowPane();
    private Button addCircle = new Button("Add Circle");
    private Button playCircle = new Button("Play");
    private Button pause = new Button("Pause");
    private Button subtract = new Button("Subtract Circle");
    private ArrayList<NewCircle>circles = new ArrayList<NewCircle>();

    public static void main(String[] args){
        launch(args);
    }

    public void start(Stage stage){


        Scene scene = new Scene(pane);
        pane.setPrefSize(900, 900);
        pane.setStyle("-fx-background-color: black;");
        pane.setCenter(p);
       

        flow.setAlignment(Pos.CENTER);
        flow.setHgap(5);
        flow.getChildren().addAll(playCircle, pause, addCircle, subtract);
        pane.setBottom(flow);

        stage.setScene(scene);
        stage.show();
         
        addCircle.setOnMouseClicked(event -> {
            circles.add(new NewCircle());

            p.getChildren().add(circles.get(circles.size() - 1));
        });

        subtract.setOnMouseClicked(event -> {
            if(circles.size() > 0){
               circles.remove(circles.get(circles.size() -1));
               p.getChildren().remove(circles.size() );
            }
        });
        
        playCircle.setOnMouseClicked(event -> {
            for(int i = 0; i < circles.size(); i++)
                circles.get(i).play();
        });
        
        

        pause.setOnMouseClicked(event -> {
            for(int i = 0; i < circles.size(); i++)
                circles.get(i).pause();
        });
    }

    class NewCircle extends Circle {

        private PathTransition pt = new PathTransition(Duration.seconds(5), new Line((int)(Math.random() * pane.getWidth()), 0, (int)(Math.random() * pane.getWidth()), pane.getHeight() - 75), this);

        NewCircle(){
            super(25);
            this.setFill(Color.rgb((int)(Math.random() * 256), (int)(Math.random() * 256), (int)(Math.random() * 256)));
            pt.setAutoReverse(true);
            pt.setCycleCount(Animation.INDEFINITE);
            pt.play();
        }

        public void pause(){
            pt.pause();
        }

        public void play(){
            pt.play();
        }
    }

}
