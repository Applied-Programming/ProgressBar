package progress.bar;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class ProgressBar extends JApplet {

    Container container = null;
    JButton start_button, stop_button;
    JTextField input_TextField, output_TextField;
    JProgressBar progress_bar = null;
    Timer timer = null;

    static int sum = 0;
    static int counter = 0;

    public void init() {
        // 1. Get the handle on the content pane and assign the grid layout.
        container = this.getContentPane();
        container.setLayout(new GridLayout(3, 1));

        // 2. Add a horizontal box to the container.
        Box horizontal_box1 = Box.createHorizontalBox();
        container.add(horizontal_box1);

        // 3. Add labels and input and output text fields
        //    to the horizontal box.
        horizontal_box1.add(Box.createHorizontalGlue());
        JLabel label1 = new JLabel("Sum of first ", JLabel.LEFT);
        label1.setFont(new Font("Dialog", Font.PLAIN, 15));
        horizontal_box1.add(label1);

        input_TextField = new JTextField("100", 4);
        horizontal_box1.add(input_TextField);

        JLabel label2 = new JLabel(" numbers is ", JLabel.LEFT);
        label2.setFont(new Font("Dialog", Font.PLAIN, 15));
        horizontal_box1.add(label2);

        output_TextField = new JTextField(10);
        horizontal_box1.add(output_TextField);
        horizontal_box1.add(Box.createHorizontalGlue());

        // 4. Add another horizontal box to the container.
        Box horizontal_box2 = Box.createHorizontalBox();
        container.add(horizontal_box2);

        // 5. Add Start and Stop buttons to the container.
        start_button = new JButton("Start");
        start_button.addActionListener(new ButtonListener());
        horizontal_box2.add(Box.createHorizontalGlue());
        horizontal_box2.add(start_button);
        horizontal_box2.add(Box.createHorizontalGlue());
        stop_button = new JButton("Stop");
        stop_button.addActionListener(new ButtonListener());
        horizontal_box2.add(Box.createHorizontalGlue());
        horizontal_box2.add(stop_button);
        horizontal_box2.add(Box.createHorizontalGlue());

        // 6. Create and add a progress bar to the remaining
        //    display area.
        progress_bar = new JProgressBar();
        progress_bar.setStringPainted(true);
        Border border = BorderFactory.createLineBorder(Color.red, 2);
        progress_bar.setBorder(border);
        progress_bar.setBackground(Color.white);
        progress_bar.setForeground(Color.blue);
        progress_bar.setMinimum(0);

        progress_bar.setMaximum(Integer.parseInt(input_TextField.getText()));
        container.add(progress_bar);

        // 7. Create a timer object.
        timer = new Timer(0, new TimerListener());
    }

    // 8. Timer listener that computes the sum of natural numbers,
    //    indicates the computation progress, and displays the result.
    class TimerListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (Integer.parseInt(input_TextField.getText()) > 0) {
                counter++;
                sum = sum + counter;
                progress_bar.setValue(counter);
                output_TextField.setText(Integer.toString(sum));
            } else {
                output_TextField.setText("0");
            }

            if (counter >= Integer.parseInt(input_TextField.getText())) {
                timer.stop();
            }
        }
    }

    // 9. Button listener that actually starts or stops the process.
    class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();

            if (button.getText() == "Start") {
                output_TextField.setText("");
                if (input_TextField.getText() != " ") {
                    progress_bar.setMaximum(Integer.parseInt(
                            input_TextField.getText()));
                    sum = 0;
                    counter = 0;
                    timer.start();
                }
            } else if (button.getText() == "Stop") {
                timer.stop();
                output_TextField.setText("");
                sum = 0;
                counter = 0;
                progress_bar.setValue(0);
            }
        }
    }
}
