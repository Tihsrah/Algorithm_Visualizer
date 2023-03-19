import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.*;

public class InsertionSort extends JPanel {
    private static final long serialVersionUID = 1L;
    private final int WIDTH = 1000, HEIGHT = WIDTH * 9 / 16;
    private final int SIZE = 200;
    private final float BAR_WIDTH = (float)WIDTH / SIZE;
    private float[] bar_height = new float[SIZE];
    private SwingWorker<Void, Void> shuffler, sorter;
    private int current_index, traversing_index;

    static boolean click;
    static boolean stopped;

    private InsertionSort() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        JButton reset=new JButton("Reset");
        reset.setBounds(30,40,80,50);
        this.add(reset);

        click=true;
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (click==true){
                    invoke();
                    click=false;
                }else{
                    invoke();
                    click=true;
                }
            }
        });

        JButton stop=new JButton("Stop");
        stop.setBounds(60,40,80,50);
        this.add(stop);
        stopped=false;
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (stopped==true){
//                    initBarHeight();
                    System.out.println("stopped clicked");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    stopped=false;
                }else{
                    stopped=true;
                }
            }
        });

    }
    public void invoke(){
        initBarHeight();
        initSorter();
        initShuffler();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.CYAN);
        Rectangle2D.Float bar;
//        i * BAR_WIDTH, HEIGHT - bar_height[i], BAR_WIDTH, bar_height[i]
        for(int i = 0; i < SIZE; i++) {
            bar = new Rectangle2D.Float(i * BAR_WIDTH, 0, BAR_WIDTH, bar_height[i]);
            g2d.fill(bar);
        }

        g2d.setColor(Color.RED);
        bar = new Rectangle2D.Float(current_index * BAR_WIDTH,
                0,
                BAR_WIDTH,
                bar_height[current_index]);
        g2d.fill(bar);

        g2d.setColor(Color.GREEN);
        bar = new Rectangle2D.Float(traversing_index * BAR_WIDTH,
                0,
                BAR_WIDTH,
                bar_height[traversing_index]);
        g2d.fill(bar);
    }

    private void initSorter() {
        sorter = new SwingWorker<>() {
            @Override
            public Void doInBackground() throws InterruptedException {
                for(current_index = 1; current_index < SIZE; current_index++) {
                    traversing_index = current_index;
                    while(traversing_index > 0 &&
                            bar_height[traversing_index] < bar_height[traversing_index - 1]) {

                        swap(traversing_index, traversing_index - 1);
                        traversing_index--;

                        Thread.sleep(1);
                        repaint();
                    }
                }
                current_index = 0;
                traversing_index = 0;

                return null;
            }
        };
    }

    private void initShuffler() {
        shuffler = new SwingWorker<>() {
            @Override
            public Void doInBackground() throws InterruptedException {
                int middle = SIZE / 2;
                for(int i = 0, j = middle; i < middle; i++, j++) {
                    int random_index = new Random().nextInt(SIZE);
                    swap(i, random_index);

                    random_index = new Random().nextInt(SIZE);
                    swap(j, random_index);

                    Thread.sleep(10);
                    repaint();
                }

                return null;
            }

            @Override
            public void done() {
                super.done();
                sorter.execute();
            }
        };
        shuffler.execute();
    }

    private void initBarHeight() {
        float interval = (float)HEIGHT / SIZE;
        for(int i = 0; i < SIZE; i++)
            bar_height[i] = i * interval;
    }

    private void swap(int indexA, int indexB) {
        float temp = bar_height[indexA];
        bar_height[indexA] = bar_height[indexB];
        bar_height[indexB] = temp;
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Insertion Sort Visualizer");
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new InsertionSort());
            frame.validate();
            frame.pack();
            frame.setLocationRelativeTo(null);


            frame.setVisible(true);
        });
    }
}