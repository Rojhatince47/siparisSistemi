import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

public class TreeGUI {
    private Tree myTree;

    public TreeGUI() {
        myTree = new Tree();

        // Ana pencere oluşturma
        JFrame frame = new JFrame("Sipariş Yönetim Sistemi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Panel oluşturma
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));

        // Sipariş Ekleme Bölümü
        JPanel addOrderPanel = new JPanel();
        addOrderPanel.setLayout(new FlowLayout());
        JLabel addOrderLabel = new JLabel("Sipariş Ekle (virgülle ayırın):");
        JTextField addOrderField = new JTextField(20);
        JButton addOrderButton = new JButton("Ekle");
        addOrderPanel.add(addOrderLabel);
        addOrderPanel.add(addOrderField);
        addOrderPanel.add(addOrderButton);

        // Sipariş Silme Bölümü
        JPanel removeOrderPanel = new JPanel();
        removeOrderPanel.setLayout(new FlowLayout());
        JLabel removeOrderLabel = new JLabel("Sipariş Sil (virgülle ayırın):");
        JTextField removeOrderField = new JTextField(20);
        JButton removeOrderButton = new JButton("Sil");
        removeOrderPanel.add(removeOrderLabel);
        removeOrderPanel.add(removeOrderField);
        removeOrderPanel.add(removeOrderButton);

        // Sipariş Sorgulama Bölümü
        JPanel queryOrderPanel = new JPanel();
        queryOrderPanel.setLayout(new FlowLayout());
        JLabel queryOrderLabel = new JLabel("Sipariş Sorgula (virgülle ayırın):");
        JTextField queryOrderField = new JTextField(20);
        JButton queryOrderButton = new JButton("Sorgula");
        queryOrderPanel.add(queryOrderLabel);
        queryOrderPanel.add(queryOrderField);
        queryOrderPanel.add(queryOrderButton);

        // Ağaç Yazdırma Bölümü
        JPanel printTreePanel = new JPanel();
        printTreePanel.setLayout(new FlowLayout());
        JButton printTreeButton = new JButton("Ağacı Yazdır");
        JTextArea treeTextArea = new JTextArea(10, 50);
        treeTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(treeTextArea);
        printTreePanel.add(printTreeButton);

        // Çıkış Butonu
        JPanel exitPanel = new JPanel();
        JButton exitButton = new JButton("Çıkış");
        exitPanel.add(exitButton);

        // Panelleri ana panele ekle
        panel.add(addOrderPanel);
        panel.add(removeOrderPanel);
        panel.add(queryOrderPanel);
        panel.add(printTreePanel);
        panel.add(exitPanel);

        // Frame içine paneli ekle
        frame.add(panel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Butonların işlevlerini tanımla
        addOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] orders = addOrderField.getText().trim().split(",");
                myTree.ekle(orders);
                JOptionPane.showMessageDialog(frame, "Siparişler başarıyla eklendi.");
                addOrderField.setText("");
            }
        });

        removeOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] orders = removeOrderField.getText().trim().split(",");
                myTree.getSil(orders);
                JOptionPane.showMessageDialog(frame, "Siparişler başarıyla silindi.");
                removeOrderField.setText("");
            }
        });

        queryOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] orders = queryOrderField.getText().trim().split(",");
                int count = myTree.sorgu(orders);
                JOptionPane.showMessageDialog(frame, "Siparişiniz " + count + " defa bulundu.");
                queryOrderField.setText("");
            }
        });

        printTreeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                treeTextArea.setText("");
                JTextAreaWriter writer = new JTextAreaWriter(treeTextArea);
                PrintStream out = new PrintStream(writer);
                System.setOut(out); // Konsol çıktısını JTextArea'ya yönlendir
                myTree.yazdir();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Frame görünür yap
        frame.setVisible(true);
    }

    // JTextArea için PrintStream sınıfı
    static class JTextAreaWriter extends java.io.OutputStream {
        private JTextArea textArea;

        public JTextAreaWriter(JTextArea textArea) {
            this.textArea = textArea;
        }

        @Override
        public void write(int b) {
            textArea.append(String.valueOf((char) b));
            textArea.setCaretPosition(textArea.getDocument().getLength());
        }
    }
}