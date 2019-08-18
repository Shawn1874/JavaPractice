
package com.shawndfox.coverviewer;

import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Shawn D. Fox
 */
public class ImageTextCell extends ListCell<Book>
{
   private VBox vbox = new VBox(8.0);
   private ImageView thumbImageView = new ImageView(); 
   private Label label = new Label();
   
   public ImageTextCell() {
      vbox.setAlignment(Pos.CENTER);
      
      thumbImageView.setPreserveRatio(true);
      thumbImageView.setFitHeight(100.0);
      vbox.getChildren().add(thumbImageView);
      
      label.setWrapText(true);
      label.setTextAlignment(TextAlignment.CENTER);
      vbox.getChildren().add(label);
      
      setPrefWidth(USE_PREF_SIZE);
   }
   
   @Override
   protected void updateItem(Book item, boolean empty) {
      super.updateItem(item, empty);
      
      if(empty || item == null) {
         setGraphic(null);
      }
      else {
         thumbImageView.setImage(item.getThumbImage());
         label.setText(item.getTitle());
         setGraphic(vbox);
      }
   }
}
