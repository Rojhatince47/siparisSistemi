import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MainGUI {
    private Tree myTree = new Tree();

    public MainGUI() {
        JFrame frame = new JFrame("Sipariş Yönetim Sistemi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        // Panel oluşturma
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 10, 10));
        frame.add(panel);

        // Sipariş Verme
        JButton addButton = new JButton("Sipariş Verme");
        addButton.addActionListener(e -> handleSiparisVerme());
        panel.add(addButton);

        // Sipariş Silme
        JButton removeButton = new JButton("Sipariş Silme");
        removeButton.addActionListener(e -> handleSiparisSilme());
        panel.add(removeButton);

        // Sipariş Sorgulama
        JButton queryButton = new JButton("Sipariş Sorgulama");
        queryButton.addActionListener(e -> handleSiparisSorgulama());
        panel.add(queryButton);

        // Ağacı Yazdırma
        JButton printButton = new JButton("Oluşan Ağacı Yazdırma");
        printButton.addActionListener(e -> handleAgacYazdirma());
        panel.add(printButton);

        // Çıkış
        JButton exitButton = new JButton("Çıkış");
        exitButton.addActionListener(e -> System.exit(0));
        panel.add(exitButton);

        // Başlat
        frame.setVisible(true);
    }

    private void handleSiparisVerme() {
        String input = JOptionPane.showInputDialog(null, "Eklenecek siparişleri girin (virgülle ayırarak):");
        if (input != null && !input.trim().isEmpty()) {
            String[] siparisler = input.trim().split(",");
            myTree.ekle(siparisler);
            JOptionPane.showMessageDialog(null, "Siparişler başarıyla eklendi!");
        } else {
            JOptionPane.showMessageDialog(null, "Sipariş girişi boş olamaz.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleSiparisSilme() {
        String input = JOptionPane.showInputDialog(null, "Silinecek siparişleri girin (virgülle ayırarak):");
        if (input != null && !input.trim().isEmpty()) {
            String[] silinecekSiparisler = input.trim().split(",");
            myTree.getSil(silinecekSiparisler);
            JOptionPane.showMessageDialog(null, "Silme işlemi tamamlandı.");
        } else {
            JOptionPane.showMessageDialog(null, "Silinecek sipariş girişi boş olamaz.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleSiparisSorgulama() {
        String input = JOptionPane.showInputDialog(null, "Sorgulamak istediğiniz siparişleri girin (virgülle ayırarak):");
        if (input != null && !input.trim().isEmpty()) {
            String[] sorgulanacakSiparisler = input.trim().split(",");
            int result = myTree.sorgu(sorgulanacakSiparisler);
            JOptionPane.showMessageDialog(null, "Siparişiniz " + result + " defa bulundu.");
        } else {
            JOptionPane.showMessageDialog(null, "Sorgu girişi boş olamaz.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleAgacYazdirma() {
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        // Ağacı konsola yazdıran metodu çağırıyoruz
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(bos));

        // Konsola yazdırma işlemi
        myTree.yazdir();

        System.setOut(originalOut); // Konsol çıktılarını eski haline getir
        textArea.setText(bos.toString()); // Konsoldan okunan veriyi JTextArea'ya yaz

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(350, 300));

        JOptionPane.showMessageDialog(null, scrollPane, "Oluşan Ağaç", JOptionPane.INFORMATION_MESSAGE);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainGUI::new);
    }
}