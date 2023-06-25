import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class CanvasApp extends JFrame {
    private CanvasPanel canvasPanel;
    private Color selectedColor;
    private boolean isEraserMode;
    private int penWidth;

    public CanvasApp() {
        setTitle("Canvas App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        selectedColor = Color.BLACK;
        isEraserMode = false;
        penWidth = 1;

        canvasPanel = new CanvasPanel();
        getContentPane().add(canvasPanel, BorderLayout.CENTER);

        JPanel controlsPanel = new JPanel();

        JButton colorButton = new JButton("Color");
        colorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedColor = JColorChooser.showDialog(null, "Select a Color", selectedColor);
            }
        });
        controlsPanel.add(colorButton);

        JButton eraserButton = new JButton("Eraser");
        eraserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isEraserMode = true;
            }
        });
        controlsPanel.add(eraserButton);

        JLabel widthLabel = new JLabel("Pen Width:");
        controlsPanel.add(widthLabel);

        JSlider widthSlider = new JSlider(1, 10, 1);
        widthSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                penWidth = widthSlider.getValue();
            }
        });
        controlsPanel.add(widthSlider);

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                canvasPanel.clearCanvas();
            }
        });
        controlsPanel.add(clearButton);

        getContentPane().add(controlsPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CanvasApp app = new CanvasApp();
                app.setVisible(true);
            }
        });
    }

    private class CanvasPanel extends JPanel {
        private boolean isDrawing;
        private int prevX, prevY;

        public CanvasPanel() {
            setBackground(Color.WHITE);
            addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    isDrawing = true;
                    prevX = e.getX();
                    prevY = e.getY();
                }

                public void mouseReleased(MouseEvent e) {
                    isDrawing = false;
                }
            });

            addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent e) {
                    if (isDrawing) {
                        int x = e.getX();
                        int y = e.getY();

                        Graphics2D g2d = (Graphics2D) getGraphics();
                        g2d.setColor(isEraserMode ? getBackground() : selectedColor);
                        g2d.setStroke(new BasicStroke(penWidth));
                        g2d.drawLine(prevX, prevY, x, y);

                        prevX = x;
                        prevY = y;
                    }
                }
            });
        }

        public void clearCanvas() {
            Graphics g = getGraphics();
            g.setColor(getBackground());
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}
