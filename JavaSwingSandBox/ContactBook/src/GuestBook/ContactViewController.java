package GuestBook;

import javax.swing.JOptionPane;

/**
 * This view controller mediates between the view and the data model.  It registers the
 * listeners that respond to user actions and updates the model when appropriate.
 * 
 * @author Shawn D. Fox
 */
public class ContactViewController {
   private ContactBookView view;
   private ContactBook contactBook;
   
   /**
    * Constructs the view controller which stores references to the view and 
    * the data model, and registers a listener so that new contacts can be
    * validated and stored.
    * 
    * @param theView
    * @param theContactBook 
    */
   public ContactViewController(ContactBookView theView, ContactBook theContactBook) {
      view = theView;
      contactBook = theContactBook;
      view.registerSubmitContactListener(e -> this.addContact());
   }
   
   /**
    * Add a valid contact into the data model, or display an error and return without adding it if the 
    * contact information is not valid.  The first name and last name are mandatory and the contact
    * must also be unique.
    */
   private void addContact() {
      String firstName = view.getFirstNameText();
      String lastName = view.getLastNameText();
      
      if(!Contact.validateName(firstName) || !Contact.validateName(lastName)) {
         JOptionPane.showMessageDialog(view, 
                 "The first and last name are mandatory fields!", 
                 "Invalid Contact!", 
                 JOptionPane.ERROR_MESSAGE);
         return;
      }
      
      // If contact is valid then it will be added to the book.
      try {
         Contact newContact = new Contact(firstName, lastName, view.getPhoneText(), view.getAddressText());
         contactBook.addContact(newContact);
      }
      catch(IllegalArgumentException e) {
         JOptionPane.showMessageDialog(view, 
                 e.getMessage(), 
                 "Contact already exists!", 
                 JOptionPane.ERROR_MESSAGE);
         return;
      }
      
      // Added successfully if this point is reached.  Clear the entry fields.
      view.clearContactInformation();
      view.refreshContacts(contactBook.toString());
   }
}
