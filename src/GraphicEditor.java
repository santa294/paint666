import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GraphicEditor extends JFrame {

    private int currentX, currentY, previousX, previousY;
    private Color brushColor;
    private int brushThickness;

    public GraphicEditor() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Graphic Editor");
        setSize(800, 600);

        brushColor = Color.BLACK; // исходный цвет кисти
        brushThickness = 1; // исходная толщина кисти

        // Обработчик событий мыши
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                previousX = e.getX();
                previousY = e.getY();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                currentX = e.getX();
                currentY = e.getY();

                // Рисование линии между предыдущими и текущими координатами
                Graphics graphics = getGraphics();
                graphics.setColor(brushColor);
                Graphics2D g2d = (Graphics2D) graphics;
                g2d.setStroke(new BasicStroke(brushThickness));
                graphics.drawLine(previousX, previousY, currentX, currentY);

                // Обновление предыдущих координат
                previousX = currentX;
                previousY = currentY;
            }
        };

        // Панель с кнопками для выбора цвета кисти
        JPanel colorPanel = new JPanel();
        colorPanel.setLayout(new FlowLayout());

        JButton yellowButton = new JButton();
        yellowButton.setBackground(Color.YELLOW);
        yellowButton.setPreferredSize(new Dimension(30, 30));
        yellowButton.addActionListener(e -> brushColor = Color.YELLOW);

        JButton greenButton = new JButton();
        greenButton.setBackground(Color.GREEN);
        greenButton.setPreferredSize(new Dimension(30, 30));
        greenButton.addActionListener(e -> brushColor = Color.GREEN);

        JButton blackButton = new JButton();
        blackButton.setBackground(Color.BLACK);
        blackButton.setPreferredSize(new Dimension(30, 30));
        blackButton.addActionListener(e -> brushColor = Color.BLACK);

        JButton redButton = new JButton();
        redButton.setBackground(Color.RED);
        redButton.setPreferredSize(new Dimension(30, 30));
        redButton.addActionListener(e -> brushColor = Color.RED);

        JButton blueButton = new JButton();
        blueButton.setBackground(Color.BLUE);
        blueButton.setPreferredSize(new Dimension(30, 30));
        blueButton.addActionListener(e -> brushColor = Color.BLUE);

        JButton orangeButton = new JButton();
        orangeButton.setBackground(Color.ORANGE);
        orangeButton.setPreferredSize(new Dimension(30, 30));
        orangeButton.addActionListener(e -> brushColor = Color.ORANGE);

        colorPanel.add(yellowButton);
        colorPanel.add(greenButton);
        colorPanel.add(blackButton);
        colorPanel.add(redButton);
        colorPanel.add(blueButton);
        colorPanel.add(orangeButton);

        // Панель с ползунком для выбора толщины кисти
        JPanel thicknessPanel = new JPanel();
        thicknessPanel.setLayout(new FlowLayout());

        JSlider thicknessSlider = new JSlider(1, 10, 1);
        thicknessSlider.setPreferredSize(new Dimension(150, 30));
        thicknessSlider.addChangeListener(e -> brushThickness = thicknessSlider.getValue());

        thicknessPanel.add(new JLabel("Толщина кисти: "));
        thicknessPanel.add(thicknessSlider);

        // Кнопка для очистки рисунка
        JButton clearButton = new JButton("Очистить");
        clearButton.addActionListener(e -> {
            Graphics graphics = getGraphics();
            graphics.clearRect(0, 0, getWidth(), getHeight());
        });

        // Размещение компонентов в окне
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        contentPane.add(colorPanel, BorderLayout.NORTH);
        contentPane.add(thicknessPanel, BorderLayout.SOUTH);
        contentPane.add(clearButton, BorderLayout.WEST);

        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            GraphicEditor graphicEditor = new GraphicEditor();
            graphicEditor.setVisible(true);
        });
    }
}