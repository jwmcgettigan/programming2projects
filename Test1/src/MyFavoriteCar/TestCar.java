/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyFavoriteCar;

/**
 *
 * @author Justin McGettigan
 */
public class TestCar {
    public static void main(String[] args) {
        Car car = new Car(1997, "Red", "Toyota", 80);
        
        System.out.println("Speed: " + car.getSpeed());
        System.out.println("Accelerate!");
        for(int i = 0; i < 5; i++){
            car.accelerate();
            System.out.println("Speed: " + car.getSpeed());
        }
        System.out.println("Brake!");
        for(int i = 0; i < 5; i++){
            car.brake();
            System.out.println("Speed: " + car.getSpeed());
        }
    }
}
