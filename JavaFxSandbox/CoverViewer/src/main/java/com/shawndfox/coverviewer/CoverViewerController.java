package com.shawndfox.coverviewer;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CoverViewerController implements Initializable
{

   @FXML
   private ImageView coverImageView;

   @FXML
   private ListView<Book> booksListView;

   private final ObservableList<Book> books = FXCollections.observableArrayList();

   @Override
   public void initialize(URL url, ResourceBundle rb)
   {
      books.add(new Book("Android How to Program",
                         "/images/small/androidhtp.jpg", "/images/large/androidhtp.jpg"));
      books.add(new Book("C How to Program",
                         "/images/small/chtp.jpg", "/images/large/chtp.jpg"));
      books.add(new Book("C++ How to Program",
                         "/images/small/cpphtp.jpg", "/images/large/cpphtp.jpg"));
      books.add(new Book("Internet and World Wide Web How to Program",
                         "/images/small/iw3htp.jpg", "/images/large/iw3htp.jpg"));
      books.add(new Book("Java How to Program",
                         "/images/small/jhtp.jpg", "/images/large/jhtp.jpg"));
      books.add(new Book("Visual Basic How to Program",
                         "/images/small/vbhtp.jpg", "/images/large/vbhtp.jpg"));
      books.add(new Book("Visual C# How to Program",
                         "/images/small/vcshtp.jpg", "/images/large/vcshtp.jpg"));
      booksListView.setItems(books);
      
      // When the selection changes show the cover image
      booksListView.getSelectionModel().selectedItemProperty().addListener(
         (ObservableValue<? extends Book> ov, Book oldValue, Book newValue) -> {
            coverImageView.setImage(new Image(newValue.getLargeImage()));
         });
   }
}
