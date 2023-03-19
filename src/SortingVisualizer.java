//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.geom.Rectangle2D;
//import java.util.Random;
//
//import javax.swing.*;
//
//public class SortingVisualizer extends JPanel {
//    private static final long serialVersionUID = 1L;
//    private final int WIDTH = 1000, HEIGHT = WIDTH * 9 / 16;
//    private final int SIZE = 200;
//    private final float BAR_WIDTH = (float) WIDTH / SIZE;
//    private float[] bar_height = new float[SIZE];
//    private SwingWorker<Void, Void> shuffler, sorter;
//    private int current_index, traversing_index;
//
//    static boolean click;
//    static boolean stopped;
//
//    private SortingVisualizer() {
//        setBackground(Color.BLACK);
//        setPreferredSize(new Dimension(WIDTH, HEIGHT));
//
//        JButton reset = new JButton("Reset");
//        this.add(reset);
//        reset.setLocation(30, 40);
//        reset.setSize(80, 50);
//
//        click = true;
//        reset.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (click == true) {
//                    String loopCode = JOptionPane.showInputDialog("Enter loop code (e.g. i<5):");
//                    invoke(loopCode);
//                    click = false;
//                } else {
//                    invoke("");
//                    click = true;
//                }
//            }
//        });
//
//        JButton stop = new JButton("Stop");
//        stop.setBounds(60, 40, 80, 50);
//        this.add(stop);
//        stopped = false;
//        stop.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                if (stopped == true) {
//                    System.out.println("stopped clicked");
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException ex) {
//                        throw new RuntimeException(ex);
//                    }
//                    stopped = false;
//                } else {
//                    stopped = true;
//                }
//            }
//        });
//    }
//
//    public void invoke(String loopCode) {
//        initBarHeight();
//        initSorter(loopCode);
//        initShuffler();
//    }
//
//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.setColor(Color.CYAN);
//        Rectangle2D.Float bar;
////        i * BAR_WIDTH, HEIGHT - bar_height[i], BAR_WIDTH, bar_height[i]
//        for (int i = 0; i < SIZE; i++) {
//            bar = new Rectangle2D.Float(i * BAR_WIDTH, 0, BAR_WIDTH, bar_height[i]);
//            g2d.fill(bar);
//        }
//
//        g2d.setColor(Color.RED);
//        bar = new Rectangle2D.Float(current_index * BAR_WIDTH,
//                0,
//                BAR_WIDTH,
//                bar_height[current_index]);
//        g2d.fill(bar);
//
//        g2d.setColor(Color.GREEN);
//        bar = new Rectangle2D.Float(traversing_index * BAR_WIDTH,
//                0,
//                BAR_WIDTH,
//                bar_height[traversing_index]);
//        g2d.fill(bar);
//    }
//
//    private void initSorter(String loopCode) {
//        sorter = new SwingWorker<>() {
//            @Override
//            public Void doInBackground() throws InterruptedException {
//                int loopLimit = 0;
//                try {
//                    loopLimit = Integer.parseInt(loopCode);
//                } catch (NumberFormatException e) {
//                    System.err.println("Invalid loop condition: " + loopCode);
//                }
//                for (int i = 1; i < SIZE; i++) {
//                    current_index = i;
//                    traversing_index = i - 1;
//                    float key = bar_height[i];
//                    while (traversing_index >= 0 && bar_height[traversing_index] > key) {
//                        bar_height[traversing_index + 1] = bar_height[traversing_index];
//                        traversing_index--;
//                        repaint();
//                        Thread.sleep(50);
//                        if (stopped == true) {
//                            return null;
//                        }
//                    }
//                    bar_height[traversing_index + 1] = key;
//                    repaint();
//                    Thread.sleep(50);
//                    if (stopped == true) {
//                        return null;
//                    }
//                }
//                return null;
//            }
//
//            @Override
//            public void done() {
//                for (int i = 0; i < SIZE; i++) {
//                    bar_height[i] = (float) HEIGHT / SIZE * (i + 1);
//                }
//                repaint();
//            }
//        };
//    }
//
//    private void initShuffler() {
//        shuffler = new SwingWorker<>() {
//            @Override
//            protected Void doInBackground() throws Exception {
//                Random random = new Random();
//                for (int i = 0; i < SIZE; i++) {
//                    int randomIndexToSwap = random.nextInt(bar_height.length);
//                    float temp = bar_height[randomIndexToSwap];
//                    bar_height[randomIndexToSwap] = bar_height[i];
//                    bar_height[i] = temp;
//                    repaint();
//                    Thread.sleep(50);
//                    if (stopped == true) {
//                        return null;
//                    }
//                }
//                return null;
//            }
//
//            @Override
//            protected void done() {
//                super.done();
//                sorter.execute();
//            }
//        };
//        shuffler.execute();
//    }
//
//    private void initBarHeight() {
//        for (int i = 0; i < SIZE; i++) {
//            bar_height[i] = (float) HEIGHT / SIZE * (i + 1);
//        }
//    }
//
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Insertion Sort Visualization");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setResizable(false);
//        frame.add(new SortingVisualizer());
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//    }
//}


import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.swing.*;


public class SortingVisualizer extends JPanel {
    private static final long serialVersionUID = 1L;
    private final int WIDTH = 1000, HEIGHT = WIDTH * 9 / 16;
    private final int SIZE = 200;
    private final float BAR_WIDTH = (float) WIDTH / SIZE;
    private float[] bar_height = new float[SIZE];
    private SwingWorker<Void, Void> shuffler, sorter;
    private int current_index, traversing_index;
    private boolean isSorting;

    private SortingVisualizer() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        JButton sortBtn = new JButton("Sort");
        sortBtn.setBounds(20, 20, 100, 30);
        sortBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isSorting) {
                    String code = JOptionPane.showInputDialog("Enter your sorting algorithm code:");
                    if (code != null && !code.isEmpty()) {
                        startSorting(code);
                    }
                }
            }
        });
        this.add(sortBtn);

        JButton resetBtn = new JButton("Reset");
        resetBtn.setBounds(140, 20, 100, 30);
        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
        this.add(resetBtn);
    }

    private void startSorting(String code) {
        isSorting = true;
        initBarHeight();
        initSorter(code);
        initShuffler();
        sorter.execute();
    }

    private void reset() {
        if (isSorting) {
            sorter.cancel(true);
        }
        isSorting = false;
        initBarHeight();
        repaint();
    }

    private void initSorter(String code) {
        sorter = new SwingWorker<>() {
            @Override
            public Void doInBackground() throws InterruptedException {
                String sortingCode = "void sort(float[] arr) {\n" + code + "\n}";
                System.out.print(code);
                Sorter sorter = new Sorter(sortingCode);
                try {
                    sorter.sort(bar_height);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                isSorting = false;
                return null;
            }

            @Override
            protected void done() {
                isSorting = false;
            }
        };
    }

    private void initShuffler() {
        shuffler = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                Random random = new Random();
                for (int i = 0; i < SIZE - 1; i++) {
                    int j = random.nextInt(SIZE - i - 1) + i + 1;
                    float temp = bar_height[i];
                    bar_height[i] = bar_height[j];
                    bar_height[j] = temp;
                    Thread.sleep(10);
                    repaint();
                }
                return null;
            }

            @Override
            protected void done() {
                isSorting = false;
            }
        };
        shuffler.execute();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.CYAN);
        Rectangle2D.Float bar;
        for (int i = 0; i < SIZE; i++) {
            bar = new Rectangle2D.Float(i * BAR_WIDTH, 0, BAR_WIDTH, bar_height[i] * HEIGHT);
            g2d.fill(bar);
        }

        if (isSorting) {
            g2d.setColor(Color.WHITE);
            g2d.drawString("Sorting...", 20, 50);
            if (current_index != -1) {
                g2d.setColor(Color.YELLOW);
                g2d.fill(new Rectangle2D.Float(current_index * BAR_WIDTH, 0, BAR_WIDTH, bar_height[current_index] * HEIGHT));
            }
            if (traversing_index != -1) {
                g2d.setColor(Color.ORANGE);
                g2d.fill(new Rectangle2D.Float(traversing_index * BAR_WIDTH, 0, BAR_WIDTH, bar_height[traversing_index] * HEIGHT));
            }
        }
    }

    private void initBarHeight() {
        for (int i = 0; i < SIZE; i++) {
            bar_height[i] = (float) (i + 1) / SIZE;
        }
        shuffleBarHeight();
    }

    private void shuffleBarHeight() {
        Random random = new Random();
        for (int i = 0; i < SIZE - 1; i++) {
            int j = random.nextInt(SIZE - i - 1) + i + 1;
            float temp = bar_height[i];
            bar_height[i] = bar_height[j];
            bar_height[j] = temp;
        }
    }

    private class Sorter {
        private final String code;

        public Sorter(String code) {
            this.code = code;
        }

        //        public void sort(float[] arr) throws Exception {
//            ScriptEngineManager manager = new ScriptEngineManager();
//            ScriptEngine engine = manager.getEngineByName("java");
//            engine.eval(code);
//            Invocable inv = (Invocable) engine;
//            inv.invokeFunction("sort", arr);
//        }
        public void sort(float[] arr) {
            boolean isSorted = false;
            while (!isSorted) {
                isSorted = true;
                for (int i = 0; i < arr.length - 1; i++) {
                    if (arr[i] > arr[i + 1]) {
                        float temp = arr[i];
                        arr[i] = arr[i + 1];
                        arr[i + 1] = temp;
                        isSorted = false;
                    }
                }
                repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sorting Visualizer");
        SortingVisualizer panel = new SortingVisualizer();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

// 2nd method

//import java.awt.*;
//import java.awt.event.*;
//import java.awt.geom.Rectangle2D;
//import javax.script.ScriptEngine;
//import javax.script.ScriptEngineManager;
//import javax.script.ScriptException;
//import javax.swing.*;
//
//public class SortingVisualizer extends JPanel {
//    private static final long serialVersionUID = 1L;
//    private final int WIDTH = 1000, HEIGHT = WIDTH * 9 / 16;
//    private final int SIZE = 200;
//    private final float BAR_WIDTH = (float)WIDTH / SIZE;
//    private float[] bar_height = new float[SIZE];
//    private SwingWorker<Void, Void> shuffler, sorter;
//    private int current_index, traversing_index;
//    private boolean isSorting;
//
//    private SortingVisualizer() {
//        setBackground(Color.BLACK);
//        setPreferredSize(new Dimension(WIDTH, HEIGHT));
//
//        JButton sortBtn = new JButton("Sort");
//        sortBtn.setBounds(20, 20, 100, 30);
//        sortBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (!isSorting) {
//                    String code = JOptionPane.showInputDialog("Enter your sorting algorithm code:");
//                    if (code != null && !code.isEmpty()) {
//                        startSorting(code);
//                    }
//                }
//            }
//        });
//        this.add(sortBtn);
//
//        JButton resetBtn = new JButton("Reset");
//        resetBtn.setBounds(140, 20, 100, 30);
//        resetBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                reset();
//            }
//        });
//        this.add(resetBtn);
//    }
//
//    private void startSorting(String code) {
//        isSorting = true;
//        initBarHeight();
//        initSorter(code);
//        initShuffler();
//        sorter.execute();
//    }
//
//    private void reset() {
//        if (isSorting) {
//            sorter.cancel(true);
//        }
//        isSorting = false;
//        initBarHeight();
//        repaint();
//    }
//
////    private void initSorter(String code) {
////        sorter = new SwingWorker<>() {
////            @Override
////            public Void doInBackground() throws InterruptedException, ScriptException, NoSuchMethodException {
////                String sortingCode = "void sort(float[] arr) {\n" + code + "\n}";
////                Sorter sorter = new Sorter(sortingCode);
////                while (isSorting) {
////                    try {
////                        sorter.sort(bar_height);
////                    } catch (Exception ex) {
////                    JOptionPane.showMessageDialog(null, "Error occurred while sorting: " + ex.getMessage());
////                    reset();
////                    return null;
////                }
////                repaint();
////                Thread.sleep(10);
////            }
////        return null;
////        }
////            @Override
////            protected void done() {
////                isSorting = false;
////            }
////        };
////    }
//private void initSorter(String code) {
//    sorter = new SwingWorker<>() {
//        @Override
//        public Void doInBackground() throws InterruptedException, ScriptException, NoSuchMethodException {
//            String sortingCode = "void sort(float[] arr) {\n" + code + "\n}";
//            Sorter sorter = new Sorter(sortingCode);
//            while (isSorting) {
//                try {
//                    sorter.sort(bar_height);
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//                if (isSorted()) {
//                    isSorting = false;
//                }
//                repaint();
//                Thread.sleep(50);
//            }
//            return null;
//        }
//    };
//}
//    private boolean isSorted() {
//        for (int i = 1; i < bar_height.length; i++) {
//            if (bar_height[i] < bar_height[i-1]) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private void initShuffler() {
//        shuffler = new SwingWorker<>() {
//            @Override
//            protected Void doInBackground() throws InterruptedException {
//                while (isSorting) {
//                    shuffleBarHeight();
//                    Thread.sleep(300);
//                }
//                return null;
//            }
//
//            @Override
//            protected void done() {
//                shuffleBarHeight();
//            }
//        };
//        shuffler.execute();
//    }
//
//    private void initBarHeight() {
//        for (int i = 0; i < SIZE; i++) {
//            bar_height[i] = (float)HEIGHT / SIZE * (i + 1);
//        }
//    }
//
//    private void shuffleBarHeight() {
//        for (int i = 0; i < SIZE; i++) {
//            int j = (int)(Math.random() * SIZE);
//            float temp = bar_height[i];
//            bar_height[i] = bar_height[j];
//            bar_height[j] = temp;
//        }
//        repaint();
//    }
//
//    private void drawBar(Graphics2D g, int index, float height, boolean isCurrent, boolean isTraversing) {
//        float x = index * BAR_WIDTH;
//        float y = HEIGHT - height;
//        Rectangle2D.Float rect = new Rectangle2D.Float(x, y, BAR_WIDTH, height);
//
//        if (isCurrent) {
//            g.setColor(Color.RED);
//        } else if (isTraversing) {
//            g.setColor(Color.BLUE);
//        } else {
//            g.setColor(Color.WHITE);
//        }
//        g.fill(rect);
//    }
//
//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        Graphics2D g2d = (Graphics2D)g;
//        for (int i = 0; i < SIZE; i++) {
//            boolean isCurrent = (i == current_index);
//            boolean isTraversing = (i == traversing_index);
//            drawBar(g2d, i, bar_height[i], isCurrent, isTraversing);
//        }
//    }
//
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Sorting Visualizer");
//        SortingVisualizer sortingVisualizer = new SortingVisualizer();
//        frame.add(sortingVisualizer);
//        frame.pack();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLocationRelativeTo(null);
//        frame.setResizable(false);
//        frame.setVisible(true);
//    }
//}
//
//class Sorter {
//    private final ScriptEngine engine;
//
//    Sorter(String sortingCode) throws ScriptException {
//        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
//        this.engine = scriptEngineManager.getEngineByName("js");
//        this.engine.eval(sortingCode);
//    }
//
//    void sort(float[] arr) throws ScriptException, NoSuchMethodException {
//        engine.put("arr", arr);
//        engine.eval("sort(arr)");
//    }
//}
