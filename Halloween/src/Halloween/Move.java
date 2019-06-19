/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Halloween;

/**
 *
 * @author Justin
 */
public interface Move {
    
    public void jump();
    public void run();
    public void moveSideways();
    
    default public void sayHello(String who){
        System.out.println("Hello " + who + "!");
    }
    
    private void chop(){
        System.out.println("chop chop");
    }
}
