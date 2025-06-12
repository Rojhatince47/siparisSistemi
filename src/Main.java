import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Tree myTree = new Tree();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nLütfen yapmak istediğiniz işlemi seçin:");
            System.out.println("1 - Sipariş Verme");
            System.out.println("2 - Sipariş Silme");
            System.out.println("3 - Sipariş Sorgulama");
            System.out.println("4 - Oluşan Ağacı Yazdırma");
            System.out.println("5 - Çıkış");
            System.out.print("İşlem numarasını giriniz: ");

            int operation;
            try{
                operation = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Hatalı giriş yaptınız. Lütfen geçerli bir sayı girin.");
                continue;
            }

            switch (operation) {
                case 1: // Sipariş Verme
                    System.out.print("Eklenecek siparişleri girin (virgülle ayırarak): ");
                    String[] eklenecekSiparisler = scanner.nextLine().trim().split(",");
                    myTree.ekle(eklenecekSiparisler);
                    System.out.println("Siparişler başarıyla eklendi.");
                    break;

                case 2: // Sipariş Silme
                    System.out.print("Silinecek siparişleri girin (virgülle ayırarak): ");
                    String[] silinecekSiparisler = scanner.nextLine().trim().split(",");
                    myTree.getSil(silinecekSiparisler);
                    break;

                case 3: // Sipariş Sorgulama
                    System.out.print("Sorgulamak istediğiniz siparişleri girin (virgülle ayırarak): ");
                    String[] sorgulanacakSiparisler = scanner.nextLine().trim().split(",");
                    int siparisSayisi = myTree.sorgu(sorgulanacakSiparisler);
                    System.out.println("Siparişiniz " + siparisSayisi + " defa bulundu.");
                    break;

                case 4: // Ağacı Yazdırma
                    System.out.println("Oluşan ağaç: ");
                    myTree.yazdir();
                    break;

                case 5: // Çıkış
                    System.out.println("Programdan çıkılıyor. İyi günler!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Hatalı giriş yaptınız. Lütfen 1 ile 5 arasında bir değer girin.");
                    break;
            }
        }
    }
}
