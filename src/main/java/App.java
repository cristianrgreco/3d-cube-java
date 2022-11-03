public class App {

  public static void main(String[] args) {
    var world = new World();
    var window = new Window("3D Cube", world);
    window.addComponent(new Cube());
    window.show();
  }
}
