import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Controls {

  private final Cube cube;
  private final JPanel jPanel;

  public Controls(Cube cube) {
    this.cube = cube;

    this.jPanel = new JPanel();

    this.jPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

    var layout = new GridBagLayout();
    this.jPanel.setLayout(layout);

    scaleControls(0, layout);
    rotationVelocity(1, layout);
    enabledRotations(2, layout);
    positionX(3, layout);
    positionY(4, layout);
    positionZ(5, layout);
  }

  private void scaleControls(int y, GridBagLayout layout) {
    var c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridy = y;

    c.gridx = 0;
    var label = new JLabel("Scale");
    layout.setConstraints(label, c);
    this.jPanel.add(label);

    c.gridx = 1;
    c.gridwidth = 3;
    var slider = new JSlider(0, 300, (int) cube.getScale());
    slider.addChangeListener(e -> cube.setScale(((JSlider) e.getSource()).getValue()));
    layout.setConstraints(slider, c);
    this.jPanel.add(slider);
  }

  private void rotationVelocity(int y, GridBagLayout layout) {
    var c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridy = y;

    c.gridx = 0;
    var label = new JLabel("Rotation velocity");
    layout.setConstraints(label, c);
    this.jPanel.add(label);

    c.gridx = 1;
    c.gridwidth = 3;
    var slider = new JSlider(0, 500, (int) cube.getRotationVelocity());
    slider.addChangeListener(e -> cube.setRotationVelocity(((JSlider) e.getSource()).getValue()));
    layout.setConstraints(slider, c);
    this.jPanel.add(slider);
  }

  private void enabledRotations(int y, GridBagLayout layout) {
    var c = new GridBagConstraints();
    c.gridy = y;
    c.weightx = 1;
    c.anchor = GridBagConstraints.LAST_LINE_END;

    c.gridx = 1;
    var xCheckBox = new JCheckBox("X rotation", true);
    xCheckBox.addChangeListener(e -> cube.setRotationX(((JCheckBox) e.getSource()).isSelected()));
    layout.setConstraints(xCheckBox, c);
    this.jPanel.add(xCheckBox);

    c.gridx = 2;
    var yCheckBox = new JCheckBox("Y rotation", true);
    yCheckBox.addChangeListener(e -> cube.setRotationY(((JCheckBox) e.getSource()).isSelected()));
    layout.setConstraints(yCheckBox, c);
    this.jPanel.add(yCheckBox);

    c.gridx = 3;
    var zCheckBox = new JCheckBox("Z rotation", true);
    zCheckBox.addChangeListener(e -> cube.setRotationZ(((JCheckBox) e.getSource()).isSelected()));
    layout.setConstraints(zCheckBox, c);
    this.jPanel.add(zCheckBox);
  }

  private void positionX(int y, GridBagLayout layout) {
    var c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridy = y;

    c.gridx = 0;
    var label = new JLabel("Position X");
    layout.setConstraints(label, c);
    this.jPanel.add(label);

    c.gridx = 1;
    c.gridwidth = 3;
    var slider = new JSlider(-200, 800, (int) cube.getPosition().x());
    slider.addChangeListener(e -> cube.setPositionX(((JSlider) e.getSource()).getValue()));
    layout.setConstraints(slider, c);
    this.jPanel.add(slider);
  }

  private void positionY(int y, GridBagLayout layout) {
    var c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridy = y;

    c.gridx = 0;
    var label = new JLabel("Position Y");
    layout.setConstraints(label, c);
    this.jPanel.add(label);

    c.gridx = 1;
    c.gridwidth = 3;
    var slider = new JSlider(-200, 800, (int) cube.getPosition().y());
    slider.addChangeListener(e -> cube.setPositionY(((JSlider) e.getSource()).getValue()));
    layout.setConstraints(slider, c);
    this.jPanel.add(slider);
  }

  private void positionZ(int y, GridBagLayout layout) {
    var c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridy = y;

    c.gridx = 0;
    var label = new JLabel("Position Z");
    layout.setConstraints(label, c);
    this.jPanel.add(label);

    c.gridx = 1;
    c.gridwidth = 3;
    var slider = new JSlider(-300, -100, (int) cube.getPosition().z() * 100);
    slider.addChangeListener(e -> cube.setPositionZ(((JSlider) e.getSource()).getValue() / 100d));
    layout.setConstraints(slider, c);
    this.jPanel.add(slider);
  }

  public JPanel getJPanel() {
    return jPanel;
  }
}
