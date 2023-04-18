/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Apple_Cart;

import javafx.scene.layout.VBox;

/**
 *
 * @author norvinholness
 */
public interface User {

    public VBox getLayout();
    
    /**
     * TODO
     * @return 
     */
    default public String getUserName()
    {
        return "Apple Cart User";
    }
}
